/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.model;

import data.model.File;
import data.model.Messaggio;
import data.model.Partecipazione;
import data.model.Progetto;
import data.model.Skill;
import data.model.Sviluppatore;
import data.model.Task;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eugenio
 */
public interface SocialDevelopDataLayer {

    /*
    @Override
    public File creaFile()
    {
    return new FileImpl(this);
    }
    @Override
    public File creaFile(ResultSet rs) throws DataLayerException
    {
    try {
    FileImpl a = new FileImpl(this);
    a.setId(rs.getInt("id"));
    a.setNome(rs.getString("nome"));
    a.setLocalfile(rs.getString("localfile"));
    a.setSize(rs.getInt("size"));
    return a;
    }
    catch (SQLException ex)
    {
    throw new DataLayerException("Unable to create file object form ResultSet", ex);
    }
    }
     */
    Messaggio creaMessaggio();

    Messaggio creaMessaggio(ResultSet rs) throws DataLayerException;

    Partecipazione creaPartecipazione();

    Partecipazione creaPartecipazione(ResultSet rs) throws DataLayerException;

    Progetto creaProgetto();

    Progetto creaProgetto(ResultSet rs) throws DataLayerException;

    Skill creaSkill();

    Skill creaSkill(ResultSet rs) throws DataLayerException;

    Sviluppatore creaSviluppatore();

    Sviluppatore creaSviluppatore(ResultSet rs) throws DataLayerException;

    Task creaTask();

    Task creaTask(ResultSet rs) throws DataLayerException;

    /*
    @Override
    public void storeMessage(Messaggio messaggio) throws DataLayerException {
    int key = messaggio.getId();
    try {
    if (key > 0) { //update
    //non facciamo nulla se l'oggetto non ha subito modifiche
    //do not store the object if it was not modified
    uMessaggio.setBoolean(1, messaggio.isPrivato());
    uMessaggio.setString(2, messaggio.getTesto());
    uMessaggio.setString(3, messaggio.getTipo());
    if(messaggio.getProgetto()!=null){
    uMessaggio.setInt(4, messaggio.getProgetto().getId());
    }else{
    uMessaggio.setNull(4, java.sql.Types.INTEGER);
    }
    uMessaggio.setInt(5, messaggio.getId());
    uMessaggio.executeUpdate();
    } else { //insert
    iMessaggio.setBoolean(1, messaggio.isPrivato());
    iMessaggio.setString(2, messaggio.getTesto());
    iMessaggio.setString(3, messaggio.getTipo());
    if(messaggio.getProgetto()!=null){
    iMessaggio.setInt(4, messaggio.getProgetto().getId());
    }else{
    iMessaggio.setNull(4, java.sql.Types.INTEGER);
    }
    if(iMessaggio.getSviluppatore()!=null){
    iMessaggio.setInt(5, mes.getDeveloper().getKey());
    }else{
    iMessaggio.setNull(5, java.sql.Types.INTEGER);
    }
    if (iMessaggio.executeUpdate() == 1) {
    try (ResultSet keys = iMessaggio.getGeneratedKeys()) {
    if (keys.next()) {
    key = keys.getInt(1);
    }
    }
    }
    }
    if (key > 0) {
    messaggio.copyFrom(getMessaggio(key));
    }
    } catch (SQLException ex) {
    throw new DataLayerException("Unable to store article", ex);
    }
    }
     */
    void deleteMessaggio(Messaggio messaggio) throws DataLayerException;

    void deleteProgetto(Progetto progetto) throws DataLayerException;

    void deleteSkill(Skill skill) throws DataLayerException;
    // da correggetre

    int deleteSkillSviluppatore(int id_task, int id_sviluppatore) throws DataLayerException;

    void deleteSviluppatore(Sviluppatore sviluppatore) throws DataLayerException;

    // ???????
    /*
    public int storeFile(Part file_to_upload, File uploaded_file, String sdigest) throws DataLayerException{
    int key = 0;
    try{
    iImg.setString(1, file_to_upload.getSubmittedFileName());
    iImg.setLong(2, file_to_upload.getSize());
    iImg.setString(3, uploaded_file.getName());
    iImg.setString(4, sdigest);
    iImg.setString(5, file_to_upload.getContentType());
    //iImg.executeUpdate();
    if (iImg.executeUpdate() == 1) {
    try (ResultSet keys = iImg.getGeneratedKeys()) {
    if (keys.next()) {
    key = keys.getInt(1);
    }
    }
    }
    }catch (SQLException ex){
    throw new DataLayerException("Unable to store img", ex);
    }
    return key;
    }
     */
    int deleteSviluppatoreByTask(int id_task, int id_sviluppatore) throws DataLayerException;

    void deleteTask(Task task) throws DataLayerException;

    void deleteTasksFromProgetto(int id_progetto) throws DataLayerException;

    List<Sviluppatore> getCollaboratoreRichiestaByTask(int id_task) throws DataLayerException;

    Map<Sviluppatore, Integer> getCollaboratoriByTask(int id_task) throws DataLayerException;

    /**
     *
     * @param file
     * @return
     * @throws DataLayerException
     */
    File getFile(int file) throws DataLayerException;

    // Messaggi relativi ad un progetto
    List<Messaggio> getMessaggi(int id_progetto) throws DataLayerException;

    Messaggio getMessaggio(int id_messaggio) throws DataLayerException;

    int getNumeroTaskByIdProgetto(int id_progetto) throws DataLayerException;

    /**
     *
     * @return
     * @throws DataLayerException
     */
    List<Progetto> getProgetti() throws DataLayerException;

    List<Progetto> getProgettiByCoordinatore(int id_coordinatore) throws DataLayerException;

    /**
     *
     * @param id_progetto
     * @return
     * @throws DataLayerException
     */
    Progetto getProgetto(int id_progetto) throws DataLayerException;

    //progetti tramite ricerca
    List<Progetto> getProjects(String filtro) throws DataLayerException;

    Skill getSkill(int id_skill) throws DataLayerException;

    int getSkillByName(String nome) throws DataLayerException;

    List<Skill> getSkills() throws DataLayerException;

    Map<Skill, Integer> getSkillsBySviluppatore(int id_sviluppatore) throws DataLayerException;

    Map<Skill, Integer> getSkillsByTask(int id_task) throws DataLayerException;

    Map<Sviluppatore, Integer> getSviluppatorBySkillLimite(int id_skill, int livello, int n) throws DataLayerException;

    Sviluppatore getSviluppatore(int id_sviluppatore) throws DataLayerException;

    Sviluppatore getSviluppatoreByEmail(String email) throws DataLayerException;

    List<Integer> getSviluppatoreByEmailLike(String email) throws DataLayerException;

    Sviluppatore getSviluppatoreByMessaggio(int id_messaggio) throws DataLayerException;

    List<Sviluppatore> getSviluppatoreBySkill(int id_skill) throws DataLayerException;

    Map<Sviluppatore, Integer> getSviluppatoriBySkill(int id_skill) throws DataLayerException;

    Map<Sviluppatore, Integer> getSviluppatoriBySkill(int id_skill, int livello) throws DataLayerException;

    Task getTask(int id_task) throws DataLayerException;

    // Tasks relativi ad un progetto
    List<Task> getTasks(int id_progetto) throws DataLayerException;

    Map<Task, Integer> getTasksBySviluppatore(int id_sviluppatore) throws DataLayerException;

    Map<Task, Integer> getTasksBySviluppatoreCorrenti(int id_sviluppatore) throws DataLayerException;

    Map<Task, Integer> getTasksFinitiBySviluppatore(int id_sviluppatore) throws DataLayerException;

    int getVoto(int id_task, int id_sviluppatore) throws DataLayerException;

    void init() throws DataLayerException;

    boolean loginCheck(String email, String password) throws SQLException;

    int storeProgetto(Progetto progetto) throws DataLayerException;

    void storeSkill(Skill skill) throws DataLayerException;

    void storeSviluppatore(Sviluppatore sviluppatore) throws DataLayerException;

    int storeTask(Task task) throws DataLayerException;

   // public void creaSviluppatore(String nome, String cognome, String email, String password, Date dataDiNascita);
    
}
