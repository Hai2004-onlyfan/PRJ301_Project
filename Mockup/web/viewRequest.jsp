<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xem Đơn Xin Nghỉ</title>
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

        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }

        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #f2f2f2;
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

        .btn-delete {
            background-color: #dc3545;
            color: white;
            border-radius: 50px;
            padding: 5px 15px;
            font-size: 14px;
            transition: background-color 0.3s ease;
        }

        .btn-delete:hover {
            background-color: #c82333;
        }

        .btn-view {
            background-color: #28a745;
            color: white;
            border-radius: 50px;
            padding: 5px 15px;
            font-size: 14px;
            transition: background-color 0.3s ease;
        }

        .btn-view:hover {
            background-color: #218838;
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
                Xem Đơn Xin Nghỉ
                <div>
                    <a href="homeE.jsp" class="btn btn-custom">Home</a>
                    <a href="logout.jsp" class="btn btn-custom">Logout</a>
                </div>
            </div>
            <div class="card-body">

                <!-- Bảng thông tin đơn xin nghỉ -->
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Created By</th>
                            <th>Status</th>
                            <th>Processed By</th>
                            <th>Action</th>
                            <th>View</th> <!-- Cột View cho Manager -->
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Dữ liệu đơn xin nghỉ cứng -->
                        <tr>
                            <td><a href="#">Xin nghỉ cưới...</a></td>
                            <td>1/1/2025</td>
                            <td>3/1/2025</td>
                            <td>Mr F</td>
                            <td><span class="text-warning">In Progress</span></td>
                            <td>---</td>
                            <td>
                                <button class="btn btn-delete" onclick="deleteRequest(this)">Xóa</button>
                            </td>
                            <!-- Liên kết tới trang xem chi tiết đơn xin nghỉ -->
                            <td>
                                <a href="viewReqForMag.jsp" class="btn btn-view">View</a>
                            </td>
                        </tr>
                        <tr>
                            <td><a href="#">Xin nghỉ đi chơi</a></td>
                            <td>1/1/2025</td>
                            <td>5/1/2025</td>
                            <td>Mr E</td>
                            <td><span class="text-danger">Rejected</span></td>
                            <td>Mr B</td>
                            <td>
                                <button class="btn btn-delete" onclick="deleteRequest(this)">Xóa</button>
                            </td>
                            <!-- Liên kết tới trang xem chi tiết đơn xin nghỉ -->
                            <td>
                                <a href="viewReqForMag.jsp" class="btn btn-view">View</a>
                            </td>
                        </tr>
                        <tr>
                            <td><a href="#">Xin nghỉ cuối tuần</a></td>
                            <td>2/1/2025</td>
                            <td>4/1/2025</td>
                            <td>Mr D</td>
                            <td><span class="text-success">Approved</span></td>
                            <td>Mr C</td>
                            <td>
                                <button class="btn btn-delete" onclick="deleteRequest(this)">Xóa</button>
                            </td>
                            <!-- Liên kết tới trang xem chi tiết đơn xin nghỉ -->
                            <td>
                                <a href="viewReqForMag.jsp" class="btn btn-view">View</a>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div class="text-center">
                    <a href="homeE.jsp" class="btn btn-custom">Quay lại trang chủ</a>
                </div>

            </div>
        </div>
    </div>

    <div class="footer">
        <p>&copy; 2025 Công ty ABC. All Rights Reserved.</p>
    </div>

    <!-- Thêm Javascript để Bootstrap hoạt động và Xóa -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

    <script>
        function deleteRequest(button) {
            // Cảnh báo trước khi xóa
            if (confirm("Bạn có chắc chắn muốn xóa đơn xin nghỉ này?")) {
                // Xóa hàng tương ứng trong bảng
                var row = button.closest("tr");
                row.remove();
            }
        }
    </script>
</body>
</html>
