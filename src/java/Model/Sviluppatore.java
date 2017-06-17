/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.SocialDevelopDataLayer;
import framework.DataLayerException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author LuKa
 */
public interface Sviluppatore{

    void copyFrom(Sviluppatore sviluppatore) throws DataLayerException;

    String getCognome();

    int getCurriculumFile();

    String getCurriculumString();

    GregorianCalendar getDataDiNascita();

    String getEmail();

    String getNome();

    SocialDevelopDataLayer getOwnerdatalayer();

    String getPassword();

    List<Skill> getSkills();

    String getUsername();

    boolean isDirty();

    void setCognome(String cognome);

    void setCurriculumFile(int curriculumFile);

    void setCurriculumString(String curriculumString);

    void setDataDiNascita(GregorianCalendar dataDiNascita);

    void setDirty(boolean dirty);

    void setEmail(String email);

    void setNome(String nome);

    void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer);

    void setPassword(String password);

    void setSkills(List<Skill> skills);

    void setUsername(String username);
    
}
