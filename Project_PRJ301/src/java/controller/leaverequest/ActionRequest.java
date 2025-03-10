package controller.leaverequest;

import controller.authentication.RequiredAuthenticationBaseController;
import dal.LeaveRequestDBContext;
import dal.DepartmentDBContext; // Thêm
import models.LeaveRequest;
import models.User;
import models.Department; // Thêm
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActionRequest extends RequiredAuthenticationBaseController {

    // Xử lý các yêu cầu POST (cập nhật trạng thái yêu cầu nghỉ)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        // Lấy tham số reqId và action từ URL
        String reqIdParam = req.getParameter("rid");
        String action = req.getParameter("action");

        // Kiểm tra nếu reqId bị thiếu hoặc rỗng
        if (reqIdParam == null || reqIdParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request ID is missing");
            return;
        }

        // Kiểm tra nếu action bị thiếu hoặc không hợp lệ
        Set<String> validActions = new HashSet<>(Set.of("approve", "reject", "inprogress"));
        if (action == null || action.isEmpty() || !validActions.contains(action)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            return;
        }

        try {
            int rid = Integer.parseInt(reqIdParam); // Chuyển reqId thành số nguyên

            // Khởi tạo đối tượng xử lý LeaveRequestDBContext
            LeaveRequestDBContext db = new LeaveRequestDBContext();

            // Kiểm tra hành động và cập nhật trạng thái
            int newStatus = 0;  // Default: In Progress
            switch (action) {
                case "approve":
                    newStatus = 2; // 2 = Accepted
                    break;
                case "reject":
                    newStatus = 1; // 1 = Rejected
                    break;
                case "inprogress":
                    newStatus = 0; // 0 = In Progress
                    break;
            }

            // Gọi phương thức updateStatus để cập nhật trạng thái trong cơ sở dữ liệu
            int rowsUpdated = db.updateStatus(rid, newStatus); // Cập nhật trạng thái yêu cầu nghỉ
            if (rowsUpdated == 0) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update status. Please check if the Request ID exists.");
                return;
            }

            // Sau khi cập nhật trạng thái, chuyển hướng lại trang danh sách yêu cầu
            resp.sendRedirect("../view/manager/listActionResult.jsp?updateStatus=success");  // Chuyển hướng về trang danh sách yêu cầu của Manager

        } catch (NumberFormatException e) {
            // Nếu reqId không thể chuyển thành Integer, trả về lỗi
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Request ID");
        } catch (Exception e) {
            // Xử lý lỗi bất kỳ khác
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        }
    }

    // Xử lý các yêu cầu GET (hiển thị danh sách yêu cầu nghỉ)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        // Lấy tham số did từ yêu cầu
        String did = req.getParameter("did");
        Integer id = null;

        if (did != null && !did.trim().isEmpty() && !did.equals("-1")) {
            try {
                id = Integer.parseInt(did);  // Chuyển đổi tham số did thành Integer nếu có giá trị hợp lệ
            } catch (NumberFormatException e) {
                id = null;
            }
        }

        // Lấy danh sách yêu cầu nghỉ (tùy thuộc vào did)
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        DepartmentDBContext deptDB = new DepartmentDBContext(); 
        ArrayList<LeaveRequest> leaves = db.getByDept(id);

        // Đặt thuộc tính cho JSP để hiển thị
        req.setAttribute("leaves", leaves);
        req.setAttribute("depts", deptDB.list()); // Đặt danh sách phòng ban vào request

        // Chuyển tiếp đến trang danh sách yêu cầu
        req.getRequestDispatcher("/view/manager/listAction.jsp").forward(req, resp);
    }
}
