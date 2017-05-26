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
import KQT.QuoterKQT;
import Entity.Quoter;
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

@WebServlet(name = "UnansweredFRequestServlet", urlPatterns = (".friend_request"))
public class UnasnwerdFRequestServlet extends HttpServlet{
    @EJB
    private QuoterKQT quoterKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html;charset=UTF-8");
        ServletContext ctx = getServletConfig().getServletContext();
        HttpSession sess = hsrqst.getSession(true);
        Integer personId = (Integer)sess.getAttribute("personId");
        PrintWriter out = hsrsp.getWriter();
        try{
            List<Quoter> unanswerdFRequests = quoterKQT.unansweredFRequestFor(personId);
            hsrqst.setAttribute("friendRequests", unanswerdFRequests);
            ctx.getRequestDispatcher("/friendRequest.jsp").forward(hsrqst, hsrsp);
            return;
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
