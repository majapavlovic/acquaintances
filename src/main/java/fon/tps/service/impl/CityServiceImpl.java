/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.service.impl;

import fon.tps.domain.City;
import fon.tps.dto.CityDto;
import fon.tps.dto.mapping.DtoEntityMapper;
import fon.tps.repository.CityRepository;
import fon.tps.service.CityService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private DtoEntityMapper mapper;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityDto save(CityDto t) throws Exception {
        Optional<City> dbCity = cityRepository.findByName(t.name());
        if (dbCity.isPresent()) {
            throw new Exception("City with that name already exists!");
        }
        City city = mapper.mapDtoToCity(t);
        city = cityRepository.save(city);
        return mapper.mapCityToDto(city);
    }

    @Override
    public CityDto getById(Long id) throws Exception {
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent()) {
            return mapper.mapCityToDto(city.get());
        } else {
            throw new Exception("City not found");
        }
    }

    @Override
    public CityDto update(CityDto t) throws Exception {
        Long id = t.id();
        Optional<City> dbCity = cityRepository.findById(id);
        if (dbCity.isPresent()) {
            City updated = dbCity.get();
            updated.setName(t.name());
            updated.setPtt(t.ptt());
            updated.setCitizens(t.citizens());
            return mapper.mapCityToDto(cityRepository.save(updated));
        } else {
            throw new Exception("City not found");
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Optional<City> dbCity = cityRepository.findById(id);
        if (dbCity.isPresent()) {
            cityRepository.delete(dbCity.get());
        } else {
            throw new Exception("City not found");
        }
    }

    @Override
    public List<CityDto> getAll() {
        return cityRepository
                .findAll()
                .stream().map(entity -> mapper.mapCityToDto(entity))
                .collect(Collectors.toList());
    }

}
