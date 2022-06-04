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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Booking;
import models.ChildrenPitch;
import models.Pitch;
import utils.DBUtils;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class OwnerDAO {
    private static final String GET_PITCH_OF_OWNER = "SELECT * FROM Pitch WHERE UserID = ?";
    private static final String GET_CHILDRENPITCH_OF_OWNER = "SELECT * FROM ChildrenPitch WHERE PitchID = ?";
    private static final String FIND_TIME = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE ChildrenPitchID = ? AND BookingDate = ? ORDER BY TimeStart;";
    private static final String GET_BOOKING_PLAYED_EQUAL_BEFORE = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE ChildrenPitchID = ? AND BookingDate = ? AND TiemEnd < ? ORDER BY TimeStart";
    private static final String GET_BOOKING_PLAYED_EQUAL_AFTER = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE ChildrenPitchID = ? AND BookingDate = ? AND TiemEnd > ? ORDER BY TimeStart";
    private static final String GET_BOOKING_PLAYED_BEFORE = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE ChildrenPitchID = ? AND BookingDate = ? ORDER BY TimeStart";
    private static final String GET_BOOKING_PLAYED_AFTER = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE ChildrenPitchID = ? AND BookingDate = ? ORDER BY TimeStart";
    
    public List<Pitch> getPitch(String UserID) throws SQLException {
        List<Pitch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PITCH_OF_OWNER);
                stm.setString(1, UserID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String pitchID = rs.getString("PitchID");
                    String wardID = rs.getString("WardID");
                    String districtID = rs.getString("DistrictID");
                    String userID = rs.getString("UserID");
                    String pitchName = rs.getString("PitchName");
                    String pitchAddress = rs.getString("PitchAddress");
                    int estimation = rs.getInt("Estimation");
                    String pitchLocation = rs.getString("PitchLocation");
                    String pitchDescription = rs.getString("PitchDescription");
                    list.add(new Pitch(pitchID, wardID, districtID, userID, pitchName, pitchAddress, estimation, pitchLocation, pitchDescription));
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
    
    public List<ChildrenPitch> getChildrenPitch(String PitchID) throws SQLException {
        List<ChildrenPitch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_CHILDRENPITCH_OF_OWNER);
                stm.setString(1, PitchID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String childrenPitchID = rs.getString("ChildrenPitchID");
                    String pitchID = rs.getString("PitchID");
                    String childrenPitchName = rs.getString("ChildrenPitchName");
                    String childrenPitchType = rs.getString("ChildrenPitchType");
                    Double price = rs.getDouble("Price");
                    list.add(new ChildrenPitch(childrenPitchID, pitchID, childrenPitchName, childrenPitchType, price));
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
    
    public List<Booking> findTime(String ChildrenPitchID, Date BookingDate) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(FIND_TIME);
                stm.setString(1, ChildrenPitchID);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(2, df.format(BookingDate));
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("BookingID");
                    String childrenPitchID = rs.getString("ChildrenPitchID");
                    String userID = rs.getString("UserID");
                    Date bookingDate = rs.getDate("BookingDate");
                    String timeID = rs.getString("TimeID");
                    java.sql.Time timeStart = rs.getTime("TimeStart");
                    java.sql.Time timeEnd = rs.getTime("TiemEnd");
                    list.add(new Booking(bookingID, childrenPitchID, userID, bookingDate, timeID, timeStart,timeEnd));
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
    
    public List<Booking> getUserBookingPlayedEqualBefore(String ChildrenPitchID, Date dateNow, String Time) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_EQUAL_BEFORE);
                stm.setString(1, ChildrenPitchID);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(2, df.format(dateNow));
                stm.setString(3, Time);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("BookingID");
                    String childrenPitchID = rs.getString("ChildrenPitchID");
                    String userID = rs.getString("UserID");
                    Date bookingDate = rs.getDate("BookingDate");
                    String timeID = rs.getString("TimeID");
                    java.sql.Time timeStart = rs.getTime("TimeStart");
                    java.sql.Time timeEnd = rs.getTime("TiemEnd");
                    list.add(new Booking(bookingID, childrenPitchID, userID, bookingDate, timeID, timeStart, timeEnd));
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
    
    public List<Booking> getUserBookingPlayedEqualAfter(String ChildrenPitchID, Date dateNow, String Time) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_EQUAL_AFTER);
                stm.setString(1, ChildrenPitchID);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(2, df.format(dateNow));
                stm.setString(3, Time);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("BookingID");
                    String childrenPitchID = rs.getString("ChildrenPitchID");
                    String userID = rs.getString("UserID");
                    Date bookingDate = rs.getDate("BookingDate");
                    String timeID = rs.getString("TimeID");
                    java.sql.Time timeStart = rs.getTime("TimeStart");
                    java.sql.Time timeEnd = rs.getTime("TiemEnd");
                    list.add(new Booking(bookingID, childrenPitchID, userID, bookingDate, timeID, timeStart, timeEnd));
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
    
    public List<Booking> getUserBookingPlayedBefore(String ChildrenPitchID, Date dateNow) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_BEFORE);
                stm.setString(1, ChildrenPitchID);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(2, df.format(dateNow));
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("BookingID");
                    String childrenPitchID = rs.getString("ChildrenPitchID");
                    String userID = rs.getString("UserID");
                    Date bookingDate = rs.getDate("BookingDate");
                    String timeID = rs.getString("TimeID");
                    java.sql.Time timeStart = rs.getTime("TimeStart");
                    java.sql.Time timeEnd = rs.getTime("TiemEnd");
                    list.add(new Booking(bookingID, childrenPitchID, userID, bookingDate, timeID, timeStart, timeEnd));
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
    
    public List<Booking> getUserBookingPlayedAfter(String ChildrenPitchID, Date dateNow) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_AFTER);
                stm.setString(1, ChildrenPitchID);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(2, df.format(dateNow));
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("BookingID");
                    String childrenPitchID = rs.getString("ChildrenPitchID");
                    String userID = rs.getString("UserID");
                    Date bookingDate = rs.getDate("BookingDate");
                    String timeID = rs.getString("TimeID");
                    java.sql.Time timeStart = rs.getTime("TimeStart");
                    java.sql.Time timeEnd = rs.getTime("TiemEnd");
                    list.add(new Booking(bookingID, childrenPitchID, userID, bookingDate, timeID, timeStart, timeEnd));
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

    
    public static void main(String[] args) throws SQLException, ParseException {
        OwnerDAO dao = new OwnerDAO();
        SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
        Date dateNow = new Date();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-04");
        List<Booking> list = dao.getUserBookingPlayedEqualBefore("C03", date, smt.format(dateNow));
        for (Booking booking : list) {
            System.out.println(booking.getBookingID());
        }
        
//        List<Booking> list = dao.findTime("C01", "06/03/2022");
//        for (Booking booking : list) {
//            System.out.println(booking.getTimeStart());
//        }
    }
}
