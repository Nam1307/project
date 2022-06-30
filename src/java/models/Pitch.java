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
public class Pitch {
    private String pitchID;
    private String wardID;
    private String districtID;
    private String userID;
    private String pitchName;
    private String pitchAddress;
    private int estimation;
    private String pitchLocation;
    private String pitchDescription;
    private boolean pitchStatus;

    public Pitch() {
    }

    public Pitch(String pitchID, String wardID, String districtID, String userID, String pitchName, String pitchAddress, int estimation, String pitchLocation, String pitchDescription, boolean pitchStatus) {
        this.pitchID = pitchID;
        this.wardID = wardID;
        this.districtID = districtID;
        this.userID = userID;
        this.pitchName = pitchName;
        this.pitchAddress = pitchAddress;
        this.estimation = estimation;
        this.pitchLocation = pitchLocation;
        this.pitchDescription = pitchDescription;
        this.pitchStatus = pitchStatus;
    }

    public String getPitchID() {
        return pitchID;
    }

    public void setPitchID(String pitchID) {
        this.pitchID = pitchID;
    }

    public String getWardID() {
        return wardID;
    }

    public void setWardID(String wardID) {
        this.wardID = wardID;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPitchName() {
        return pitchName;
    }

    public void setPitchName(String pitchName) {
        this.pitchName = pitchName;
    }

    public String getPitchAddress() {
        return pitchAddress;
    }

    public void setPitchAddress(String pitchAddress) {
        this.pitchAddress = pitchAddress;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public String getPitchLocation() {
        return pitchLocation;
    }

    public void setPitchLocation(String pitchLocation) {
        this.pitchLocation = pitchLocation;
    }

    public String getPitchDescription() {
        return pitchDescription;
    }

    public void setPitchDescription(String pitchDescription) {
        this.pitchDescription = pitchDescription;
    }

    public boolean isPitchStatus() {
        return pitchStatus;
    }

    public void setPitchStatus(boolean pitchStatus) {
        this.pitchStatus = pitchStatus;
    }
    
}
