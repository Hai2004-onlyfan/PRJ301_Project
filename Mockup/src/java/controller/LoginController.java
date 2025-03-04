package controller;
import jakarta.servlet.*;

import jakarta.servlet.http.*;
import java.io.IOException;



public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin người dùng từ form đăng nhập
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra tài khoản và mật khẩu cứng
        if ("hai".equals(username) && "123".equals(password)) {
            // Nếu là director, chuyển đến trang quản lý director
            response.sendRedirect("directorPage.jsp");
        } else if ("an".equals(username) && "123".equals(password)) {
            // Nếu là employee, chuyển đến trang quản lý employee
            response.sendRedirect("employeePage.jsp");
        } else {
            // Nếu tài khoản hoặc mật khẩu sai, quay lại trang đăng nhập với thông báo lỗi
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
