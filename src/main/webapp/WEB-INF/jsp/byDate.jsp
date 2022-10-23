<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meetings by Date</title>
    </head>
    <body>
        <form method="POST" action="<%=application.getContextPath()%>/laikas">
             <div align="center">
             <h2> Meetings by Date </h2>
                <table border="1" cellpadding="5">
                    <tr>
                        <th>Type</th>
                        <th>Values</th>
                    </tr>
                    <tr>
                        <th>Data nuo:</th>
                        <th><input type="datetime-local" name="dataNuo" value="${value.dataNuo}"></th>
                    </tr>
                    <tr>
                        <th>Data iki:</th>
                        <th><input type="datetime-local" name="dataIki" value="${value.dataIki}"></th>
                    </tr>
                    <tr>
                    <th> <input type="submit" value="Tikrinam"></th>
                    <th> <a href="<%=application.getContextPath()%>/">Cancel</a></th>
                    </tr>

                </table>
                </div>
        </form>
    </body>
</html>
