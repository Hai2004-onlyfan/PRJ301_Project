<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Duyệt Đơn Xin Nghỉ Phép</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 50px;
        }

        .card {
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #e9f7fd;
        }

        .card-header {
            background-color: #007bff;
            color: white;
            font-size: 20px;
            font-weight: bold;
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .card-body {
            padding: 30px;
        }

        .form-control {
            border-radius: 10px;
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

        .btn-reject {
            background-color: #dc3545;
            color: white;
            border-radius: 50px;
            padding: 10px 30px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .btn-reject:hover {
            background-color: #c82333;
        }

        .btn-approve {
            background-color: #28a745;
            color: white;
            border-radius: 50px;
            padding: 10px 30px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .btn-approve:hover {
            background-color: #218838;
        }

        .header-info {
            font-size: 14px;
            color: #555;
            margin-bottom: 30px;
        }

        .footer {
            background-color: #343a40;
            color: white;
            padding: 20px;
            text-align: center;
            position: absolute;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="card">
            <div class="card-header text-center">
                Duyệt Đơn Xin Nghỉ Phép
                <div>
                    <!-- Thêm các nút Home và Logout -->
                    <a href="homeE.jsp" class="btn btn-custom">Home</a>
                    <a href="logout.jsp" class="btn btn-custom">Logout</a>
                </div>
            </div>
            <div class="card-body">

                <div class="header-info">
                    <p>Duyệt bởi User: <strong>mr Tít</strong>, Role: <strong>Trưởng phòng</strong></p>
                    <p>Tạo bởi: <strong>mr Tèo</strong></p>
                    <p>Từ ngày: <strong>1/1/2025</strong></p>
                    <p>Tới ngày: <strong>3/1/2025</strong></p>
                </div>

                <div class="form-group">
                    <label for="reason">Lý do:</label>
                    <textarea class="form-control" id="reason" rows="4" disabled>Em nghỉ lấy vợ</textarea>
                </div>

                <div class="text-center mt-4">
                    <button class="btn btn-reject" onclick="rejectRequest()">Reject</button>
                    <button class="btn btn-approve" onclick="approveRequest()">Approve</button>
                </div>
            </div>
        </div>
    </div>

    <div class="footer">
        <p>&copy; 2025 Công ty ABC. All Rights Reserved.</p>
    </div>

    <!-- Thêm Javascript để Bootstrap hoạt động -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

    <script>
        function rejectRequest() {
            alert("Đơn xin nghỉ đã bị từ chối.");
        }

        function approveRequest() {
            alert("Đơn xin nghỉ đã được phê duyệt.");
        }
    </script>
</body>
</html>
