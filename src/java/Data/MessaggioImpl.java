package data.Implementation;

import data.model.Sviluppatore;
import data.model.Messaggio;

/**
 *
 * @author luka
 */
public class MessaggioImpl implements Messaggio 
{
    
    private int id;
    private boolean privato;
    private String testo;
    private String tipo;
    private int id_progetto;
    private int id_sviluppatore;
    private Sviluppatore sviluppatore;
   
    protected SocialDevelopDataLayer ownerdatalayer;
 
    
    
    public MessaggioImpl(SocialDevelopDataLayer ownerdatalayer) 
    {
        this.ownerdatalayer = ownerdatalayer;
        id = 0;
        privato = false;
        testo = "";
        id_progetto = 0;
        tipo = "";
        sviluppatore = null;
        id_sviluppatore = 0;
        
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean isPrivato() {
        return privato;
    }

    @Override
    public void setPrivato(boolean privato) {
        this.privato = privato;
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
    public int getId_progetto() {
        return id_progetto;
    }

    @Override
    public void setId_progetto(int id_progetto) {
        this.id_progetto = id_progetto;
    }

    @Override
    public int getId_sviluppatore() {
        return id_sviluppatore;
    }

    @Override
    public void setId_sviluppatore(int id_sviluppatore) {
        this.id_sviluppatore = id_sviluppatore;
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
    public SocialDevelopDataLayer getOwnerdatalayer() {
        return ownerdatalayer;
    }

    public void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer) {
        this.ownerdatalayer = ownerdatalayer;
    }
    
    
    
}
