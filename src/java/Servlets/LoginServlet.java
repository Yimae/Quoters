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

@WebServlet(name="LoginServlet", urlPatterns = ("/login"))
public class LoginServlet extends HttpServlet{
    
    @EJB
    private QuoterKQT quoterKQT;
    
    private ServletContext ctx;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        ServletContext ctx = getServletConfig().getServletContext();
        hsrqst.setAttribute("error", "");
        HttpSession sess = hsrqst.getSession(true);
        Integer personId = (Integer)sess.getAttribute("personId");
        if(personId == null){
            hsrsp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = hsrsp.getWriter();
            try{
                Quoter quoter = quoterKQT.login(hsrqst.getParameter("Qname"), hsrqst.getParameter("password"));
                if(quoter == null){
                    if(hsrqst.getParameter("Qname") != null)
                        hsrqst.setAttribute("error", "Qname/password combination invalid");
                    ctx.getRequestDispatcher("/login.jsp").forward(hsrqst, hsrsp);
                }else{
                    sess.setAttribute("personId", quoter.getId());
                    sess.setAttribute("Qname", quoter.getQname());
                    hsrsp.sendRedirect("wall?ownerId=" + quoter.getId());
                }
            }finally{
                out.close();
            }
        }else{
            hsrsp.sendRedirect("wall?onwerId="+personId);
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
