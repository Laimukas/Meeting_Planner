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
        <title>atendees</title>
    </head>
    <body>
     <div align="center">
     <h3> Susitikimo " ${meeting.name} " dalyvių sąrašas</h3><br>
     <table>
     <tr>
     <td>Atendee to choose:
     </td><td>
        <form method="GET" action="addAtendee/${atendId}">
        <select name="atendId">
          <c:forEach var="person" items="${people}">
          <option value="${person.id}" <c:if test="${person.id==value.person.id}">selected</c:if>>
          Id:${person.id};
          Name:${person.prname}
          </option>
          </c:forEach>
        </select>
        <input type="submit" value="ADD Atendee" >
        </form>
     </td>

     <td>
        <form method="GET" action="<%=application.getContextPath()%>/meetings">
        <input type="submit" value="Go Back" >
        </form>
     </td></tr></table>
        <hr>
        <ul>
        <table border="1" cellpadding="5">

                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Functions</th>
                </tr>
        <c:forEach var="atendee" items="${list}">

            <tr>
                 <td>${atendee.id}</td>
                 <td>${atendee.prname}</td>
                 <td>${atendee.status}</td>
                 <td>
                     <a href="atendee/${atendee.id}/remove">remove</a>
                 </td>
            </tr>

        </c:forEach>
        </ul>
    </div>
    </body>
</html>
