<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bit.lt.lt.data.Meeting"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Meeting> list = (List<Meeting>) request.getAttribute("meetings");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meeting List</title>
    </head>
    <body>
     <div align="center">
     <table>
     <tr>
     <td>
        <form method="GET" action="<%=application.getContextPath()%>/meeting/new">
        <input type="submit" value="ADD Meeting" >
        </form>
     </td>
     <td>
             <form method="GET" action="<%=application.getContextPath()%>/">
                     <input type="submit" value="Go to Home page" >
                     </form>
          </td></tr></table>
        <hr>
         <table><tr><td>
        <form method="GET" action="search">
                   Name: <input type="text" name="name" value="${name}">
                   Description: <input type="text" name="description" value="${description}">
                   <input type="submit" value="Search">
        </form>
        </td><td>
        <form method="GET" action="<%=application.getContextPath()%>/meetings">
                   <input type="submit" value="Full List" >
                   </form></td></tr></table>
        <ul>
        <table border="1" cellpadding="5">

                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Type</th>
                    <th>Start Date</th>
                    <th>Functions</th>
                </tr>
        <c:forEach var="meeting" items="${meetings}">

            <tr>
                 <td>${meeting.name}</td>
                 <td>${meeting.description}</td>
                 <td>${meeting.category}</td>
                 <td>${meeting.type}</td>
                 <td>${meeting.startDate}</td>
                 <td>
                     <a href="<%=application.getContextPath()%>/meeting/${meeting.id}">edit</a>
                     <a href="<%=application.getContextPath()%>/meeting/${meeting.id}/delete">delete</a>
                     <a href="<%=application.getContextPath()%>/meeting/${meeting.id}/atendees">atendees</a>
                 </td>
            </tr>

        </c:forEach>
        </ul>

        <hr>
    </div>
    </body>
</html>
