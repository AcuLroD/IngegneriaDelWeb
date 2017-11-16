/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.Implementation;

import data.model.Sviluppatore;
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
    private PreparedStatement loginUtente;

    public SocialDevelopDataLayerMysqlImpl(DataSource datasource) throws SQLException, NamingException {
        super(datasource);
    }
    
    
    @Override
    public void init() throws DataLayerException
    {
        try
        {
            super.init();
             loginUtente = connection.prepareStatement("SELECT * FROM utenti WHERE email = ? and password = ?");
        }
    }
    
     public Sviluppatore loginCheck(String email, String password) throws SQLException
    {
    
        Sviluppatore user = new SviluppatoreImpl(this);
        loginUtente.setString(1, email);
        loginUtente.setString(2, password);
        
        
        if(loginUtente.execute()){     
            user = this.selectUtenteByEmail(email);
           
        }
         return user ; 
    }
}
