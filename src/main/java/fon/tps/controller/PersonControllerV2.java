/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.controller;

import fon.tps.dto.PersonResponseDto;
import fon.tps.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("api/v2/tps/person")
public class PersonControllerV2 {
    
    @Autowired
    private PersonService personService;
    
    @GetMapping("smederevci")
    public ResponseEntity<List<PersonResponseDto>> getAll() {
        List<PersonResponseDto> people = personService.getAllSmederevci();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

}
