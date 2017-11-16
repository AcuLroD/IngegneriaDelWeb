package data.Implementation;

import data.model.SocialDevelopDataLayer;
import data.model.Sviluppatore;
import data.model.Partecipazione;
import java.util.Date;

/**
 *
 * @author luka
 */
public class SviluppatoreImpl implements Sviluppatore 
{
    
    private int id;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String biografia;
    private Date dataDiNascita;
    private int id_curriculum;
    private int id_foto;
    
    private Partecipazione[] partecipazioni;
    
    protected SocialDevelopDataLayer ownerdatalayer;

    
    
    public SviluppatoreImpl (SocialDevelopDataLayer ownerdatalayer) 
    {
        
        this.ownerdatalayer = ownerdatalayer;
        
        id = 0;
        username ="";
        password = "";
        nome = "";
        cognome = "";
        biografia = "";
        dataDiNascita = null;
        id_curriculum = 0;
        id_foto = 0;
        
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
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    @Override
    public void setDataDiNascita(Date dataDiNascita) {
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
    public Partecipazione[] getPartecipazioni() {
        return partecipazioni;
    }

    @Override
    public void setPartecipazioni(Partecipazione[] partecipazioni) {
        this.partecipazioni = partecipazioni;
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