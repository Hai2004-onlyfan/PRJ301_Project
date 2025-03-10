<%@page import="java.util.ArrayList" %>
<%@page import="models.LeaveRequest" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh Sách Yêu Cầu Nghỉ</title>
        <!-- Thêm Bootstrap CSS -->
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
            .action-buttons a {
                padding: 5px 15px;
                border-radius: 5px;
                text-decoration: none;
            }
            .btn-accept {
                background-color: #28a745;
                color: white;
            }
            .btn-reject {
                background-color: #dc3545;
                color: white;
            }
            .btn-in-progress {
                background-color: #ffc107;
                color: white;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="d-flex justify-content-end mb-3">
                <a href="../view/manager/managerDashboard.jsp" class="btn-home">🏠 Home</a>
            </div>

            <div class="card">
                <div class="card-header text-center">
                    Danh Sách Yêu Cầu Nghỉ
                </div>
                <div class="card-body">
                    <!-- Bộ lọc phòng ban -->
                    <form id="search" action="viewformag" method="GET" class="mb-4">
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
                    <c:if test="${param.updateStatus == 'success'}">
                        <div class="alert alert-success">
                            Trạng thái yêu cầu đã được cập nhật thành công!
                        </div>
                        <a href="../manager/managerDashboard.jsp" class="btn btn-primary">Quay về trang</a>
                    </c:if>
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
                                <th>Action</th> <!-- Cột hành động cho Manager -->
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
                                    <td class="action-buttons">
                                        <form action="../leaverequest/viewformag" method="POST" style="display:inline;">
                                            <input type="hidden" name="rid" value="${l.id}"> <!-- ID yêu cầu nghỉ -->
                                            <input type="hidden" name="action" value="approve"> <!-- Hành động là "approve" -->
                                            <button type="submit" class="btn-accept">Approve</button>
                                        </form>

                                        <!-- Form POST cho hành động "Reject" -->
                                        <form action="../leaverequest/viewformag" method="POST" style="display:inline;">
                                            <input type="hidden" name="rid" value="${l.id}"> <!-- ID yêu cầu nghỉ -->
                                            <input type="hidden" name="action" value="reject"> <!-- Hành động là "reject" -->
                                            <button type="submit" class="btn-reject">Reject</button>
                                        </form>

                                        <!-- Form POST cho hành động "In Progress" -->
                                        <form action="../leaverequest/viewformag" method="POST" style="display:inline;">
                                            <input type="hidden" name="rid" value="${l.id}"> <!-- ID yêu cầu nghỉ -->
                                            <input type="hidden" name="action" value="inprogress"> <!-- Hành động là "inprogress" -->
                                            <button type="submit" class="btn-in-progress">In Progress</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

    </body>
</html>
