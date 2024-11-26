/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.tps.service;

import java.util.List;

/**
 *
 * @author User
 */
public interface DomainService<Res, Req, ID> {

    Res save(Req t) throws Exception;

    Res getById(ID id) throws Exception;

    Res update(Req t) throws Exception;

    void deleteById(ID id) throws Exception;

    List<Res> getAll();
}
