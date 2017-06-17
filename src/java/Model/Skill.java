/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.SocialDevelopDataLayer;

/**
 *
 * @author LuKa
 */
public interface Skill {

    int getId_skill();

    String getNome();

    SocialDevelopDataLayer getOwnerdatalayer();

    boolean isDirty();

    void setDirty(boolean dirty);

    void setId_skill(int id_skill);

    void setNome(String nome);

    void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer);
    
}
