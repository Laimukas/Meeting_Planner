<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Printinam laika</title>

    </head>
    <body>

        <form method="POST" action="<%=application.getContextPath()%>/printLaikas">
             <div align="center">
             <h2> Printing Time </h2>
                <table border="1" cellpadding="5">
                    <tr>
                        <th>Type</th>
                        <th>Values</th>
                    </tr>
                    <tr>
                        <th>DateTime:</th>
                        <th><input type="date" name="laikas" value="${laikas}"></th>
                        <% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                         String time = sdf.format(${laikas});
                         System.out.print(time); %>
                    </tr>
                    <tr>
                    <th> <input type="submit" value="Print"></th>
                    <th> <a href="<%=application.getContextPath()%>/">Cancel</a></th>
                    </tr>

                </table>
                </div>
        </form>
    </body>
</html>
