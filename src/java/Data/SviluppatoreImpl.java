/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Skill;
import Model.Sviluppatore;
import framework.DataLayerException;
import java.util.GregorianCalendar;
import java.util.List;


/**
 *
 * @author LuKa
 */
public class SviluppatoreImpl implements Sviluppatore {
    
    
    private String username;
    private String nome;
    private String cognome;
    private String password;
    private String email;
    private GregorianCalendar dataDiNascita;
    private String curriculumString;
    private int curriculumFile;
    private List<Skill> skills;
    protected SocialDevelopDataLayer ownerdatalayer;
    protected boolean dirty;
    
    public SviluppatoreImpl(SocialDevelopDataLayer ownerdatalayer) {
        this.ownerdatalayer = ownerdatalayer;
        username = "";
        nome = "";
        cognome = "";
        password = "";
        email = "";
        dataDiNascita = null;
        curriculumString = "";
        curriculumFile = 0;
        skills = null;
        dirty = false;
        
        
        
}

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
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
    public String getCognome() {
        return cognome;
    }

    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public GregorianCalendar getDataDiNascita() {
        return dataDiNascita;
    }

    @Override
    public void setDataDiNascita(GregorianCalendar dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    @Override
    public String getCurriculumString() {
        return curriculumString;
    }

    @Override
    public void setCurriculumString(String curriculumString) {
        this.curriculumString = curriculumString;
    }

    @Override
    public int getCurriculumFile() {
        return curriculumFile;
    }

    @Override
    public void setCurriculumFile(int curriculumFile) {
        this.curriculumFile = curriculumFile;
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
     public void copyFrom(Sviluppatore sviluppatore) throws DataLayerException {
        username = sviluppatore.getUsername();
        nome = sviluppatore.getNome();
        cognome = sviluppatore.getCognome();
        password = sviluppatore.getPassword();
        email = sviluppatore.getEmail();
        dataDiNascita = sviluppatore.getDataDiNascita();
        curriculumFile = sviluppatore.getCurriculumFile();
        curriculumString = sviluppatore.getCurriculumString();
        this.dirty = true;
        
       
}
    
    
    
    
    
}
