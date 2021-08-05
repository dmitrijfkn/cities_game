<%@ page import="entities.City" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>End game</title>
</head>
<body>

<p align="center">Спасибо за игру

<p align="center">
<form action="begin" method="get">
    <input type="submit" value="Начать новую игру">
</form>

<p align="center">
<form action="next" method="get">
    <input type="submit" value="Продолжить игру">
</form>

<%
    List<City> cityList = (List) request.getAttribute("cityList");
%>

<p align="center">
<table border="1">
    <caption>История ваших городов</caption>
    <tr>
        <td align="center">Name</td>
    </tr>
    <% for (City city : cityList) {%>
    <tr>
        <td><%= city.getName() %>
        </td>
    </tr>
    <% } %>
</table>

</body>
</html>
