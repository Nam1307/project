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
public class UserDAO {
    private static final String GET_USER = "SELECT * FROM tblUser where UserID=?";
    private static final String GET_ALL_USER = "SELECT * FROM tblUser";
    private static final String CHECK_USER_EMAIL = "SELECT * FROM tblUser where Email=?";
    private static final String INSERT_USER = "INSERT INTO tblUser VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    
    public User getUser(String UserID) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER);
                stm.setString(1, UserID);
                rs = stm.executeQuery();
                if (rs.next()) {
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
                    user = new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink);
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
        return user;
    }
    
    public List<User> getAllUser() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ALL_USER);
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
                    list.add(new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink));
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
    
    public User checkUserEmail(String userEmail) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_USER_EMAIL);
                stm.setString(1, userEmail);
                rs = stm.executeQuery();
                if (rs.next()) {
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
                    user = new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink);
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
        return user;
    }
    
    public boolean insertUser(User user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_USER);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getRoleID());
                stm.setString(3, user.getWardID());
                stm.setString(4, user.getDistrictID());
                stm.setString(5, user.getUserName());
                stm.setString(6, user.getPass());
                stm.setString(7, user.getFullName());
                stm.setString(8, user.getPhone());
                stm.setString(9, user.getUserAddress());
                stm.setString(10, user.getEmail());
                stm.setString(11, user.getImgLink());
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
    
    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        User u = new User("U05", "US", null, null, "", "", "Hu?nh Th? M? Hòa 1/9/1978", "", "", "myhoabibo67@gmail.com", "https://lh3.googleusercontent.com/a/AATXAJzraIIFB4zw6oRCECDGMMBEXWMmzf1mwqvmD84P=s96-cc");
        System.out.println(dao.insertUser(u));
    }
}
