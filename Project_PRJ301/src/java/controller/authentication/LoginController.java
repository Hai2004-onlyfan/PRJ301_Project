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

            // Xác định vai trò người dùng và lưu vào session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            
            // Phân quyền và chuyển hướng dựa trên vai trò
            if (user.isDirector()) {  // Kiểm tra nếu là Director
                resp.sendRedirect("view/manager/managerDashboard.jsp");  // Chuyển hướng đến trang của Director
            } else if (user.isManager()) {  // Kiểm tra nếu là Manager
                resp.sendRedirect("view/manager/managerDashboard.jsp");   // Chuyển hướng đến trang của Manager
            } else if (user.isEmployee()) {  // Kiểm tra nếu là Employee
                resp.sendRedirect("view/employee/employeeDashboard.jsp");  // Chuyển hướng đến trang của Employee
            } else {
                resp.getWriter().println("Role not assigned properly!");
            }
        } else {
            resp.getWriter().println("login failed!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/auth/login.jsp").forward(req, resp);
    }
}
