package com.aerovik.nordic.validation;


import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal(expression = "currentUser")
public @interface CurrentUser {


}
