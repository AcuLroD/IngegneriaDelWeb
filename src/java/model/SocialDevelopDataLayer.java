/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author luka
 */
public interface SocialDevelopDataLayer {

    void init() throws DataLayerException;

    public List<Task> getTasks(int id);

    public List<Messaggio> getMessaggi(int id);

    public Map<Skill, Integer> getSkillsByDeveloper(int id);

    public Map<Skill, Integer> getSkillsBySviluppatore(int id);

    public Map<Skill, Integer> getSkillsByTask(int id);

    public Map<Sviluppatore, Integer> getCollaboratoriByTask(int id);

    public Sviluppatore getSviluppatore(int id_sviluppatore);

    public Progetto getProgetto(int id_progetto);

    public Map<Task, Integer> getTasksBySviluppatore(int id);

    public File getFile(int id_foto);

    public Sviluppatore getCoordinatoreByTask(int id_task);

    public Task getTask(int id_task);

    public Integer getVoto(int id, int id0);
    
}
