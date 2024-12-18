package fon.tps.service;

import fon.tps.domain.City;
import fon.tps.domain.Person;
import fon.tps.dto.CityDto;
import fon.tps.dto.PersonRequestDto;
import fon.tps.dto.PersonResponseDto;
import fon.tps.dto.mapping.DtoEntityMapper;
import fon.tps.exception.NotFoundException;
import fon.tps.exception.UnprocessableEntityException;
import fon.tps.repository.CityRepository;
import fon.tps.repository.PersonRepository;
import fon.tps.service.impl.PersonServiceImpl;
import fon.tps.validation.PersonValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private DtoEntityMapper mapper;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_PersonIsValid() throws Exception {
        City city1 = new City(1l, 11000, "Beograd", "71", 1382000);
        City city2 = new City(2l, 34000, "Kragujevac", "72", 147786);

        Person person = new Person(1l, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, city1, city2);

        CityDto city1dto = mapper.mapCityToDto(city1);
        CityDto city2dto = mapper.mapCityToDto(city2);

        PersonRequestDto request = new PersonRequestDto(1l, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, 1l, 2l);
        when(mapper.mapRequestDtoToPerson(request)).thenReturn(person);
        when(cityRepository.findById(1l)).thenReturn(Optional.of(city1));
        when(cityRepository.findById(2l)).thenReturn(Optional.of(city2));
        when(validator.isValidV2(person)).thenReturn(true);
        when(personRepository.save(person)).thenReturn(person);
        when(mapper.mapPersonToResponseDto(person)).thenReturn(new PersonResponseDto(1l, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, city1dto, city2dto));

        PersonResponseDto response = personService.save(request);

        assertNotNull(response);
        verify(personRepository).save(person);
    }

    @Test
    void testSave_PersonHasInvalidCity() {
        PersonRequestDto request = new PersonRequestDto(1l, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, 1l, 1l);
        Person person = new Person();

        when(mapper.mapRequestDtoToPerson(request)).thenReturn(person);
        when(cityRepository.findById(1l)).thenReturn(Optional.empty());

        assertThrows(UnprocessableEntityException.class, () -> personService.save(request));
        verify(personRepository, never()).save(any());
    }

    @Test
    void testGetById_PersonExists() throws Exception {
        Person person = new Person();
        City city1 = new City(1l, 11000, "Beograd", "71", 1382000);
        CityDto city1dto = mapper.mapCityToDto(city1);

        when(personRepository.findById(1l)).thenReturn(Optional.of(person));
        when(mapper.mapPersonToResponseDto(person)).thenReturn(new PersonResponseDto(1l, "1906977714551", "Marko", "Markovic", 187, LocalDate.of(1977, 6, 19), 569, city1dto, city1dto));

        PersonResponseDto response = personService.getById(1l);

        assertNotNull(response);
        verify(personRepository).findById(1l);
    }

    @Test
    void testGetById_PersonDoesNotExist() {
        when(personRepository.findById(1l)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> personService.getById(1l));
    }

    @Test
    void testDeleteById_PersonExists() throws Exception {
        Person person = new Person();
        when(personRepository.findById(1l)).thenReturn(Optional.of(person));

        personService.deleteById(1l);

        verify(personRepository).delete(person);
    }

    @Test
    void testDeleteById_PersonDoesNotExist() {
        when(personRepository.findById(1l)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> personService.deleteById(1l));
    }

    @Test
    void testGetAll_ReturnsListOfPeople() {
        City city1 = new City(1l, 11000, "Beograd", "71", 1382000);
        CityDto city1dto = mapper.mapCityToDto(city1);

        Person person = new Person();
        when(personRepository.findAll()).thenReturn(List.of(person));
        when(mapper.mapPersonToResponseDto(person)).thenReturn(new PersonResponseDto(
                1l, "1906977714551", "Marko", "Markovic", 187,
                LocalDate.of(1977, 6, 19), 569, city1dto, city1dto));

        List<PersonResponseDto> response = personService.getAll();

        assertEquals(1, response.size());
        verify(personRepository).findAll();
    }

    @Test
    void testFindAverageAge() {
        when(personRepository.findAverageAge()).thenReturn(35.5f);

        Float averageAge = personService.findAverageAge();

        assertEquals(35.5f, averageAge);
        verify(personRepository).findAverageAge();
    }

    @Test
    void testFindMaxHeight() {
        when(personRepository.findMaxHeight()).thenReturn(200);

        int maxHeight = personService.findMaxHeight();

        assertEquals(200, maxHeight);
        verify(personRepository).findMaxHeight();
    }
}
