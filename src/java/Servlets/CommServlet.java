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

import KQT.CommKQT;
import Entity.Comm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CommServlet", urlPatterns=("/Comms"))
public class CommServlet extends HttpServlet {
    @EJB
    private CommKQT commKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
       hsrsp.setContentType("text/html/charset=UTF-8");
       ServletContext ctx = getServletConfig().getServletContext();
       HttpSession session = hsrqst.getSession(true);
       Integer personId = (Integer)session.getAttribute("personId");
       /*if(personId == null){
        hsrqst.sendRedirect("login");
        return;
       }*/
       Integer postId = Integer.parseInt(hsrqst.getParameter("postId"));
       List<Comm> comms = commKQT.commentsFor(postId);
       hsrqst.setAttribute("Comments", comms);
       ctx.getRequestDispatcher("/comm.jsp").forward(hsrqst, hsrsp);
       
       PrintWriter out = hsrsp.getWriter();
       try{
           /*
           out.println("<html>");
           out.println("<head><title>Servlet CommServlet</title></header>");
           out.println("<body><h1>hsrqst.getContextPath()</h1></body></html>");
           */
       } finally{
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
        return "Short descriptoin";
    }
}
