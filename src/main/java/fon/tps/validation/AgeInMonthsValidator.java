/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.validation;

import fon.tps.annotation.ValidAgeInMonths;
import fon.tps.domain.Person;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author User
 */
public class AgeInMonthsValidator implements ConstraintValidator<ValidAgeInMonths, Person> {

    @Override
    public boolean isValid(Person t, ConstraintValidatorContext cvc) {
       if (t.getBirthdate() == null) {
            return true; 
        }
        LocalDate birthdate = t.getBirthdate();
        int calculatedAgeInMonths = (int) Period.between(birthdate, LocalDate.now()).toTotalMonths();
        return t.getAgeInMonths() == calculatedAgeInMonths;
    }
    
}
