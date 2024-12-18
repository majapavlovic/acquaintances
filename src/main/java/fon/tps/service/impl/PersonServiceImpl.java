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
import fon.tps.exception.NotFoundException;
import fon.tps.exception.UnprocessableEntityException;
import fon.tps.repository.CityRepository;
import fon.tps.repository.PersonRepository;
import fon.tps.service.PersonService;
import fon.tps.validation.PersonValidator;
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

    private DtoEntityMapper mapper;
    private CityRepository cityRepository;
    private PersonRepository personRepository;
    private final PersonValidator validator;

    public PersonServiceImpl(DtoEntityMapper mapper, CityRepository cityRepository, PersonRepository personRepository, PersonValidator validator) {
        this.mapper = mapper;
        this.cityRepository = cityRepository;
        this.personRepository = personRepository;
        this.validator = validator;
    }

    @Override
    public PersonResponseDto save(PersonRequestDto t) throws Exception {
        Person person = mapper.mapRequestDtoToPerson(t);
        Optional<City> cityOfBirth = cityRepository.findById(t.cityOfBirth());
        Optional<City> residence = cityRepository.findById(t.residence());

        if (cityOfBirth.isPresent() && residence.isPresent()) {
            person.setCityOfBirth(cityOfBirth.get());
            person.setResidence(residence.get());

        } else {
            throw new UnprocessableEntityException("Person contains a reference to non-existant City");
        }
        if (validator.isValidV2(person)) {

            Person saved = personRepository.save(person);
            return mapper.mapPersonToResponseDto(saved);
        }
        return null;
    }

    @Override
    public PersonResponseDto getById(Long id) throws Exception {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return mapper.mapPersonToResponseDto(person.get());
        } else {
            throw new NotFoundException("Person", "ID", id.toString());
        }
    }

    @Override
    public PersonResponseDto getByJmbg(String jmbg) throws Exception {
        Optional<Person> person = personRepository.findByJmbg(jmbg);
        if (person.isPresent()) {
            return mapper.mapPersonToResponseDto(person.get());
        } else {
            throw new NotFoundException("Person", "JMBG", jmbg);
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
            Optional<City> residence = cityRepository.findById(t.residence());

            if (cityOfBirth.isPresent() && residence.isPresent()) {
                updated.setCityOfBirth(cityOfBirth.get());
                updated.setResidence(residence.get());
                return mapper.mapPersonToResponseDto(personRepository.save(updated));

            } else {
                throw new UnprocessableEntityException("Person contains a reference to non-existant City");
            }
        } else {
            throw new NotFoundException("Person", "JMBG", t.jmbg());
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            personRepository.delete(person.get());
        } else {
            throw new NotFoundException("Person", "ID", id.toString());
        }
    }

    @Override
    public void deleteByJmbg(String jmbg) throws Exception {
        Optional<Person> person = personRepository.findByJmbg(jmbg);
        if (person.isPresent()) {
            personRepository.delete(person.get());
        } else {
            throw new NotFoundException("Person", "JMBG", jmbg);
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
        return personRepository.getAllSmederevciFromView();
    }

    ///SQL STORED PROCEDURE CALLS
    @Override
    public PersonResponseDto insertPerson(PersonRequestDto p) throws Exception {

        Person person = mapper.mapRequestDtoToPerson(p);
        Optional<City> cityOfBirth = cityRepository.findById(p.cityOfBirth());
        Optional<City> residence = cityRepository.findById(p.residence());

        if (cityOfBirth.isPresent() && residence.isPresent()) {
            person.setCityOfBirth(cityOfBirth.get());
            person.setResidence(residence.get());

        } else {
            throw new UnprocessableEntityException("Person contains a reference to non-existant City");
        }

        if (validator.isValidV2(person)) {
            Person saved = personRepository.insertPerson(
                    p.jmbg(),
                    p.name(),
                    p.surname(),
                    p.heightInCm(),
                    java.sql.Date.valueOf(p.birthdate()),
                    p.ageInMonths(),
                    p.cityOfBirth(),
                    p.residence());
            return mapper.mapPersonToResponseDto(saved);
        }

        return null;

    }

    @Override
    public PersonResponseDto updatePerson(Long id, PersonRequestDto p) {
        Person person = mapper.mapRequestDtoToPerson(p);
        Optional<City> cityOfBirth = cityRepository.findById(p.cityOfBirth());
        Optional<City> residence = cityRepository.findById(p.residence());

        if (cityOfBirth.isPresent() && residence.isPresent()) {
            person.setCityOfBirth(cityOfBirth.get());
            person.setResidence(residence.get());

        } else {
            throw new UnprocessableEntityException("Person contains a reference to non-existant City");
        }
        if (validator.isValidV2(person)) {
            Person updated = personRepository.updatePerson(
                    id,
                    p.jmbg(),
                    p.name(),
                    p.surname(),
                    p.heightInCm(),
                    java.sql.Date.valueOf(p.birthdate()),
                    p.ageInMonths(),
                    p.cityOfBirth(),
                    p.residence());
            return mapper.mapPersonToResponseDto(updated);
        }

        return null;
    }
    @Override
    public void deletePerson(Long id) throws Exception {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            personRepository.deletePerson(id);
        } else {
            throw new NotFoundException("Person", "ID", id.toString());
        }

    }
    
    @Override
    public PersonResponseDto getPersonByJmbg(String jmbg) throws Exception {

        Optional<Person> person = personRepository.getPersonByJmbg(jmbg);
        if (person.isPresent()) {
            return mapper.mapPersonToResponseDto(person.get());
        } else {
            throw new NotFoundException("Person", "JMBG", jmbg);
        }
    }

    @Override
    public Float findAverageAge() {
        return personRepository.findAverageAge();
    }

    @Override
    public int findMaxHeight() {
        return personRepository.findMaxHeight();
    }
}
