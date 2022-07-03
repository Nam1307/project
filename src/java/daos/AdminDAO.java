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
import models.Booking;
import models.User;
import utils.DBUtils;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class AdminDAO {

    private static final String GET_USER_FOR_BECOMING_OWNER = "SELECT * FROM tblUser where OwnerStatus=1";
    private static final String UPDATE_CONFIRM_OWNER = "UPDATE tblUser SET OwnerStatus = 0, UserStatus = 1 WHERE UserID = ?";
    private static final String GET_USER_ACTIVE = "  SELECT * FROM tblUser where UserStatus=1 AND (RoleID = 'US' OR RoleID = 'OW')";
    private static final String GET_NUMBER_USER_BY_ROLE = " SELECT COUNT(*) AS total FROM tblUser WHERE RoleID = ? AND UserStatus=1";
    private static final String DELETE_USER = "UPDATE tblUser SET UserStatus = 0 WHERE UserID = ?";
    private static final String DELETE_COMMENT = "DELETE tblComment WHERE CommentID = ?";
    private static final String COUNT_CANCEL = "  SELECT UserID, COUNT(*) AS CountCancel FROM Booking WHERE StatusBooking = 0 AND ReasonContent != N'Sân con đã bị xóa bởi chủ sân' GROUP BY UserID";
    private static final String DELETE_COMMENT_BY_PITCHID = "DELETE tblComment WHERE PitchID = ?";
    
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
    
    public int getNumberOfUserByRole(String roleID) throws SQLException {
        int num = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_NUMBER_USER_BY_ROLE);
                stm.setString(1, roleID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    num = rs.getInt("total");
                }
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
        return num;
    }
    
    public boolean deleteUser(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE_USER);
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
    
    public boolean deleteComment(String commentID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE_COMMENT);
                stm.setString(1, commentID);
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
    
    public boolean deleteCommentByPitchID(String pitchID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE_COMMENT_BY_PITCHID);
                stm.setString(1, pitchID);
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
    
    public List<Booking> countCancel() throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(COUNT_CANCEL);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    int countCancel = rs.getInt("CountCancel");
                    list.add(new Booking(userID, countCancel));
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
