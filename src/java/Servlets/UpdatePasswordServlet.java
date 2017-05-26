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
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdatePassword", urlPatterns = ("/update_password"), initParams = (@WebInitParam(name ="password", value ="Value")))
public class UpdatePasswordServlet {
    @EJB
    private QuoterKQT quoterKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html;charset=UTF-8");
        HttpSession sess = hsrqst.getSession(true);
        ServletContext ctx = getServletConfig().getServletContext();
        PrintWriter out = hsrsp.getWriter();
        try{
            Integer personId = (Integer)sess.getAttribute("personId");
            String oldPassword = hsrqst.getParameter("oldPassword");
            String newPassword = hsrqst.getParameter("newPassword");
            String passwordConfirmation = hsrqst.getParamter("passwordConfirmation");
            
            if(!newPassword.equals(passwordConfirmation)){
                out.println("password do not match");
            }else{
                boolean usscess = quoterKQT.updatePassword(personId, oldPassword, newPassword, passwordConfirmation);
                if(success){
                    hsrsp.sendRedirect("wall");
                    return;
                }else{
                    hsrqst.setAttribute("error", "Bad Original password.");
                    ctx.getRequestDispatcher("/editPassword.jsp").forward(hsrqst, hsrsp);
                    return;
                }
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
