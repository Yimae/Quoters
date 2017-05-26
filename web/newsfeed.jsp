<%-- 
    Document   : newsfeed
    Created on : 2017. 5. 17, 오후 12:05:36
    Author     : Kva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quoters</title>
        <link rel="stylesheet" type="text/css" href="./css/welcome_page.css">
        <link rel="stylesheet" type="text/css" href="./css/main_page.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <br>
        <div class="logo_menu_div">
            <br>
            <ul class="men_ul">
                <li class="men_li"><a class="a_men" href="newsfeed">HOME</a></li>
                <li class="men_li"><a class="a_men" >HOT</a></li>
                <li class="men_li"><a class="a_men" >PROFILE</a></li>
            </ul>
        </div>
        <hr><br> 
        <div class="yourquotebox">
            <form>
            <input type="textarea" class="quotebox" placeholder="Enter Your Quote Here.">
            <br>
            <input type="submit" class="but2" value="Submit">
            </form>
        </div>
        <div>
            <table>
                <tbody>
                <c:forEach var="post" items="$(posts)">
                    <tr>
                        <td>
                         
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
