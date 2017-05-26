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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "UpdatePersonalInfoServlet", urlPatterns = ("/update_personal_info"))
public class UpdatePersonalInfoServlet extends HttpServlet{
    @EJB
    private QuoterKQT quoterKQT;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException{
        hsrsp.setContentType("text/html;charset=UTF-8");
        ServletContext ctx = getServletConfig().getServletContext();
        HttpSession sess = hsrqst.getSession(true);
        PrintWriter out = hsrsp.getWriter();
        try{
            FileItemFactory fct = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUplaod(fct);
            
            List<FileItem> item = upload.parseRequest(hsrqst);
            
            Integer person = (Integer)sess.getAttribute("personId");
            String Qname = item.get(0).getString();
            String Qemail = item.get(1).getString();
                      
            boolean success = quoterKQT.updatePersonInformation(person, Qname, Qemail);
            out.println(success);
            if(success){
                hsrsp.sendRedirect("profile");
                return;
            }else{
                hsrqst.setAttribute("error", "illegal operation");
                hsrsp.sendRedirect("edit_profile");
                return;
            }
        }catch(Exception ex){
            out.println(ex);
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
    
    
    public String getServletinfo(){
        return "Short description";
    }
}
