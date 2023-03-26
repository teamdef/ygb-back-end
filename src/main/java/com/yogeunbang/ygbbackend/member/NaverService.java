package com.yogeunbang.ygbbackend.member;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yogeunbang.ygbbackend.member.dto.TokenDto;
import com.yogeunbang.ygbbackend.member.entity.Member;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NaverService {
     // Bearer 다음에 공백 추가
    private final String apiURL = "https://openapi.naver.com/v1/nid/me";

    @Value("${key.secret}")
    private String clientSecret;
    @Value("${key.id}")
    private String clientId;


    public Member requestMember(TokenDto token) {
        String header = "Bearer " + token.getAccessToken();
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);
        Map<String, String> memberInfo = getNicknameAndId(responseBody);

        return new Member(
            memberInfo.get("identity"), memberInfo.get("nickname"),
            memberInfo.get("profile"), memberInfo.get("accessToken"));
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                System.out.println(header.getKey() + header.getValue());
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    public Map<String, String> getNicknameAndId(String responseBody) throws RuntimeException {
        Map<String, String> memberInfo = new HashMap<>();
        JsonElement element = JsonParser.parseString(responseBody);
        String message = element.getAsJsonObject().get("message").getAsString();
        System.out.println(message);
        if (message.equals("success")) {
            JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
            memberInfo.put("identity",response.get("id").getAsString());
            memberInfo.put("nickname", response.get("nickname").getAsString());
            memberInfo.put("profile", response.get("profile_image").getAsString());
            return memberInfo;
        }

        throw new RuntimeException("로그인 실패!!");
    }

    public String unregister(Member member) {
        String url = "https://nid.naver.com/oauth2.0/token?grant_type=delete&service_provider=NAVER";
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("access_token", member.getAccessToken());
        requestParams.put("client_id", clientId);
        requestParams.put("client_secret", clientSecret);
        for (String key : requestParams.keySet()) {
            url += "&" + key + "=" + requestParams.get(key);
        }
        String response = get(url, new HashMap<String, String>());
        return response;
    }
}
