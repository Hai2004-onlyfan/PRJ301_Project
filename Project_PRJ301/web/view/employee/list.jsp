<%@page import="java.util.ArrayList" %>
<%@page import="models.Employee" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Nhân Viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f6f9;
        }
        .card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        .header-links {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 20px;
        }
        .btn-home-logout {
            background-color: #007bff;
            color: white;
            font-size: 18px;
            border-radius: 30px;
            padding: 10px 25px;
            margin-right: 15px;
            transition: background-color 0.3s ease;
        }
        .btn-home-logout:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <!-- Header -->
    <header class="bg-primary text-white text-center py-4 mb-4">
        <h1>Danh Sách Nhân Viên</h1>
        <p class="lead">Trang quản lý danh sách nhân viên</p>
        
        <!-- Thêm các nút Home và Logout -->
        <div class="header-links">
            <a href="../view/manager/managerDashboard.jsp" class="btn-home-logout">Home</a>
            <a href="../login" class="btn-home-logout">Logout</a>
           
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">

        <!-- Bộ lọc phòng ban -->
        <div class="mb-4">
            <form id="search" action="view" method="GET">
                <div class="row">
                    <div class="col-md-6">
                        <label for="departmentSelect" class="form-label">Chọn Phòng Ban</label>
                        <select class="form-select" id="departmentSelect" name="did" onchange="document.getElementById('search').submit();">
                            <option value="-1">---Tất cả---</option>
                            <c:forEach items="${requestScope.depts}" var="d">
                                <option 
                                    <c:if test="${d.id eq param.did}">selected</c:if>
                                    value="${d.id}">${d.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form>
        </div>

        <!-- Bảng Danh Sách Nhân Viên -->
        <div class="card">
            <div class="card-header text-center">
                Thông Tin Nhân Viên
            </div>
            <div class="card-body">
                <table class="table table-bordered table-striped">
                    <thead class="table-primary">
                        <tr>
                            <th>STT</th>
                            <th>Họ và Tên</th>
                            <th>Số Điện Thoại</th>
                            <th>Ngày Sinh</th>
                            <th>Phòng Ban</th>
                            <th>Trưởng Phòng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.employees}" var="e" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${e.name}</td>
                                <td>${e.phone}</td>
                                <td><fmt:formatDate value="${e.dob}" pattern="yyyy-MM-dd" /></td>
                                <td>${e.dept.name}</td>
                                <td>${e.manager != null ? e.manager.name : 'No Manager'}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>

    <!-- Footer -->
    <footer class="text-center bg-dark text-white py-3 mt-4">
        <p>&copy; 2025 Công ty ABC. All Rights Reserved.</p>
    </footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
