package controller.leaverequest;

import controller.authentication.RequiredAuthenticationBaseController;
import dal.DepartmentDBContext;
import dal.LeaveRequestDBContext;
import models.LeaveRequest;
import models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewLeaveRequestForEmp extends RequiredAuthenticationBaseController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        DepartmentDBContext deptDB = new DepartmentDBContext();

        // Lấy danh sách yêu cầu nghỉ theo username của nhân viên đang đăng nhập
        ArrayList<LeaveRequest> leaves = db.getByEmployee(user.getUsername());
        Map<Integer, String> approvedByMap = new HashMap<>();

        for (LeaveRequest leave : leaves) {
            String approvedBy = db.getApprovedBy(leave.getId());
            if (approvedBy != null && !approvedBy.isEmpty()) {
                approvedByMap.put(leave.getId(), approvedBy);
            } else {
                approvedByMap.put(leave.getId(), "N/A"); // Hoặc một giá trị mặc định khác
            }
        }

        request.setAttribute("approvedByMap", approvedByMap);
        request.setAttribute("leaves", leaves);
        request.setAttribute("depts", deptDB.list());

        request.getRequestDispatcher("../view/leaverequest/listForAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }
}