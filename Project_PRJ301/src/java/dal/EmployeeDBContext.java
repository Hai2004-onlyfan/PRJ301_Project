/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import models.Department;
import models.Employee;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author sonnt-local
 */
public class EmployeeDBContext extends DBContext<Employee> {

    @Override
    public ArrayList<Employee> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Employee get(int id) {
       ArrayList<Employee> employees = new ArrayList<>();
    try {
        String sql = "WITH EmployeeHierarchy AS (\n" +
            "    SELECT \n" +
            "        eid,\n" +
            "        managerid,\n" +
            "        0 AS Level\n" +
            "    FROM Employees\n" +
            "    WHERE eid = ?\n" +
            "    \n" +
            "    UNION ALL\n" +
            "\n" +
            "    SELECT e.eid, e.managerid, m.Level + 1 AS Level \n" +
            "    FROM Employees e\n" +
            "    INNER JOIN EmployeeHierarchy m ON m.eid = e.managerid\n" +
        ")\n" +
        "SELECT \n" +
        "    emp.eid, emp.ename, emp.phone, emp.dob, d.did, d.dname, h.managerid, h.Level, \n" +
        "    m.eid AS managerid, m.ename AS managername \n" +
        "FROM EmployeeHierarchy h \n" +
        "INNER JOIN Employees emp ON emp.eid = h.eid\n" +
        "INNER JOIN Departments d ON d.did = emp.did\n" +
        "LEFT JOIN Employees m ON m.eid = h.managerid\n" +
        "WHERE d.did = ? OR h.eid = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id); // id of the employee to get
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()) {
            Employee e = new Employee();
            e.setId(rs.getInt("eid"));
            e.setName(rs.getString("ename"));
            e.setPhone(rs.getString("phone"));
            e.setDob(rs.getDate("dob"));
            
            Department d = new Department();
            d.setId(rs.getInt("did"));
            d.setName(rs.getString("dname"));
            e.setDept(d);
            
            int mid = rs.getInt("managerid");
            if(mid != 0) {
                e.setManager(new Employee());
                e.getManager().setId(mid);
                e.getManager().setName(rs.getString("managername"));
            }
            employees.add(e);
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }

    if(!employees.isEmpty()) {
        Employee root = employees.get(0);
        for (Employee employee : employees) {
            if(employee != root) {
                //look up for its manager
                Employee manager = getManagerNode(employees, employee);
                employee.setManager(manager);
                manager.getDirectstaffs().add(employee);
                root.getStaffs().add(employee);
            }
        }
        return root;
    } else {
        return null;
    }
}
    
    private Employee getManagerNode(ArrayList<Employee> emps, Employee e)
    {
        for (Employee emp : emps) {
            if(emp.getId() == e.getManager().getId())
            {
                return emp;
            }
        }
        return null;
    }

    @Override
    public void insert(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
public ArrayList<Employee> getByDepartment(Integer did) {
      ArrayList<Employee> employees = new ArrayList<>();
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT e.eid, e.ename, e.phone, e.dob, e.did, e.managerid, d.dname, d.did AS dept_id, m.ename AS manager_name " +
                     "FROM Employees e " +
                     "INNER JOIN Departments d ON e.did = d.did " +
                     "LEFT JOIN Employees m ON e.managerid = m.eid "; // Kết nối với bảng Employees để lấy tên trưởng phòng

        // Thêm điều kiện WHERE nếu did không phải null
        if (did != null) {
            sql += "WHERE e.did = ? ";
        }

        stm = connection.prepareStatement(sql);

        if (did != null) {
            stm.setInt(1, did);
        }

        rs = stm.executeQuery();

        while (rs.next()) {
            Employee e = new Employee();
            e.setId(rs.getInt("eid"));
            e.setName(rs.getString("ename"));
            e.setPhone(rs.getString("phone"));
            e.setDob(rs.getDate("dob"));

            Department d = new Department();
            d.setId(rs.getInt("dept_id"));
            d.setName(rs.getString("dname"));
            e.setDept(d);

            // Lấy thông tin trưởng phòng
            String managerName = rs.getString("manager_name");
            if (managerName != null) {
                Employee manager = new Employee();
                manager.setName(managerName);
                e.setManager(manager); // Thiết lập trưởng phòng
            }

            employees.add(e);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        throw new RuntimeException("Lỗi khi truy vấn nhân viên theo phòng ban", ex);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Lỗi khi đóng tài nguyên cơ sở dữ liệu", ex);
        }
    }
    return employees;
}
   public Employee getEmployeeByUser(User user) {
        Employee emp = null;
        try {
            String sql = "SELECT e.eid, e.ename, e.did, d.dname " +
                         "FROM Employees e " +
                         "INNER JOIN Departments d ON e.did = d.did " +
                         "WHERE e.eid = (SELECT eid FROM Users WHERE username = ?)";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUsername());  // Sử dụng username để tìm nhân viên

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                emp = new Employee();
                emp.setId(rs.getInt("eid"));
                emp.setName(rs.getString("ename"));
                
                Department dept = new Department();
                dept.setId(rs.getInt("did"));
                dept.setName(rs.getString("dname"));
                
                emp.setDept(dept);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return emp;
    }
}