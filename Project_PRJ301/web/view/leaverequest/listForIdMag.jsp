<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Yêu Cầu Nghỉ</title>
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
        .table th {
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- Nút Home -->
        <div class="d-flex justify-content-end mb-3">
            <a href="../view/manager/managerDashboard.jsp" class="btn-home">🏠 Home</a>
        </div>

        <div class="card">
            <div class="card-header text-center">
                Danh Sách Yêu Cầu Nghỉ
            </div>
            <div class="card-body">
                <!-- Hiển thị thông báo lỗi nếu có -->
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>

                <!-- Thêm thông báo cho người quản lý -->
                <div class="alert alert-info" role="alert">
                    Bạn chỉ có thể xem yêu cầu nghỉ của phòng ban mà bạn quản lý.
                </div>

                <!-- Form chọn phòng ban -->
                <form id="search" action="findbydept" method="GET" class="mb-4">
                    <div class="row">
                        <div class="col-md-4">
                            <label for="departmentSelect" class="form-label">Chọn Phòng Ban</label>
                            <select name="did" id="departmentSelect" class="form-select" onchange="document.getElementById('search').submit();">
                                <option value="-1">---Tất cả---</option>
                                <c:forEach items="${requestScope.depts}" var="d">
                                    <option <c:if test="${d.id eq param.did}">selected</c:if> value="${d.id}">${d.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>

                <!-- Bảng danh sách yêu cầu nghỉ -->
                <table class="table table-striped table-bordered table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Reason</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Created By</th>
                            <th>Created Date</th>
                            <th>Status</th>
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
                                <td>${l.createdby.displayname}</td>
                                <td>${l.createddate}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${l.status eq 0}">In Progress</c:when>
                                        <c:when test="${l.status eq 1}">Rejected</c:when>
                                        <c:otherwise>Accepted</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Thêm Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

</body>
</html>
