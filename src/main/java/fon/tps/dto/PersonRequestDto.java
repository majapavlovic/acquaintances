/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package fon.tps.dto;

import java.time.LocalDate;
import lombok.Builder;

/**
 *
 * @author User
 */
@Builder
public record PersonRequestDto(
        Long id,
        String jmbg,
        String name,
        String surname,
        LocalDate birthdate,
        int ageInMonths,
        Long cityOfBirth,
        Long residence) {

}
