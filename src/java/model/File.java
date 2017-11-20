/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import data.model.SocialDevelopDataLayer;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.GregorianCalendar;

/**
 *
 * @author luka
 */
public interface File {

    void copyFrom(File file) throws DataLayerException;

    GregorianCalendar getCaricamento();

    int getId();

    String getLocalfile();

    String getNome();

    SocialDevelopDataLayer getOwnerdatalayer();

    int getSize();

    void setCaricamento(GregorianCalendar caricamento);

    void setId(int id);

    void setLocalfile(String localfile);

    void setNome(String nome);

    void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer);

    /**
     * @param size the size to set
     */
    void setSize(int size);
    
}
