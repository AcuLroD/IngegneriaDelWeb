package data.Implementation;

import data.model.File;
import data.model.Partecipazione;
import data.model.SocialDevelopDataLayer;
import data.model.Sviluppatore;
import data.model.Skill;
import data.model.Task;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 *
 * @author luka
 */
public class SviluppatoreImpl implements Sviluppatore 
{
    
    private int id;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String biografia;
    private GregorianCalendar dataDiNascita;
    private int id_curriculum;
    private int id_foto;
    private File fotoFile;
    private String ruolo;    // get e set da fare
    
    private Map<Skill,Integer> skills;    
    
    protected SocialDevelopDataLayer ownerdatalayer;

    
    
    public SviluppatoreImpl (SocialDevelopDataLayer ownerdatalayer) 
    {
        
        this.ownerdatalayer = ownerdatalayer;
        
        id = 0;
        email ="";
        password = "";
        nome = "";
        cognome = "";
        biografia = "";
        dataDiNascita = null;
        id_curriculum = 0;
        id_foto = 0;
        skills = null;
        
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
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
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
    public String getBiografia() {
        return biografia;
    }

    @Override
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public GregorianCalendar getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(GregorianCalendar dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    @Override
    public int getId_curriculum() {
        return id_curriculum;
    }

    @Override
    public void setId_curriculum(int id_curriculum) {
        this.id_curriculum = id_curriculum;
    }

    @Override
    public int getId_foto() {
        return id_foto;
    }

    @Override
    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }
    
    
    @Override
    public void setSkills(Map<Skill, Integer> skills)
    {
        this.skills = skills;
    }
    
    
    @Override
    public Map<Skill, Integer> getSkillsBySviluppatore() throws DataLayerException
    {
        if(skills == null)
        {
            skills = ownerdatalayer.getSkillsBySviluppatore(this.id);
        }
        return skills;
    }
    
     
    @Override
    public void addSkill(Skill skill, int livello){
        this.skills.put(skill,livello);
    }
    
    
    @Override
    public void removeSkill(Skill skill)
    {
        this.skills.remove(skill);
    }
    
    @Override
    public void modSkillLevel(Skill skill, int livello)
    {
        this.skills.put(skill, livello);
    }
    

    @Override
    public Map<Task, Integer> getTasksBySviluppatore() throws DataLayerException
    {
        return ownerdatalayer.getTasksBySviluppatore(this.id);
    } 

    @Override
    public void copyFrom(Sviluppatore sviluppatore) throws DataLayerException {
        id = sviluppatore.getId();
        nome = sviluppatore.getNome();
        cognome = sviluppatore.getCognome();
        email = sviluppatore.getEmail();
        password = sviluppatore.getPassword();
        dataDiNascita = sviluppatore.getDataDiNascita();
        biografia = sviluppatore.getBiografia();
        id_curriculum = sviluppatore.getId_curriculum();
        id_foto = sviluppatore.getId_foto();
    }

    @Override
    public File getFotoFile() throws DataLayerException 
    {
        if(id_foto>0 && fotoFile==null){
            this.fotoFile = ownerdatalayer.getFile(id_foto);
        }
        return fotoFile;
    }

    /**
     * @param fotoFile the fotoFile to set
     */
    
    @Override
    public void setFotoFile(File fotoFile) 
    {
        this.fotoFile = fotoFile;
        this.id_foto = fotoFile.getId();
    }
    
    @Override
    public boolean equals(Object o)
    {

        if (o == this) return true;
        if (!(o instanceof Sviluppatore)) 
        {
            return false;
        }

       Sviluppatore dev = (Sviluppatore) o;

        return dev.getId() == id && dev.getNome().equals(nome) &&
               dev.getCognome().equals(cognome) && dev.getPassword().equals(password) 
                && dev.getEmail().equals(email) && dev.getDataDiNascita().equals(dataDiNascita) && dev.getBiografia().equals(biografia) &&
                dev.getId_curriculum() == id_curriculum && dev.getId_foto() == id_foto;
    }

    


}