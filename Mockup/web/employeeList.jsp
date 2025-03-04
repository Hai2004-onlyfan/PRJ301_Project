<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        footer {
            background-color: #343a40;
            color: white;
            padding: 20px;
        }
        .filter-section {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

    <!-- Header -->
    <header class="bg-primary text-white text-center py-4 mb-4">
        <h1>Danh Sách Nhân Viên</h1>
    </header>

    <!-- Main Content -->
    <div class="container">

        <!-- Phần Lọc Phòng Ban -->
        <div class="filter-section">
            <form method="get" action="employeeList.jsp">
                <div class="row">
                    <div class="col-md-6">
                        <label for="departmentSelect" class="form-label">Chọn Phòng Ban</label>
                        <select class="form-select" id="departmentSelect" name="department">
                            <option value="">Tất cả</option>
                            <option value="IT">Phòng IT</option>
                            <option value="HR">Phòng HR</option>
                            <option value="Finance">Phòng Tài Chính</option>
                            <option value="Marketing">Phòng Marketing</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-primary mt-4">Lọc</button>
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
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Họ và Tên</th>
                            <th>Ngày Sinh</th>
                            <th>Phòng Ban</th>
                            <th>Trưởng Phòng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Hiển thị danh sách 5 nhân viên cứng -->
                        <tr>
                            <td>1</td>
                            <td>Nguyễn Văn A</td>
                            <td>1990-01-01</td>
                            <td>IT</td>
                            <td>Trần Văn B</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Phan Thị C</td>
                            <td>1989-02-15</td>
                            <td>IT</td>
                            <td>Trần Văn B</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Trần Minh D</td>
                            <td>1995-03-22</td>
                            <td>IT</td>
                            <td>Trần Văn B</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Vũ Thị E</td>
                            <td>1992-04-30</td>
                            <td>IT</td>
                            <td>Trần Văn B</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>Hoàng Minh F</td>
                            <td>1988-06-12</td>
                            <td>IT</td>
                            <td>Trần Văn B</td>
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
