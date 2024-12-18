/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.mapper;

import fon.tps.domain.City;
import fon.tps.domain.Person;
import fon.tps.dto.CityDto;
import fon.tps.dto.PersonRequestDto;
import fon.tps.dto.PersonResponseDto;
import fon.tps.dto.mapping.DtoEntityMapper;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author User
 */
public class DtoEntityMapperTest {

    private final DtoEntityMapper mapper = new DtoEntityMapper();

    @Test
    void testMapCityToDto() {
        City city = new City(1L, 11300, "Smederevo", "76", 60263);

        CityDto dto = mapper.mapCityToDto(city);

        assertEquals(city.getId(), dto.id());
        assertEquals(city.getPtt(), dto.ptt());
        assertEquals(city.getName(), dto.name());
        assertEquals(city.getRegionCode(), dto.regionCode());
        assertEquals(city.getCitizens(), dto.citizens());
    }

    @Test
    void testMapDtoToCity() {
        CityDto dto = new CityDto(1L, 11300, "Smederevo", "76", 60263);

        City city = mapper.mapDtoToCity(dto);

        assertEquals(dto.id(), city.getId());
        assertEquals(dto.ptt(), city.getPtt());
        assertEquals(dto.name(), city.getName());
        assertEquals(dto.regionCode(), city.getRegionCode());
        assertEquals(dto.citizens(), city.getCitizens());
    }

    @Test
    void testMapRequestDtoToPerson() {
        PersonRequestDto dto = new PersonRequestDto(1L, "1906977714551", "Marko", "Markovic", 187,
                LocalDate.of(1977, 6, 19), 569, 1L, 1L);

        Person person = mapper.mapRequestDtoToPerson(dto);

        assertEquals(dto.id(), person.getId());
        assertEquals(dto.jmbg(), person.getJmbg());
        assertEquals(dto.name(), person.getName());
        assertEquals(dto.surname(), person.getSurname());
        assertEquals(dto.heightInCm(), person.getHeightInCm());
        assertEquals(dto.birthdate(), person.getBirthdate());
        assertEquals(dto.ageInMonths(), person.getAgeInMonths());
    }

    @Test
    void testMapResponseDtoToPerson() {
        CityDto cityDto = new CityDto(1L, 11300, "Smederevo", "76", 60263);
        PersonResponseDto dto = new PersonResponseDto(1L, "1906977714551", "Marko", "Markovic", 187,
                LocalDate.of(1977, 6, 19), 569, cityDto, cityDto);

        Person person = mapper.mapResponseDtoToPerson(dto);

        assertEquals(dto.id(), person.getId());
        assertEquals(dto.jmbg(), person.getJmbg());
        assertEquals(dto.name(), person.getName());
        assertEquals(dto.surname(), person.getSurname());
        assertEquals(dto.heightInCm(), person.getHeightInCm());
        assertEquals(dto.birthdate(), person.getBirthdate());
        assertEquals(dto.ageInMonths(), person.getAgeInMonths());
        assertEquals(dto.cityOfBirth().id(), person.getCityOfBirth().getId());
        assertEquals(dto.residence().id(), person.getResidence().getId());
    }

    @Test
    void testMapPersonToRequestDto() {
        City city = new City(1L, 11300, "Smederevo", "76", 60263);
        Person person = new Person(1L, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569,
                city, city);

        PersonRequestDto dto = mapper.mapPersonToRequestDto(person);

        assertEquals(person.getId(), dto.id());
        assertEquals(person.getJmbg(), dto.jmbg());
        assertEquals(person.getName(), dto.name());
        assertEquals(person.getSurname(), dto.surname());
        assertEquals(person.getHeightInCm(), dto.heightInCm());
        assertEquals(person.getBirthdate(), dto.birthdate());
        assertEquals(person.getAgeInMonths(), dto.ageInMonths());
        assertEquals(person.getCityOfBirth().getId(), dto.cityOfBirth());
        assertEquals(person.getResidence().getId(), dto.residence());
    }

    @Test
    void testMapPersonToResponseDto() {
        City city = new City(1L, 11300, "Smederevo", "76", 60263);
        Person person = new Person(1L, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569,
                city, city);

        PersonResponseDto dto = mapper.mapPersonToResponseDto(person);

        assertEquals(person.getId(), dto.id());
        assertEquals(person.getJmbg(), dto.jmbg());
        assertEquals(person.getName(), dto.name());
        assertEquals(person.getSurname(), dto.surname());
        assertEquals(person.getHeightInCm(), dto.heightInCm());
        assertEquals(person.getBirthdate(), dto.birthdate());
        assertEquals(person.getAgeInMonths(), dto.ageInMonths());
        assertEquals(person.getCityOfBirth().getId(), dto.cityOfBirth().id());
        assertEquals(person.getResidence().getId(), dto.residence().id());
    }
}
