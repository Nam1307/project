/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class Booking {
    private String bookingID;
    private String childrenPitchID;
    private String userID;
    private Date bookingDate;
    private String timeID;
    private java.sql.Time timeStart;
    private java.sql.Time timeEnd;
    private boolean status;
    private String reasonContent;
    private int countCancel;

    public Booking() {
    }

    public Booking(String bookingID, String childrenPitchID, String userID, Date bookingDate, String timeID) {
        this.bookingID = bookingID;
        this.childrenPitchID = childrenPitchID;
        this.userID = userID;
        this.bookingDate = bookingDate;
        this.timeID = timeID;
    }

    public Booking(String bookingID, String childrenPitchID, String userID, Date bookingDate, String timeID, java.sql.Time timeStart, java.sql.Time timeEnd) {
        this.bookingID = bookingID;
        this.childrenPitchID = childrenPitchID;
        this.userID = userID;
        this.bookingDate = bookingDate;
        this.timeID = timeID;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Booking(String bookingID, String childrenPitchID, String userID, Date bookingDate, String timeID, Time timeStart, Time timeEnd, boolean status, String reasonContent) {
        this.bookingID = bookingID;
        this.childrenPitchID = childrenPitchID;
        this.userID = userID;
        this.bookingDate = bookingDate;
        this.timeID = timeID;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.status = status;
        this.reasonContent = reasonContent;
    }

    public Booking(String userID, int countCancel) {
        this.userID = userID;
        this.countCancel = countCancel;
    }

    
    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getChildrenPitchID() {
        return childrenPitchID;
    }

    public void setChildrenPitchID(String childrenPitchID) {
        this.childrenPitchID = childrenPitchID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTimeID() {
        return timeID;
    }

    public void setTimeID(String timeID) {
        this.timeID = timeID;
    }

    public java.sql.Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(java.sql.Time timeStart) {
        this.timeStart = timeStart;
    }

    public java.sql.Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(java.sql.Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getReasonContent() {
        return reasonContent;
    }

    public void setReasonContent(String reasonContent) {
        this.reasonContent = reasonContent;
    }

    public int getCountCancel() {
        return countCancel;
    }

    public void setCountCancel(int countCancel) {
        this.countCancel = countCancel;
    }
}
