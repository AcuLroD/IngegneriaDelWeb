package data.Implementation;

import data.model.Partecipazione;

/**
 *
 * @author luka
 */
public class PartecipazioneImpl implements Partecipazione 
{
    
    private String nomeProgetto;
    private String nomeTask;
    private int votoTask;
    
    private SocialDevelopDataLayer ownerdatalayer;
    
    public PartecipazioneImpl (SocialDevelopDataLayer ownerdatalayer)
    {
        this.ownerdatalayer = ownerdatalayer;
        
        this.nomeProgetto = "";
        this.nomeTask = "";
        this.votoTask = 0;
    }

    @Override
    public String getNomeProgetto() {
        return nomeProgetto;
    }

    @Override
    public void setNomeProgetto(String nomeProgetto) {
        this.nomeProgetto = nomeProgetto;
    }

    @Override
    public String getNomeTask() {
        return nomeTask;
    }

    @Override
    public void setNomeTask(String nomeTask) {
        this.nomeTask = nomeTask;
    }

    @Override
    public int getVotoTask() {
        return votoTask;
    }

    @Override
    public void setVotoTask(int votoTask) {
        this.votoTask = votoTask;
    }

    public SocialDevelopDataLayer getOwnerdatalayer() {
        return ownerdatalayer;
    }

    @Override
    public void setOwndatalayer(SocialDevelopDataLayer owndatalayer) {
        this.ownerdatalayer = owndatalayer;
    }

    @Override
    public SocialDevelopDataLayer getOwndatalayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
