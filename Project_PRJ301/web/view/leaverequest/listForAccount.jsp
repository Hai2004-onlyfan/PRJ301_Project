<%@page import="java.util.ArrayList" %>
<%@page import="models.LeaveRequest" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Yêu Cầu Nghỉ Của Bạn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f6f9;
        }
        .container {
            margin-top: 30px;
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
        .btn-home {
            background-color: #28a745;
            color: white;
            border-radius: 50px;
            padding: 10px 20px;
            text-align: center;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }
        .btn-home:hover {
            background-color: #218838;
            color: white;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="d-flex justify-content-end mb-3">
            <a href="../view/employee/employeeDashboard.jsp" class="btn-home">🏠 Home</a>
        </div>

        <div class="card">
            <div class="card-header text-center">
                Danh Sách Yêu Cầu Nghỉ Của Bạn
            </div>
            <div class="card-body">
                <table class="table table-striped table-bordered table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Tiêu Đề</th>
                            <th>Lý Do</th>
                            <th>Từ Ngày</th>
                            <th>Đến Ngày</th>
                            <th>Ngày Tạo</th>
                            <th>Trạng Thái</th>
                            <th>Người Duyệt</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.leaves}" var="l">
                            <tr>
                                <td>${l.id}</td>
                                <td>${l.title}</td>
                                <td>${l.reason}</td>
                                <td>${l.from}</td>
                                <td>${l.to}</td>
                                <td>${l.createddate}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${l.status eq 0}">Đang Xử Lý</c:when>
                                        <c:when test="${l.status eq 1}">Bị Từ Chối</c:when>
                                        <c:otherwise>Đã Chấp Nhận</c:otherwise>
                                    </c:choose>
                                </td>
                               <td>${l.approvedBy}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

</body>
</html>