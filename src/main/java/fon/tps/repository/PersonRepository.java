/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.tps.repository;

import fon.tps.domain.Person;
import fon.tps.dto.AdultsFromSmederevo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

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

}
