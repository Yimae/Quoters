<%-- 
    Document   : login.jsp
    Created on : 2017. 5. 17, 오전 10:15:09
    Author     : Kva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head><title>Quoters</title></head>
    <link rel="stylesheet" type="text/css" href="./css/welcome_page.css">
    <body>
        <jsp:include page="header.jsp"/>
        <%= (request.getAttribute("Error") == null) ? "" : request.getAttribute("Error") %>
        <div class="left_div">
            <div class="slogtype fade"><img src="./img/Welcome_slog.png"></div>
            <div class="slogtype fade"><img src="./img/Welcome_slog2.png"></div>
        </div>
        <div class="right_div">
            <img src="./img/Qlogo_log2.png">
            <form action ="login" method="post">
                <input type="text" class="inbox" name="Qname" placeholder="Enter Your Quoter Name">
                <br><br>
                <input type="password" class="inbox" name="passcode" placeholder="Enter Your Password">
                <br><br>
                <input type="submit" class="but" name="login" value="LOG IN">
            </form>
            <hr>
            <form action="register" method="post" enctype="multipart/form-data">
                <input type="text" class="inbox" name="Qname" placeholder="Enter Your Quoter Name">
                <br><br>
                <input type="text" class="inbox" name="Qemail" placeholder="Enter Your Email">
                <br><br>
                <input type="password" class="inbox" name="passcode" placeholder="Enter Your Password">
                <br><br>
                <input type="password" class="inbox" name="passcode" placeholder="Re-Enter Your Password">
                <br><br>
                <input type="submit" class="but" name="register" value="SIGN UP">
            </form>
        </div>
        <script>
            var sIndex=0;
            startSlide();
            
            function startSlide(){
                var i;
                var slog = document.getElementsByClassName("slogtype");
                
                for(i = 0; i < slog.length; i++){
                    slog[i].style.display = "none";
                }
                sIndex++;
                if(sIndex > slog.length){
                    sIndex = 1
                }
                slog[sIndex-1].style.display="block";
                setTimeout(startSlide, 15000);
            }
        </script>
    </body>
</html>