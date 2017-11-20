/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author luka
 */
public interface Progetto {

    void addMessaggio(Messaggio messaggio);

    void addTask(Task task);

    void copyFrom(Progetto progetto) throws DataLayerException;

    Sviluppatore getCoordinatore();

    GregorianCalendar getDataCreazione();

    String getDescrizione();

    int getId();

    int getId_coordinatore();

    List<Messaggio> getMessaggi() throws DataLayerException;

    String getNome();

    String getStato();

    List<Task> getTasks() throws DataLayerException;

    void removeMessage(Messaggio messaggio);

    void removeTask(Task task);

    void setCoordinatore(Sviluppatore coordinatore);

    void setDataCreazione(GregorianCalendar dataCreazione);

    void setDescrizione(String descrizione);

    void setId(int id);

    void setId_coordinatore(int id_coordinatore);

    void setNome(String nome);

    void setStato(String stato);

    void setTasks(List<Task> tasks);
    
}
