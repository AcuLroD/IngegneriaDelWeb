/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 *
 * @author luka
 */
public interface Sviluppatore{

    void addSkill(Skill skill, int livello);

    void copyFrom(Sviluppatore sviluppatore) throws DataLayerException;

    boolean equals(Object o);

    String getBiografia();

    String getCognome();

    GregorianCalendar getDataDiNascita();

    String getEmail();

    File getFotoFile() throws DataLayerException;

    int getId();

    int getId_curriculum();

    int getId_foto();

    String getNome();

    String getPassword();

    Map<Skill, Integer> getSkillsBySviluppatore() throws DataLayerException;

    Map<Task, Integer> getTasksBySviluppatore() throws DataLayerException;

    void modSkillLevel(Skill skill, int livello);

    void removeSkill(Skill skill);

    void setBiografia(String biografia);

    void setCognome(String cognome);

    void setDataDiNascita(GregorianCalendar dataDiNascita);

    void setEmail(String email);

    /**
     * @param fotoFile the fotoFile to set
     */
    void setFotoFile(File fotoFile);

    void setId(int id);

    void setId_curriculum(int id_curriculum);

    void setId_foto(int id_foto);

    void setNome(String nome);

    void setPassword(String password);

    void setSkills(Map<Skill, Integer> skills);
    
}
