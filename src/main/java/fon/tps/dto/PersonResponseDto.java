/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package fon.tps.dto;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public record PersonResponseDto(
        Long id,
        String jmbg,
        String name,
        String surname,
        LocalDate birthdate,
        int ageInMonths,
        CityDto cityOfBirth,
        CityDto residence) {

}
