package com.yogeunbang.ygbbackend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void 정수와_정수를_더한다() {
        long result = calculator.add(1234543, 1);
        assertThat(result).isEqualTo(1234544L);
    }
}