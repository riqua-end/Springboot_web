package com.example.validation.annotation;

import com.example.validation.validator.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { PhoneNumberValidator.class }) // 해당 어노테이션은 PhoneNumberValidator 클래스에서 지정된 유효성 검사를 수행합니다.
@Target({ ElementType.FIELD }) // 어노테이션이 적용될 대상을 지정합니다. 여기서는 필드에만 적용됩니다.
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 정보가 유지되는 기간을 지정합니다. 실행 시간까지 유지됩니다.
public @interface PhoneNumber {

    // 유효성 검사 실패 시 출력할 메시지를 지정합니다. 기본값은 "핸드폰 번호 양식에 맞지 않습니다. ex) 010-1234-1234" 입니다.
    String message() default "핸드폰 번호 양식에 맞지 않습니다. ex) 010-1234-1234";

    // 유효성 검사에 사용될 정규식을 지정합니다. 기본값은 "^\d{2,3}-\d{3,4}-\d{4}$" 입니다.
    // 이 정규식은 010-1234-1234와 같은 핸드폰 번호 양식을 검증합니다.
    String regexp() default "^\\d{2,3}-\\d{3,4}-\\d{4}$";

    // 해당 제약 어노테이션을 그룹으로 묶을 수 있습니다. 기본값은 빈 배열입니다.
    Class<?>[] groups() default { };

    // 사용자 정의 Payload 타입을 지정할 수 있습니다. 기본값은 빈 배열입니다.
    Class<? extends Payload>[] payload() default { };
}
