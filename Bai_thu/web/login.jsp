<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
    <form action="login" method="post">
        Username: <input type="text" name="username" required><br>
        Password: <input type="password" name="password" required><br>
        <button type="submit">Login</button>
    </form>
</body>
</html>
