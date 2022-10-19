<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bit.lt.lt.data.Person"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person</title>
    </head>
    <body>

<div align="center">
<h3> Making new person</h3>
    <table border="1" cellpadding="5">
        <form method="POST" action="../editPerson">
            <c:if test="${person.id != null}">
                <input type="hidden" name="id" value="${person.id}">
            </c:if>
            <tr><th>Name:</th> <td> <input type="text" name="prname" value="${person.prname}"></td> </tr>
            <tr><th>Status:</th> <td> <input type="text" name="status" value="${person.status}"></td> </tr>
            <tr><th>Meetings:</th> <td> <input type="text" name="meetings" value="${person.meetings}"></td> </tr>
            <tr><td><input type="submit" value="Save"></td>
            <td><a href="../">Cancel</a></td> </tr>
        </form>
        </div>

    </body>
</html>
