/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Task;
import Model.Skill;
import Model.Progetto;
import Model.Sviluppatore;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author LuKa
 */
public class TaskImpl implements Task {   
    
    private int id_task;
    private String nome;
    private GregorianCalendar inizio;
    private GregorianCalendar fine;
    private boolean stato;
    private String descrizione;
    private int numCollaboratori;
    private List<Skill> skills;
    private List<Sviluppatore> collaboratori;
    private Progetto prog;
    private int id_progetto;
   
    protected SocialDevelopDataLayer ownerdatalayer;
    protected boolean dirty;
    
    
    public TaskImpl (SocialDevelopDataLayer ownerdatalayer) {
        
        this.ownerdatalayer = ownerdatalayer;
        id_task = 0;
        nome = "";
        inizio = null;
        fine = null;
        stato = true;   // lo stato del progetto è "in corso"
        descrizione = "";
        numCollaboratori = 0;
        skills = null;
        collaboratori = null;
        prog = null;
        id_progetto = 0;
        
    }

    @Override
    public int getId_task() {
        return id_task;
    }

    @Override
    public void setId_task(int id_task) {
        this.id_task = id_task;
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
    public GregorianCalendar getInizio() {
        return inizio;
    }

    @Override
    public void setInizio(GregorianCalendar inizio) {
        this.inizio = inizio;
    }

    @Override
    public GregorianCalendar getFine() {
        return fine;
    }

    @Override
    public void setFine(GregorianCalendar fine) {
        this.fine = fine;
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
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
    public List<Skill> getSkills() {
        return skills;
    }

    @Override
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public List<Sviluppatore> getCollaboratori() {
        return collaboratori;
    }

    

    @Override
    public Progetto getProg() {
        return prog;
    }

    @Override
    public void setProg(Progetto prog) {
        this.prog = prog;
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
    public SocialDevelopDataLayer getOwnerdatalayer() {
        return ownerdatalayer;
    }

    @Override
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

    @Override
    public void setCollaboratori(List<Sviluppatore> collaboratori) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
