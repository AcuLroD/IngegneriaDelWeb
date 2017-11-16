package data.Implementation;

import data.model.Task;
import data.model.Messaggio;
import java.util.GregorianCalendar;

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
    
    
    private Messaggio[] messaggi;
    
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
    public boolean isStato() {
        return stato;
    }

    @Override
    public void setStato(boolean stato) {
        this.stato = stato;
    }

    @Override
    public GregorianCalendar getDataInizio() {
        return dataInizio;
    }

    @Override
    public void setDataInizio(GregorianCalendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    @Override
    public GregorianCalendar getDataFine() {
        return dataFine;
    }

    @Override
    public void setDataFine(GregorianCalendar dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public int getNumCollaboratori() {
        return numCollaboratori;
    }

    @Override
    public void setNumCollaboratori(int numCollaboratori) {
        this.numCollaboratori = numCollaboratori;
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
    public Messaggio[] getMessaggi() {
        return messaggi;
    }

    @Override
    public void setMessaggi(Messaggio[] messaggi) {
        this.messaggi = messaggi;
    }
    
    
    
    
    
}
