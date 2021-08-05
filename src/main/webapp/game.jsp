<%@ page import="entities.City" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<%
    City cityByBot = (City) request.getAttribute("lastCityByBot");
    String warning;
    String lastCity;

    if (request.getAttribute("warning") != null) {
        warning = (String) request.getAttribute("warning");
    } else {
        warning = "null";
    }

    if (request.getAttribute("lastCity") != null) {
        lastCity = (String) request.getAttribute("lastCity");
    } else {
        lastCity = "";
    }

%>

<p><%= cityByBot %>
</p>

<%if (!warning.equals("null")) { %>
<p style="color:#DB2801"><%= warning %>
</p>
<% } %>

<form action="next" method="get">
    <input type="text" name="word" value="<%= lastCity%>"><br>
    <input type="submit" value="Подтвердить">
</form>

<form action="begin" method="get">
    <input type="submit" value="Начать новую игру">
</form>

<form action="end" method="post">
    <input type="submit" value="Закончить игру">
</form>

</body>
</html>
