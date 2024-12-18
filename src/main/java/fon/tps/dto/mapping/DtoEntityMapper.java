/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.tps.dto.mapping;

import fon.tps.domain.City;
import fon.tps.domain.Person;
import fon.tps.dto.CityDto;
import fon.tps.dto.PersonRequestDto;
import fon.tps.dto.PersonResponseDto;
import org.springframework.stereotype.Component;

/**
 *
 * @author User
 */
@Component
public class DtoEntityMapper {

    public CityDto mapCityToDto(City city) {
        CityDto dto = new CityDto(
                city.getId(),
                city.getPtt(),
                city.getName(),
                city.getRegionCode(),
                city.getCitizens());
        return dto;
    }

    public City mapDtoToCity(CityDto dto) {
        City city = new City();
        city.setId(dto.id());
        city.setPtt(dto.ptt());
        city.setName(dto.name());
        city.setRegionCode(dto.regionCode());
        city.setCitizens(dto.citizens());
        return city;
    }

    public Person mapRequestDtoToPerson(PersonRequestDto dto) {
        Person p = new Person();
        p.setId(dto.id());
        p.setJmbg(dto.jmbg());
        p.setName(dto.name());
        p.setSurname(dto.surname());
        p.setHeightInCm(dto.heightInCm());
        p.setBirthdate(dto.birthdate());
        p.setAgeInMonths(dto.ageInMonths());
        return p;
    }

    public Person mapResponseDtoToPerson(PersonResponseDto dto) {
        Person p = new Person();
        p.setId(dto.id());
        p.setJmbg(dto.jmbg());
        p.setName(dto.name());
        p.setSurname(dto.surname());
        p.setHeightInCm(dto.heightInCm());
        p.setBirthdate(dto.birthdate());
        p.setAgeInMonths(dto.ageInMonths());
        p.setCityOfBirth(mapDtoToCity(dto.cityOfBirth()));
        p.setResidence(mapDtoToCity(dto.residence()));
        return p;
    }

    public PersonRequestDto mapPersonToRequestDto(Person p) {
        PersonRequestDto dto = new PersonRequestDto(
                p.getId(), p.getJmbg(),
                p.getName(),
                p.getSurname(),
                p.getHeightInCm(),
                p.getBirthdate(),
                p.getAgeInMonths(),
                p.getCityOfBirth().getId(),
                p.getResidence().getId()
        );
        return dto;
    }

    public PersonResponseDto mapPersonToResponseDto(Person p) {
        PersonResponseDto dto = new PersonResponseDto(
                p.getId(), p.getJmbg(),
                p.getName(),
                p.getSurname(),
                p.getHeightInCm(),
                p.getBirthdate(),
                p.getAgeInMonths(),
                mapCityToDto(p.getCityOfBirth()),
                mapCityToDto(p.getResidence())
        );
        return dto;
    }

}
