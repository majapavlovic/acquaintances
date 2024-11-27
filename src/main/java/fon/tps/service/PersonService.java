/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.tps.service;

import fon.tps.dto.PersonRequestDto;
import fon.tps.dto.PersonResponseDto;

/**
 *
 * @author User
 */
public interface PersonService extends DomainService<PersonResponseDto, PersonRequestDto, Long> {

    public PersonResponseDto getByJmbg(String jmbg) throws Exception;

    public void deleteByJmbg(String jmbg) throws Exception;
}
