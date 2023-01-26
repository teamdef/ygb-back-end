package com.yogeunbang.ygbbackend;

public class Calculator {

    public long add(long a, long b) {
        return a+b;
    }

    public long subtract(long a, long b) {
        return a - b;
    }

    public long multiply(long a, long b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다!");
        }
        return a / b;
    }
}
