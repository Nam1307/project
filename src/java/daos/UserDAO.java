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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Comment;
import models.User;
import utils.DBUtils;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class UserDAO {

    private static final String GET_USER = "SELECT * FROM tblUser where UserID=?";
    private static final String GET_ALL_USER = "SELECT * FROM tblUser";
    private static final String CHECK_USER_EMAIL = "SELECT * FROM tblUser where Email=? AND UserStatus = 1";
    private static final String INSERT_USER = "INSERT INTO tblUser VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String CHECK_LOGIN = "SELECT * FROM tblUser where UserName=? AND Pass = ? AND UserStatus = 1";
    private static final String CHECK_DUPLICATE_USERNAME = "SELECT * FROM tblUser where UserName=? AND UserStatus = 1";
    private static final String UPDATE_USER_PHONE = "UPDATE tblUser SET Phone = ?  WHERE UserID = ?;";
    private static final String GET_COMMENT = "SELECT * FROM tblComment where PitchID=?";
    private static final String GET_A_COMMENT = "SELECT * FROM tblComment where CommentID=?";
    private static final String GET_ALL_COMMENT = "SELECT * FROM tblComment";
    private static final String INSERT_COMMENT = "INSERT INTO tblComment VALUES (?,?,?,?,?,?)";
    

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
                    boolean ownerStatus = rs.getBoolean("OwnerStatus");
                    boolean userStatus = rs.getBoolean("UserStatus");
                    user = new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink,ownerStatus, userStatus);
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
                    boolean ownerStatus = rs.getBoolean("OwnerStatus");
                    boolean userStatus = rs.getBoolean("UserStatus");
                    list.add(new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink,ownerStatus, userStatus));
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
                    boolean ownerStatus = rs.getBoolean("OwnerStatus");
                    boolean userStatus = rs.getBoolean("UserStatus");
                    user = new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink,ownerStatus, userStatus);
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
                stm.setBoolean(12, user.isOwnerStatus());
                stm.setBoolean(13, user.isUserStatus());
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

    public User checkLogin(String UserName, String password) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_LOGIN);
                stm.setString(1, UserName);
                stm.setString(2, password);
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
                    boolean ownerStatus = rs.getBoolean("OwnerStatus");
                    boolean userStatus = rs.getBoolean("UserStatus");
                    user = new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink,ownerStatus, userStatus);
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

    public User checkDuplicateUsername(String UserName) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_DUPLICATE_USERNAME);
                stm.setString(1, UserName);
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
                    boolean ownerStatus = rs.getBoolean("OwnerStatus");
                    boolean userStatus = rs.getBoolean("UserStatus");
                    user = new User(userID, roleID, wardID, districtID, userName, pass, fullName, phone, userAddress, email, imgLink,ownerStatus, userStatus);
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

    public boolean updatetUserPhone(String userID, String phone) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_USER_PHONE);
                stm.setString(1, phone);
                stm.setString(2, userID);
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
    
    public List<Comment> getComment(String PitchID) throws SQLException {
        List<Comment> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_COMMENT);
                stm.setString(1, PitchID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String commentID = rs.getString("CommentID");
                    String pitchID = rs.getString("PitchID");
                    String userID = rs.getString("UserID");
                    Date commentDate = rs.getDate("CommentDate");
                    String content = rs.getString("Content");
                    int rating = rs.getInt("Rating");
                    list.add(new Comment(commentID, pitchID, userID, commentDate, content, rating));
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
    
    public List<Comment> getAllComment() throws SQLException {
        List<Comment> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ALL_COMMENT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String commentID = rs.getString("CommentID");
                    String pitchID = rs.getString("PitchID");
                    String userID = rs.getString("UserID");
                    Date commentDate = rs.getDate("CommentDate");
                    String content = rs.getString("Content");
                    int rating = rs.getInt("Rating");
                    list.add(new Comment(commentID, pitchID, userID, commentDate, content, rating));
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
    
    public boolean insertComment(Comment comment) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_COMMENT);
                stm.setString(1, comment.getCommentID());
                stm.setString(2, comment.getPitchID());
                stm.setString(3, comment.getUserID());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(4, df.format(comment.getCommentDate()));
                stm.setString(5, comment.getContent());
                stm.setInt(6, comment.getRating());
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
    
    public Comment getAComment(String CommentID) throws SQLException {
        Comment comment = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_A_COMMENT);
                stm.setString(1, CommentID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String commentID = rs.getString("CommentID");
                    String pitchID = rs.getString("PitchID");
                    String userID = rs.getString("UserID");
                    Date commentDate = rs.getDate("CommentDate");
                    String content = rs.getString("Content");
                    int rating = rs.getInt("Rating");
                    comment = new Comment(commentID, pitchID, userID, commentDate, content, rating);
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
        return comment;
    }


    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        List<Comment> list = dao.getAllComment();
        //SimpleDateFormat smt = new SimpleDateFormat("dd-MM-yyyy");
        for (Comment comment : list) {
            System.out.println(comment.getCommentID());
            System.out.println(comment.getCommentDate());
        }
    }
}
