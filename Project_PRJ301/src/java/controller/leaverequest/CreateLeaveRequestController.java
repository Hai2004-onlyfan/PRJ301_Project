/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.leaverequest;

import controller.authentication.RequiredAuthenticationBaseController;
import dal.LeaveRequestDBContext;
import models.LeaveRequest;
import models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author sonnt-local hand-some
 */
public class CreateLeaveRequestController extends RequiredAuthenticationBaseController {

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String title = req.getParameter("title");
        String reason = req.getParameter("reason");
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        
        LeaveRequest lr = new LeaveRequest();
        lr.setTitle(title);
        lr.setReason(reason);
        lr.setFrom(Date.valueOf(from));
        lr.setTo(Date.valueOf(to));
        lr.setCreatedby(user);
        
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        db.insert(lr);
         req.setAttribute("success", true);
          req.getRequestDispatcher("../view/leaverequest/create.jsp").forward(req, resp);
    
        System.out.println("inserted successful!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        req.getRequestDispatcher("../view/leaverequest/create.jsp").forward(req, resp);

    }

}
