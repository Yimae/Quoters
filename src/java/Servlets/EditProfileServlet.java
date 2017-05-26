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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EditProfileServlet", urlPatterns = ("/edit_profile"))
public class EditProfileServlet extends HttpServlet{
    @EJB
    private QuoterKQT quoterKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html;Charset=UTF-8");
        HttpSession sess = hsrqst.getSession(true);
        Integer personId = (Integer)sess.getAttribute("personId");
        ServletContext ctx = getServletConfig().getServletContext();
        if(personId == null){
            hsrsp.sendRedirect("login");
            return;
        }
        PrintWriter out = hsrsp.getWriter();
        
        Quoter quoter = quoterKQT.findById(personId);
        hsrqst.setAttribute("quoter", quoter);
        ctx.getRequestDispatcher("/editPersonalInfo.jsp").forward(hsrqst, hsrsp);
        return;
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
        return "Short Dscription";
    }
}
