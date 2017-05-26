/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.stateless;

/**
 *
 * @author Kva
 */
import java.io.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class ServletStatelessKQT extends HttpServlet {
    @EJB
    private StatelessSBean sless;
    
    @Override
    public void service(HttpServletRequest hsreq, HttpServletResponse hsresp) throws ServletException, IOException{
        hsresp.setContentType("text/html");
        PrintWriter out = hsresp.getWriter();
        
        try{
            out.println("<html><head><title>Quoter Servlet Output</title></head></body BGCOLOR=white");
            out.println("<center><font size=+1> ServletStatelessKQT:: Pleas Enter YOur Name </font></center></p>");
            out.println("<form method=\"QUOTE\"");
            out.println("<table>");
            out.println("<tr><td>Name: </td>");
            out.println("<td><input type=\"text\" name=\"name\"> </td>");
            out.println("<tr><td>Name: </td>");
            out.println("<td><input type=\"submit\" name=\"sub\"> </td>");
            out.println("</table>");
            out.println("</form");
            String val = hsreq.getParameter("name");
            
            if((val != null) && (val.trim().length() > 0)){
                out.println("<font size=+1 color= red>Greeting from StatelessSBean: </font>" + sless.sayHello(val) + "<br>");
            }
            out.println("</body></html>");
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("webclinet servlet test failed");
            throw new ServletException(ex);
        }
    }
}
