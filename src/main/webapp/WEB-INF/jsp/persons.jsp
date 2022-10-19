<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bit.lt.lt.data.Person"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Persons Data</title>
    </head>
    <body>
     <div align="center">
     <table><tr><td>
        <form method="GET" action="person/new">
        <input type="submit" value="New Person" >
        </form></td>
        <td><a href="<%=application.getContextPath()%>/">Go Back</a></td> </tr></table>
        <hr>
        <table><tr><td>
        <form method="GET" action="searchPerson">
          Status:  <select name="status">
                      <option>responsiblePerson</option>
                      <option>participant</option>
                      <option>admin</option>
                    </select>
        <input type="submit" value="Search by status">
        </form></td><td>
        <form method="GET" action="persons">
        <input type="submit" value="Full List" >
        </form></td></tr></table>
        <hr>
        <ul>
        <table border="1" cellpadding="5">

                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Functions</th>
                </tr>
        <c:forEach var="person" items="${persons}">

            <tr>
                 <td>${person.id}</td>
                 <td>${person.prname}</td>
                 <td>${person.status}</td>
                 <td>
                     <a href="person/${person.id}">edit</a>
                     <a href="person/${person.id}/delete">delete</a>
                 </td>
            </tr>

        </c:forEach>
        </ul>
    </div>
    </body>
</html>
