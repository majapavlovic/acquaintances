/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import fon.tps.annotation.ValidPersonData;

/**
 *
 * @author User
 */
@ValidPersonData
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Size(min = 13, max = 13, message = "JMBG must be exactly 13 numbers long")
    private String jmbg;

    @NotNull
    @Size(min = 2, max = 15, message = "Name must be between 2 and 15 characters long")
    private String name;

    @NotNull
    @Size(min = 2, max = 33, message = "Surname must be between 2 and 30 characters long")
    private String surname;

    @Min(value = 70, message = "Minimum height is 70cm")
    @Max(value = 230, message = "Maximum height is 230cm")
    @Column(name = "height_in_cm")
    private int heightInCm;

    @NotNull
    @Past
    private LocalDate birthdate;

    @NotNull
    private int ageInMonths;

    @ManyToOne
    private City cityOfBirth;

    @ManyToOne
    private City residence;

}
