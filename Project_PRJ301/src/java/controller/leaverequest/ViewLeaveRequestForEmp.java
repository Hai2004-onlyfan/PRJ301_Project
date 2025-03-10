/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.leaverequest;

import controller.authentication.RequiredAuthenticationBaseController;
import dal.DepartmentDBContext;
import dal.LeaveRequestDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.LeaveRequest;
import models.User;

/**
 *
 * @author ADMIN
 */
public class ViewLeaveRequestForEmp extends RequiredAuthenticationBaseController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,User user)
    throws ServletException, IOException {
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        DepartmentDBContext deptDB = new DepartmentDBContext();
        
        // Lấy danh sách yêu cầu nghỉ theo username của nhân viên đang đăng nhập
        ArrayList<LeaveRequest> leaves = db.getByEmployee(user.getUsername());
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
