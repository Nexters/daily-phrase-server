package com.nexters.dailyphrase.common.annotation;

import java.lang.annotation.*;

import org.springframework.stereotype.Component;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ExplainError {
    String value() default "";
}
