package controller.leaverequest;

import controller.authentication.RequiredAuthenticationBaseController;
import dal.AgendaDBContext;
import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import models.AttendanceRecord;
import models.User;

public class ViewAgendaController extends RequiredAuthenticationBaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        // Lấy ngày bắt đầu và ngày kết thúc từ request
        String fromDateStr = req.getParameter("fromDate");
    String toDateStr = req.getParameter("toDate");
    String employeeName = req.getParameter("employeeName"); // Lấy tên nhân viên từ form

    // Nếu không có input, lấy khoảng thời gian mặc định (7 ngày gần nhất)
    Date fromDate = (fromDateStr != null && !fromDateStr.isEmpty()) ? Date.valueOf(fromDateStr) : new Date(System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000);
    Date toDate = (toDateStr != null && !toDateStr.isEmpty()) ? Date.valueOf(toDateStr) : new Date(System.currentTimeMillis());

    // 1️⃣ Lấy danh sách nhân viên từ UserDBContext
    UserDBContext userDB = new UserDBContext();
    ArrayList<User> allUsers = userDB.getAllUsers();
    ArrayList<User> filteredUsers = new ArrayList<>();

    // Nếu có nhập tên, lọc theo tên
    if (employeeName != null && !employeeName.trim().isEmpty()) {
        for (User u : allUsers) {
            if (u.getDisplayname().toLowerCase().contains(employeeName.toLowerCase())) {
                filteredUsers.add(u);
            }
        }
    } else {
        filteredUsers = allUsers; // Nếu không nhập gì, lấy tất cả nhân viên
    }

    // 2️⃣ Lấy danh sách chấm công
    AgendaDBContext agendaDB = new AgendaDBContext();
    ArrayList<AttendanceRecord> attendanceList = agendaDB.getEmployeeAttendance(fromDate, toDate);

    // 3️⃣ Đưa vào request để hiển thị trên JSP
    req.setAttribute("users", filteredUsers);
    req.setAttribute("attendanceList", attendanceList);
    req.setAttribute("fromDate", fromDate);
    req.setAttribute("toDate", toDate);
    req.setAttribute("employeeName", employeeName);

        // 4️⃣ Chuyển hướng sang trang JSP để hiển thị bảng lịch làm việc
        req.getRequestDispatcher("/view/agenda/viewAgenda.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        // Xử lý khi người dùng lọc theo ngày
        doGet(req, resp, user);
    }

    @Override
    public String getServletInfo() {
        return "Hiển thị lịch làm việc và nghỉ phép của nhân viên";
    }
}
