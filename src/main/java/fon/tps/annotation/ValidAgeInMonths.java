/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package fon.tps.annotation;

import fon.tps.validation.AgeInMonthsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author User
 */
@Constraint(validatedBy = AgeInMonthsValidator.class)
@Target({ ElementType.TYPE }) 
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAgeInMonths {
    String message() default "ageInMonths is not consistent with birthdate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
