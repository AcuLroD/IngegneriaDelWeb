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
public interface Messaggio {

    void copyFrom(Messaggio messaggio) throws DataLayerException;

    int getId();

    int getId_progetto();

    int getId_sviluppatore();

    Progetto getProgetto() throws DataLayerException;

    /**
     *
     * @return
     * @throws DataLayerException
     */
    Sviluppatore getSviluppatore() throws DataLayerException;

    String getTesto();

    String getTipo();

    boolean isPrivato();

    void setId(int id);

    void setId_progetto(int id_progetto);

    void setId_sviluppatore(int id_sviluppatore);

    void setPrivato(boolean privato);

    void setProgetto(Progetto progetto);

    void setSviluppatore(Sviluppatore developer);

    void setTesto(String testo);

    void setTipo(String tipo);
    
}
