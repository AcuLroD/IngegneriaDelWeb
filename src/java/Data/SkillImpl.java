/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Skill;

/**
 *
 * @author LuKa
 */

public class SkillImpl implements Skill {
    
    private int id_skill;
    private String nome;
    protected SocialDevelopDataLayer ownerdatalayer;
    protected boolean dirty;
    
    public SkillImpl(SocialDevelopDataLayer ownerdatalayer) {
        this.ownerdatalayer = ownerdatalayer;
        id_skill = 0;
        nome = "";
        dirty = false;
    }

    @Override
    public int getId_skill() {
        return id_skill;
    }

    @Override
    public void setId_skill(int id_skill) {
        this.id_skill = id_skill;
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
    public SocialDevelopDataLayer getOwnerdatalayer() {
        return ownerdatalayer;
    }

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
    
    
    
}
