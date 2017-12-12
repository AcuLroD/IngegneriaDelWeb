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
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import it.univaq.f4i.iw.framework.result.TemplateManagerException;
import it.univaq.f4i.iw.framework.result.TemplateResult;
import it.univaq.f4i.iw.framework.security.SecurityLayer;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
public class nuovoProgetto extends SocialDevelopBaseController {

  private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException, DataLayerException {
         TemplateResult res = new TemplateResult(getServletContext());
         HttpSession s = SecurityLayer.checkSession(request);
         
         if(s == null){
             response.sendRedirect("login");
         }
         
             Sviluppatore a = new SviluppatoreImpl((SocialDevelopDataLayer) request.getAttribute("datalayer"));
             a = ((SocialDevelopDataLayer)request.getAttribute("datalayer")).getSviluppatoreByEmail((String) s.getAttribute("email"));
                request.setAttribute("email", SecurityLayer.checkSession(request).getAttribute("email"));
                request.setAttribute("sviluppatore", a);
                  
         
         res.activate("nuovoProgetto.html", request, response);
    }
    
  private void crea_progetto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException, DataLayerException {
       HttpSession s = SecurityLayer.checkSession(request);
        
       Sviluppatore a = new SviluppatoreImpl((SocialDevelopDataLayer) request.getAttribute("datalayer"));
             a = ((SocialDevelopDataLayer)request.getAttribute("datalayer")).getSviluppatoreByEmail((String) s.getAttribute("email"));
               
          
                
             System.out.println("id :"+a.getId());   
        SocialDevelopDataLayer datalayer = (SocialDevelopDataLayer)request.getAttribute("datalayer");
       ProgettoImpl progetto = new ProgettoImpl(datalayer);
        progetto.setCoordinatore(a);
        progetto.setNome(request.getParameter("nome_progetto"));
        progetto.setDescrizione(request.getParameter("descrizione"));
        progetto.setId_coordinatore(a.getId());
        
        datalayer.storeProgetto(progetto);
        
        
  }

   @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {    
        try 
        {
            if(request.getParameter("crea_progetto") != null && SecurityLayer.checkSession(request) != null)
            {
                try {
                    crea_progetto(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedTemplateNameException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataLayerException ex) {
                    Logger.getLogger(nuovoProgetto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else 
                    action_default(request, response);
               
            } catch (IOException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateManagerException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataLayerException ex) {
          Logger.getLogger(nuovoProgetto.class.getName()).log(Level.SEVERE, null, ex);
      }
           
        }    
}
