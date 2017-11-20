/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.Implementation;

import data.model.SocialDevelopDataLayer;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import it.univaq.f4i.iw.framework.data.DataLayerMysqlImpl;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author luka
 */
public class SocialDevelopDataLayerMysqlImpl extends DataLayerMysqlImpl implements SocialDevelopDataLayer 
{
    
    private PreparedStatement sProgetti, sSkills, sSviluppatorebyEmail;
    
    /* query per la ricerca */
    
    private PreparedStatement sSviluppatoreBySkill;
    
    public SocialDevelopDataLayerMysqlImpl(DataSource datasource) throws SQLException, NamingException {
        super(datasource);
    }
    
    @Override
    public void init() throws DataLayerException 
    {
        
            super.init();

            //precompiliamo tutte le query utilizzate
            sProgetti = connection.prepareStatement("SELECT * FROM Progetto");
            sSkills = connection.prepareStatement("SELECT * FROM Skill");
            sSviluppatorebyEmail = connection.prepareStatement("SELECT * FROM Sviluppatore WHERE email = ?");
            
                    
                    
    }
}
