/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

/**
 *
 * @author Kva
 */

import KQT.QuoterKQT;
import Entity.Quoter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RunServlet", urlPatterns = ("/activate"))
public class RunServlet extends HttpServlet{
    @EJB
    private QuoterKQT quoterKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html/charset=UTF-8");
        HttpSession session = hsrqst.getSession(true);
        String password = hsrqst.getParameter("pass");
        PrintWriter out = hsrsp.getWriter();
        try{
            Quoter quoter = quoterKQT.findByPassword(password);
            if(quoter == null){
                out.println("invalid pass");
                return;
            }else{
                session.setAttribute("parameter", quoter.getId());
                session.setAttribute("QName", quoter.getQname());
                hsrsp.sendRedirect("editPassword.jsp?password=" + password);
                return;
            }
        }finally{
            out.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        processRequest(hsrqst, hsrsp);
    }
    
    @Override
    protected void doPost(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        processRequest(hsrqst, hsrsp);
    }
    
    @Override
    public String getServletInfo(){
        return "Short Description";
    }
}
