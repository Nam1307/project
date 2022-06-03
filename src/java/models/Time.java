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
public class Time {
    private String timeID;
    private java.sql.Time timeStart;
    private java.sql.Time timeEnd;

    public Time() {
    }

    public Time(String timeID, java.sql.Time timeStart, java.sql.Time timeEnd) {
        this.timeID = timeID;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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

    
}
