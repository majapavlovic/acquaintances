/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.tps.repository;

import fon.tps.domain.Person;
import fon.tps.dto.AdultsFromSmederevo;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author User
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByJmbg(String jmbg);

    Optional<Person> deleteByJmbg(String jmbg);

    @Procedure(name = "GetPeopleFromCity")
    List<Person> getPeopleFromCity(String cityName);

    @Query(value = "SELECT * FROM AdultsFromSmederevo", nativeQuery = true)
    List<AdultsFromSmederevo> getAllSmederevciFromView();

    @Query(value = "EXEC InsertPerson :jmbg, :name, :surname, :height_in_cm, :birthdate, :age_in_months, :city_of_birth_id, :residence_id;", nativeQuery = true)
    Person insertPerson(
            @Param("jmbg") String jmbg,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("height_in_cm") int heightInCm,
            @Param("birthdate") Date birthdate,
            @Param("age_in_months") int ageInMonths,
            @Param("city_of_birth_id") Long cityOfBirthId,
            @Param("residence_id") Long residenceId
    );

    @Query(value = "EXEC UpdatePerson :id, :jmbg, :name, :surname, :height_in_cm, :birthdate, :age_in_months, :city_of_birth_id, :residence_id;", nativeQuery = true)
    Person updatePerson(
            @Param("id") Long id,
            @Param("jmbg") String jmbg,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("height_in_cm") int heightInCm,
            @Param("birthdate") Date birthdate,
            @Param("age_in_months") int ageInMonths,
            @Param("city_of_birth_id") Long cityOfBirthId,
            @Param("residence_id") Long residenceId
    );
            
    @Procedure(name = "DeletePerson")
    void deletePerson(@Param("id") Long id);

    @Query(value = "EXEC SelectPersonByJmbg :jmbg;", nativeQuery = true)
    Optional<Person> getPersonByJmbg(@Param("jmbg") String id);

    @Query(value = "SELECT dbo.GetAverageAge() as averageAge;", nativeQuery = true)
    Float findAverageAge();

    @Query(value = "SELECT dbo.GetMaxHeight() as maxHeight;", nativeQuery = true)
    int findMaxHeight();

}
