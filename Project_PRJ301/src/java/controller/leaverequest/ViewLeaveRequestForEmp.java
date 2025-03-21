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

public class ViewLeaveRequestForEmp extends RequiredAuthenticationBaseController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        DepartmentDBContext deptDB = new DepartmentDBContext();

        ArrayList<LeaveRequest> leaves = db.getByEmployee(user.getUsername());

        for (LeaveRequest leave : leaves) {
    String approvedBy = db.getApprovedBy(leave.getId());
    leave.setApprovedBy((approvedBy == null || approvedBy.isEmpty()) ? "N/A" : approvedBy);
}

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