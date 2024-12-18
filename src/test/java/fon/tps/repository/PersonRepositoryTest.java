/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fon.tps.repository;

import fon.tps.domain.City;
import fon.tps.domain.Person;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.val;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author User
 */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CityRepository cityRepository;

    private final City city1 = new City(1L, 11000, "Beograd", "71", 1382000);
    private final City city2 = new City(2L, 34000, "Kragujevac", "72", 147786);

    private final Person person = new Person(1L, "1906977714551", "Marko", "Markovic", 187,
            LocalDate.of(1977, 6, 19), 569, city1, city1);

    @BeforeAll
    public void setUp() {
        cityRepository.save(city1);
        cityRepository.save(city2);
        personRepository.save(person);
    }

    @AfterAll
    public void tearDown() {
        personRepository.deleteAll();
        cityRepository.deleteAll();
    }

    @Test
    void saveTest() {
        val personDB = personRepository.save(person);

        assertThat(personDB).isNotNull();
        assertThat(personDB.getJmbg()).isEqualTo(person.getJmbg());
    }

    @Test
    void selectTest() {
        List<Person> persons = personRepository.findAll();

        assertThat(persons).isNotEmpty().contains(person);
    }

    @Test
    void selectByIdTest() {
        val personDbOptional = personRepository.findById(person.getId());

        assertThat(personDbOptional).isPresent();
        assertThat(personDbOptional.get()).isEqualTo(person);
    }
    
}
