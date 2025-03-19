package controller.leaverequest;

import controller.authentication.RequiredAuthenticationBaseController;
import dal.LeaveRequestDBContext;
import dal.DepartmentDBContext;
import models.LeaveRequest;
import models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ViewLeaveRequestByIdMag extends RequiredAuthenticationBaseController {

    // Xử lý các yêu cầu POST (cập nhật trạng thái yêu cầu nghỉ)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String reqIdParam = req.getParameter("rid");
        String action = req.getParameter("action");

        if (reqIdParam == null || reqIdParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request ID is missing");
            return;
        }

        Set<String> validActions = new HashSet<>(Set.of("approve", "reject"));
        if (action == null || action.isEmpty() || !validActions.contains(action)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            return;
        }

        try {
            int rid = Integer.parseInt(reqIdParam);
            LeaveRequestDBContext db = new LeaveRequestDBContext();
            int newStatus = 0;  // Default: In Progress
            switch (action) {
                case "approve":
                    newStatus = 2; // 2 = Accepted
                    break;
                case "reject":
                    newStatus = 1; // 1 = Rejected
                    break;

            }

            // Cập nhật trạng thái yêu cầu nghỉ
            int rowsUpdated = db.updateStatus(rid, newStatus);
            if (rowsUpdated == 0) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update status");
                return;
            }
            db.updateApprovedBy(rid, user.getUsername());
            // Sau khi cập nhật trạng thái, chuyển hướng về trang danh sách yêu cầu
            resp.sendRedirect("../view/manager/listActionResult.jsp?updateStatus=success");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Request ID");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        }
    }

    // Xử lý các yêu cầu GET (hiển thị chi tiết yêu cầu nghỉ)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String reqIdParam = req.getParameter("rid");

        if (reqIdParam == null || reqIdParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request ID is missing");
            return;
        }

        try {
            int rid = Integer.parseInt(reqIdParam);
            LeaveRequestDBContext db = new LeaveRequestDBContext();
            LeaveRequest l = db.getById(rid);

            if (l == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Leave Request not found");
                return;
            }

            req.setAttribute("l", l);
            req.getRequestDispatcher("/view/manager/viewLeaveRequest.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Request ID");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        }
    }
}
