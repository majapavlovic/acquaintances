/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.controller;

import fon.tps.dto.AdultsFromSmederevo;
import fon.tps.dto.PersonRequestDto;
import fon.tps.dto.PersonResponseDto;
import fon.tps.exception.ResponseObject;
import fon.tps.exception.InvalidDataException;
import fon.tps.exception.UnprocessableEntityException;
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
@RequestMapping("api/v2/tps/person")
public class PersonControllerV2 {

    @Autowired
    private PersonService personService;

    @GetMapping("smederevci")
    public ResponseEntity<Object> getAll() {
        List<PersonResponseDto> people = personService.getAllSmederevci();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("smederevci2")
    public ResponseEntity<List<AdultsFromSmederevo>> getAllFromView() {
        List<AdultsFromSmederevo> people = personService.getAllSmederevciFromView();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonResponseDto> save(@Valid @RequestBody PersonRequestDto dto) throws Exception {
        PersonResponseDto response = personService.insertPerson(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<PersonResponseDto> update(@PathVariable Long id, @Valid @RequestBody PersonRequestDto dto) throws Exception {
        PersonResponseDto response = personService.updatePerson(id,dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/jmbg/{jmbg}")
    public ResponseEntity<PersonResponseDto> getPersonByJmbg(@PathVariable String jmbg) throws Exception {
        PersonResponseDto person = personService.getPersonByJmbg(jmbg);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws Exception {
        personService.deletePerson(id);
        return new ResponseEntity<>("Person removed!", HttpStatus.OK);
    }

    @GetMapping("average-age")
    public ResponseEntity<Float> findAverageAge() {
        Float avg = personService.findAverageAge();
        return new ResponseEntity<>(avg, HttpStatus.OK);
    }

    @GetMapping("max-height")
    public ResponseEntity<Integer> findMaxHeight() {
        int max = personService.findMaxHeight();
        return new ResponseEntity<>(max, HttpStatus.OK);
    }

}
