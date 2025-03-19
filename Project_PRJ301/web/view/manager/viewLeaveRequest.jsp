<%@ page import="models.LeaveRequest" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Duyệt Đơn Xin Nghỉ Phép</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
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
        .action-buttons {
            margin-top: 20px;
            text-align: center;
        }
        .action-buttons .btn {
            width: 150px;
            padding: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <a href="../view/manager/managerDashboard.jsp" class="btn-home">🏠 Home</a>

    <div class="card">
        <div class="card-header text-center">
            Duyệt Đơn Xin Nghỉ Phép
        </div>
        <div class="card-body">
            <div class="mb-3">
                <strong>Duyệt bởi User:</strong> ${sessionScope.user.displayname}, Role: ${sessionScope.role}
            </div>
            <div class="mb-3">
                <strong>Tạo bởi:</strong> ${l.createdby.displayname}
            </div>
            <div class="mb-3">
                <strong>Từ ngày:</strong> ${l.from}
            </div>
            <div class="mb-3">
                <strong>Tới ngày:</strong> ${l.to}
            </div>
            <div class="mb-3">
                <strong>Lý do:</strong>
                <p>${l.reason}</p>
            </div>

            <div class="action-buttons">
                <form action="../leaverequest/findbydepts" method="POST" style="display:inline;">
                    <input type="hidden" name="rid" value="${l.id}">
                    <input type="hidden" name="action" value="reject">
                    <button type="submit" class="btn btn-danger">Reject</button>
                </form>

                <form action="../leaverequest/findbydepts" method="POST" style="display:inline;">
                    <input type="hidden" name="rid" value="${l.id}">
                    <input type="hidden" name="action" value="approve">
                    <button type="submit" class="btn btn-success">Approve</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

</body>
</html>
