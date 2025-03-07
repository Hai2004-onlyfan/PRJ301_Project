package controller.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy session hiện tại
        HttpSession session = req.getSession(false);
        
        if (session != null) {
            // Xóa toàn bộ thông tin trong session (bao gồm thông tin người dùng)
            session.invalidate();
        }
        
        // Sau khi đăng xuất, chuyển hướng người dùng về trang đăng nhập
        resp.sendRedirect("view/auth/login.jsp");  // Bạn có thể thay đổi đường dẫn nếu cần
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Gọi phương thức doPost để xử lý logout (hoặc có thể để doGet nếu chỉ cần đơn giản xóa session)
        doPost(req, resp);
    }
}
