/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.Implementation;

import data.model.File;
import data.model.Messaggio;
import data.model.Partecipazione;
import data.model.Progetto;
import data.model.Skill;
import data.model.SocialDevelopDataLayer;
import data.model.Sviluppatore;
import data.model.Task;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import it.univaq.f4i.iw.framework.data.DataLayerMysqlImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author luka
 */
public class SocialDevelopDataLayerMysqlImpl extends DataLayerMysqlImpl implements SocialDevelopDataLayer 
{
    
    private PreparedStatement sProgetti, sSkills, loginCheck, loginSviluppatore;
    private PreparedStatement iFile, sFileById;
    private PreparedStatement iMessaggio, uMessaggio, dMessaggio;
    private PreparedStatement iProgetto, uProgetto, dProgetto;
    private PreparedStatement iSkill, uSkill, dSkill, sSkillById, sSkillByNome;
    private PreparedStatement iSviluppatore, uSviluppatore, dSviluppatore;
    private PreparedStatement iTask, uTask, dTask, dTasksFromProgetto;
    private PreparedStatement dSkillSviluppatore, dSviluppatoreByTask;
    private PreparedStatement sAdmin, sProgettoById, sProgettiByCoordinatore, sTaskById, sCountTaskByProgettoId;
    private PreparedStatement sSviluppatoreById, sSviluppatoreByEmail, sSviluppatoreByEmailRicerca, sMessaggiByProgetto, sMessaggioById;
    private PreparedStatement sSviluppatoreByMessaggio, sMessaggiPubbliciByProgetto, sProgettiByFiltro, sTasksByProgetto;
    private PreparedStatement sSkillsByTask, sTasksBySviluppatore, sTasksCorrentiBySviluppatore, sTasksFinitiBySviluppatore, sCoordinatoreByTask;
    private PreparedStatement sCollaboratoriByTask,sVoto , sCollaboratoreRichiestaByTask, sSviluppatoreBySkill, sSviluppatoreBySkillLimiteLivello, sSkillsBySviluppatore;
       
    public SocialDevelopDataLayerMysqlImpl(DataSource datasource) throws SQLException, NamingException {
        super(datasource);
    }
    
    @Override
    public void init() throws DataLayerException 
    {
        
        try 
        {
            super.init();            
            
            // insert,upload,delete oggetti
            
            iFile = connection.prepareStatement("INSERT INTO file (nome,localfile) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            sFileById = connection.prepareStatement("SELECT * FROM file WHERE id=?");
            
            iMessaggio = connection.prepareStatement("INSERT INTO messaggio (privato,testo,tipo,id_progetto,id_sviluppatore) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uMessaggio = connection.prepareStatement("UPDATE messaggio SET privato=?,testo=?,tipo=?,id_progetto=? WHERE id=?");
            dMessaggio = connection.prepareStatement("DELETE FROM messaggio WHERE id=?");
            
            
            iProgetto = connection.prepareStatement("INSERT INTO progetto (nome,descrizione,id_coordinatore) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uProgetto = connection.prepareStatement("UPDATE progetto SET nome=?,descrizione=?,id_coordinatore=? WHERE id=?");
            dProgetto = connection.prepareStatement("DELETE FROM progetto WHERE id=?");
            
            
            iSkill = connection.prepareStatement("INSERT INTO skill (nome) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            uSkill = connection.prepareStatement("UPDATE skill SET nome=? WHERE id=?");
            dSkill = connection.prepareStatement("DELETE FROM skill WHERE id=?");
            
            iSviluppatore = connection.prepareStatement("INSERT INTO sviluppatore(email,password,nome,cognome,biografia,id_curriculum,id_foto) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uSviluppatore = connection.prepareStatement("UPDATE sviluppatore SET email=?,nome=?,cognome=?,biografia=?, id_curriculum=?,id_foto=? WHERE id=?");
            dSviluppatore = connection.prepareStatement("DELETE FROM sviluppatore WHERE id=?");
            
            
            iTask = connection.prepareStatement("INSERT INTO task (nome,descrizione,stato,dataInizio,dataFine,numCollaboratori,id_progetto) VALUES(?,?,?,?,?,?,?)");
            uTask = connection.prepareStatement("UPDATE task SET nome=?,descrizione=?,stato=?,dataInizio=?,dataFine=?,numCollaboratori=?,id_progetto=? WHERE id=?");
            dTask = connection.prepareStatement("DELETE FROM task WHERE id=?");
            dTasksFromProgetto = connection.prepareStatement("DELETE FROM task WHERE id_progetto=?");
            dSviluppatoreByTask= connection.prepareStatement("DELETE FROM partecipazione WHERE id_task=? AND id_sviluppatore=?");
            
        //    iSkillSviluppatore = connection.prepareStatement("INSERT INTO skillsviluppatore (is_skill, id_sviluppatore, livello) VALUES (?,?,?)")
            dSkillSviluppatore = connection.prepareStatement("DELETE FROM skillsviluppatore WHERE id_skill=? AND id_sviluppatore=?");
            
            
            // precompilazione delle query
            
            sAdmin = connection.prepareStatement("SELECT id from sviluppatore WHERE ruolo='amministratore' ");
            sProgettoById = connection.prepareStatement("SELECT * FROM progetto WHERE id=?");
            sProgettiByCoordinatore = connection.prepareStatement("SELECT id from progetto WHERE id_coordinatore=?");
            sSkills = connection.prepareStatement("SELECT * FROM skill");
            sTaskById = connection.prepareStatement("SELECT * FROM task WHERE id=?");
            sCountTaskByProgettoId =connection.prepareStatement("SELECT COUNT(*) AS n FROM task WHERE id_progetto=?");
            sSviluppatoreById = connection.prepareStatement("SELECT * FROM sviluppatore WHERE id=?");
            sSviluppatoreByEmail = connection.prepareStatement("SELECT * FROM sviluppatore WHERE email = ? ");
            sSviluppatoreByEmailRicerca = connection.prepareStatement("SELECT sviluppatore.id FROM sviluppatore WHERE email LIKE ?");
            sMessaggiByProgetto = connection.prepareStatement("SELECT id FROM messaggio WHERE id_progetto=? ORDER BY tipo");
            sMessaggioById = connection.prepareStatement("SELECT * FROM messaggio WHERE id=?");
            sSviluppatoreByMessaggio = connection.prepareStatement("SELECT m.id_sviluppatore FROM messaggio AS m WHERE m.id=?");
            sMessaggiPubbliciByProgetto= connection.prepareStatement("SELECT id FROM messaggio WHERE id_progetto=? and privato=0 ORDER BY tipo");
            sProgetti = connection.prepareStatement("SELECT id FROM progetto");
            sProgettiByFiltro = connection.prepareStatement("SELECT id FROM progetto WHERE "
                                                            + "(nome LIKE ? or descrizione LIKE ?)");
            sTasksByProgetto = connection.prepareStatement("SELECT task.id FROM task WHERE id_progetto=?");
            sSkillById = connection.prepareStatement("SELECT * FROM skill WHERE id=?");
            sSkillByNome = connection.prepareStatement("SELECT id FROM skill WHERE nome=?");
            sSkillsByTask = connection.prepareStatement("SELECT skill.id,SkillTask.competenzaMinima FROM skill INNER JOIN SkillTask ON"
                                                        + "(skill.id = SkillTask.id_skill) WHERE SkillTask.id_task=?");
            sTasksBySviluppatore = connection.prepareStatement("SELECT id_task FROM partecipazione WHERE id_sviluppatore=? AND stato>0");  // se 0 la richiesta non è in attesa
            sTasksCorrentiBySviluppatore = connection.prepareStatement("SELECT id_task FROM partecipazione WHERE id_sviluppatore=? AND stato=1"); // in corso
            sTasksFinitiBySviluppatore = connection.prepareStatement("SELECT id_task,voto FROM partecipazione WHERE id_sviluppatore=? AND stato=2"); // finiti
            sCollaboratoriByTask = connection.prepareStatement("SELECT * FROM partecipazione WHERE id_task=? AND stato>=1");
            sCollaboratoreRichiestaByTask = connection.prepareStatement("SELECT id_sviluppatore FROM partecipazione WHERE id_task=? AND stato=0");
            sSviluppatoreBySkill = connection.prepareStatement("SELECT sviluppatore.*, SkillSviluppatore.livello FROM sviluppatore INNER JOIN SkillSviluppatore "
                                                            + "ON(sviluppatore.id = SkillSviluppatore.id_sviluppatore) WHERE SkillSviluppatore.id_skill=?");
            sSviluppatoreBySkillLimiteLivello = connection.prepareStatement("SELECT sviluppatore.*, SkillSviluppatore.livello FROM sviluppatore INNER JOIN "
                                                                            + "SkillSviluppatore ON (sviluppatore.id = SkillSviluppatore.id_sviluppatore)"
                                                                            + "WHERE SkillSviluppatore.id_skill=? AND SkillSviluppatore.livello>='n' ");
            sSkillsBySviluppatore = connection.prepareStatement("SELECT id_skill, livello FROM SkillSviluppatore WHERE id_sviluppatore=?");
            sVoto = connection.prepareStatement("SELECT vote FROM task_has_developer WHERE task_ID=? "
                                                                    + "AND developer_ID=?");
            
            loginSviluppatore = connection.prepareStatement("SELECT * FROM sviluppatore WHERE email = ? and password = ?");
        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SocialDevelopDataLayerMysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
                    
    }
    
    
    @Override
    public Progetto creaProgetto()
    {
    return new ProgettoImpl(this);
    }
    
    @Override
    public Progetto creaProgetto(ResultSet rs) throws DataLayerException
    {
        try 
        {
            ProgettoImpl a = new ProgettoImpl(this);
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setDescrizione(rs.getString("descrizione"));
            a.setId_coordinatore(rs.getInt("id_coordinatore"));
            
            return a;
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to create project object form ResultSet", ex);
}
    }
    
  
    
    @Override
    public Task creaTask()
    {
        return new TaskImpl(this);
    }
    
    @Override
    public Task creaTask(ResultSet rs) throws DataLayerException
    {
        try 
        {
            TaskImpl a = new TaskImpl(this);
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            
            Timestamp ts = rs.getTimestamp("dataInizio");
            GregorianCalendar dataInizio = new GregorianCalendar();
            dataInizio.setTime(ts);
            a.setDataInizio(dataInizio);
            
            Timestamp ts2 = rs.getTimestamp("dataFine");
            GregorianCalendar dataFine = new GregorianCalendar();
            dataFine.setTime(ts2);
            a.setDataFine(dataFine);
            
            a.setStato(rs.getBoolean("stato"));
            a.setNumCollaboratori(rs.getInt("numCollaboratori"));
            a.setDescrizione(rs.getString("descrizione"));
            a.setId_progetto(rs.getInt("id_progetto"));
            
            return a;
        } 
        catch (SQLException ex) 
        {
            throw new DataLayerException("Unable to create task object form ResultSet", ex);
        }
    }
    
    @Override
    public Skill creaSkill() 
    {
        return new SkillImpl(this);
    }

    @Override
    public Skill creaSkill(ResultSet rs) throws DataLayerException 
    {
        try 
        {
            SkillImpl a = new SkillImpl(this);
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            
            return a;
        } 
        catch (SQLException ex)
        {
            throw new DataLayerException("Unable to create skill object form ResultSet", ex);
        }
    }
      
    @Override
    public Sviluppatore creaSviluppatore() {
        return new SviluppatoreImpl(this);
    }

    public Sviluppatore creaSviluppatore(ResultSet rs) throws DataLayerException 
    {
        try 
        {
            SviluppatoreImpl a = new SviluppatoreImpl(this);
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setCognome(rs.getString("cognome"));
            a.setEmail(rs.getString("email"));
            a.setPassword(rs.getString("password"));
            a.setBiografia(rs.getString("biografia"));
            java.sql.Date date;
            date = rs.getDate("dataDiNascita");
            if(date != null){
                GregorianCalendar dataDiNascita = new GregorianCalendar();
                dataDiNascita.setTime(date);
                a.setDataDiNascita(dataDiNascita);

            }
            else
            {
                a.setDataDiNascita(null);
            }
                
            a.setId_curriculum(rs.getInt("id_curriculum"));
            
            a.setId_foto(rs.getInt("id_foto"));
            return a;
        } 
        catch (SQLException ex) 
        {
            throw new DataLayerException("Unable to create developer object form ResultSet", ex);
        }
    }
    
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
    @Override
     public Messaggio creaMessaggio() {
        return new MessaggioImpl(this);
    }

    @Override
    public Messaggio creaMessaggio(ResultSet rs) throws DataLayerException 
    {
        try 
        {
            MessaggioImpl a = new MessaggioImpl(this);
            a.setId(rs.getInt("id"));
            a.setTesto(rs.getString("testo"));
            a.setPrivato(rs.getBoolean("privato"));
            a.setTipo(rs.getString("tipo"));
            a.setId_progetto(rs.getInt("id_progetto"));
            a.setId_sviluppatore(rs.getInt("id_sviluppatore"));
            return a;
        } 
        catch (SQLException ex) 
        {
            throw new DataLayerException("Unable to create message object form ResultSet", ex);
        }
    }
    
    @Override
    public Partecipazione creaPartecipazione() 
    {
        return new PartecipazioneImpl(this);
    }

    @Override
    public Partecipazione creaPartecipazione(ResultSet rs) throws DataLayerException 
    {
        try 
        {
            PartecipazioneImpl a = new PartecipazioneImpl(this);
            GregorianCalendar requestDate = new GregorianCalendar();
            java.sql.Date date;
            date = rs.getDate("data");
            requestDate.setTime(date);
            a.setData(requestDate);
            a.setStato(rs.getInt("stato"));
            a.setId_collaboratore(rs.getInt("id_collaboratore"));
            a.setId_task(rs.getInt(("id_task")));
            a.setId_sender(rs.getInt("id_sender"));
            return a;
        }
        catch (SQLException ex) 
        {
            throw new DataLayerException("Unable to create collaborationRequest object form ResultSet", ex);
        }
    }
    
    /**
     *
     * @param id_progetto
     * @return
     * @throws DataLayerException
     */
    
    
    
    
   
    @Override
    public Progetto getProgetto(int id_progetto) throws DataLayerException
    {
        try {
            sProgettoById.setInt(1, id_progetto); //setta primo parametro query a project_key
            try (ResultSet rs = sProgettoById.executeQuery()) {
                if (rs.next()) {
                    
                    return creaProgetto(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load progetto by id", ex);
        }
        return null;
}
    
    
    @Override
    public int getNumeroTaskByIdProgetto(int id_progetto) throws DataLayerException
    {
        try 
        {
            sCountTaskByProgettoId.setInt(1, id_progetto); //setta primo parametro query a project_key
            try (ResultSet rs = sCountTaskByProgettoId.executeQuery())
            {
                if (rs.next()) 
                {
                    return rs.getInt('n') ;
                }
            }
        } 
        catch (SQLException ex)
        {
            throw new DataLayerException("Unable to load number of task by project key", ex);
        }
        return 0;
    }
    
    /**
     *
     * @return
     * @throws DataLayerException
     */
    
    @Override
    public List<Progetto> getProgetti() throws DataLayerException {         // ritorna la lista dei progetti
        List<Progetto> result = new ArrayList();

        try (ResultSet rs = sProgetti.executeQuery()) {
            while (rs.next()) {
                result.add((Progetto) getProgetto(rs.getInt("id")));
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load articles", ex);
        }
        return result;
    }
    
    
    @Override
     public List<Progetto> getProgettiByCoordinatore(int id_coordinatore) throws DataLayerException
     {
         List<Progetto> result = new ArrayList();
         try
         {
             sProgettiByCoordinatore.setInt(1,id_coordinatore);
         
            try (ResultSet rs = sProgettiByCoordinatore.executeQuery()) 
            {
                while (rs.next()) 
                {
                    result.add((Progetto) getProgetto(rs.getInt("id")));
                }
            }
         }
         catch (SQLException ex) 
        {
            throw new DataLayerException("Unable to load projects by coordinatore id", ex);
        }
        return result; //restituisce in result tutti gli oggetti Project di cui il coordinatore è quello indicato
    }
     
    
     
    //progetti tramite ricerca 
     
    @Override
    public List<Progetto> getProjects(String filtro) throws DataLayerException {
        List<Progetto> result = new ArrayList();
        try {
            sProgettiByFiltro.setString(1, "%" + filtro + "%"); 
            sProgettiByFiltro.setString(2,  "%" + filtro + "%"); 

            try (ResultSet rs = sProgettiByFiltro.executeQuery()) {
                while (rs.next()) {
                    result.add((Progetto) getProgetto(rs.getInt("id")));
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load projects", ex);
        }
        return result; //restituisce in result tutti gli oggetti Progetto esistenti
    }
    
    
    
    
    // Tasks relativi ad un progetto

    @Override
    public List<Task> getTasks(int id_progetto) throws DataLayerException {
        List<Task> result = new ArrayList();
         try {
            sTasksByProgetto.setInt(1, id_progetto);
            try (ResultSet rs = sTasksByProgetto.executeQuery()) {
                while (rs.next()) {
                    result.add((Task) getTask(rs.getInt("id")));

                }
            } 
         }catch (SQLException ex) {
                throw new DataLayerException("Unable to load tasks by project", ex);
            }
        return result; 
    }
    
    
    // Messaggi relativi ad un progetto
   
    @Override
    public List<Messaggio> getMessaggi(int id_progetto) throws DataLayerException {
        List<Messaggio> result = new ArrayList();
        try {
            sMessaggiByProgetto.setInt(1, id_progetto);
            try (ResultSet rs = sMessaggiByProgetto.executeQuery()) {
                while (rs.next()) {
                    result.add((Messaggio) getMessaggio(rs.getInt("id")));
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load messages", ex);
        }
        return result; //restituisce in result tutti gli oggetti Progetto esistenti
    }
    
    /**
     *
     * @param file
     * @return
     * @throws DataLayerException
     */
    @Override
    public File getFile(int file) throws DataLayerException {
        try {
            sFileById.setInt(1, file); //setta primo parametro query a project_key
            try (ResultSet rs = sFileById.executeQuery()) {
                if (rs.next()) {
                    //notare come utilizziamo il costrutture
                    //"helper" della classe AuthorImpl
                    //per creare rapidamente un'istanza a
                    //partire dal record corrente
                  //  return creaFile(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load file by id", ex);
        }
        return null;
    }
    
    
    @Override
    public Messaggio getMessaggio(int id_messaggio) throws DataLayerException {
         try {
            sMessaggioById.setInt(1, id_messaggio); 
            try (ResultSet rs = sMessaggioById.executeQuery()) {
                if (rs.next()) {
                    return creaMessaggio(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load message by ID", ex);
        }
        return null;
    }
    
    
    @Override
    public Sviluppatore getSviluppatoreByMessaggio(int id_messaggio) throws DataLayerException{
        try{
            sSviluppatoreByMessaggio.setInt(1, id_messaggio);
            try(ResultSet rs = sSviluppatoreByMessaggio.executeQuery()) {
                if(rs.next()){
                    return creaSviluppatore(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("unable to load sviluppatore by messaggio id", ex);
        }
        return null;
    }
    
    
    
    @Override
     public Skill getSkill(int id_skill) throws DataLayerException {
        try {
            sSkillById.setInt(1, id_skill); 
            try (ResultSet rs = sSkillById.executeQuery()) {
                if (rs.next()) {
                    return creaSkill(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load skill by ID", ex);
        }
        return null;
    
    }
     
    @Override
    public int getSkillByName(String nome) throws DataLayerException {
        try {
            sSkillByNome.setString(1, nome); 
            try (ResultSet rs = sSkillByNome.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID");
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load skill by nome", ex);
        }
        return 0;
    }
    
    
    @Override
    public Task getTask(int id_task) throws DataLayerException {
        try {
            sTaskById.setInt(1, id_task); 
            try (ResultSet rs = sTaskById.executeQuery()) {
                if (rs.next()) {
                    return creaTask(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load task by ID", ex);
        }
        return null;
    }
    
    
    
    @Override
    public Map<Skill, Integer> getSkillsByTask(int id_task) throws DataLayerException{
        Map<Skill,Integer> result = new HashMap<>();
        try{
            sSkillsByTask.setInt(1, id_task);
            try (ResultSet rs = sSkillsByTask.executeQuery()) {
                while (rs.next()){
                    result.put((Skill) getSkill(rs.getInt("id")), rs.getInt("level_min"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load skillsByTask", ex);
            }
        return result;
    }
    
    
    @Override
    public Map<Task, Integer> getTasksBySviluppatore(int id_sviluppatore) throws DataLayerException{
        Map<Task, Integer> result = new HashMap<>();
        try{
            sTasksBySviluppatore.setInt(1, id_sviluppatore);
            try(ResultSet rs = sTasksBySviluppatore.executeQuery()) {
                while (rs.next()){
                   result.put((Task) getTask(rs.getInt("id_task")), rs.getInt("voto"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load taskByDev", ex);
            }
        return result;
    }
    
    
    @Override
    public Map<Task, Integer> getTasksBySviluppatoreCorrenti(int id_sviluppatore) throws DataLayerException{
        Map<Task, Integer> result = new HashMap<>();
        try{
            sTasksCorrentiBySviluppatore.setInt(1, id_sviluppatore);
            try(ResultSet rs = sTasksCorrentiBySviluppatore.executeQuery()) {
                while (rs.next()){
                   result.put((Task) getTask(rs.getInt("id_task")), rs.getInt("voto"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load taskBySviluppatore", ex);
            }
        return result;
    }
    
    
    @Override
    public Map<Task, Integer> getTasksFinitiBySviluppatore(int id_sviluppatore) throws DataLayerException{
        Map<Task, Integer> result = new HashMap<>();
        try{
            sTasksFinitiBySviluppatore.setInt(1, id_sviluppatore);
            try(ResultSet rs = sTasksFinitiBySviluppatore.executeQuery()) {
                while (rs.next()){
                   result.put((Task) getTask(rs.getInt("id_task")), rs.getInt("voto"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load taskByDev", ex);
            }
        return result;
    }
    
    
    @Override
    public Sviluppatore getSviluppatore(int id_sviluppatore) throws DataLayerException {
        try {
            sSviluppatoreById.setInt(1, id_sviluppatore); 
            try (ResultSet rs = sSviluppatoreById.executeQuery()) {
                if (rs.next()) {
                    return creaSviluppatore(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load sviluppatore by id", ex);
        }
        return null;
    }
    
    
    
    @Override
    public Sviluppatore getSviluppatoreByEmail(String email) throws DataLayerException{
        
        try{
            System.out.println(email);
           sSviluppatoreByEmail.setString(1, email);
            try(
                ResultSet rs = sSviluppatoreByEmail.executeQuery()){
                  System.out.println("sono fuori if");
                    if(rs.next())
                    {
                      System.out.println("sono dentro if");
                        SviluppatoreImpl a = new SviluppatoreImpl(this);
                        a.setId(rs.getInt("id"));
                        a.setNome(rs.getString("nome"));
                        a.setCognome(rs.getString("cognome"));
                        a.setEmail(rs.getString("email"));
                        a.setPassword(rs.getString("password"));
                        a.setBiografia(rs.getString("biografia"));
                        java.sql.Date date;
           /* date = rs.getDate("dataDiNascita");
            if(date != null){
                GregorianCalendar dataDiNascita = new GregorianCalendar();
                dataDiNascita.setTime(date);
                a.setDataDiNascita(dataDiNascita);

            }
            else
            {
                a.setDataDiNascita(null);
            }
               */ 
           // a.setId_curriculum(rs.getInt("id_curriculum"));
            
            //a.setId_foto(rs.getInt("id_foto"));
            return a;
                    }
                  
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load developer", ex);
       }   
        return null;
    }
    
    
    
    
    
    
    
    @Override
    public List<Skill> getSkills() throws DataLayerException{
        List<Skill> result = new ArrayList();
        try {
            try (ResultSet rs = sSkills.executeQuery()) {
                while (rs.next()) {
                    result.add((Skill) getSkill(rs.getInt("id")));
                }
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to load skills", ex);
        }
        return result; 
    }
    
    
    @Override
    public Map<Sviluppatore,Integer> getCollaboratoriByTask(int id_task) throws DataLayerException{
         Map<Sviluppatore, Integer> result = new HashMap<>();
        try{
            sCollaboratoriByTask.setInt(1, id_task);
            try(ResultSet rs = sCollaboratoriByTask.executeQuery()) {
                while (rs.next()){
                   result.put((Sviluppatore) getSviluppatore(rs.getInt("id_sviluppatore")), rs.getInt("voto"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load collaboratoriByTask", ex);
            }
        return result;
    }
    
    
    @Override
     public List<Sviluppatore> getCollaboratoreRichiestaByTask(int id_task) throws DataLayerException{
        List<Sviluppatore> result = new ArrayList();
         try{
             sCollaboratoreRichiestaByTask.setInt(1, id_task);
            try(ResultSet rs = sCollaboratoreRichiestaByTask.executeQuery()) {
                while (rs.next()){
                   result.add((Sviluppatore) getSviluppatore(rs.getInt("id_sviluppatore")));
                }
            }
        }catch (SQLException ex) {
            throw new DataLayerException("Unable to load collaboratorRequestsByTask", ex);
            }
        return result;
    }
     
     
     
    @Override
   public Map<Sviluppatore,Integer> getSviluppatoriBySkill(int id_skill) throws DataLayerException{
        Map<Sviluppatore,Integer> result = new HashMap<>();
        try{
            sSviluppatoreBySkill.setInt(1, id_skill);
            try(ResultSet rs = sSviluppatoreBySkill.executeQuery()) {
                while (rs.next()){
                   result.put((Sviluppatore) getSviluppatore(rs.getInt("id")), rs.getInt("livello"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load developerBySkill", ex);
            }
        return result;
    }
   
   
    @Override
   public Map<Sviluppatore,Integer> getSviluppatoriBySkill(int id_skill, int livello) throws DataLayerException{
        Map<Sviluppatore,Integer> result = new HashMap<>();
        try{
            sSviluppatoreBySkillLimiteLivello.setInt(1, id_skill);
            sSviluppatoreBySkillLimiteLivello.setInt(2, livello);
            
            try(ResultSet rs = sSviluppatoreBySkillLimiteLivello.executeQuery()) {
                while (rs.next()){
                   result.put((Sviluppatore) getSviluppatore(rs.getInt("id")), rs.getInt("livello"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load developerBySkill", ex);
            }
        return result;
    }
   
   
    @Override
   public Map<Skill,Integer> getSkillsBySviluppatore(int id_sviluppatore) throws DataLayerException{
        Map<Skill,Integer> result = new HashMap<>();
        try{
            sSkillsBySviluppatore.setInt(1, id_sviluppatore);
            
            try(ResultSet rs = sSkillsBySviluppatore.executeQuery()) {
                while (rs.next()){
                   result.put((Skill) getSkill(rs.getInt("id_skill")), rs.getInt("livello"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load skillBySviluppatore", ex);
            }
        return result;
    }
   
   
    @Override
    public int getVoto(int id_task, int id_sviluppatore) throws DataLayerException{
        try{
            sVoto.setInt(1,id_task);
            sVoto.setInt(2, id_sviluppatore);
            try(ResultSet rs = sVoto.executeQuery()){
                if(rs.next()){
                    return rs.getInt("voto");
                }
            }
        }catch (SQLException ex) {
            throw new DataLayerException("Unable to load vote by developer key and task key ", ex);
        }
        return -1;
    }
    
    
    

    @Override
    public int storeProgetto(Progetto progetto) throws DataLayerException {
        int key = progetto.getId();
        try {
            if (key > 0) { 
                
                uProgetto.setString(1, progetto.getNome());
                uProgetto.setString(2, progetto.getDescrizione());
                if (progetto.getCoordinatore() != null) {
                    uProgetto.setInt(3, progetto.getCoordinatore().getId());
                } else {
                    uProgetto.setNull(3, java.sql.Types.INTEGER);
                }
                uProgetto.setInt(4, progetto.getId());
                uProgetto.executeUpdate();
            } else { //insert
                iProgetto.setString(1, progetto.getNome());
                iProgetto.setString(2, progetto.getDescrizione());
                if (progetto.getCoordinatore() != null) {
                    iProgetto.setInt(3, progetto.getCoordinatore().getId());
                } else {
                    iProgetto.setNull(3, java.sql.Types.INTEGER);
                }
                
                if (iProgetto.executeUpdate() == 1) {
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    try (ResultSet keys = iProgetto.getGeneratedKeys()) {
                        //il valore restituito è un ResultSet con un record
                        //per ciascuna chiave generata (uno solo nel nostro caso)
                        
                        if (keys.next()) {
                            //i campi del record sono le componenti della chiave
                            //(nel nostro caso, un solo intero)
                            key = keys.getInt(1);
                        }
                    }
                }
            }
            //restituiamo l'oggetto appena inserito RICARICATO
            //dal database tramite le API del modello. In tal
            //modo terremo conto di ogni modifica apportata
            //durante la fase di inserimento
           
            if (key > 0) {
                progetto.copyFrom(getProgetto(key));
            }
            
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to store article", ex);
        }
        return key;
    }
    

    @Override
    public void deleteProgetto(Progetto progetto) throws DataLayerException{
        int key = progetto.getId();
        try{
            dProgetto.setInt(1, key);
            dProgetto.executeUpdate();
            }catch (SQLException ex) {
            throw new DataLayerException("Unable to store article", ex);
        }
    }
    

  
    @Override
    public void storeSviluppatore(Sviluppatore sviluppatore) throws DataLayerException {
        int key = sviluppatore.getId();
        try {

            if (key > 0) {
                
                uSviluppatore.setString(1, sviluppatore.getEmail());
               // uSviluppatore.setString(2, sviluppatore.getPassword());
                uSviluppatore.setString(2, sviluppatore.getNome());
                uSviluppatore.setString(3, sviluppatore.getCognome());
                uSviluppatore.setString(4, sviluppatore.getBiografia());
                /*if(sviluppatore.getId_curriculum() != 0) {
                    uSviluppatore.setInt(4, sviluppatore.getId_curriculum());
                } else {
                    uSviluppatore.setNull(4, java.sql.Types.INTEGER);
                }
                if(sviluppatore.getId_foto() != 0) {
                    uSviluppatore.setInt(5, sviluppatore.getId_foto());
                } else {
                    uSviluppatore.setNull(5, java.sql.Types.INTEGER);
                }*/
                //uSviluppatore.setInt(6, sviluppatore.getId());
                uSviluppatore.executeUpdate();
            } else { //insert
                System.out.println("sono fuori if");
                iSviluppatore.setString(1, sviluppatore.getEmail());
                iSviluppatore.setString(2, sviluppatore.getPassword());
                iSviluppatore.setString(3, sviluppatore.getNome());
                iSviluppatore.setString(4, sviluppatore.getCognome());
                iSviluppatore.setString(5, sviluppatore.getBiografia());
                iSviluppatore.setInt(6, sviluppatore.getId_curriculum());
                iSviluppatore.setInt(7, sviluppatore.getId_foto());
            /*    if(sviluppatore.getDataDiNascita()!= null){
                    Date sqldate = new Date(sviluppatore.getDataDiNascita().getTimeInMillis());
                    iSviluppatore.setDate(8, (java.sql.Date) sqldate);
                }else{
                    iSviluppatore.setDate(8, null);
                }
               */
                if (iSviluppatore.executeUpdate() == 1) {
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    try (ResultSet keys = iSviluppatore.getGeneratedKeys()) {
                        //il valore restituito è un ResultSet con un record
                        //per ciascuna chiave generata (uno solo nel nostro caso)
                        
                        if (keys.next()) {
                            //i campi del record sono le componenti della chiave
                            //(nel nostro caso, un solo intero)
                            key = keys.getInt(1);
                        }
                    }
                }
            }
            //restituiamo l'oggetto appena inserito RICARICATO
            //dal database tramite le API del modello. In tal
            //modo terremo conto di ogni modifica apportata
            //durante la fase di inserimento
           
            if (key > 0) {
                sviluppatore.copyFrom(getSviluppatore(key));
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to store sviluppatore", ex);
        }
    }
    
   
    @Override
    public void deleteSviluppatore(Sviluppatore sviluppatore) throws DataLayerException{
        int key = sviluppatore.getId();
        try{
            dSviluppatore.setInt(1, key);
            dSviluppatore.executeUpdate();
            }catch (SQLException ex) {
            throw new DataLayerException("Unable to store article", ex);
        }
    }
    

    @Override
    public int storeTask(Task task) throws DataLayerException {
        int key = task.getId();
        try {
            if (key > 0) { //update
                //non facciamo nulla se l'oggetto non ha subito modifiche
                //do not store the object if it was not modified
                
                uTask.setString(1, task.getNome());
                uTask.setString(2, task.getDescrizione());
                uTask.setBoolean(3, task.isStato());
                Date sqldate1 = new Date(task.getDataInizio().getTimeInMillis());
                uTask.setDate(4, (java.sql.Date) sqldate1);
                Date sqldate2 = new Date(task.getDataFine().getTimeInMillis());
                uTask.setDate(5, (java.sql.Date) sqldate2);
                uTask.setInt(6, task.getNumCollaboratori());
               
                
                if (task.getProgetto() != null) {
                    uTask.setInt(7, task.getProgetto().getId());
                } else {
                    uTask.setNull(7, java.sql.Types.INTEGER);
                }
                uTask.setInt(8, task.getId());
                uTask.executeUpdate();
            } else { //insert
                iTask.setString(1, task.getNome());
                iTask.setString(2, task.getDescrizione());
                iTask.setBoolean(3, task.isStato()); 
                Date sqldate1 = new Date(task.getDataInizio().getTimeInMillis());
                iTask.setDate(4, (java.sql.Date) sqldate1);
                Date sqldate2 = new Date(task.getDataFine().getTimeInMillis());
                iTask.setDate(5, (java.sql.Date) sqldate2);
                iTask.setInt(6, task.getNumCollaboratori());                
                if (task.getProgetto() != null) {
                    iTask.setInt(7, task.getProgetto().getId());
                } else {
                    iTask.setNull(7, java.sql.Types.INTEGER);
                }
                
               
                if (iTask.executeUpdate() == 1) {
                    try (ResultSet keys = iTask.getGeneratedKeys()) {
                       
                        if (keys.next()) {
                           key = keys.getInt(1);
                        }
                    }
                }
            }
           
            if (key > 0) {
                task.copyFrom(getTask(key));
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to store task", ex);
        }
        return key;
    }
    
    @Override
    public void deleteTask(Task task) throws DataLayerException{
        int key = task.getId();
        try{
            dTask.setInt(1, key);
            dTask.executeUpdate();
            }catch (SQLException ex) {
            throw new DataLayerException("Unable to store article", ex);
        }
    }
    
    
    @Override
    public void deleteTasksFromProgetto(int id_progetto) throws DataLayerException{
        
        try{
            dTasksFromProgetto.setInt(1, id_progetto);
            dTasksFromProgetto.executeUpdate();
            }catch (SQLException ex) {
            throw new DataLayerException("Unable to store article", ex);
        }
    }
    
    
    @Override
    public void storeSkill(Skill skill) throws DataLayerException {
        int key = skill.getId();
        try {
            if (key > 0) { //update
                
                uSkill.setString(1, skill.getNome());
                
                uSkill.setInt(2, skill.getId());
                uSkill.executeUpdate();
            } else { //insert
                iSkill.setString(1, skill.getNome());
                
                if (iSkill.executeUpdate() == 1) {
                    try (ResultSet keys = iSkill.getGeneratedKeys()) {
                        if (keys.next()) {
                           key = keys.getInt(1);
                        }
                    }
                }
            }
           
            if (key > 0) {
               //getSkill(key).setParentKey(-1);
                skill.copyFrom(getSkill(key));
            }
        } catch (SQLException ex) {
            throw new DataLayerException("Unable to store article", ex);
        }
    }
    
    @Override
    public void deleteSkill(Skill skill) throws DataLayerException{
        int key = skill.getId();
        try{
            dSkill.setInt(1, key);
            dSkill.executeUpdate();
            }catch (SQLException ex) {
            throw new DataLayerException("Unable to store article", ex);
        }
    }
    
// da correggetre
    
    
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
    @Override
    public void deleteMessaggio(Messaggio messaggio) throws DataLayerException{
        int key = messaggio.getId();
        try{
            dMessaggio.setInt(1, key);
            dMessaggio.executeUpdate();
            
            }catch (SQLException ex) {
            throw new DataLayerException("Unable to store article", ex);
        }
    }
    
    
    
    
    
    

     
     
     
     
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
    
   
    
    @Override
    public int deleteSviluppatoreByTask(int id_task,int id_sviluppatore) throws DataLayerException{
        try{
            dSviluppatoreByTask.setInt(1, id_task);
            dSviluppatoreByTask.setInt(2, id_sviluppatore);
            if(dSviluppatoreByTask.executeUpdate()==1){
                return 1;
            }
            }catch (SQLException ex) {
            throw new DataLayerException("Unable to delete", ex);
        }return 0;
    }
    
    
    @Override
    public int deleteSkillSviluppatore(int id_task,int id_sviluppatore) throws DataLayerException{
        try{
            dSkillSviluppatore.setInt(1, id_task);
            dSkillSviluppatore.setInt(2, id_sviluppatore);
            if(dSkillSviluppatore.executeUpdate()==1){
                return 1;
            }
            }catch (SQLException ex) {
            throw new DataLayerException("Unable to delete", ex);
        }return 0;
    }
    
    
    
    
    
    @Override
    public List<Integer> getSviluppatoreByEmailLike(String email) throws DataLayerException{
        List<Integer> result = new ArrayList();
        try{
            sSviluppatoreByEmailRicerca.setString(1, "%" + email + "%");
            try(ResultSet rs = sSviluppatoreByEmailRicerca.executeQuery()){
                while(rs.next()){
                    result.add(rs.getInt("id"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load developer", ex);
            }
        return result;
    }
    
   
    @Override
    public List<Sviluppatore> getSviluppatoreBySkill(int id_skill) throws DataLayerException{
        List<Sviluppatore> result = new ArrayList<>();
        try{
            sSviluppatoreBySkill.setInt(1, id_skill);
            
            try(ResultSet rs = sSviluppatoreBySkillLimiteLivello.executeQuery()) {
                while (rs.next()){
                   result.add((Sviluppatore) getSviluppatore(rs.getInt("id")));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load developerBySkill", ex);
            }
        return result;
    }

   
    @Override
    public Map<Sviluppatore,Integer>  getSviluppatorBySkillLimite(int id_skill, int livello,int n) throws DataLayerException{
       Map<Sviluppatore,Integer> result = new HashMap<>();
        try{
            sSviluppatoreBySkillLimiteLivello.setInt(1, id_skill);
            sSviluppatoreBySkillLimiteLivello.setInt(2, livello);
            sSviluppatoreBySkillLimiteLivello.setInt(3, n);
            try(ResultSet rs = sSviluppatoreBySkillLimiteLivello.executeQuery()) {
                while (rs.next()){
                   result.put((Sviluppatore) getSviluppatore(rs.getInt("id")), rs.getInt("livello"));
                }
            }
        }catch (SQLException ex) {
                throw new DataLayerException("Unable to load developerBySkill", ex);
            }
        return result;
    }
    
    @Override
     public boolean loginCheck(String email, String password) throws SQLException
    {
        loginSviluppatore.setString(1, email);
        loginSviluppatore.setString(2, password);
        
        
         ResultSet rs = loginSviluppatore.executeQuery() ;
         
         if(rs.next())
         {
             return true;
         }
         return false;
    }
    
    
}
