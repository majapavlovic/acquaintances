/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package fon.tps.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Builder;

/**
 *
 * @author User
 */
@Builder
public record AdultsFromSmederevo(
        Long id,
        String jmbg,
        String name,
        String surname,
        int heightInCm,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthdate,
        int ageInMonths,
        String cityOfBirth,
        String residence,
        int residencePTT) {

}
