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
public interface AdultsFromSmederevo {

    Long getId();

    String getJmbg();

    String getName();

    String getSurname();
    
    int getHeightInCm();

    LocalDate getBirthdate();

    int getAgeInMonths();

    String getCityOfBirth();

    String getResidence();

    int getResidencePTT();
}
