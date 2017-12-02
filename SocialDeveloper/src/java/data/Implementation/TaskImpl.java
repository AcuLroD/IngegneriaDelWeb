package data.Implementation;

import data.model.SocialDevelopDataLayer;
import data.model.Task;
import data.model.Progetto;
import data.model.Skill;
import data.model.Sviluppatore;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luka
 */
public class TaskImpl implements Task
{
    
    private int id;
    private String nome;
    private boolean stato;
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataFine;
    private int numCollaboratori;
    private String descrizione;
    
    private Progetto progetto;
    private int id_progetto;
    
    
    private Map<Skill, Integer> skills;
    private Map<Sviluppatore, Integer> collaboratori;
    
    private SocialDevelopDataLayer owndatalayer;
    
    public TaskImpl(SocialDevelopDataLayer owndatalayer)
    {
        
        this.owndatalayer = owndatalayer;
        
        id = 0;
        nome  = "";
        stato = false;
        dataInizio = null;
        dataFine = null;
        numCollaboratori = 0;
        descrizione = "";
        skills = null;
        collaboratori = null;
        progetto = null;
        id_progetto = 0;
    }

   
    @Override
    public int getId()
    {
        return id;
    }
    
    @Override
    public void setNumCollaboratori(int numCollaboratori)
    {
        this.numCollaboratori = numCollaboratori;
    }
    
    @Override
    public int getNumCollaboratori()
    {
        return numCollaboratori;
    }
    
    @Override
    public void setId_progetto(int id_progetto)
    {
        this.id_progetto = id_progetto;
        this.progetto = null;
    }
    
    
    @Override
    public int getId_progetto()
    {
        return id_progetto;
    }
    
    @Override
    public void setProgetto(Progetto progetto)
    {
        this.progetto = progetto;
        this.id_progetto = progetto.getId();
    }
    
   
    @Override
    public Progetto getProgetto() throws DataLayerException
    {
        //notare come il coordinatore in relazione venga caricato solo su richiesta
        if (progetto == null && id_progetto > 0) 
        {
            progetto = owndatalayer.getProgetto(id_progetto);
        }
    
       
        return progetto;
    }
    
    
    @Override
    public void setStato(boolean isStato)
    {
        this.stato = isStato;
    }
    
     
    @Override
    public boolean isStato()
    {
        return stato;
    }
    
    @Override
    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }
    
     
    @Override
    public String getDescrizione()
    {
        return descrizione;
    }
    
    
    @Override
    public GregorianCalendar getDataInizio()
    {
        return dataInizio;
    }
    
    @Override
    public GregorianCalendar getDataFine()
    {
        return dataFine;
    }
    
    
    public void setDataInizio(GregorianCalendar dataInizio)
    {
        this.dataInizio = dataInizio;
    }
    
    public void setDataFine(GregorianCalendar dataFine)
    {
        this.dataFine =dataFine;
    }
    
    
    @Override
    public Map<Skill, Integer> getSkillsByTask() throws DataLayerException
    {
        if(skills == null)
        {
            skills = owndatalayer.getSkillsByTask(this.id);
        }
        return skills;
    }
    
    
    @Override
    public void setSkills(Map<Skill, Integer> skill_compatenzaMinima)
    {
        this.skills = skill_compatenzaMinima;
    }
    
  
    @Override
    public void addSkill(Skill skill, int competenzaMinima)
    {
        this.skills.put(skill,competenzaMinima);
    }
    
    
    @Override
    public void removeSkill(Skill skill)
    {
        this.skills.remove(skill);
    }
    
    
    @Override
    public Map<Sviluppatore,Integer> getCollaboratori()throws DataLayerException
    {
        if(collaboratori == null)
        {
            collaboratori = owndatalayer.getCollaboratoriByTask(this.id);
        }
        return collaboratori;
    }
    
    
    @Override
    public void setCollaboratori(List<Sviluppatore> sviluppatori)
    {
        Map<Sviluppatore,Integer> coll = new HashMap<>();
        for (Sviluppatore sviluppatore : sviluppatori)
        {
            coll.put(sviluppatore, null);
        }
        this.collaboratori = coll;
    }
    
    
    @Override
    public void setCollaboratori(Map<Sviluppatore, Integer> sviluppatori)
    {
        this.collaboratori = sviluppatori;
    }
    
    @Override
    public void addCollaborator(Sviluppatore collaboratore)
    {
        this.collaboratori.put(collaboratore, null);
    }
    
    @Override
    public void removeCollaborator(Sviluppatore sviluppatore)
    {
        this.collaboratori.remove(sviluppatore);
    }
    
    @Override
    public int getVoto(Sviluppatore sviluppatore) throws DataLayerException 
    {
        if(collaboratori.get(sviluppatore)==null)
        {
            collaboratori.put(sviluppatore, owndatalayer.getVoto(this.id, sviluppatore.getId()));
        }
        return collaboratori.get(sviluppatore);
    }
    
    @Override
    public void setVoto(Sviluppatore sviluppatore,int voto)
    {
        this.collaboratori.put(sviluppatore, voto);
    }
    
    
    @Override
    public boolean isFull()
    {
        boolean isFull = false ;
        try 
        {
            isFull = (numCollaboratori == this.getCollaboratori().size());
        } catch (DataLayerException ex) 
        {
            Logger.getLogger(TaskImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isFull;   
    }
    
    
    @Override
    public void setId(int id) 
    {
        this.id = id;
    }

    
    @Override
    public String getNome()
    {
        return nome;
    }

    @Override
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    
    
    public void copyFrom(Task task) throws DataLayerException
    {
        id = task.getId();
        id_progetto = task.getProgetto().getId();
        nome = task.getNome();
        numCollaboratori = task.getNumCollaboratori();
        dataInizio = task.getDataInizio();
        dataFine = task.getDataFine();
        descrizione = task.getDescrizione();
        stato = task.isStato();
    }
    
}
