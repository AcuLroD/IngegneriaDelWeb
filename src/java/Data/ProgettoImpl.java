package data.Implementation;

import data.model.SocialDevelopDataLayer;
import data.model.Task;
import data.model.Sviluppatore;
import data.model.Progetto;
import data.model.Messaggio;
import java.util.GregorianCalendar;

/**
 *
 * @author luka
 */
public class ProgettoImpl implements Progetto 
{

    private int id;
    private String nome;
    private String descrizione;
    private GregorianCalendar dataCreazione;
    private String stato;
    
    private Sviluppatore coordinatore;
    private Task[] tasks;
    private Messaggio[] messaggi;

    
    
    protected SocialDevelopDataLayer ownerdatalayer;


    
    public ProgettoImpl(SocialDevelopDataLayer ownerdatalayer) 
    {
        this.ownerdatalayer = ownerdatalayer;
        
        id = 0;
        nome ="";
        descrizione = "";
        dataCreazione = null;
        stato = "";
        coordinatore = null;
        tasks = null;
        messaggi = null;
       
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
    public GregorianCalendar getDataCreazione() {
        return dataCreazione;
    }

    @Override
    public void setDataCreazione(GregorianCalendar dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    @Override
    public String getStato() {
        return stato;
    }

    @Override
    public void setStato(String stato) {
        this.stato = stato;
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
    public Task[] getTasks() {
        return tasks;
    }

    @Override
    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    @Override
    public Messaggio[] getMessaggi() {
        return messaggi;
    }

    @Override
    public void setMessaggi(Messaggio[] messaggi) {
        this.messaggi = messaggi;
    }

    @Override
    public SocialDevelopDataLayer getOwnerdatalayer() {
        return ownerdatalayer;
    }

    @Override
    public void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer) {
        this.ownerdatalayer = ownerdatalayer;
    }
    
    
    
}