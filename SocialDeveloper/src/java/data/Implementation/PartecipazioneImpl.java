package data.Implementation;

import data.model.SocialDevelopDataLayer;
import data.model.Partecipazione;
import data.model.Sviluppatore;
import data.model.Task;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.GregorianCalendar;

/**
 *
 * @author luka
 */
public class PartecipazioneImpl implements Partecipazione 
{
    
    private Sviluppatore coordinatore;
    private int id_coordinatore;
    private Task task;
    private int id_task;
    private int voto;
    private Sviluppatore collaboratore;
    private int id_collaboratore;
    private GregorianCalendar data;   //data richiesta partecipazione per lista richieste
    private int stato;  // 0 se in attesa, 1 se accettato, 2 se rifiutato;
    private int id_sender;
    private Sviluppatore sender;
    
    private SocialDevelopDataLayer ownerdatalayer;
    
    public PartecipazioneImpl (SocialDevelopDataLayer ownerdatalayer)
    {
        this.ownerdatalayer = ownerdatalayer;
        
        coordinatore = null;
        id_coordinatore = 0;
        task = null;
        id_task = 0;
        collaboratore = null;
        id_collaboratore = 0;
        voto = 0;
        data = null;
        stato = 0;
        id_sender = 0;
        sender = null;
    
    }
    
    
    @Override
    public int getId_sender(){
        return id_sender;
    }
    
    @Override
    public void setId_sender(int id_sender){
        this.id_sender = id_sender;
    }
    
    @Override
    public Sviluppatore getSender() throws DataLayerException{
        if(sender == null){
            sender = ownerdatalayer.getSviluppatore(id_sender);
        }
        return sender;
    }
    
    
    public void setSender(Sviluppatore sender){
        this.sender = sender;
        this.id_sender = sender.getId();
    }
    
    
    
   /* @Override
    public Sviluppatore getCoordinatorePartecipazione() throws DataLayerException
    {
        if(coordinatore == null){
            coordinatore = ownerdatalayer.getCoordinatoreByTask(this.id_task);
        }
        return coordinatore;
    }
*/
    
    @Override
    public void setCoordinatorePartecipazione(Sviluppatore coordinatore) 
    {
        this.coordinatore = coordinatore;
        this.id_coordinatore = coordinatore.getId();
    }

    
    @Override
    public void setId_coordinatore(int id_coordinatore)
    {
        this.id_coordinatore = id_coordinatore;
        this.coordinatore = null;
    }
    
    @Override
    public int getId_coordinatore()
    {
        return id_coordinatore;
    }
    
    
    @Override
    public Task getTaskByPartecipazione() throws DataLayerException {
        if(task == null){
            task = ownerdatalayer.getTask(id_task);
        }
        return task;
    }
    
    @Override
    public void setId_task(int id_task)
    {
        this.id_task = id_task;
        this.task = null;
    }
    
    
    @Override
    public int getId_task()
    {
        return id_task;
    }

    @Override
    public void setTask(Task task)
    {
        this.task = task;
        this.id_task = task.getId();
    }

    
    @Override
    public Sviluppatore getCollaboratorePartecipazione() throws DataLayerException 
    {
        if(collaboratore == null){
            collaboratore = ownerdatalayer.getSviluppatore(this.id_collaboratore);
        }
        return collaboratore;
    }

   
    @Override
    public void setCollaboratorePartecipazione(Sviluppatore collaboratore) 
    {
        this.collaboratore = collaboratore;
        this.id_collaboratore = collaboratore.getId();
    }
    
    
    
    @Override
    public void setId_collaboratore(int id_collaboratore)
    {
        this.id_collaboratore = id_collaboratore;
        this.collaboratore = null;
    }
    
    
    @Override
    public int getId_collaboratore()
    {
        return id_collaboratore;
    }

    
    @Override
    public void setVoto(int voto)
    {
        this.voto = voto;
    }
    
    
    @Override
    public int getVoto(){
        return voto;
    }
    
    
    @Override
    public GregorianCalendar getData() {
        return data;
    }

    
    @Override
    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    
    @Override
    public int getStato() {
        return stato;
    }

    
    public void setStato(int stato) {
        this.stato = stato;
    }
    
    
    @Override
    public void copyFrom(Partecipazione partecipazione) throws DataLayerException {
        id_task = partecipazione.getId_task();
        id_collaboratore = partecipazione.getId_collaboratore();
        voto = partecipazione.getVoto();
        data = partecipazione.getData();
        id_sender = partecipazione.getId_sender();
        stato = partecipazione.getStato();
        id_coordinatore = partecipazione.getId_coordinatore();
}

   
    
    
    
}
