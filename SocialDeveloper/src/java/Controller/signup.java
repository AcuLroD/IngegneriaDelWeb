/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import data.model.SocialDevelopDataLayer;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import it.univaq.f4i.iw.framework.result.FailureResult;
import it.univaq.f4i.iw.framework.result.TemplateManagerException;
import it.univaq.f4i.iw.framework.result.TemplateResult;
import it.univaq.f4i.iw.framework.security.SecurityLayer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    
    private void action_signup(HttpServletRequest request, HttpServletResponse response) throws SQLException, java.text.ParseException, IOException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, TemplateManagerException, ServletException
    {
        if(request.getParameter("email") != null && !(request.getParameter("email").isEmpty()) && request.getParameter("nome") != null && !(request.getParameter("nome").isEmpty()) && request.getParameter("cognome") != null && !(request.getParameter("cognome").isEmpty()) && request.getParameter("dataDiNascita") != null && !(request.getParameter("dataDiNascita").isEmpty()) && request.getParameter("password") != null && !(request.getParameter("password").isEmpty()))
        {  
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataDiNascita = sdf.parse(request.getParameter("dataDiNscita"));
            ((SocialDevelopDataLayer)request.getAttribute("datalayer")).creaSviluppatore(request.getParameter("email"), request.getParameter("nome"), request.getParameter("cognome"),request.getParameter("password"), dataDiNascita);
            
            SecurityLayer.createSession(request, request.getParameter("email"));
                     
            response.sendRedirect("index");
        }
        else
        {
           action_error(request, response);
        }
    }
    

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {    
        try 
        {
            if(request.getParameter("registrati") != null && SecurityLayer.checkSession(request) == null)
            {
                action_signup(request, response);
            }
            else if(SecurityLayer.checkSession(request) == null)
            {
                try {
                    action_default(request, response);
                } catch (IOException | TemplateManagerException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                response.sendRedirect("index");
            }
        }
        catch (TemplateException | SQLException | java.text.ParseException | IOException | TemplateManagerException ex)
        {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
