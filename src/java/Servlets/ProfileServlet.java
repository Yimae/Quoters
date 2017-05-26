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
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ProfileServlet", urlPatterns = ("/profile"))
public class ProfileServlet extends HttpServlet{
    @EJB
    private QuoterKQT quoterKQT;
    
    @EJB
    private FRequestKQT frequestKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html;charset=UTF-8");
        HttpSession sess = hsrqst.getSession(true);
        PrintWriter out = hsrsp.getWriter();
        ServletContext ctx = getServletConfig().getServletContext();
        
        Integer idFromSession = (Integer)sess.getAttribute("ersponId");
        
        Integer personId = (Integer)sess.getAttribute("personId");
        if(hsrqst.getParameter("personId") != null){
            personId = Integer.parseInt(hsrqst.getParameter("personId"));
        }
            Quoter quoter = quoterKQT.findById(personId);
            /*quoter.setIsFriend(true);
            friendRequestKQT.areFriends{
                frequestKQT.areFriends(
                (Integer)sess.getAttribute("personId");
                personId)
            }
            */
            hsrqst.setAttribute("quoter", quoter);
            
            boolean isFriend = frequestKQT.areFriends(idFromSession, personId);
            
            boolean isUnanswered = frequestKQT.areUnanswered(idFromSession, personId);
            
            boolean isSelf = personId.equals(idFromSession);
            
            String frequestFragment = "";
            if(!isSelf && !isFriend && !isUnanswered) frequestFragment = "<a href ='create_frequest?targetId=" + personId + ">'Add friend</a>";
            if(!isSelf && !isFriend && isUnanswered) frequestFragment = "Friendship pending";
            if(!isSelf && isFriend) frequestFragment = "<a href='remove_frequest="+personId+"'>Remove Friend</a>";
            if(isSelf) frequestFragment = "This is you!";
            
            hsrqst.setAttribute("FrequestFragment", frequestFragment);
            hsrqst.setAttribute("isSelf", isSelf);
            ctx.getRequestDispatcher("/personalInfo.jsp").forward(hsrqst, hsrsp);
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
        return "short dsecription";
    }

}
