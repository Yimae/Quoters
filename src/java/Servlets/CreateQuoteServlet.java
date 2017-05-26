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
import KQT.QuoteKQT;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUplaod;

@WebServlet(name = "CreateQuoteServlet", urlPatterns = ("/create_quote"))
public class CreateQuoteServlet extends HttpServlet{
    @EJB
    private QuoteKQT quoteKQT;
    
    @EJB
    private FRequestKQT frequestKQT;
    
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
        boolean isMultipart = ServletFileUpload.isMultipartContent(hsrqst);
        
        try{
            FileItemFactory fct = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUplaod(fct);
            
            List<FileItem> item = upload.parseRequest(hsrqst);
            
            String title = item.get(0).getString();
            String text = item.get(1).getString();
            
            String link = item.get(2).getSTring();
            Integer ownerId = null;
            if(item.get(3).getString() != null && !item.get(3).getString().equals("null")){
                ownerId = Integer.parseInt(item.get(3).getString());
            }else{
                ownerId = personId;
            }
            if(!personId.equals(ownerId) && !frequestKQT.areFriends(personId. ownerId)){
                out.println("You DO NOT have Permission to POST HERE");
                return;
            }
            
            if(quoteKQT.createQuote(title, text, personId, ownerId, link)){
                hsrsp.sendRedirect("wall?ownerId=" + ownerId);
                return;
            }else{
                out.println("failure");
            }
        }catch(Exception ex){
            System.out.println(ex);
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
