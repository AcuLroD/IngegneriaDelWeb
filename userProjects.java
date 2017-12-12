/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import data.Implementation.ProgettoImpl;
import data.Implementation.SviluppatoreImpl;
import data.model.Progetto;
import data.model.SocialDevelopDataLayer;
import data.model.Sviluppatore;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import it.univaq.f4i.iw.framework.result.TemplateManagerException;
import it.univaq.f4i.iw.framework.result.TemplateResult;
import it.univaq.f4i.iw.framework.security.SecurityLayer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eugenio
 */
public class userProjects extends SocialDevelopBaseController {

   private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException, DataLayerException {
         TemplateResult res = new TemplateResult(getServletContext());
          
         HttpSession s = request.getSession(true);
          
          Sviluppatore a = new SviluppatoreImpl((SocialDevelopDataLayer) request.getAttribute("datalayer"));
             a = ((SocialDevelopDataLayer)request.getAttribute("datalayer")).getSviluppatoreByEmail((String) s.getAttribute("email"));
                request.setAttribute("email", SecurityLayer.checkSession(request).getAttribute("email"));
                request.setAttribute("sviluppatore", a);
         
             int id_coordinatore = a.getId();
                    
          if(s == null){
              response.sendRedirect("login");
          }
          
                
                List<Progetto> p = ((SocialDevelopDataLayer)request.getAttribute("datalayer")).getProgettiByCoordinatore(id_coordinatore);
                request.setAttribute("proge", p);
                
                
                
                
        System.out.println(id_coordinatore);
        
                    
               request.setAttribute("nome", p);
         
            res.activate("userProjects.html", request, response);
    }
    
   private void progetti_utente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException {
         HttpSession s = request.getSession(true);
         
         
   }
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
            {
        
        
            try {
                if(request.getParameter("modificaProfilo") != null){
                    progetti_utente(request, response);
                }else{
                    action_default(request, response);
                }
                
            } catch (IOException | TemplateManagerException ex) {
                Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataLayerException ex) {
           Logger.getLogger(userProjects.class.getName()).log(Level.SEVERE, null, ex);
       }
        } 
        
    }
