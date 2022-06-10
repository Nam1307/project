/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class ReasonDelete {
    private String reasonID;
    private String reasonContent;
    private String bookingID;

    public ReasonDelete() {
    }

    public ReasonDelete(String reasonID, String reasonContent, String bookingID) {
        this.reasonID = reasonID;
        this.reasonContent = reasonContent;
        this.bookingID = bookingID;
    }

    public String getReasonID() {
        return reasonID;
    }

    public void setReasonID(String reasonID) {
        this.reasonID = reasonID;
    }

    public String getReasonContent() {
        return reasonContent;
    }

    public void setReasonContent(String reasonContent) {
        this.reasonContent = reasonContent;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }
    
}
