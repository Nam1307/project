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
public class User {
    private String userID;
    private String roleID;
    private String wardID;
    private String districtID;
    private String userName;
    private String pass;
    private String fullName;
    private String phone;
    private String userAddress;
    private String email;
    private String imgLink;
    private boolean ownerStatus;
    private boolean userStatus;

    public User() {
    }

    public User(String userID, String roleID, String wardID, String districtID, String userName, String pass, String fullName, String phone, String userAddress, String email, String imgLink, boolean ownerStatus, boolean userStatus) {
        this.userID = userID;
        this.roleID = roleID;
        this.wardID = wardID;
        this.districtID = districtID;
        this.userName = userName;
        this.pass = pass;
        this.fullName = fullName;
        this.phone = phone;
        this.userAddress = userAddress;
        this.email = email;
        this.imgLink = imgLink;
        this.ownerStatus = ownerStatus;
        this.userStatus = userStatus;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public boolean isOwnerStatus() {
        return ownerStatus;
    }

    public void setOwnerStatus(boolean ownerStatus) {
        this.ownerStatus = ownerStatus;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }
}
