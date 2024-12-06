/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.tps.service;

import fon.tps.dto.AdultsFromSmederevo;
import fon.tps.dto.PersonRequestDto;
import fon.tps.dto.PersonResponseDto;
import java.util.List;

/**
 *
 * @author User
 */
public interface PersonService extends DomainService<PersonResponseDto, PersonRequestDto, Long> {

    public PersonResponseDto getByJmbg(String jmbg) throws Exception;

    public void deleteByJmbg(String jmbg) throws Exception;

    public List<PersonResponseDto> getAllSmederevci(); //svi smederevci

    public List<AdultsFromSmederevo> getAllSmederevciFromView(); //samo punoletni
    
    //STORED PROCEDURE CALLS

    public PersonResponseDto insertPerson(PersonRequestDto p);

    public PersonResponseDto updatePerson(PersonRequestDto p);
    
    public void deletePerson(Long id) throws Exception;
    
    public PersonResponseDto getPersonByJmbg(String jmbg) throws Exception;
    
    public Float findAverageAge();
    
    public int findMaxHeight();


}
