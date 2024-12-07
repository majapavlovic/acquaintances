/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.exception;

import lombok.Builder;

/**
 *
 * @author User
 */
@Builder
public record ErrorDetails(
        String message) {

}
