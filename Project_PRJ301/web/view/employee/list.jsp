<%-- 
    Document   : list
    Created on : Mar 1, 2025, 11:47:09 PM
    Author     : ADMIN
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="models.Employee" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="search" action="view" method="GET">
            <select name="did" onchange="document.getElementById('search').submit();">
                <option value="-1">---Select All---</option>
                <c:forEach items="${requestScope.depts}" var="d">
                    <option
                        <c:if test="${d.id eq param.did}">
                        selected="selected"
                        </c:if>
                        value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
        </form>
        <table border="1px">
            <tr>
                <td>Id</td>
                <td>Name</td>

            </tr>
            <c:forEach items="${requestScope.employees}" var="e">
                <tr>
                    <td>${e.id}</td>
                    <td>${e.name}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
