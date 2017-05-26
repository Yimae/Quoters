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
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CreateCommServlet", urlPatterns = ("/create_comm"))
public class CreateCommServlet extends HttpServlet{
    @EJB
    private CommKQT commKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html;charset=UTF-8");
        HttpSession sess = hsrqst.getSession(true);
        Integer personId = (Integer)sess.getAttribute("personId");
        ServletContext ctx = getServletConfig().getServletContext();
        if(personId == null){
            hsrsp.sendRedirect("login");
            return;
        }
        PrintWriter out = hsrsp.getWriter();
        try{
            String text = hsrqst.getParameter("text");
            Integer postId = Integer.parseInt(hsrqst.getParameter("postId"));
            if(commKQT.createComm(text, personId, postId)){
                hsrsp.sendRedirect("comments?postId=," + postId);
                out.println("sucess");
            }else{
                out.println("failure");
            }
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
        return "Short description";
    }
}
