<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="models.User"%>
<%@page import="models.AttendanceRecord"%>

<%
    // Lấy dữ liệu từ request
    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
    ArrayList<AttendanceRecord> attendanceList = (ArrayList<AttendanceRecord>) request.getAttribute("attendanceList");
    Date fromDate = (Date) request.getAttribute("fromDate");
    Date toDate = (Date) request.getAttribute("toDate");
    String employeeName = (String) request.getAttribute("employeeName");

    long interval = 24 * 60 * 60 * 1000; // 1 ngày
    int maxDays = 7; // Giới hạn hiển thị 7 ngày
    int totalDays = (int) ((toDate.getTime() - fromDate.getTime()) / interval) + 1;

    // Nếu số ngày lớn hơn 7, chỉ lấy 7 ngày đầu tiên
    boolean showWarning = totalDays > maxDays;
    totalDays = Math.min(totalDays, maxDays);
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Agenda</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .workday, .leaveday {
            text-align: center;
            text-indent: -9999px; /* Đẩy chữ ra ngoài vùng hiển thị */
            white-space: nowrap;  /* Ngăn chữ xuống dòng */
        }
        .workday {
            background-color: #28a745 !important; /* Màu xanh */
        }
        .leaveday {
            background-color: #dc3545 !important; /* Màu đỏ */
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <div class="mb-4">
            <a href="javascript:history.back()" class="btn btn-secondary">Quay lại</a>
        </div>

        <h2 class="mb-4">Lịch làm việc</h2>

        <form method="GET" action="viewAgenda">
            <div class="row g-3">
                <div class="col-md-3">
                    <label for="fromDate" class="form-label">Từ ngày:</label>
                    <input type="date" id="fromDate" name="fromDate" class="form-control" value="<%= fromDate %>">
                </div>
                <div class="col-md-3">
                    <label for="toDate" class="form-label">Đến ngày:</label>
                    <input type="date" id="toDate" name="toDate" class="form-control" value="<%= toDate %>">
                </div>
                <div class="col-md-3">
                    <label for="employeeName" class="form-label">Tên nhân viên:</label>
                    <input type="text" id="employeeName" name="employeeName" class="form-control"
                                   value="<%= employeeName != null ? employeeName : "" %>">
                </div>
                <div class="col-md-3 d-flex align-items-end">
                    <button type="submit" class="btn btn-primary">Lọc</button>
                </div>
            </div>
        </form>

        <br>

        <% if (showWarning) { %>
            <div class="alert alert-warning">
                Bạn đã chọn hơn 7 ngày. Chỉ hiển thị 7 ngày đầu tiên!
            </div>
        <% } %>

        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Nhân viên</th>
                    <%
                        int dayCount = 0;
                        for (long time = fromDate.getTime(); dayCount < totalDays; time += interval, dayCount++) {
                            Date currentDate = new Date(time);
                    %>
                    <th><%= currentDate %></th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <% if (users.isEmpty()) { %>
                    <tr>
                        <td colspan="<%= totalDays + 1 %>" class="text-center text-danger">
                            Không tìm thấy nhân viên phù hợp!
                        </td>
                    </tr>
                <% } else {
                    for (User user : users) { %>
                    <tr>
                        <td><%= user.getDisplayname() %></td>
                        <%
                            dayCount = 0;
                            for (long time = fromDate.getTime(); dayCount < totalDays; time += interval, dayCount++) {
                                Date currentDate = new Date(time);
                                String status = "Work"; // Mặc định là đi làm
                                for (AttendanceRecord record : attendanceList) {
                                    if (record.getUser().getUsername().equals(user.getUsername()) &&
                                        record.getWorkDate().equals(currentDate)) {
                                        status = record.getStatus();
                                        break;
                                    }
                                }
                        %>
                        <td class="<%= status.equals("Leave") ? "leaveday" : "workday" %>"></td>
                        <% } %>
                    </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
</body>
</html>