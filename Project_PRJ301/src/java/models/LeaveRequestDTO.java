package models;


public class LeaveRequestDTO {
    private LeaveRequest leaveRequest;
    private String approvedBy;

    public LeaveRequestDTO(LeaveRequest leaveRequest, String approvedBy) {
        this.leaveRequest = leaveRequest;
        this.approvedBy = approvedBy;
    }

    public LeaveRequest getLeaveRequest() {
        return leaveRequest;
    }

    public String getApprovedBy() {
        return approvedBy;
    }
}