package data.Implementation;

import data.model.SocialDevelopDataLayer;
import data.model.Sviluppatore;
import data.model.Messaggio;
import data.model.Progetto;
import it.univaq.f4i.iw.framework.data.DataLayerException;

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
    private Progetto progetto;
   
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
        progetto = null;
        
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

    /**
     *
     * @return
     * @throws DataLayerException
     */
    @Override
    public Sviluppatore getSviluppatore() throws DataLayerException
    {
        if(sviluppatore == null && id_sviluppatore > 0)
        {
            sviluppatore = ownerdatalayer.getSviluppatore(id_sviluppatore);
        }
        return sviluppatore;
    }

    public void setSviluppatore(Sviluppatore developer){
        this.sviluppatore = developer;
        this.id_sviluppatore = sviluppatore.getId();
}

    @Override
    public Progetto getProgetto() throws DataLayerException
    {
        if (progetto == null && id_progetto > 0)
        {
            progetto = ownerdatalayer.getProgetto(id_progetto);
        }
        
        return progetto;
    }

    
    @Override
    public void setProgetto(Progetto progetto){
        this.progetto = progetto;
        this.id_progetto = progetto.getId();
    }
  

    @Override
    public void copyFrom(Messaggio messaggio) throws DataLayerException {
        id = messaggio.getId();
        privato = messaggio.isPrivato();
        testo = messaggio.getTesto();
        id_progetto = messaggio.getId_progetto();
        id_sviluppatore = messaggio.getId_sviluppatore();
}
    
    
    
}
