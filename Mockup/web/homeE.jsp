<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f6f9;
        }
        .card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .navbar {
            border-bottom: 2px solid #ccc;
        }
        .navbar-brand {
            font-weight: bold;
        }
        footer {
            background-color: #343a40;
            color: white;
            padding: 20px;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            border-radius: 50px;
            padding: 10px 30px;
            transition: background-color 0.3s ease;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
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
        .header-links {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 20px;
        }
        .welcome-message {
            margin-top: 20px;
            font-size: 20px;
            font-weight: bold;
        }
        .task-cards {
            margin-top: 30px;
        }
    </style>
</head>
<body>

    <!-- Phần Header -->
    <header class="bg-primary text-white text-center py-4 mb-4">
        <h1>Welcome to Company ABC</h1>
        <p class="lead">Trang quản lý dành cho Employee</p>
        <div class="header-links">
            <a href="homeE.jsp" class="btn-home-logout">Home</a>
            <a href="login.jsp" class="btn-home-logout">Logout</a>
        </div>
    </header>

    <!-- Phần Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Company Dashboard</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Phần Nội Dung Chính -->
    <div class="container">

        <!-- Chào Mừng Nhân Viên -->
        <div class="welcome-message text-center">
            <p>Chào mừng bạn, <strong>An</strong> từ Phòng ban: <strong>IT</strong></p>
        </div>

        <!-- Phần Tạo và Xem Đơn Xin Nghỉ -->
        <div class="row task-cards g-4">

            <!-- Tạo Đơn Xin Nghỉ -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header text-center">
                        Tạo Đơn Xin Nghỉ
                    </div>
                    <div class="card-body text-center">
                        <p class="card-text">Tạo đơn xin nghỉ phép khi bạn cần nghỉ.</p>
                        <a href="createLeaveRequest.jsp" class="btn btn-custom">Tạo Đơn</a>
                    </div>
                </div>
            </div>

            <!-- Xem Đơn Xin Nghỉ -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header text-center">
                        Xem Đơn Xin Nghỉ
                    </div>
                    <div class="card-body text-center">
                        <p class="card-text">Xem các đơn xin nghỉ của bạn đã được duyệt.</p>
                        <a href="viewLeaveRequests.jsp" class="btn btn-custom">Xem Đơn</a>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <!-- Phần Footer -->
    <footer class="text-center">
        <p>&copy; 2025 Công ty ABC. All Rights Reserved.</p>
    </footer>

    <!-- Thêm Javascript để Bootstrap hoạt động -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
