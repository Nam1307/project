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
public class Ward {
    private String wardID;
    private String districtID;
    private String wardName;

    public Ward() {
    }

    public Ward(String wardID, String districtID, String wardName) {
        this.wardID = wardID;
        this.districtID = districtID;
        this.wardName = wardName;
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

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
    
}
