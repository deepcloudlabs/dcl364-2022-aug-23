package com.example.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[A-Z]{3,5}$")
@Constraint(validatedBy = {})
public @interface StockSymbol {
	   String message() default "{validation.strongPassword1}";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}
