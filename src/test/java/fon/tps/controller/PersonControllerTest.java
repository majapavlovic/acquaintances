/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fon.tps.domain.City;
import fon.tps.service.PersonService;
import fon.tps.dto.AdultsFromSmederevo;
import fon.tps.dto.CityDto;
import fon.tps.dto.PersonRequestDto;
import fon.tps.dto.PersonResponseDto;
import fon.tps.dto.mapping.DtoEntityMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author User
 */
class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonControllerV2 personControllerV2;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DtoEntityMapper mapper;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personControllerV2).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    @Test
    void testGetAll() throws Exception {

        City city1 = new City(1L, 11300, "Smederevo", "76", 60263);
        CityDto cityDto = mapper.mapCityToDto(city1);

        List<PersonResponseDto> people = List.of(
                new PersonResponseDto(1L, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, cityDto, cityDto)
        );

        when(personService.getAllSmederevci()).thenReturn(people);

        mockMvc.perform(get("/api/v2/tps/person/smederevci"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].jmbg").value("1906977714551"))
                .andExpect(jsonPath("$[0].name").value("Marko"))
                .andExpect(jsonPath("$[0].surname").value("Markovic"))
                .andExpect(jsonPath("$[0].heightInCm").value(187))
                .andExpect(jsonPath("$[0].birthdate").value("1977-06-19"))
                .andExpect(jsonPath("$[0].ageInMonths").value(569))
                .andExpect(jsonPath("$[0].cityOfBirth").value(cityDto))
                .andExpect(jsonPath("$[0].residence").value(cityDto));

        verify(personService, times(1)).getAllSmederevci();
    }

    @Test
    void testGetAllFromView() throws Exception {

        AdultsFromSmederevo adult = new AdultsFromSmederevo(
                1L, "1906977714551", "Marko", "Markovic", 187,
                LocalDate.of(1977, 6, 19), 569, "Smederevo", "Smederevo", 11300
        );

        List<AdultsFromSmederevo> adults = List.of(adult);
        when(personService.getAllSmederevciFromView()).thenReturn(adults);

        mockMvc.perform(get("/api/v2/tps/person/smederevci2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].jmbg").value("1906977714551"))
                .andExpect(jsonPath("$[0].name").value("Marko"))
                .andExpect(jsonPath("$[0].surname").value("Markovic"))
                .andExpect(jsonPath("$[0].heightInCm").value(187))
                .andExpect(jsonPath("$[0].birthdate").value("1977-06-19")) // Explicit check for ISO date
                .andExpect(jsonPath("$[0].ageInMonths").value(569))
                .andExpect(jsonPath("$[0].cityOfBirth").value("Smederevo"))
                .andExpect(jsonPath("$[0].residence").value("Smederevo"))
                .andExpect(jsonPath("$[0].residencePTT").value(11300));

        verify(personService, times(1)).getAllSmederevciFromView();
    }

    @Test
    void testSave() throws Exception {

        CityDto cityDto = new CityDto(1l, 11300, "Smederevo", "76", 60263);
        PersonRequestDto requestDto = new PersonRequestDto(1l, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, 1l, 1l);
        PersonResponseDto responseDto = new PersonResponseDto(1l, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, cityDto, cityDto);

        when(personService.insertPerson(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v2/tps/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.jmbg").value("1906977714551"))
                .andExpect(jsonPath("$.name").value("Marko"))
                .andExpect(jsonPath("$.surname").value("Markovic"))
                .andExpect(jsonPath("$.heightInCm").value(187))
                .andExpect(jsonPath("$.birthdate").value("1977-06-19"))
                .andExpect(jsonPath("$.ageInMonths").value(569))
                .andExpect(jsonPath("$.cityOfBirth.id").value(1L))
                .andExpect(jsonPath("$.cityOfBirth.ptt").value(11300))
                .andExpect(jsonPath("$.cityOfBirth.name").value("Smederevo"))
                .andExpect(jsonPath("$.cityOfBirth.regionCode").value("76"))
                .andExpect(jsonPath("$.cityOfBirth.citizens").value(60263))
                .andExpect(jsonPath("$.residence.id").value(1L))
                .andExpect(jsonPath("$.residence.ptt").value(11300))
                .andExpect(jsonPath("$.residence.name").value("Smederevo"))
                .andExpect(jsonPath("$.residence.regionCode").value("76"))
                .andExpect(jsonPath("$.residence.citizens").value(60263));

        verify(personService, times(1)).insertPerson(requestDto);
    }

    @Test
    void testUpdate() throws Exception {

        CityDto cityDto = new CityDto(1L, 11300, "Smederevo", "76", 60263);
        PersonRequestDto requestDto = new PersonRequestDto(1l, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, 1l, 1l);
        PersonResponseDto responseDto = new PersonResponseDto(1l, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, cityDto, cityDto);

        when(personService.updatePerson(1L, requestDto)).thenReturn(responseDto);

        mockMvc.perform(put("/api/v2/tps/person/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.jmbg").value("1906977714551"))
                .andExpect(jsonPath("$.name").value("Marko"))
                .andExpect(jsonPath("$.surname").value("Markovic"))
                .andExpect(jsonPath("$.heightInCm").value(187))
                .andExpect(jsonPath("$.birthdate").value("1977-06-19"))
                .andExpect(jsonPath("$.ageInMonths").value(569))
                .andExpect(jsonPath("$.cityOfBirth.id").value(1L))
                .andExpect(jsonPath("$.cityOfBirth.ptt").value(11300))
                .andExpect(jsonPath("$.cityOfBirth.name").value("Smederevo"))
                .andExpect(jsonPath("$.cityOfBirth.regionCode").value("76"))
                .andExpect(jsonPath("$.cityOfBirth.citizens").value(60263))
                .andExpect(jsonPath("$.residence.id").value(1L))
                .andExpect(jsonPath("$.residence.ptt").value(11300))
                .andExpect(jsonPath("$.residence.name").value("Smederevo"))
                .andExpect(jsonPath("$.residence.regionCode").value("76"))
                .andExpect(jsonPath("$.residence.citizens").value(60263));

        verify(personService, times(1)).updatePerson(1L, requestDto);
    }

    @Test
    void testGetPersonByJmbg() throws Exception {

        CityDto cityDto = new CityDto(1L, 11300, "Smederevo", "76", 60263);

        PersonResponseDto personDto = new PersonResponseDto(
                1L, "1906977714551", "Marko", "Markovic", 187,
                LocalDate.of(1977, 6, 19), 569, cityDto, cityDto
        );

        when(personService.getPersonByJmbg("1906977714551")).thenReturn(personDto);

        MvcResult result = mockMvc.perform(get("/api/v2/tps/person/jmbg/1906977714551"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        PersonResponseDto response = objectMapper.readValue(jsonResponse, PersonResponseDto.class);

        assertEquals(personDto, response);

        verify(personService, times(1)).getPersonByJmbg("1906977714551");

    }

    @Test
    void testDeleteById() throws Exception {

        mockMvc.perform(delete("/api/v2/tps/person/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Person removed!"));

        verify(personService, times(1)).deletePerson(1L);
    }

    @Test
    void testFindAverageAge() throws Exception {
        when(personService.findAverageAge()).thenReturn(30.5f);

        mockMvc.perform(get("/api/v2/tps/person/average-age"))
                .andExpect(status().isOk())
                .andExpect(content().string("30.5"));

        verify(personService, times(1)).findAverageAge();
    }

    @Test
    void testFindMaxHeight() throws Exception {
        when(personService.findMaxHeight()).thenReturn(190);

        mockMvc.perform(get("/api/v2/tps/person/max-height"))
                .andExpect(status().isOk())
                .andExpect(content().string("190"));

        verify(personService, times(1)).findMaxHeight();
    }
}
