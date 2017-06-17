/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.SocialDevelopDataLayer;
import Model.Progetto;
import Model.Skill;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author LuKa
 */
public interface Task {

    List<Sviluppatore> getCollaboratori();

    String getDescrizione();

    GregorianCalendar getFine();

    int getId_progetto();

    int getId_task();

    GregorianCalendar getInizio();

    String getNome();

    int getNumCollaboratori();

    SocialDevelopDataLayer getOwnerdatalayer();

    Progetto getProg();

    List<Skill> getSkills();

    boolean isDirty();

    boolean isStato();

    void setCollaboratori(List<Sviluppatore> collaboratori);

    void setDescrizione(String descrizione);

    void setDirty(boolean dirty);

    void setFine(GregorianCalendar fine);

    void setId_progetto(int id_progetto);

    void setId_task(int id_task);

    void setInizio(GregorianCalendar inizio);

    void setNome(String nome);

    void setNumCollaboratori(int numCollaboratori);

    void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer);

    void setProg(Progetto prog);

    void setSkills(List<Skill> skills);

    void setStato(boolean stato);
    
}
