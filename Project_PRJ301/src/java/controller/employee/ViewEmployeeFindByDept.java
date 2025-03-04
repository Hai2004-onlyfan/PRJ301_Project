/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.employee;

import controller.authentication.RequiredAuthenticationBaseController;
import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.Employee;

import models.User;

/**
 *
 * @author ADMIN
 */
public class ViewEmployeeFindByDept extends RequiredAuthenticationBaseController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user)
        throws ServletException, IOException {
    
    String did = request.getParameter("did");
    EmployeeDBContext eb = new EmployeeDBContext();
    DepartmentDBContext deptDB = new DepartmentDBContext();
    
    Integer id = null;
    if (did != null && !did.trim().isEmpty() && !did.equals("-1")) {
        try {
            id = Integer.parseInt(did);
        } catch (NumberFormatException e) {
            id = null;
        }
    }

    // Lấy danh sách nhân viên theo phòng ban
    ArrayList<Employee> employees = eb.getByDepartment(id);

    request.setAttribute("employees", employees);
    request.setAttribute("depts", deptDB.list());
    request.getRequestDispatcher("../view/employee/list.jsp").forward(request, response);
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

 

    