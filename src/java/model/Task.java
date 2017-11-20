/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author luka
 */
public interface Task
{

    void addCollaborator(Sviluppatore collaboratore);

    void addSkill(Skill skill, int competenzaMinima);

    void copyFrom(Task task) throws DataLayerException;

    Map<Sviluppatore, Integer> getCollaboratori() throws DataLayerException;

    GregorianCalendar getDataFine();

    GregorianCalendar getDataInizio();

    String getDescrizione();

    int getId();

    int getId_progetto();

    String getNome();

    int getNumCollaboratori();

    Progetto getProgetto() throws DataLayerException;

    Map<Skill, Integer> getSkillsByTask() throws DataLayerException;

    int getVoto(Sviluppatore sviluppatore) throws DataLayerException;

    boolean isFull();

    boolean isStato();

    void removeCollaborator(Sviluppatore sviluppatore);

    void removeSkill(Skill skill);

    void setCollaboratori(List<Sviluppatore> sviluppatori);

    void setCollaboratori(Map<Sviluppatore, Integer> sviluppatori);

    void setDescrizione(String descrizione);

    void setEndDate(GregorianCalendar dataFine);

    void setId(int id);

    void setId_progetto(int id_progetto);

    void setNome(String nome);

    void setNumCollaboratori(int numCollaboratori);

    void setProgetto(Progetto progetto);

    void setSkills(Map<Skill, Integer> skill_compatenzaMinima);

    void setStartDate(GregorianCalendar dataInizio);

    void setStato(boolean isStato);

    void setVoto(Sviluppatore sviluppatore, int voto);
    
}
