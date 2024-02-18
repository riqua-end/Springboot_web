package com.example.validation.annotation;

import com.example.validation.validator.PhoneNumberValidator;
import com.example.validation.validator.YearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { YearMonthValidator.class }) // 해당 어노테이션은 YearMonthValidator 클래스에서 지정된 유효성 검사를 수행합니다.
@Target({ ElementType.FIELD }) // 어노테이션이 적용될 대상을 지정합니다. 여기서는 필드에만 적용됩니다.
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 정보가 유지되는 기간을 지정합니다. 실행 시간까지 유지됩니다.
@NotBlank
public @interface YearMonth {

    // 유효성 검사 실패 시 출력할 메시지를 지정합니다.
    String message() default "Year Month 양식에 맞지 않습니다. ex) 2024-02";

    // 유효성 검사에 사용될 정규식을 지정합니다.
    String pattern() default "yyyyMMdd";

    // 해당 제약 어노테이션을 그룹으로 묶을 수 있습니다. 기본값은 빈 배열입니다.
    Class<?>[] groups() default { };

    // 사용자 정의 Payload 타입을 지정할 수 있습니다. 기본값은 빈 배열입니다.
    Class<? extends Payload>[] payload() default { };
}
