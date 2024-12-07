/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author User
 */
@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private String name;
    private String field;
    private String value;

    public NotFoundException(String name, String field, String value) {
        super(name + " with " + field + " = " + value + " was not found");

        this.name = name;
        this.field = field;
        this.value = value;
    }
}
