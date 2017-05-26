<%-- 
    Document   : header
    Created on : 2017. 5. 17, 오전 11:34:51
    Author     : Kva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header_div">
    <img class="logoimg" src="./img/headlogo.png">
    <% if (session.getAttribute("QID") != null) { %>
    <a href="profile" class="a_head"><%= session.getAttribute("QName")%></a><br>
    <%} else {%>
    <a href="login" class="a_head">Anonymous Quoter</a><br>
    <% } %>
    <%if (session.getAttribute("QID") != null) { %>
    <a href="logout" class="a_head_log_cont">Log Out</a>
    <% } %>
</div>