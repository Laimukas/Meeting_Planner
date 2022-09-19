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
        <title>JSP Page</title>
    </head>
    <body>
        <form method="GET" action="meeting/new">
        <input type="submit" value="ADD Meeting" >
        </form>
        <hr>
        <form method="POST" action="../search">
                   <c:if test="${meeting.id != null}">
                       <input type="hidden" name="id" value="${meeting.id}">
                   </c:if>
                   Name: <input type="text" name="name" value="${meeting.name}">
                   Description: <input type="text" name="description" value="${meeting.description}">
                        <input type="hidden" name="category" value="${meeting.category}">
                        <input type="hidden" name="type" value="${meeting.type}">
                        <input type="hidden" name="startDate" value="${meeting.startDate}">
                   <input type="submit" value="Search">

        </form>
        <hr>
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
                     <a href="meeting/${meeting.id}">edit</a>
                     <a href="meeting/${meeting.id}/delete">delete</a>
                     <a href="meeting/${meeting.id}">edit</a>
                 </td>
            </tr>

        </c:forEach>
        </ul>

        <hr>

    </body>
</html>
