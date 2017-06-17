/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Progetto;
import Model.Messaggio;
import Model.Sviluppatore;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author LuKa
 */
public class ProgettoImpl implements Progetto {
    
    private int id_progetto;
    private Sviluppatore coordinatore;
    private String coordinatore_username;
    private String nome;
    private String descrizione;
    private List<Task> tasks;
    private List<Messaggio> messaggi;
    
    protected SocialDevelopDataLayer ownerdatalayer;
    protected boolean dirty;
    
    
    public ProgettoImpl (SocialDevelopDataLayer ownerdatalayer) {
  
        this.ownerdatalayer = ownerdatalayer;
        id_progetto = 0;
        coordinatore = null;
        coordinatore_username = "";
        nome = "";
        descrizione = "";
        tasks = null;
        messaggi = null;
        dirty = false;
    }

    @Override
    public int getId_progetto() {
        return id_progetto;
    }

    @Override
    public void setId_progetto(int id_progetto) {
        this.id_progetto = id_progetto;
    }

    @Override
    public Sviluppatore getCoordinatore() {
        return coordinatore;
    }

    @Override
    public void setCoordinatore(Sviluppatore coordinatore) {
        this.coordinatore = coordinatore;
    }

    @Override
    public String getCoordinatore_username() {
        return coordinatore_username;
    }

    @Override
    public void setCoordinatore_username(String coordinatore_username) {
        this.coordinatore_username = coordinatore_username;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<Messaggio> getMessaggi() {
        return messaggi;
    }

    @Override
    public void setMessaggi(List<Messaggio> messaggi) {
        this.messaggi = messaggi;
    }

    @Override
    public SocialDevelopDataLayer getOwnerdatalayer() {
        return ownerdatalayer;
    }

    public void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer) {
        this.ownerdatalayer = ownerdatalayer;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
    
        
}
    

