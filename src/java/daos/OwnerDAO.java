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
import models.Time;
import utils.DBUtils;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class OwnerDAO {

    private static final String GET_PITCH_OF_OWNER = "SELECT * FROM Pitch WHERE UserID = ? AND PichStatus = 1";
    private static final String GET_CHILDRENPITCH_OF_OWNER = "SELECT * FROM ChildrenPitch WHERE PitchID = ? AND StatusChildrenPitch = 1";
    private static final String FIND_TIME = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE ChildrenPitchID = ? AND BookingDate = ? AND StatusBooking = 1 ORDER BY TimeStart;";
    private static final String GET_BOOKING_PLAYED_EQUAL_BEFORE = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE BookingDate = ? AND TiemEnd < ? AND StatusBooking = 1 ORDER BY TimeStart";
    private static final String GET_BOOKING_PLAYED_EQUAL_AFTER = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE BookingDate = ? AND TiemEnd > ? AND StatusBooking = 1 ORDER BY TimeStart";
    private static final String GET_BOOKING_PLAYED_BEFORE = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE BookingDate = ? AND StatusBooking = 1 ORDER BY TimeStart";
    private static final String GET_BOOKING_PLAYED_AFTER = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE BookingDate = ? AND StatusBooking = 1 ORDER BY TimeStart";
    private static final String GET_CHILDRENPITCH = "SELECT * FROM ChildrenPitch WHERE ChildrenPitchID = ? AND StatusChildrenPitch = 1";
    private static final String GET_TIME = "SELECT * FROM tblTime WHERE TimeID = ?";
    private static final String UPDATE_CHILDRENPITCH = "UPDATE ChildrenPitch SET ChildrenPitchName = ?, ChildrenPitchType = ?, Price = ?  WHERE ChildrenPitchID = ?";
    private static final String DELETE_CHILDRENPITCH = "UPDATE ChildrenPitch SET StatusChildrenPitch = 0 WHERE ChildrenPitchID = ?";
    private static final String GET_BOOKING_PLAYED_AFTER_FOR_CP = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE ChildrenPitchID = ? AND BookingDate > ? AND StatusBooking = 1 ORDER BY TimeStart";
    private static final String GET_BOOKING_PLAYED_EQUAL_AFTER_FOR_CP = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE ChildrenPitchID = ? AND BookingDate = ? AND TiemEnd > ? AND StatusBooking = 1 ORDER BY TimeStart";
    private static final String INSERT_CHILDRENPITCH = "INSERT INTO ChildrenPitch VALUES (?,?,?,?,?,?)";
    private static final String GET_DATA_FOR_CHART = "select count(*) as total from Booking, tblUser, Pitch, ChildrenPitch where Booking.ChildrenPitchID = ChildrenPitch.ChildrenPitchID \n" +
"  and ChildrenPitch.PitchID = Pitch.PitchID and tblUser.UserID = Pitch.UserID and  BookingDate between ? and ? and tblUser.UserID = ? and Booking.StatusBooking = 1";
    private static final String GET_ALL_CHILDRENPITCH_FOR_OWNER = "  select count(*) as total from  tblUser, Pitch, ChildrenPitch "
            + "where ChildrenPitch.PitchID = Pitch.PitchID and tblUser.UserID = Pitch.UserID and ChildrenPitch.StatusChildrenPitch = 1 and tblUser.UserID = ? and PichStatus = 1";
    private static final String GET_ALL_BOOKING_FOR_OWNER = "select count(*) as total from Booking, tblUser, Pitch, ChildrenPitch where Booking.ChildrenPitchID = ChildrenPitch.ChildrenPitchID \n" +
"  and ChildrenPitch.PitchID = Pitch.PitchID and tblUser.UserID = Pitch.UserID and tblUser.UserID = ? and Booking.StatusBooking = 1";
    private static final String UPDATE_PITCH = "UPDATE Pitch SET WardID = ?, DistrictID = ?, PitchName = ?, PitchAddress = ?, PitchDescription = ?  WHERE PitchID = ?";
    private static final String GET_ALL_CHILDRENPITCH = "SELECT * FROM ChildrenPitch LEFT JOIN Pitch ON Pitch.PitchID = ChildrenPitch.PitchID WHERE Pitch.UserID = ? AND StatusChildrenPitch = 1";
    private static final String INSERT_PITCH = "INSERT INTO Pitch VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_PITCH = "UPDATE Pitch SET PichStatus = 0 WHERE PitchID = ?";
    
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
                    String pitchDescription = rs.getString("PitchDescription");
                    boolean status = rs.getBoolean("PichStatus");
                    list.add(new Pitch(pitchID, wardID, districtID, userID, pitchName, pitchAddress, estimation, pitchDescription, status));
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
                    boolean status = rs.getBoolean("StatusChildrenPitch");
                    list.add(new ChildrenPitch(childrenPitchID, pitchID, childrenPitchName, childrenPitchType, price, status));
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

    public List<Booking> getUserBookingPlayedEqualBefore(Date dateNow, String Time) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_EQUAL_BEFORE);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(1, df.format(dateNow));
                stm.setString(2, Time);
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

    public List<Booking> getUserBookingPlayedEqualAfter(Date dateNow, String Time) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_EQUAL_AFTER);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(1, df.format(dateNow));
                stm.setString(2, Time);
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

    public List<Booking> getUserBookingPlayedBefore(Date dateNow) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_BEFORE);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(1, df.format(dateNow));
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

    public List<Booking> getUserBookingPlayedAfter(Date dateNow) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_AFTER);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(1, df.format(dateNow));
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
    
    public ChildrenPitch getChildrenPitchEmail(String ChildrenPitchID) throws SQLException {
        ChildrenPitch childrenPitch = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_CHILDRENPITCH);
                stm.setString(1, ChildrenPitchID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String childrenPitchID = rs.getString("ChildrenPitchID");
                    String pitchID = rs.getString("PitchID");
                    String childrenPitchName = rs.getString("ChildrenPitchName");
                    String childrenPitchType = rs.getString("ChildrenPitchType");
                    Double price = rs.getDouble("Price");
                    boolean status = rs.getBoolean("StatusChildrenPitch");
                    childrenPitch = new ChildrenPitch(childrenPitchID, pitchID, childrenPitchName, childrenPitchType, price, status);
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
        return childrenPitch;
    }
    
    public Time getTime(String TimeID) throws SQLException {
        Time time = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_TIME);
                stm.setString(1, TimeID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String timeID = rs.getString("TimeID");
                    java.sql.Time timeStart = rs.getTime("TimeStart");
                    java.sql.Time timeEnd = rs.getTime("TiemEnd");
                    time = new Time(timeID, timeStart, timeEnd);
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
        return time;
    }
    
    public boolean updateChildrenPitch(String childrenPitchName, String childrenPitchType, double price, String childrenPitchID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_CHILDRENPITCH);
                stm.setString(1, childrenPitchName);
                stm.setString(2, childrenPitchType);
                stm.setDouble(3, price);
                stm.setString(4, childrenPitchID);
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
    
    public boolean deleteChildrenPitch(String childrenPitchID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE_CHILDRENPITCH);
                stm.setString(1, childrenPitchID);
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
    
    public List<Booking> getUserBookingPlayedEqualAfterForCP(String ChildrenPitchID, Date dateNow, String Time) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_EQUAL_AFTER_FOR_CP);
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
    
    public List<Booking> getUserBookingPlayedAfterForCP(String ChildrenPitchID, Date dateNow) throws SQLException {
        List<Booking> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOOKING_PLAYED_AFTER_FOR_CP);
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
    
    public boolean insertChildrenPitch(ChildrenPitch childrenPitch) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_CHILDRENPITCH);
                stm.setString(1, childrenPitch.getChildrenPitchID());
                stm.setString(2, childrenPitch.getPitchID());
                stm.setString(3, childrenPitch.getChildrenPitchName());
                stm.setString(4, childrenPitch.getChildrenPitchType());
                stm.setDouble(5, childrenPitch.getPrice());
                stm.setBoolean(6, true);
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
    
    public int getDataForChart(String startDate, String endDate, String ownerId) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_DATA_FOR_CHART);
                stm.setString(1, startDate);
                stm.setString(2, endDate);
                stm.setString(3, ownerId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("total");
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
        return count;
    }
    
    public int getAllChildrenPitchForOwner(String ownerId) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ALL_CHILDRENPITCH_FOR_OWNER);
                stm.setString(1, ownerId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("total");
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
        return count;
    }
    
    public int getAllBookingForOwner(String ownerId) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ALL_BOOKING_FOR_OWNER);
                stm.setString(1, ownerId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("total");
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
        return count;
    }
    
    public boolean updatePitch(String wardID, String districtID, String pitchName, String pitchAddress, String pitchDescription, String pitchID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_PITCH);
                stm.setString(1, wardID);
                stm.setString(2, districtID);
                stm.setString(3, pitchName);
                stm.setString(4, pitchAddress);
                stm.setString(5, pitchDescription);
                stm.setString(6, pitchID);
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
    
    public List<ChildrenPitch> getChildrenPitchForOwner(String userID) throws SQLException {
        List<ChildrenPitch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ALL_CHILDRENPITCH);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String childrenPitchID = rs.getString("ChildrenPitchID");
                    String pitchID = rs.getString("PitchID");
                    String childrenPitchName = rs.getString("ChildrenPitchName");
                    String childrenPitchType = rs.getString("ChildrenPitchType");
                    Double price = rs.getDouble("Price");
                    boolean status = rs.getBoolean("StatusChildrenPitch");
                    list.add(new ChildrenPitch(childrenPitchID, pitchID, childrenPitchName, childrenPitchType, price, status));
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
    
    public boolean insertPitch(Pitch pitch) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_PITCH);
                stm.setString(1, pitch.getPitchID());
                stm.setString(2, pitch.getWardID());
                stm.setString(3, pitch.getDistrictID());
                stm.setString(4, pitch.getUserID());
                stm.setString(5, pitch.getPitchName());
                stm.setString(6, pitch.getPitchAddress());
                stm.setInt(7, pitch.getEstimation());
                stm.setString(8, pitch.getPitchDescription());
                stm.setBoolean(9, true);
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
    
    public boolean deletePitch(String pitchID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE_PITCH);
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

    public static void main(String[] args) throws SQLException, ParseException {
        OwnerDAO dao = new OwnerDAO();
        int count = dao.getAllBookingForOwner("U02");
        System.out.println(count);
    }
}
