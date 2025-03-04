<%-- 
    Document   : welcome
    Created on : Feb 19, 2025, 10:48:47 AM
    Author     : sonnt-local
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.user.displayname}</h1>
        You need to report to ${sessionScope.user.e.manager.name}
        Your Direct Staffs: <br/>
        <c:forEach items="${sessionScope.user.e.directstaffs}" var="s" >
            ${s.name} <br/>
         </c:forEach>
            
        Your All Staffs: <br/>
        <c:forEach items="${sessionScope.user.e.staffs}" var="s" >
            ${s.name} <br/>
         </c:forEach>    
        
    </body>
</html>
