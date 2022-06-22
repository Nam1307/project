/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.User;
import utils.DBUtils;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class AdminDAO {

    private static final String GET_USER_FOR_BECOMING_OWNER = "SELECT * FROM tblUser where OwnerStatus=1";
    private static final String UPDATE_CONFIRM_OWNER = "UPDATE tblUser SET OwnerStatus = 0, UserStatus = 1 WHERE UserID = ?";
    private static final String SEARCH_USER_FOR_BECOMING_OWNER = "SELECT * FROM tblUser where OwnerStatus=1 AND FullName LIKE ?";
    private static final String GET_USER_ACTIVE = "SELECT * FROM tblUser where   RoleID = 'US' OR RoleID = 'OW' AND UserStatus=1";
    private static final String GET_USER_ACTIVE_BY_ROLE = "SELECT * FROM tblUser where   RoleID = ? AND UserStatus=1";

    public List<User> getUserForBecomingOwner() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_FOR_BECOMING_OWNER);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String roleID = rs.getString("RoleID");
                    String wardID = rs.getString("WardID");
                    String districtID = rs.getString("DistrictID");
                    String userName = rs.getString("UserName");
                    String pass = rs.getString("Pass");
                    String fullName = rs.getString("FullName");
                    String phone = rs.getString("Phone");
                    String userAddress = rs.getString("UserAddress");
                    String email = rs.getString("Email");
                    String imgLink = rs.getString("ImgLink");
                    boolean ownerStatus = rs.getBoolean("OwnerStatus");
                    boolean userStatus = rs.getBoolean("UserStatus");
                    list.add(new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink, ownerStatus, userStatus));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean updateBecomingOwner(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_CONFIRM_OWNER);
                stm.setString(1, userID);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public List<User> searchUserForBecomingOwner(String name) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SEARCH_USER_FOR_BECOMING_OWNER);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String roleID = rs.getString("RoleID");
                    String wardID = rs.getString("WardID");
                    String districtID = rs.getString("DistrictID");
                    String userName = rs.getString("UserName");
                    String pass = rs.getString("Pass");
                    String fullName = rs.getString("FullName");
                    String phone = rs.getString("Phone");
                    String userAddress = rs.getString("UserAddress");
                    String email = rs.getString("Email");
                    String imgLink = rs.getString("ImgLink");
                    boolean ownerStatus = rs.getBoolean("OwnerStatus");
                    boolean userStatus = rs.getBoolean("UserStatus");
                    list.add(new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink, ownerStatus, userStatus));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public List<User> getUserActive() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_ACTIVE);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String roleID = rs.getString("RoleID");
                    String wardID = rs.getString("WardID");
                    String districtID = rs.getString("DistrictID");
                    String userName = rs.getString("UserName");
                    String pass = rs.getString("Pass");
                    String fullName = rs.getString("FullName");
                    String phone = rs.getString("Phone");
                    String userAddress = rs.getString("UserAddress");
                    String email = rs.getString("Email");
                    String imgLink = rs.getString("ImgLink");
                    boolean ownerStatus = rs.getBoolean("OwnerStatus");
                    boolean userStatus = rs.getBoolean("UserStatus");
                    list.add(new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink, ownerStatus, userStatus));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public List<User> getUserActiveByRole(String RoleID) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_ACTIVE_BY_ROLE);
                stm.setString(1, RoleID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String roleID = rs.getString("RoleID");
                    String wardID = rs.getString("WardID");
                    String districtID = rs.getString("DistrictID");
                    String userName = rs.getString("UserName");
                    String pass = rs.getString("Pass");
                    String fullName = rs.getString("FullName");
                    String phone = rs.getString("Phone");
                    String userAddress = rs.getString("UserAddress");
                    String email = rs.getString("Email");
                    String imgLink = rs.getString("ImgLink");
                    boolean ownerStatus = rs.getBoolean("OwnerStatus");
                    boolean userStatus = rs.getBoolean("UserStatus");
                    list.add(new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink, ownerStatus, userStatus));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
        AdminDAO dao = new AdminDAO();
        List<User> list = dao.getUserForBecomingOwner();
        for (User user : list) {
            System.out.println(user.getFullName());
        }
    }
}
