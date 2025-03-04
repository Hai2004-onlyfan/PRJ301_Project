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
"    emp.eid, emp.ename, d.did, d.dname, h.managerid, h.Level, \n" +
"    m.eid AS managerid, m.ename AS managername \n" +
"FROM EmployeeHierarchy h \n" +
"INNER JOIN Employees emp ON emp.eid = h.eid  -- Đổi alias từ e -> emp\n" +
"INNER JOIN Departments d ON d.did = emp.did  -- Kết nối với bảng Departments\n" +
"LEFT JOIN Employees m ON m.eid = h.managerid -- Lấy thông tin manager nếu có\n" +
"WHERE d.did = ? OR h.eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next())
            {
                    Employee e = new Employee();
                    e.setId(rs.getInt("eid"));
                    e.setName(rs.getString("ename"));
                    Department d = new Department();
                    d.setId(rs.getInt("did"));
                    d.setName(rs.getString("dname"));
                    e.setDept(d);
                    int mid = rs.getInt("managerid");
                    if(mid != 0)
                    {
                        e.setManager(new Employee());
                        e.getManager().setId(mid);
                        e.getManager().setName(rs.getString("managername"));
                    }
                    employees.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            
        }
        if(!employees.isEmpty())
            {
                Employee root = employees.get(0);
                for (Employee employee : employees) {
                    if(employee != root)
                    {
                        //look up for its manager
                        Employee manager = getManagerNode(employees,employee);
                        employee.setManager(manager);
                        manager.getDirectstaffs().add(employee);
                        root.getStaffs().add(employee);
                    }
                }
                return root;
            }
            else 
                return null;
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
        // Khởi tạo câu SQL ban đầu
        String sql = "SELECT e.eid, e.ename, d.did, d.dname " +
                     "FROM Employees e " +
                     "INNER JOIN Departments d ON e.did = d.did ";
        
        // Thêm điều kiện WHERE nếu did không phải null
        if (did != null) {
            sql += "WHERE d.did = ? ";
        }

        // Chuẩn bị câu lệnh SQL
        stm = connection.prepareStatement(sql);
        
        // Nếu did có giá trị, gán giá trị cho parameter
        if (did != null) {
            stm.setInt(1, did);
        }

        // Thực thi câu lệnh SQL
        rs = stm.executeQuery();

        // Duyệt kết quả và tạo danh sách nhân viên
        while (rs.next()) {
            Employee e = new Employee();
            e.setId(rs.getInt("eid"));
            e.setName(rs.getString("ename"));

            Department d = new Department();
            d.setId(rs.getInt("did"));
            d.setName(rs.getString("dname"));
            e.setDept(d);

            employees.add(e);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        throw new RuntimeException("Lỗi khi truy vấn nhân viên theo phòng ban", ex);
    } finally {
        // Đảm bảo đóng kết nối, PreparedStatement, và ResultSet sau khi sử dụng
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



}
 