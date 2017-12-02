package data.Implementation;

import data.model.SocialDevelopDataLayer;
import data.model.Task;
import data.model.Sviluppatore;
import data.model.Progetto;
import data.model.Messaggio;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.GregorianCalendar;
import java.util.List;

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
    private int id_coordinatore;
    
    private Sviluppatore coordinatore;
    
    
    private List<Task> tasks;
    private List<Messaggio> messaggi;

    
    
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
    public void setId_coordinatore(int id_coordinatore)
    {
        this.id_coordinatore = id_coordinatore;
        this.coordinatore = null;
    }
    
    @Override
    public int getId_coordinatore(){
        return id_coordinatore;
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
    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }
    
    
    
    @Override
    public List<Task> getTasks() throws DataLayerException
    {
        
        if(tasks == null){
           tasks = ownerdatalayer.getTasks(this.id);
        }
        return tasks;
    }
    
    
    @Override
    public void addTask(Task task)
    {
        this.tasks.add(task);
    }
    
    
    
    @Override
    public void removeTask(Task task)
    {
        this.tasks.remove(task);
    }
    
    
    @Override
    public List<Messaggio> getMessaggi() throws DataLayerException {
        if(messaggi == null){
           messaggi = ownerdatalayer.getMessaggi(this.id);
        }
        return messaggi;
    }
    
    
    @Override
    public void addMessaggio(Messaggio messaggio)
    {
        this.messaggi.add(messaggio);
    }
    
    
    @Override
    public void removeMessage(Messaggio messaggio)
    {
        this.messaggi.remove(messaggio);
    }
    
    
    @Override
    public void copyFrom(Progetto progetto) throws DataLayerException {
        id = progetto.getId();
        id_coordinatore= progetto.getCoordinatore().getId();
        descrizione = progetto.getDescrizione();
        nome = progetto.getNome();
        dataCreazione = progetto.getDataCreazione();
        stato = progetto.getStato();
    }   
 
    
    
    
}