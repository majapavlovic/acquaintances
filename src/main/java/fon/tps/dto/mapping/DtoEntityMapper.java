/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.tps.dto.mapping;

import fon.tps.domain.City;
import fon.tps.dto.CityDto;
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
                city.getCitizens());
        return dto;
    }

    public City mapDtoToCity(CityDto dto) {
        City city = new City();
        city.setId(dto.id());
        city.setPtt(dto.ptt());
        city.setName(dto.name());
        city.setCitizens(dto.citizens());
        return city;
    }
}
