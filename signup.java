/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import data.Implementation.SviluppatoreImpl;
import data.model.SocialDevelopDataLayer;
import data.model.Sviluppatore;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import it.univaq.f4i.iw.framework.result.FailureResult;
import it.univaq.f4i.iw.framework.result.TemplateManagerException;
import it.univaq.f4i.iw.framework.result.TemplateResult;
import it.univaq.f4i.iw.framework.security.SecurityLayer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eugenio
 */
public class signup extends SocialDevelopBaseController {

    private void action_error(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, TemplateManagerException
    {
        if(request.getAttribute("exception") != null)
        {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        }
        else
        {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }

    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, SQLException
    {
        TemplateResult res = new TemplateResult(getServletContext());       
        res.activate("signup.html", request, response);
    }
    
    private void action_signup(HttpServletRequest request, HttpServletResponse response) throws SQLException, java.text.ParseException, IOException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, TemplateManagerException, ServletException, DataLayerException
    {
       String email = request.getParameter("email");
       String password = request.getParameter("password");
       String nome = request.getParameter("nome");
       String cognome = request.getParameter("cognome");
      // String dataDiNascita = request.getParameter("dataDiNascita");
      
              
       SocialDevelopDataLayer datalayer = (SocialDevelopDataLayer)request.getAttribute("datalayer");
       SviluppatoreImpl sviluppatore = new SviluppatoreImpl(datalayer);
       
       sviluppatore.setNome(nome);
       sviluppatore.setCognome(cognome);
       sviluppatore.setEmail(email);
       sviluppatore.setPassword(password);
       //sviluppatore.setBiografia(biografia);
      
       /* GregorianCalendar gc = new GregorianCalendar();
        gc.setLenient(false);
        gc.set(GregorianCalendar.YEAR, Integer.valueOf(dataDiNascita.split("/")[2]));
        gc.set(GregorianCalendar.MONTH, Integer.valueOf(dataDiNascita.split("/")[1])-1); //parte da 0
        gc.set(GregorianCalendar.DATE, Integer.valueOf(dataDiNascita.split("/")[0]));
        
        sviluppatore.setDataDiNascita(gc);*/
       
       datalayer.storeSviluppatore(sviluppatore);
       
       SecurityLayer.createSession(request, request.getParameter("email"));
       
       response.sendRedirect("index");
       
    }
    

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {    
        try 
        {
            if(request.getParameter("registrati") != null && SecurityLayer.checkSession(request) == null)
            {
                try {
                    action_signup(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedTemplateNameException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataLayerException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (java.text.ParseException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else 
                    action_default(request, response);
               
            } catch (IOException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateManagerException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        }    
}
