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
public class ChildrenPitch {
    private String childrenPitchID;
    private String pitchID;
    private String childrenPitchName;
    private String childrenPitchType;
    private Double Price;

    public ChildrenPitch() {
    }

    public ChildrenPitch(String childrenPitchID, String pitchID, String childrenPitchName, String childrenPitchType, Double Price) {
        this.childrenPitchID = childrenPitchID;
        this.pitchID = pitchID;
        this.childrenPitchName = childrenPitchName;
        this.childrenPitchType = childrenPitchType;
        this.Price = Price;
    }

    public String getChildrenPitchID() {
        return childrenPitchID;
    }

    public void setChildrenPitchID(String childrenPitchID) {
        this.childrenPitchID = childrenPitchID;
    }

    public String getPitchID() {
        return pitchID;
    }

    public void setPitchID(String pitchID) {
        this.pitchID = pitchID;
    }

    public String getChildrenPitchName() {
        return childrenPitchName;
    }

    public void setChildrenPitchName(String childrenPitchName) {
        this.childrenPitchName = childrenPitchName;
    }

    public String getChildrenPitchType() {
        return childrenPitchType;
    }

    public void setChildrenPitchType(String childrenPitchType) {
        this.childrenPitchType = childrenPitchType;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }
    
}
