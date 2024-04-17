package com.nexters.dailyphrase.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Mapper {
    // 스프링 빈의 이름을 지정
    @AliasFor(annotation = Component.class)
    String value() default "";
}