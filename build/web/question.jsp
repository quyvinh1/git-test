<%-- 
    Document   : showQuestion
    Created on : Nov 10, 2023, 1:36:02 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Question" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<Question> data=new ArrayList<Question>();
            if(request.getAttribute("data")!=null)
                data=(ArrayList<Question>)request.getAttribute("data");
                
                int currentIndex = 0; 
    if (request.getAttribute("currentIndex") != null) {
        currentIndex = (int) request.getAttribute("currentIndex");
    }

    String selectedAnswer = request.getParameter("selectedAnswer");
    if (selectedAnswer != null && selectedAnswer.equals(data.get(currentIndex).getAnswer())) {
        currentIndex++;
    }
        %>
        <form action="question" method="post">
             <input type="hidden" name="currentIndex" value="<%= currentIndex %>">
    Question: ${data.get(currentIndex).getContent()}
    <br>
    <input type="radio" name="selectedAnswer" value="A"> A. ${data.get(currentIndex).getA()}
    <input type="radio" name="selectedAnswer" value="B"> B. ${data.get(currentIndex).getB()}
    <input type="radio" name="selectedAnswer" value="C"> C. ${data.get(currentIndex).getC()}
    <input type="radio" name="selectedAnswer" value="D"> D. ${data.get(currentIndex).getD()}
    <br>
    <input type="submit" name="next" value="Next">
        </form>
    </body>
</html>