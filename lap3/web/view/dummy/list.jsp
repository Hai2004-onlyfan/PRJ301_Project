<%-- 
    Document   : list
    Created on : Mar 3, 2025, 1:20:30 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Dummy" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>List of Dummy</h1>
        
        <!-- Duyệt qua danh sách dummys và hiển thị -->
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody>
                <!-- Duyệt qua danh sách dummys -->
                <c:forEach items="${dummys}" var="dummy">
                    <tr>
                        <td>${dummy.id}</td>
                        <td>${dummy.name}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
