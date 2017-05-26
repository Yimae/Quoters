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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.SerlvetFileUpload;
        
@WebServlet(name = "RgeisterServlet", urlPatterns = ("/register"))
public class RegisterServlet extends HttpServlet{
    @EJB
    private QuoterKQT quoterkqt;
    
    protected void processRequest(HttpServletRequest hsrqst, HttpServletResponse hsrsp) throws ServletException, IOException {
        hsrsp.setContentType("text/html;charset=UTF-8");
        ServletContext ctx = getServletConfig().getServletContext();
        PrintWriter out = hsrsp.getWriter();
        try{
            FileItemFactory fct = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fct);
            
            List<FileItem> item = upload.parseRequest(hsrqst);
            
            String Qname = item.get(0).getString();
            String Qemail = item.get(1).getString();
            String password = item.get(2).getString();
            
            boolean success = quoterkqt.register(Qname, Qemail, password);
            if(success){
                out.println("We Emailed confirmation link");
                return;
            }else{
                hsrqst.setAttribute("error", "There was an error processing your requesting");
                ctx.getRequestDispatcher("/ignup.jsp").forward(hsrqst, hsrsp);
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
    
    @Override
    public String getServletInfo(){
        return "short description";
    }
}
