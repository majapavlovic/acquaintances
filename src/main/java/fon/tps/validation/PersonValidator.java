/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.validation;

import fon.tps.domain.City;
import fon.tps.domain.Person;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fon.tps.annotation.ValidPersonData;
import fon.tps.exception.InvalidDataException;
import java.time.Period;
import org.springframework.stereotype.Component;

/**
 *
 * @author User
 */
@Component
public class PersonValidator implements ConstraintValidator<ValidPersonData, Person> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMyy");

    @Override
    public void initialize(ValidPersonData constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean isValid(Person t, ConstraintValidatorContext cvc) {

        String jmbg = t.getJmbg();
        LocalDate birthDate = t.getBirthdate();
        City cityOfBirth = t.getCityOfBirth();

        if (jmbg == null || jmbg.length() != 13 || !jmbg.matches("\\d+")) {
            return false;
        }

        if (!isValidDate(jmbg, birthDate)) {
            return false;
        }

        if (!isValidRegion(jmbg, cityOfBirth)) {
            return false;
        }

        if (!isValidControlCode(jmbg)) {
            return false;
        }

        if (!isValidAgeInMonths(t)) {
            return false;
        }

        return true;
    }

    private boolean isValidDate(String jmbg, LocalDate birthDate) {

        String datePart = jmbg.substring(0, 7);

        int day = Integer.parseInt(datePart.substring(0, 2));
        int month = Integer.parseInt(datePart.substring(2, 4));
        int year = Integer.parseInt(datePart.substring(4));

        if (year >= 900) {
            year += 1000;
        } else {
            year += 2000;
        }

        LocalDate extractedDate = LocalDate.of(year, month, day);

        return extractedDate.equals(birthDate);
    }

    private boolean isValidRegion(String jmbg, City cityOfBirth) {
        String region = jmbg.substring(7, 9);
        if (cityOfBirth == null || cityOfBirth.getRegionCode() == null) {
            return false;
        }
        return region.equals(cityOfBirth.getRegionCode());
    }

    private boolean isValidControlCode(String jmbg) {
        int[] weights = {7, 6, 5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        int sum = 0;

        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(jmbg.charAt(i)) * weights[i];
        }

        int remainder = sum % 11;
        int controlCode = (remainder == 0) ? 0 : 11 - remainder;

        return controlCode == Character.getNumericValue(jmbg.charAt(12));
    }

    private boolean isValidAgeInMonths(Person t) {
        if (t.getBirthdate() == null) {
            return true;
        }
        LocalDate birthdate = t.getBirthdate();
        int calculatedAgeInMonths = (int) Period.between(birthdate, LocalDate.now()).toTotalMonths();
        return t.getAgeInMonths() == calculatedAgeInMonths;
    }

    public boolean isValidV2(Person t) throws InvalidDataException {

        String jmbg = t.getJmbg();
        LocalDate birthDate = t.getBirthdate();
        City cityOfBirth = t.getCityOfBirth();

        if (jmbg == null || jmbg.length() != 13 || !jmbg.matches("\\d+")) {
            throw new InvalidDataException("JMBG is invalid");
        }

        if (!isValidDate(jmbg, birthDate)) {
            throw new InvalidDataException("JMBG and BirthDate are  not consistent");

        }

        if (!isValidRegion(jmbg, cityOfBirth)) {
            throw new InvalidDataException("JMBG is invalid. Region code not consistent with CityOfBirth");
        }

        if (!isValidControlCode(jmbg)) {
            throw new InvalidDataException("Invalid control code");
        }

        if (!isValidAgeInMonths(t)) {
            throw new InvalidDataException("Incorrect ageInMonths");
        }

        return true;
    }
}
