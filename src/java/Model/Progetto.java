/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.SocialDevelopDataLayer;
import Model.Messaggio;
import Model.Sviluppatore;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author LuKa
 */
public interface Progetto {

    Sviluppatore getCoordinatore();

    String getCoordinatore_username();

    String getDescrizione();

    int getId_progetto();

    List<Messaggio> getMessaggi();

    String getNome();

    SocialDevelopDataLayer getOwnerdatalayer();

    List<Task> getTasks();

    boolean isDirty();

    void setCoordinatore(Sviluppatore coordinatore);

    void setCoordinatore_username(String coordinatore_username);

    void setDescrizione(String descrizione);

    void setDirty(boolean dirty);

    void setId_progetto(int id_progetto);

    void setMessaggi(List<Messaggio> messaggi);

    void setNome(String nome);

    void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer);

    void setTasks(List<Task> tasks);
    
}
