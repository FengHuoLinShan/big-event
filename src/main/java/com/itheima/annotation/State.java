package com.itheima.annotation;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    String message() default "state的参数只能是已发布或草稿";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
