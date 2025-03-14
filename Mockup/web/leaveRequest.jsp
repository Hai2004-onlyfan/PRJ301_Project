<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đơn Xin Nghỉ Phép</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 50px;
        }

        .card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
        }

        .card-header {
            background-color: #007bff;
            color: white;
            font-size: 20px;
            font-weight: bold;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .card-body {
            padding: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-control {
            border-radius: 8px;
            padding: 10px;
            font-size: 14px;
        }

        .btn-custom {
            background-color: #007bff;
            color: white;
            border-radius: 50px;
            padding: 10px 30px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .btn-custom:hover {
            background-color: #0056b3;
        }

        .header-info {
            font-size: 14px;
            color: #555;
            margin-bottom: 30px;
        }

        .card-footer {
            text-align: center;
            background-color: #f8f9fa;
            border-bottom-left-radius: 10px;
            border-bottom-right-radius: 10px;
        }

    </style>
</head>
<body>

    <div class="container">
        <div class="card">
            <div class="card-header text-center">
                Đơn Xin Nghỉ Phép
                <div>
                    <a href="homeE.jsp" class="btn btn-custom">Home</a>
                    <a href="logout.jsp" class="btn btn-custom">Logout</a>
                </div>
            </div>
            <div class="card-body">
                <div class="header-info">
                    <p>User: <strong>${user.displayname}</strong>, Role: <strong>Nhân viên</strong>, Dep: <strong>${user.e.department}</strong></p>
                </div>

                <!-- Form nhập thông tin đơn xin nghỉ phép -->
                <form action="submitLeaveRequest.jsp" method="post">
                    <div class="form-group">
                        <label for="fromDate">Từ ngày:</label>
                        <input type="date" id="fromDate" name="fromDate" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="toDate">Tới ngày:</label>
                        <input type="date" id="toDate" name="toDate" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="reason">Lý do:</label>
                        <textarea id="reason" name="reason" class="form-control" rows="4" required></textarea>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-custom">Gửi</button>
                    </div>
                </form>
            </div>
            <div class="card-footer">
                <p>&copy; 2025 Công ty ABC. All Rights Reserved.</p>
            </div>
        </div>
    </div>

    <!-- Thêm Javascript để Bootstrap hoạt động -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
