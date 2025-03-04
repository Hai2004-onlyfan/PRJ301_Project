<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thời Gian Hoạt Động Của Nhân Viên</title>
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
        footer {
            background-color: #343a40;
            color: white;
            padding: 20px;
        }
        .calendar-table th, .calendar-table td {
            text-align: center;
            padding: 10px;
            border: 1px solid #ddd;
        }
        .workday {
            background-color: #4caf50; /* Màu xanh cho ngày làm việc */
        }
        .leave {
            background-color: #f44336; /* Màu đỏ cho ngày nghỉ phép */
        }
    </style>
</head>
<body>

    <!-- Header -->
    <header class="bg-primary text-white text-center py-4 mb-4">
        <h1>Thời Gian Hoạt Động Của Nhân Viên</h1>
        <p class="lead">Biểu đồ lịch làm việc của các nhân viên trong công ty</p>
    </header>

    <!-- Main Content -->
    <div class="container">
        <!-- Bảng Lịch Làm Việc -->
        <div class="card">
            <div class="card-header text-center">
                Lịch Làm Việc của Nhân Viên (Từ ngày 1/1 đến ngày 9/1)
            </div>
            <div class="card-body">
                <table class="table table-bordered calendar-table">
                    <thead>
                        <tr>
                            <th>Nhân sự</th>
                            <th>1/1</th>
                            <th>2/1</th>
                            <th>3/1</th>
                            <th>4/1</th>
                            <th>5/1</th>
                            <th>6/1</th>
                            <th>7/1</th>
                            <th>8/1</th>
                            <th>9/1</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Dữ liệu nhân viên -->
                        <tr>
                            <td>Mr A</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="leave">❌</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                        </tr>
                        <tr>
                            <td>Mr B</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="leave">❌</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                        </tr>
                        <tr>
                            <td>Mr C</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="leave">❌</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                        </tr>
                        <tr>
                            <td>Mr D</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                            <td class="leave">❌</td>
                            <td class="workday">✔</td>
                            <td class="workday">✔</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="text-center">
        <p>&copy; 2025 Công ty ABC. All Rights Reserved.</p>
    </footer>

    <!-- Thêm Javascript để Bootstrap hoạt động -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
