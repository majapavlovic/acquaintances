/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.service.impl;

import fon.tps.domain.City;
import fon.tps.domain.Person;
import fon.tps.dto.AdultsFromSmederevo;
import fon.tps.dto.PersonRequestDto;
import fon.tps.dto.PersonResponseDto;
import fon.tps.dto.mapping.DtoEntityMapper;
import fon.tps.repository.CityRepository;
import fon.tps.repository.PersonRepository;
import fon.tps.service.PersonService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private DtoEntityMapper mapper;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonResponseDto save(PersonRequestDto t) throws Exception {
        Person person = mapper.mapRequestDtoToPerson(t);
        Optional<City> cityOfBirth = cityRepository.findById(t.cityOfBirth());
        if (cityOfBirth.isPresent()) {
            person.setCityOfBirth(cityOfBirth.get());
        }
        Optional<City> residence = cityRepository.findById(t.residence());
        if (residence.isPresent()) {
            person.setResidence(residence.get());
        }
        Person saved = personRepository.save(person);
        return mapper.mapPersonToResponseDto(saved);
    }

    @Override
    public PersonResponseDto getById(Long id) throws Exception {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return mapper.mapPersonToResponseDto(person.get());
        } else {
            throw new Exception("Person not found");
        }
    }

    @Override
    public PersonResponseDto getByJmbg(String jmbg) throws Exception {
        Optional<Person> person = personRepository.findByJmbg(jmbg);
        if (person.isPresent()) {
            return mapper.mapPersonToResponseDto(person.get());
        } else {
            throw new Exception("Person not found");
        }
    }

    @Override
    public PersonResponseDto update(PersonRequestDto t) throws Exception {
        Optional<Person> person = personRepository.findByJmbg(t.jmbg());
        if (person.isPresent()) {
            Person updated = person.get();
            updated.setName(t.name());
            updated.setSurname(t.surname());
            updated.setBirthdate(t.birthdate());
            updated.setAgeInMonths(t.ageInMonths());

            Optional<City> cityOfBirth = cityRepository.findById(t.cityOfBirth());
            if (cityOfBirth.isPresent()) {
                updated.setCityOfBirth(cityOfBirth.get());
            }

            Optional<City> residence = cityRepository.findById(t.residence());
            if (residence.isPresent()) {
                updated.setResidence(residence.get());
            }
            return mapper.mapPersonToResponseDto(personRepository.save(updated));
        } else {
            throw new Exception("Person not found");
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            personRepository.delete(person.get());
        } else {
            throw new Exception("Person not found");
        }
    }

    @Override
    public void deleteByJmbg(String jmbg) throws Exception {
        Optional<Person> person = personRepository.findByJmbg(jmbg);
        if (person.isPresent()) {
            personRepository.delete(person.get());
        } else {
            throw new Exception("Person not found");
        }
    }

    @Override
    public List<PersonResponseDto> getAll() {
        return personRepository
                .findAll()
                .stream().map(entity -> mapper.mapPersonToResponseDto(entity))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<PersonResponseDto> getAllSmederevci() {
        return personRepository
                .getPeopleFromCity("Smederevo")
                .stream().map(entity -> mapper.mapPersonToResponseDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<AdultsFromSmederevo> getAllSmederevciFromView() {
        System.out.println(personRepository.getAllSmederevciFromView());
        return personRepository.getAllSmederevciFromView();
    }

}
