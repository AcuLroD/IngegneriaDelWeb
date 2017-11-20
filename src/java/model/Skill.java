/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import it.univaq.f4i.iw.framework.data.DataLayerException;

/**
 *
 * @author luka
 */
public interface Skill {

    void copyFrom(Skill skill) throws DataLayerException;

    int getId();

    String getNome();

    void setId(int id);

    void setNome(String nome);
    
}
