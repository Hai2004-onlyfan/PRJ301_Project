package controller.authentication;

import dal.EmployeeDBContext;
import dal.UserDBContext;
import models.Employee;
import models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import models.Department;

public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDBContext db = new UserDBContext();
        User user = db.get(username, password);

        if (user != null) {
            EmployeeDBContext edb = new EmployeeDBContext();
            Employee profile = edb.get(user.getE().getId());
            user.setE(profile);

            // Lưu thông tin user vào session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            String role = user.getRoles().get(0).getName();
            session.setAttribute("role", role);
            //lỗi department
            String department = "Phòng ban lỗi"; // Mặc định nếu không có phòng ban
            Employee employee = user.getE();
            if (employee != null) {
                Department dept = employee.getDept();
                if (dept != null) {
                    department = dept.getName(); // Lấy phòng ban từ Employee
                }
            }
            session.setAttribute("department", department);
            // Chuyển hướnString role = user.getRoles().get(0).getName();g dựa trên vai trò người dùng
            String redirectURL = "view/employee/employeeDashboard.jsp"; // Mặc định là Employee

            if (user.isDirector()) {
                redirectURL = "view/manager/managerDashboard.jsp";
            } else if (user.isManager()) {
                redirectURL = "view/manager/managerDashboard.jsp";
            }

            resp.sendRedirect(redirectURL);
        } else {
            req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            req.getRequestDispatcher("view/auth/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/auth/login.jsp").forward(req, resp);
    }
}
