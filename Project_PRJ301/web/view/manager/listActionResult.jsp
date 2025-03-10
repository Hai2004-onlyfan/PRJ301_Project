<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Báo Cập Nhật</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="card mt-5">
            <div class="card-header text-center">
                Kết Quả Cập Nhật Trạng Thái
            </div>
            <div class="card-body">
                <!-- Hiển thị thông báo thành công -->
                <c:if test="${param.updateStatus == 'success'}">
                    <div class="alert alert-success">
                        Trạng thái yêu cầu đã được cập nhật thành công!
                    </div>
                </c:if>

                <!-- Nút Quay về Màn hình Chính -->
                <div class="d-flex justify-content-center">
                    <a href="../../view/manager/managerDashboard.jsp" class="btn btn-primary">Quay về màn hình chính</a>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
