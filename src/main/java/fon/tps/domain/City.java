/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author User
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NaturalId
    @Min(value = 11000, message="Min ptt value is 11000")
    @Max(value = 38999, message="Max ptt value is 38999")
    private int ptt;
    
    @NotNull
    private String name;
    
    @NotNull
    private String regionCode; //izvan zahtevanog, dodato zbog validacije jmbg-a
    
    private int citizens;
    
}
 