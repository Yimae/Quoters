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
import KQT.FRequestKQT;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CreateFRequestServlet", urlPatterns = ("/create_frequest"))
public class CreateFRequestServlet extends HttpServlet{
    @EJB
    private FRequestKQT frequestKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html/charset=UTF-8");
        HttpSession sess = hsrqst.getSession(true);
        Integer personId = (Integer)sess.getAttribute("personId");
        PrintWriter out = hsrsp.getWriter();
        try{
            Integer targetId = Integer.parseInt(hsrqst.getParameter("targetId"));
            if(personId != targetId && frequestKQT.createFRequest(personId, targetId)){
                out.println("friend requested");
                hsrsp.sendRedirect("profile?personId=" + targetId);
            }else{
                out.println("Friend Request Failed.");
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
        return "Short description";
    }
}

