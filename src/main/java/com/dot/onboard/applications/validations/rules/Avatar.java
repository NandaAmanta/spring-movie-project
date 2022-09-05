/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.applications.validations.rules;

import com.dot.onboard.global.Config;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS ROG
 */
@Documented
@Constraint(validatedBy = CheckAvatar.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Avatar {
    //error message  

    public String message() default "your size of file is over 1Mb";

    //represents group of constraints     
    public Class<?>[] groups() default {};

    //represents additional information about annotation  
    public Class<? extends Payload>[] payload() default {};
}

@Component
class CheckAvatar implements ConstraintValidator<Avatar, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile t, ConstraintValidatorContext cvc) {
        
        if (t == null) {
            return true;
        }
        
        return t.getSize() < 100000;
    }

}
