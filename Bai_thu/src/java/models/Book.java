package models;
import java.util.Date;

public class Book {
    private int bid;
    private String bname;
    private String createdBy;
    private Date createdDate;
    private int aid;

    public Book() {}
    public Book(int bid, String bname, String createdBy, Date createdDate, int aid) {
        this.bid = bid;
        this.bname = bname;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.aid = aid;
    }

    public int getBid() { return bid; }
    public void setBid(int bid) { this.bid = bid; }
    public String getBname() { return bname; }
    public void setBname(String bname) { this.bname = bname; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public int getAid() { return aid; }
    public void setAid(int aid) { this.aid = aid; }
}
