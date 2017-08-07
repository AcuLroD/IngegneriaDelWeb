/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SocialDevelopDataLayer;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author eugen
 */
public class login extends BaseController {
private void action_error(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, TemplateManagerException {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }

    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException {
        TemplateResult res = new TemplateResult(getServletContext());
        request.setAttribute("page_title", "SocialDevelop");
        res.activate("Login.html", request, response);
    }
    
    private void action_signin(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, UnsupportedEncodingException, MalformedTemplateNameException, TemplateException, TemplateManagerException, SQLException
    {
        if(request.getParameter("username") != null && !(request.getParameter("username").isEmpty()) && request.getParameter("password") != null && !(request.getParameter("password").isEmpty()))
        {
           if(((SocialDevelopDataLayer)request.getAttribute("datalayer")).loginCheck(request.getParameter("username"), request.getParameter("password")))
           {
           
                SecurityLayer.createSession(request, request.getParameter("username"));
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
                    

                        action_signin(request, response);
                    
                }
                    
                
                else
                {
                        
                        
                            action_default(request, response);
                        
                    
                }
            } catch (IOException ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TemplateException ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TemplateManagerException ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
