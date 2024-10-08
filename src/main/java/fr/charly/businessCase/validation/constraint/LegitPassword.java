package fr.charly.businessCase.validation.constraint;

import fr.charly.businessCase.validation.validator.LegitPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LegitPasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LegitPassword {

    String message() default "Not a legit password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
