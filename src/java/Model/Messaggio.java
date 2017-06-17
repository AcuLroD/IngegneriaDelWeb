/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.SocialDevelopDataLayer;
import Model.Sviluppatore;
import java.util.Date;

/**
 *
 * @author LuKa
 */
public interface Messaggio {

    Date getData_inserimento();

    int getId_messaggio();

    int getId_progetto();

    SocialDevelopDataLayer getOwnerdatalayer();

    Progetto getProgetto();

    Sviluppatore getSviluppatore();

    String getTesto();

    String getTipo();

    String getUsernameSviluppatore();

    boolean isVisibile();

    void setData_inserimento(Date data_inserimento);

    void setId_messaggio(int id_messaggio);

    void setId_progetto(int id_progetto);

    void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer);

    void setProgetto(Progetto progetto);

    void setSviluppatore(Sviluppatore sviluppatore);

    void setTesto(String testo);

    void setTipo(String tipo);

    void setUsernameSviluppatore(String usernameSviluppatore);

    void setVisibile(boolean visibile);
    
}
