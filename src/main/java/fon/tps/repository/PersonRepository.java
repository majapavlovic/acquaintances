/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.tps.repository;

import fon.tps.domain.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByJmbg(String jmbg);

    Optional<Person> deleteByJmbg(String jmbg);

}
