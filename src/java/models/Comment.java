/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class Comment {
    private String commentID;
    private String pitchID;
    private String userID;
    private Date commentDate;
    private String content;
    private int rating;

    public Comment() {
    }

    public Comment(String commentID, String pitchID, String userID, Date commentDate, String content, int rating) {
        this.commentID = commentID;
        this.pitchID = pitchID;
        this.userID = userID;
        this.commentDate = commentDate;
        this.content = content;
        this.rating = rating;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getPitchID() {
        return pitchID;
    }

    public void setPitchID(String pitchID) {
        this.pitchID = pitchID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
}
