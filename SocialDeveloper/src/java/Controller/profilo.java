/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import data.model.SocialDevelopDataLayer;
import data.model.Sviluppatore;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import it.univaq.f4i.iw.framework.result.FailureResult;
import it.univaq.f4i.iw.framework.result.SplitSlashesFmkExt;
import it.univaq.f4i.iw.framework.result.TemplateManagerException;
import it.univaq.f4i.iw.framework.result.TemplateResult;
import it.univaq.f4i.iw.framework.security.SecurityLayer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
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
public class profilo extends SocialDevelopBaseController {
      
    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        }
    }
 /*   
    
    private void getImg(HttpServletRequest request, HttpServletResponse response, Developer dev) throws IOException, SQLException, DataLayerException, NamingException {
        StreamResult result = new StreamResult(getServletContext());
        
         SocialDevelopDataLayer datalayer = (SocialDevelopDataLayer) request.getAttribute("datalayer");
         if(dev.getFoto() != 0){
            Files foto_profilo = datalayer.getFile(dev.getFoto());
            request.setAttribute("foto_profilo", "extra-images/" + foto_profilo.getLocalFile());
         }else{
            request.setAttribute("foto_profilo", "extra-images/foto_profilo_default.png");             
         }
        
    }
    */
    
    
    private void action_profilo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException, SQLException, NamingException, DataLayerException 
    {
            HttpSession s = SecurityLayer.checkSession(request);
            
           
            
            if(SecurityLayer.checkSession(request)!= null)
            {
                
                if(s.getAttribute("email") != null){
                  
                    
                    
                Sviluppatore a = ((SocialDevelopDataLayer)request.getAttribute("datalayer")).getSviluppatoreByEmail(request.getParameter("email"));
                request.setAttribute("email", SecurityLayer.checkSession(request).getAttribute("email"));
                request.setAttribute("sviluppatore", a);
                
                
               // request.setAttribute("nome", sviluppatore.getNome());
              //  request.setAttribute("cognome", sviluppatore.getCognome());
                long currentTime = System.currentTimeMillis();
                Calendar now = Calendar.getInstance();
                now.setTimeInMillis(currentTime);
               /* request.setAttribute("dataDiNascita", sviluppatore.getDataDiNascita());
                request.setAttribute("biografia", sviluppatore.getBiografia());
                request.setAttribute("email", sviluppatore.getEmail());
                request.setAttribute("curriculum_pdf", sviluppatore.getId_curriculum());
                */
                TemplateResult res = new TemplateResult(getServletContext());
                res.activate("profilo.html", request, response);
                
            }
                
            } 
                else 
                {
                    response.sendRedirect("signup");
                }    
  }
    
    
    
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
            {
        
        
            try {
                action_profilo(request, response);
            } catch (IOException ex) {
                Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TemplateManagerException ex) {
                Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataLayerException ex) {
                Logger.getLogger(profilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
    }
