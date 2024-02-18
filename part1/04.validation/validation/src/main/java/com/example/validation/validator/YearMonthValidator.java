package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern; // YearMonth 어노테이션에서 받아온 패턴을 저장하는 변수

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        // YearMonth 어노테이션에서 받아온 패턴을 초기화합니다.
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 주어진 문자열이 유효한 날짜 형식인지 검사합니다.

        // 입력값(value)이 받아들일 수 있는 형식으로 변환합니다.
        // 예를 들어, "2024-02-17T13:00:00" -> "20240201" (날짜 부분만 남김)
        var reValue = value + "01";

        // YearMonth 어노테이션에서 가져온 패턴에 맞추어 변환된 입력값을 검사합니다.
        // 예를 들어, "yyyy-MM" 형식의 패턴을 받았다면 "yyyy-MMdd" 형식으로 변환합니다.
        var rePattern = pattern + "dd";

        try {
            // 변환된 값을 날짜로 파싱하여 LocalDate 객체로 변환합니다.
            LocalDate date = LocalDate.parse(reValue, DateTimeFormatter.ofPattern(rePattern));

            // 유효한 날짜인 경우 true를 반환합니다.
            return true;
        } catch (Exception e) {
            // 파싱 중 오류가 발생한 경우 유효하지 않은 날짜로 판단하여 false를 반환합니다.
            return false;
        }
    }
}
