package data.Implementation;

import data.model.SocialDevelopDataLayer;
import data.model.Skill;
import it.univaq.f4i.iw.framework.data.DataLayerException;

/**
 *
 * @author luka
 */
public class SkillImpl implements Skill
{
    
    private int id;
    private String nome;
    
    protected SocialDevelopDataLayer ownerdatalayer;
  
    
    public SkillImpl (SocialDevelopDataLayer ownerdatalayer) 
    {
        
        this.ownerdatalayer = ownerdatalayer;
        
        id = 0;
        nome = "";

        
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
    public void copyFrom(Skill skill) throws DataLayerException 
    {
        id = skill.getId();
        nome = skill.getNome();
    }
    
    
    
}
