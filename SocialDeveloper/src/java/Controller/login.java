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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eugenio
 */
public class login extends SocialDevelopBaseController {

     private void action_error(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, TemplateManagerException {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }
    
    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException {
         TemplateResult res = new TemplateResult(getServletContext());
         res.activate("login.html", request, response);
    }
    
    
    private void action_login(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, TemplateManagerException, SQLException
    {
        if(request.getParameter("email") != null && !(request.getParameter("email").isEmpty()) && request.getParameter("password") != null && !(request.getParameter("password").isEmpty()))
        {
           if(((SocialDevelopDataLayer)request.getAttribute("datalayer")).loginCheck(request.getParameter("email"), request.getParameter("password")))
           {
           
                SecurityLayer.createSession(request, request.getParameter("email"));
               // SecurityLayer.createSession(request, request.getParameter("id"));
                response.sendRedirect("index");
           }
           else
           {
               response.sendRedirect("login");
           }
        }
        else
        {
            action_error(request, response);
        }
    }
     
     
     
     @Override
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
         
           try
         {
         if(request.getParameter("login") != null && SecurityLayer.checkSession(request) == null)
         {
             try {
                 action_login(request, response);
             } catch (ParseException ex) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             } catch (UnsupportedEncodingException ex) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             } catch (MalformedTemplateNameException ex) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             } catch (TemplateException ex) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         else
         {
         try
         {
         action_default(request, response);
         }
         catch (ParseException | UnsupportedEncodingException | MalformedTemplateNameException ex)
         {
         Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
         }catch (IOException | TemplateManagerException ex)
         {
             try {
                 request.setAttribute("exception", ex);
                 action_error(request, response);
             }
             catch (IOException ex1) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex1);
             } catch (TemplateException ex1) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex1);
             } catch (TemplateManagerException ex1) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex1);
             }
         }

    }
    
    
    
    
    
    
    
  
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
