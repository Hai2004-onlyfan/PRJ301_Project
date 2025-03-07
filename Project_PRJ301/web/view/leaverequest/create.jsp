<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tạo Yêu Cầu Nghỉ</title>
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
        .btn-home, .btn-back {
            border-radius: 50px;
            padding: 10px 20px;
            text-align: center;
            transition: background-color 0.3s ease;
            text-decoration: none;
            color: white;
        }
        .btn-home {
            background-color: #28a745;
        }
        .btn-home:hover {
            background-color: #218838;
        }
        .btn-back {
            background-color: #6c757d;
        }
        .btn-back:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- Nút Home & Quay lại -->
        <div class="d-flex justify-content-between mb-3">
            <a href="../view/employee/employeeDashboard.jsp" class="btn-home">🏠 Home</a>
            <a href="javascript:history.back()" class="btn-back">🔙 Quay Lại</a>
        </div>

        <div class="card">
            <div class="card-header text-center">
                Tạo Yêu Cầu Nghỉ
            </div>
            <div class="card-body">
                <h3 class="text-center">Chào ${sessionScope.user.displayname}</h3>
                <form action="create" method="POST">
                    <div class="mb-3">
                        <label for="title" class="form-label">Tiêu Đề</label>
                        <input type="text" class="form-control" id="title" name="title" required />
                    </div>
                    <div class="mb-3">
                        <label for="reason" class="form-label">Lý Do</label>
                        <textarea class="form-control" id="reason" name="reason" rows="4" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="from" class="form-label">Ngày Bắt Đầu</label>
                        <input type="date" class="form-control" id="from" name="from" required />
                    </div>
                    <div class="mb-3">
                        <label for="to" class="form-label">Ngày Kết Thúc</label>
                        <input type="date" class="form-control" id="to" name="to" required />
                    </div>
                    <button type="submit" class="btn btn-primary">Gửi Yêu Cầu</button>
                </form>
            </div>
        </div>

        <!-- Hiển thị thông báo thành công sau khi submit -->
        <div class="card mt-4" style="display: <% if(request.getAttribute("success") != null) { %>block<% } else { %>none<% } %>;">
            <div class="card-header text-center">
                Thông Báo
            </div>
            <div class="card-body text-center">
                <p class="text-success">Yêu cầu nghỉ của bạn đã được tạo thành công!</p>
                <a href="../view/employee/employeeDashboard.jsp" class="btn btn-home">🏠 Quay Về Trang Home</a>
            </div>
        </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
