/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.controller;

import fon.tps.dto.PersonRequestDto;
import fon.tps.dto.PersonResponseDto;
import fon.tps.service.PersonService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@CrossOrigin
@RestController
@RequestMapping("api/v1/tps/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponseDto> save(@Valid @RequestBody PersonRequestDto dto) throws Exception {
        PersonResponseDto response = personService.save(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDto>> getAll() {
        List<PersonResponseDto> people = personService.getAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PersonResponseDto> getById(@PathVariable Long id) throws Exception {
        PersonResponseDto person = personService.getById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/jmbg/{jmbg}")
    public ResponseEntity<PersonResponseDto> getByJmbg(@PathVariable String jmbg) throws Exception {
        PersonResponseDto person = personService.getByJmbg(jmbg);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws Exception {
        personService.deleteById(id);
        return new ResponseEntity<>("Person removed!", HttpStatus.OK);
    }

    @DeleteMapping("jmbg/{jmbg}")
    public ResponseEntity<String> deleteByJmbg(@PathVariable String jmbg) throws Exception {
        personService.deleteByJmbg(jmbg);
        return new ResponseEntity<>("Person removed!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonResponseDto> update(@Valid @RequestBody PersonRequestDto dto) throws Exception {
        PersonResponseDto response = personService.update(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
