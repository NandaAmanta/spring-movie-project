/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.validations.rules;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS ROG
 */
@Documented
@Constraint(validatedBy = CheckPassword.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    //error message  

    public String message() default "Password must contain minimal 8 characters.";

    //represents group of constraints     
    public Class<?>[] groups() default {};

    //represents additional information about annotation  
    public Class<? extends Payload>[] payload() default {};
}

@Component
class CheckPassword implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        return !(t == null || t.length() < 8);
    }

}
