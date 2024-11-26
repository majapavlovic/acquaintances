/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.controller;

import fon.tps.dto.CityDto;
import fon.tps.service.CityService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("api/v1/tps/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<CityDto> save(@Valid @RequestBody CityDto cityDto) throws Exception {
        CityDto dto = cityService.save(cityDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAll() {
        List<CityDto> cities = cityService.getAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CityDto> getById(@PathVariable Long id) throws Exception {
        CityDto city = cityService.getById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws Exception {
        cityService.deleteById(id);
        return new ResponseEntity<>("City removed!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CityDto> update(@Valid @RequestBody CityDto cityDto) throws Exception {
        CityDto subDto = cityService.update(cityDto);
        return new ResponseEntity<>(subDto, HttpStatus.OK);
    }
}
