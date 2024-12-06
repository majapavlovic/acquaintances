/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.tps.seeder;

import fon.tps.domain.City;
import fon.tps.domain.Person;
import fon.tps.repository.CityRepository;
import fon.tps.repository.PersonRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author User
 */
@Component
public class DabataseSeeder implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final CityRepository cityRepository;

    public DabataseSeeder(PersonRepository personRepository, CityRepository cityRepository) {
        this.personRepository = personRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //seed cities
        City city1 = cityRepository.save(new City(null, 11000, "Beograd", "71", 1382000));
        City city2 = cityRepository.save(new City(null, 34000, "Kragujevac", "72", 147786));
        City city3 = cityRepository.save(new City(null, 18000, "Nis", "73", 183202));
        City city4 = cityRepository.save(new City(null, 16000, "Leskovac", "74", 61581));
        City city5 = cityRepository.save(new City(null, 19000, "Zajecar", "75", 32448));
        City city6 = cityRepository.save(new City(null, 11300, "Smederevo", "76", 60263));

        //seed people
        Person person1 = personRepository.save(new Person(null, "1906977714551", "Marko", "Markovic", 187,
                LocalDate.of(1977, 6, 19), 569, city1, city1));

        Person person2 = personRepository.save(new Person(null, "0609966762351", "Petar", "Peric", 178,
                LocalDate.of(1966, 9, 6), 699, city6, city6));

        Person person3 = personRepository.save(new Person(null, "2709006760011", "Bojan", "Bojic", 203,
                LocalDate.of(2006, 9, 27), 218, city6, city6));

        Person person4 = personRepository.save(new Person(null, "2412006765126", "Nina", "Nikolic", 161,
                LocalDate.of(2006, 12, 24), 215, city6, city6));
    }

}
