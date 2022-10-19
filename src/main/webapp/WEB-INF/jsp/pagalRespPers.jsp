<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bit.lt.lt.data.Person"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Resp Pers</title>
    </head>
    <body>
     <div align="center">
     <form method="GET" action="<%=application.getContextPath()%>/">
       <input type="submit" value="Go to Home page" >
     </form>
     <h3> Sorting meetings by Responsible person</h3>
        <form method="GET" action="choose/${respPers}">
        <select name="respPers">
          <c:forEach var="person" items="${list}">
          <option value="${person.id}" <c:if test="${person.id==value.person.id}">selected</c:if>>
          Name:${person.prname}
          </option>
          </c:forEach>
        </select>
        <input type="submit" value="Get meetings by Resp.Person" >
        </form>
    </div>
    </body>
</html>
