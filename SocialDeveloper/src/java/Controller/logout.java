/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import it.univaq.f4i.iw.framework.security.SecurityLayer;
import java.io.IOException;
import java.io.PrintWriter;
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
public class logout extends SocialDevelopBaseController {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        SecurityLayer.disposeSession(request);
        try 
        {
            response.sendRedirect("index");
        } 
        catch (IOException ex)
        {
            Logger.getLogger(logout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
