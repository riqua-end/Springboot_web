package com.example.validation.validator;

import com.example.validation.annotation.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

// PhoneNumberValidator 클래스는 ConstraintValidator<PhoneNumber, String>를 구현합니다.
// 이는 PhoneNumber 어노테이션을 가진 필드에 대한 유효성 검사를 수행한다는 것을 의미합니다.
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {

    //regexp 변수는 핸드폰 번호의 유효성을 검사하기 위해 사용될 정규식을 저장하는 데 사용됩니다.
    private String regexp; // 정규식을 저장하기 위한 문자열 변수입니다.

    @Override
    //initialize() 메서드는 초기화 메서드로, 어노테이션에서 정의된 정규식을 가져와 regexp 변수에 저장합니다.
    public void initialize(PhoneNumber constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp(); // 어노테이션에서 정의된 정규식을 가져와 저장합니다.
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 주어진 값(value)이 정규식(regexp)에 일치하는지 확인합니다.
        boolean result = Pattern.matches(regexp, value);
        // 유효성 검사 결과를 반환합니다. 일치하면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
        return result;
    }

}
