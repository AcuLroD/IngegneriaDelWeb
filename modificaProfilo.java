/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import data.Implementation.SviluppatoreImpl;
import data.model.SocialDevelopDataLayer;
import data.model.Sviluppatore;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import it.univaq.f4i.iw.framework.result.TemplateManagerException;
import it.univaq.f4i.iw.framework.result.TemplateResult;
import it.univaq.f4i.iw.framework.security.SecurityLayer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eugenio
 */
public class modificaProfilo extends SocialDevelopBaseController {

 
    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException, DataLayerException {
         TemplateResult res = new TemplateResult(getServletContext());
         
         HttpSession s=SecurityLayer.checkSession(request);
         
         
             Sviluppatore a = new SviluppatoreImpl((SocialDevelopDataLayer) request.getAttribute("datalayer"));
             a = ((SocialDevelopDataLayer)request.getAttribute("datalayer")).getSviluppatoreByEmail((String) s.getAttribute("email"));
                request.setAttribute("email", SecurityLayer.checkSession(request).getAttribute("email"));
                request.setAttribute("sviluppatore", a);
                  
         res.activate("modificaProfilo.html", request, response);
    }
    
    private void modifica_profilo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException, DataLayerException {
        
      HttpSession s = request.getSession(true);
           
            if (s.getAttribute("email") != null) {
                SocialDevelopDataLayer datalayer = (SocialDevelopDataLayer)request.getAttribute("datalayer");
                Sviluppatore svi = datalayer.getSviluppatoreByEmail((String)s.getAttribute("email"));
                
                svi.setBiografia(request.getParameter("biografia"));
                svi.setNome(request.getParameter("nome"));
                svi.setCognome(request.getParameter("cognome"));
                svi.setEmail(request.getParameter("email"));
                
                            
                request.setAttribute("biografia", svi.getBiografia());
                request.setAttribute("nome", svi.getNome());
                request.setAttribute("cognome", svi.getCognome());
                request.setAttribute("email", svi.getEmail());
               
                request.setAttribute("datalayer", datalayer);
                
                datalayer.storeSviluppatore(svi);
                response.sendRedirect("profilo");
               }else{
                 response.sendRedirect("index");
            }
            
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
            {
        
        
            try {
                if(request.getParameter("modificaProfilo") != null){
                    modifica_profilo(request, response);
                }else{
                    action_default(request, response);
                }
                
            } catch (IOException | TemplateManagerException | DataLayerException ex) {
                Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
    }
