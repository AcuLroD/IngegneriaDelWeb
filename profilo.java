/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SocialDevelopDataLayer;
import Model.Sviluppatore;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import it.univaq.f4i.iw.framework.result.FailureResult;
import it.univaq.f4i.iw.framework.result.TemplateManagerException;
import it.univaq.f4i.iw.framework.result.TemplateResult;
import it.univaq.f4i.iw.framework.security.SecurityLayer;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.java2d.cmm.Profile;

/**
 *
 * @author eugen
 */
public class profilo extends BaseController {
private void action_error(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, TemplateManagerException {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }

     private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, DataLayerException {
       
        
         if(SecurityLayer.checkSession(request) != null)
        {
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("page_title", request.getParameter("username") + " Profile" );
            
            Sviluppatore utente = ((SocialDevelopDataLayer)request.getAttribute("datalayer")).getSviluppatore(request.getParameter("username"));
           
            
            if(SecurityLayer.checkSession(request) != null)
            {
                request.setAttribute("email",SecurityLayer.checkSession(request).getAttribute("email"));
            }
            
            request.setAttribute("utente", utente);
            
            
            
            if(SecurityLayer.checkSession(request).getAttribute("email") == request.getParameter("usernmae"))
            {
                request.setAttribute("personal_profile","true");
            }
            
            res.activate("Profilo.html", request, response);
        }
        else
        {
            response.sendRedirect("login");
        }
    }
    
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        
        
            try
            {
                if(request.getParameter("profilo") != null && SecurityLayer.checkSession(request) == null)
                {
                    

                        action_default(request, response);
                    
                }
                    
            } catch (IOException ex) {
        Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TemplateException ex) {
        Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TemplateManagerException ex) {
        Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
    } catch (DataLayerException ex) {
        Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
