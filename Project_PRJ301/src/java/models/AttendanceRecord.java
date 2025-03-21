package models;
import java.sql.Date;

public class AttendanceRecord {
    private User user;
    private Date workDate;
    private String status; // "Work" hoặc "Leave"

    public AttendanceRecord(User user, Date workDate, String status) {
        this.user = user;
        this.workDate = workDate;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public String getStatus() {
        return status;
    }
}
