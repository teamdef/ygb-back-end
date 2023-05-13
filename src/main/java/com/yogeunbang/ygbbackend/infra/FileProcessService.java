package com.yogeunbang.ygbbackend.infra;

import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.yogeunbang.ygbbackend.accommodation.entity.Accommodation;
import com.yogeunbang.ygbbackend.accommodation.entity.AccommodationImage;
import com.yogeunbang.ygbbackend.accommodation.repo.AccommodationImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileProcessService {

    private final AmazonS3Service amazonS3Service;
    private final AccommodationImageRepo accommodationImageRepo;

    public String uploadImage(MultipartFile file) {
        String fileName = amazonS3Service.getFolderName() + createFileName(file.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException ioe) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생했습니다 (%s)", file.getOriginalFilename()));
        }

        return amazonS3Service.getFileUrl(fileName);
    }

    public List<String> uploadMultiImage(List<MultipartFile> files) {
        List<String> imgUrls = new ArrayList<>();
        for (MultipartFile file: files) {
            imgUrls.add(uploadImage(file));
        }
        return imgUrls;
    }

    private String createFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public void deleteImage(String url) {
        amazonS3Service.deleteFile(getFileName(url));
    }

    private String getFileName(String url) {
        String[] paths = url.split("/");
        return paths[paths.length-2] + "/" + paths[paths.length-1];
    }

    public void mappingImages(List<String> imgUrls, Accommodation accommodation) {
        for (String img : imgUrls) {
            AccommodationImage accommodationImage = new AccommodationImage(img, accommodation);
            accommodationImageRepo.save(accommodationImage);
        }
    }
}
