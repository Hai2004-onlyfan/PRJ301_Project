package dal;

import models.User;
import java.sql.*;
import java.util.*;
import models.AttendanceRecord;

public class AgendaDBContext extends DBContext<User> {

  public ArrayList<AttendanceRecord> getEmployeeAttendance(java.sql.Date fromDate, java.sql.Date toDate) {
    ArrayList<AttendanceRecord> attendanceList = new ArrayList<>();

    String sql = """
        WITH DateSeries AS (
            SELECT CAST(? AS DATE) AS work_date
            UNION ALL
            SELECT DATEADD(DAY, 1, work_date)
            FROM DateSeries
            WHERE work_date < ?
        ),
        EmployeeWorkDays AS (
            SELECT u.username, u.displayname, d.work_date
            FROM [Users] u
            CROSS JOIN DateSeries d
        ),
        LeaveStatus AS (
            SELECT createdBy, [from], [to], status
            FROM LeaveRequest
            WHERE status = '2' -- Chỉ lấy đơn nghỉ được duyệt
        )
        SELECT ewd.username, ewd.displayname, ewd.work_date,
               CASE 
                   WHEN ls.createdBy IS NOT NULL THEN 'Leave' 
                   ELSE 'Work' 
               END AS status
        FROM EmployeeWorkDays ewd
        LEFT JOIN LeaveStatus ls 
            ON ewd.username = ls.createdBy
            AND ewd.work_date BETWEEN ls.[from] AND ls.[to]
        ORDER BY ewd.username, ewd.work_date;
    """;

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setDate(1, fromDate);
        ps.setDate(2, toDate);
        
        ResultSet rs = ps.executeQuery();
        UserDBContext userDB = new UserDBContext();
        ArrayList<User> users = userDB.getAllUsers();  

        while (rs.next()) {
            String username = rs.getString("username");
            String displayName = rs.getString("displayname");
            java.sql.Date workDate = rs.getDate("work_date");
            String status = rs.getString("status");

            // Tạo User (hoặc lấy từ danh sách users)
            User user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseGet(() -> {
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setDisplayname(displayName);
    return newUser;
});

            // Thêm vào danh sách chấm công
            attendanceList.add(new AttendanceRecord(user, workDate, status));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return attendanceList;
}


    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
