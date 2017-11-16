/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.Implementation;

import data.model.Task;
import data.model.SkillTask;
import data.model.Skill;

/**
 *
 * @author luka
 */
public class SkillTaskImpl implements SkillTask
{

    private Task task;
    private Skill skill;
    private int competenzaMinima;
    
    private SocialDevelopDataLayer owndatalayer;
    
    public SkillTaskImpl(SocialDevelopDataLayer ownerdatalayer) 
    {
        this.owndatalayer = ownerdatalayer;  
        competenzaMinima = 0;
        
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
    public int getCompetenzaMinima() {
        return competenzaMinima;
    }

    @Override
    public void setCompetenzaMinima(int competenzaMinima) {
        this.competenzaMinima = competenzaMinima;
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
