/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.tps.repository;

import fon.tps.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface CityRepository extends JpaRepository <City, Long>{
    
}
