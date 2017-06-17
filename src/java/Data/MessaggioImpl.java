/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Progetto;
import Model.Messaggio;
import Model.Sviluppatore;
import java.util.Date;

/**
 *
 * @author LuKa
 */
public class MessaggioImpl implements Messaggio {
    
    private int id_messaggio;
    private Date data_inserimento;
    private Sviluppatore sviluppatore;
    private String usernameSviluppatore;
    private Progetto progetto;
    private int id_progetto;
    private String testo;
    private String tipo;
    private boolean visibile;
    private SocialDevelopDataLayer ownerdatalayer;
    
    
    
    public MessaggioImpl (SocialDevelopDataLayer ownerdatalayer){
        
        this.ownerdatalayer = ownerdatalayer;
        id_messaggio = 0;
        data_inserimento = null;
        sviluppatore = null;
        usernameSviluppatore = "";
        progetto = null;
        id_progetto = 0;
        testo = "";
        tipo = "";
        visibile = false;
        
    }

    @Override
    public int getId_messaggio() {
        return id_messaggio;
    }

    @Override
    public void setId_messaggio(int id_messaggio) {
        this.id_messaggio = id_messaggio;
    }

    @Override
    public Date getData_inserimento() {
        return data_inserimento;
    }

    @Override
    public void setData_inserimento(Date data_inserimento) {
        this.data_inserimento = data_inserimento;
    }

    @Override
    public Sviluppatore getSviluppatore() {
        return sviluppatore;
    }

    @Override
    public void setSviluppatore(Sviluppatore sviluppatore) {
        this.sviluppatore = sviluppatore;
    }

    @Override
    public String getUsernameSviluppatore() {
        return usernameSviluppatore;
    }

    @Override
    public void setUsernameSviluppatore(String usernameSviluppatore) {
        this.usernameSviluppatore = usernameSviluppatore;
    }

    @Override
    public Progetto getProgetto() {
        return progetto;
    }

    @Override
    public void setProgetto(Progetto progetto) {
        this.progetto = progetto;
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
    public String getTesto() {
        return testo;
    }

    @Override
    public void setTesto(String testo) {
        this.testo = testo;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean isVisibile() {
        return visibile;
    }

    @Override
    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    @Override
    public SocialDevelopDataLayer getOwnerdatalayer() {
        return ownerdatalayer;
    }

    public void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer) {
        this.ownerdatalayer = ownerdatalayer;
    }
    

    

    
}
