<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bit.lt.lt.data.Meeting"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

<div align="center">

 <h3>Making new meeting </h3>
    <table border="1" cellpadding="5">
        <form method="POST" action="<%=application.getContextPath()%>/meeting/saveNew">
            <c:if test="${meeting.id != null}">
                <input type="hidden" name="id" value="${meeting.id}">
            </c:if>
            <tr><th>Name:</th> <td> <input type="text" name="name" value="${meeting.name}"></td> </tr>
            <tr><th>Responsible person:</th> <td> <input type="text" name="respPerName" value="${respPerName}"></td> </tr>
            <tr><th>Description:</th> <td> <input type="text" name="description" value="${meeting.description}"></td> </tr>
            <tr><th>Category:</th> <td>
            <select name="category">
                    <option>CodeMonkey</option>
                    <option>Hub</option>
                    <option>Short</option>
                    <option>TeamBuilding</option>
                    </select>
            </td> </tr>
            <tr><th>Type:</th> <td>
            <select name="type">
                     <option>Live</option>
                     <option>InPerson</option>
                     </select>
            </td> </tr>
            <tr><th>Start Date:</th> <td> <input type="text" name="startDate" value="${meeting.startDate}"></td> </tr>
            <tr><th>End Date:</th> <td> <input type="text" name="endDate" value="${meeting.endDate}"></td> </tr>
            <tr><th>Atendees Id:</th> <td> <input type="text" name="atendees" value="${meeting.atendees}"></td> </tr>
            <tr><td><input type="submit" value="Save"></td>
            <td><a href="../">Cancel</a></td> </tr>
        </form></table>
        <hr>
    <h4> List of responsible people to choose from.</h4>
        <a> You need to choose name and copy it, </a><br>
        <a> then paste it in the <b>Responsible person</b> text field. </a>
        <table border="1" cellpadding="5">
        <tr><th>
            Name: </th></tr>
        <c:forEach var="person" items="${list}">
        <tr><td>
            ${person.prname}</td></tr>
        </c:forEach>
        </table>

        </div>

    </body>
</html>
