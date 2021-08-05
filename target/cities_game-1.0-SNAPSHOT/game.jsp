<%@ page import="entities.City" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cities game</title>
</head>
<body>
<%
    City cityByBot = (City) request.getAttribute("lastCityByBot");
    String warning;
    String word;

    if (request.getAttribute("warning") != null) {
        warning = (String) request.getAttribute("warning");
    } else {
        warning = "null";
    }

    if (request.getAttribute("word") != null) {
        word = (String) request.getAttribute("word");
    } else {
        word = "";
    }

%>

<p><%= cityByBot %>
</p>

<%if (!warning.equals("null")) { %>
<p style="color:#DB2801"><%= warning %>
</p>
<% } %>

<form action="next" method="get">
    <input type="text" name="word" value="<%= word%>"><br>
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
