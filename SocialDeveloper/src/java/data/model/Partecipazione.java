/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.GregorianCalendar;

/**
 *
 * @author luka
 */
public interface Partecipazione {

    void copyFrom(Partecipazione partecipazione) throws DataLayerException;

    Sviluppatore getCollaboratorePartecipazione() throws DataLayerException;

    //Sviluppatore getCoordinatorePartecipazione() throws DataLayerException;

    GregorianCalendar getData();

    int getId_collaboratore();

    int getId_coordinatore();

    int getId_sender();

    int getId_task();

    Sviluppatore getSender() throws DataLayerException;

    int getStato();

    Task getTaskByPartecipazione() throws DataLayerException;

    int getVoto();

    void setCollaboratorePartecipazione(Sviluppatore collaboratore);

    void setCoordinatorePartecipazione(Sviluppatore coordinatore);

    void setData(GregorianCalendar data);

    void setId_collaboratore(int id_collaboratore);

    void setId_coordinatore(int id_coordinatore);

    void setId_sender(int id_sender);

    void setId_task(int id_task);

    void setSender(Sviluppatore sender);

    void setStato(int stato);

    void setTask(Task task);

    void setVoto(int voto);
    
}
