
package data.Implementation;

import data.model.TaskSkill;
import data.model.Task;
import data.model.Skill;

/**
 *
 * @author luka
 */
public class TaskSkillImpl implements TaskSkill {
    
    private int id_taskskill;
    private Task task;
    private Skill skill;
    private int livelloCompetenza;
    
    private SocialDevelopDataLayer owndatalayer;
    
    
    public TaskSkillImpl (SocialDevelopDataLayer owndatalayer)
    {
    
        this.owndatalayer = owndatalayer;
        
        task = null;
        skill = null;
        livelloCompetenza = 0;
    
    }

    @Override
    public int getId_taskskill() {
        return id_taskskill;
    }

    @Override
    public void setId_taskskill(int id_taskskill) {
        this.id_taskskill = id_taskskill;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public Skill getSkill() {
        return skill;
    }

    @Override
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public int getLivelloCompetenza() {
        return livelloCompetenza;
    }

    @Override
    public void setLivelloCompetenza(int livelloCompetenza) {
        this.livelloCompetenza = livelloCompetenza;
    }

    @Override
    public SocialDevelopDataLayer getOwndatalayer() {
        return owndatalayer;
    }

    @Override
    public void setOwndatalayer(SocialDevelopDataLayer owndatalayer) {
        this.owndatalayer = owndatalayer;
    }
    
    
    
}
