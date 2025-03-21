
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import models.Department;
import models.Employee;
import models.LeaveRequest;
import models.User;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt-local
 */
public class LeaveRequestDBContext extends DBContext<LeaveRequest> {

    @Override
    public ArrayList<LeaveRequest> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LeaveRequest get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(LeaveRequest model) {
        try {
            String sql = "INSERT INTO [LeaveRequest]\n"
                    + "           ([title]\n"
                    + "           ,[reason]\n"
                    + "           ,[from]\n"
                    + "           ,[to]\n"
                    + "           ,[createdby]\n"
                    + "           ,[status]\n"
                    + "		   ,[createddate]\n"
                    + "		   )\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,0,GETDATE())";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getTitle());
            stm.setString(2, model.getReason());
            stm.setDate(3, model.getFrom());
            stm.setDate(4, model.getTo());
            stm.setString(5, model.getCreatedby().getUsername());
            stm.executeUpdate();//update insert delete
        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null)
                try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<LeaveRequest> getByDept(Integer did) {
        ArrayList<LeaveRequest> requests = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "	r.rid,r.title,r.reason,r.[from],r.[to],u.username,u.displayname,\n"
                    + "	r.createddate,r.status,\n"
                    + "	d.did,d.dname,\n"
                    + "	e.eid,e.ename\n"
                    + "FROM LeaveRequest r INNER JOIN Users u ON u.username = r.createdby\n"
                    + "			INNER JOIN Employees e ON e.eid = u.eid\n"
                    + "			INNER JOIN Departments d ON d.did = e.did";

            if (did != null) {
                sql += " WHERE d.did = ? ";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (did != null) {
                stm.setInt(1, did);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LeaveRequest r = new LeaveRequest();
                r.setId(rs.getInt("rid"));
                r.setTitle(rs.getString("title"));
                r.setReason(rs.getString("reason"));
                r.setFrom(rs.getDate("from"));
                r.setTo(rs.getDate("to"));
                r.setCreateddate(rs.getTimestamp("createddate"));
                r.setStatus(rs.getInt("status"));

                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setDisplayname(rs.getString("displayname"));
                r.setCreatedby(u);

                Employee e = new Employee();
                u.setE(e);
                e.setId(rs.getInt("eid"));
                e.setName(rs.getString("ename"));

                Department d = new Department();
                e.setDept(d);
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));

                requests.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null)
                try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requests;
    }

    @Override
    public void update(LeaveRequest model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(LeaveRequest model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


public int updateStatus(int rid, int status) {
       String sql = "UPDATE LeaveRequest SET status = ? WHERE rid = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setInt(1, status);   // Cập nhật trạng thái
        stm.setInt(2, rid);      // Đặt điều kiện WHERE với ID của yêu cầu nghỉ (rid)
        return stm.executeUpdate();  // Trả về số dòng bị ảnh hưởng (số yêu cầu nghỉ được cập nhật)
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;  // Nếu có lỗi xảy ra, trả về 0 (không có dòng nào được cập nhật)
    }
}

public ArrayList<LeaveRequest> getWithAction(Integer did, Integer status) {
    ArrayList<LeaveRequest> requests = new ArrayList<>();
    try {
        String sql = "SELECT r.rid, r.title, r.reason, r.[from], r.[to], u.username, u.displayname,\n"
                + "r.createddate, r.status,\n"
                + "d.did, d.dname,\n"
                + "e.eid, e.ename\n"
                + "FROM LeaveRequest r\n"
                + "INNER JOIN Users u ON u.username = r.createdby\n"
                + "INNER JOIN Employees e ON e.eid = u.eid\n"
                + "INNER JOIN Departments d ON d.did = e.did";

        // Nếu did không phải null, thêm điều kiện WHERE
        if (did != null) {
            sql += " WHERE d.did = ?";
        }

        // Nếu status không phải null, thêm điều kiện để lọc theo trạng thái
        if (status != null) {
            if (did != null) {
                sql += " AND r.status = ?";
            } else {
                sql += " WHERE r.status = ?";
            }
        }

        PreparedStatement stm = connection.prepareStatement(sql);

        // Đặt tham số cho truy vấn
        int paramIndex = 1;

        if (did != null) {
            stm.setInt(paramIndex++, did);
        }

        if (status != null) {
            stm.setInt(paramIndex, status);
        }

        ResultSet rs = stm.executeQuery();

        // Duyệt qua kết quả và tạo các đối tượng LeaveRequest
        while (rs.next()) {
            LeaveRequest r = new LeaveRequest();
            r.setId(rs.getInt("rid"));
            r.setTitle(rs.getString("title"));
            r.setReason(rs.getString("reason"));
            r.setFrom(rs.getDate("from"));
            r.setTo(rs.getDate("to"));
            r.setCreateddate(rs.getTimestamp("createddate"));
            r.setStatus(rs.getInt("status"));

            User u = new User();
            u.setUsername(rs.getString("username"));
            u.setDisplayname(rs.getString("displayname"));
            r.setCreatedby(u);

            Employee e = new Employee();
            e.setId(rs.getInt("eid"));
            e.setName(rs.getString("ename"));
            u.setE(e);

            Department d = new Department();
            d.setId(rs.getInt("did"));
            d.setName(rs.getString("dname"));
            e.setDept(d);

            // Thêm yêu cầu nghỉ vào danh sách
            requests.add(r);
        }

    } catch (SQLException ex) {
        Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Đảm bảo đóng kết nối nếu cần thiết
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    return requests;
}
public ArrayList<LeaveRequest> getByEmployee(String username) {
    ArrayList<LeaveRequest> requests = new ArrayList<>();
    try {
        String sql = "SELECT r.rid, r.title, r.reason, r.[from], r.[to], u.username, u.displayname,\n"
                + "r.createddate, r.status,\n"
                + "d.did, d.dname,\n"
                + "e.eid, e.ename\n"
                + "FROM LeaveRequest r\n"
                + "INNER JOIN Users u ON u.username = r.createdby\n"
                + "INNER JOIN Employees e ON e.eid = u.eid\n"
                + "INNER JOIN Departments d ON d.did = e.did"
                + " WHERE u.username = ?"; // Lọc theo username

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, username); // Đặt tham số username cho truy vấn

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            LeaveRequest r = new LeaveRequest();
            r.setId(rs.getInt("rid"));
            r.setTitle(rs.getString("title"));
            r.setReason(rs.getString("reason"));
            r.setFrom(rs.getDate("from"));
            r.setTo(rs.getDate("to"));
            r.setCreateddate(rs.getTimestamp("createddate"));
            r.setStatus(rs.getInt("status"));

            User u = new User();
            u.setUsername(rs.getString("username"));
            u.setDisplayname(rs.getString("displayname"));
            r.setCreatedby(u);

            Employee e = new Employee();
            e.setId(rs.getInt("eid"));
            e.setName(rs.getString("ename"));
            u.setE(e);

            Department d = new Department();
            d.setId(rs.getInt("did"));
            d.setName(rs.getString("dname"));
            e.setDept(d);

            requests.add(r);
        }

    } catch (SQLException ex) {
        Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    return requests;
}
 public ArrayList<LeaveRequest> getByDept2(Integer departmentId) {
        ArrayList<LeaveRequest> requests = new ArrayList<>();
        try {
            // Truy vấn lấy thông tin yêu cầu nghỉ của nhân viên thuộc phòng ban có did = departmentId
            String sql = "SELECT r.rid, r.title, r.reason, r.[from], r.[to], u.username, u.displayname,\n"
                    + "r.createddate, r.status,\n"
                    + "d.did, d.dname,\n"
                    + "e.eid, e.ename\n"
                    + "FROM LeaveRequest r\n"
                    + "INNER JOIN Users u ON u.username = r.createdby\n"
                    + "INNER JOIN Employees e ON e.eid = u.eid\n"
                    + "INNER JOIN Departments d ON d.did = e.did"
                    + " WHERE d.did = ?"; // Lọc theo departmentId (did)

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, departmentId); // Đặt tham số departmentId cho truy vấn

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                LeaveRequest r = new LeaveRequest();
                r.setId(rs.getInt("rid"));
                r.setTitle(rs.getString("title"));
                r.setReason(rs.getString("reason"));
                r.setFrom(rs.getDate("from"));
                r.setTo(rs.getDate("to"));
                r.setCreateddate(rs.getTimestamp("createddate"));
                r.setStatus(rs.getInt("status"));

                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setDisplayname(rs.getString("displayname"));
                r.setCreatedby(u);

                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getString("ename"));
                u.setE(e);

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                e.setDept(d);

                requests.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return requests;
    }
   public LeaveRequest getById(int rid) {
    LeaveRequest r = null;
    try {
        String sql = "SELECT r.rid, r.title, r.reason, r.[from], r.[to], u.username, u.displayname, r.createddate, r.status, "
                   + "d.did, d.dname, e.eid, e.ename "
                   + "FROM LeaveRequest r "
                   + "INNER JOIN Users u ON u.username = r.createdby "
                   + "INNER JOIN Employees e ON e.eid = u.eid "
                   + "INNER JOIN Departments d ON d.did = e.did "
                   + "WHERE r.rid = ?"; // Lọc theo yêu cầu nghỉ (rid)

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, rid); // Đặt tham số cho rid

        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            r = new LeaveRequest();
            r.setId(rs.getInt("rid"));
            r.setTitle(rs.getString("title"));
            r.setReason(rs.getString("reason"));
            r.setFrom(rs.getDate("from"));
            r.setTo(rs.getDate("to"));
            r.setCreateddate(rs.getTimestamp("createddate"));
            r.setStatus(rs.getInt("status"));

            User u = new User();
            u.setUsername(rs.getString("username"));
            u.setDisplayname(rs.getString("displayname"));
            r.setCreatedby(u);

            Employee e = new Employee();
            e.setId(rs.getInt("eid"));
            e.setName(rs.getString("ename"));
            u.setE(e);

            Department d = new Department();
            d.setId(rs.getInt("did"));
            d.setName(rs.getString("dname"));
            e.setDept(d);
        }
    } catch (SQLException ex) {
        Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    return r;
}
   public int updateApprovedBy(int rid, String approvedBy) {
    String sql = "UPDATE LeaveRequest SET approvedBy = ? WHERE rid = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setString(1, approvedBy);
        stm.setInt(2, rid);
        return stm.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(LeaveRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
}
 public String getApprovedBy(int rid) {
        String approvedBy = null;
        String sql = "SELECT approvedBy FROM LeaveRequest WHERE rid = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, rid);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    approvedBy = rs.getString("approvedBy");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return approvedBy;
    }

 public HashMap<Integer, HashMap<String, String>> getLeaveStatus(String from, String to) {
    HashMap<Integer, HashMap<String, String>> leaveStatus = new HashMap<>();
    try {
        String sql = "SELECT createdBy, `from`, `to` FROM LeaveRequest WHERE `from` <= ? AND `to` >= ? AND status = 'Approved'";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, to);
        stm.setString(2, from);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            int empId = rs.getInt("createdBy");
            Date start = rs.getDate("from");
            Date end = rs.getDate("to");

            HashMap<String, String> days = leaveStatus.getOrDefault(empId, new HashMap<>());
            LocalDate startDate = start.toLocalDate();
            LocalDate endDate = end.toLocalDate();

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                days.put(date.toString(), "leave");
            }

            leaveStatus.put(empId, days);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return leaveStatus;
}
}