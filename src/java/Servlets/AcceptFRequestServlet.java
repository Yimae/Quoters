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

@WebServlet(name = "AcceptFRequestServlet", urlPatterns = ("/accept_friend_request"))
public class AcceptFRequestServlet  extends HttpServlet{
    
    @EJB
    private FRequestKQT frequestKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html/charset = UTF-8");
        HttpSession session = hsrqst.getSession(true);
        Integer personId = (Integer)session.getAttribute("personId");
        PrintWriter out = hsrsp.getWriter();
        try{
            Integer sourceId = Integer.parseInt(hsrqst.getParameter("sourceId"));
            if(frequestKQT.acceptFRequest(sourceId, personId)){
                out.println("accepted");
            }else{
                out.println("failed");
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
