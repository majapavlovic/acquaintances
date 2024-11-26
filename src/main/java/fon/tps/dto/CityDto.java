/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package fon.tps.dto;

import lombok.Builder;

/**
 *
 * @author User
 */
@Builder
public record CityDto(
        Long id,
        int ptt,
        String name,
        int citizens) {

}
