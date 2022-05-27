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
import models.Time;
import utils.DBUtils;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class BookingDAO {

    private static final String FIND_TIME = "SELECT * FROM Booking LEFT JOIN tblTime ON Booking.TimeID = tblTime.TimeID WHERE ChildrenPitchID = ? AND BookingDate = ?;";
    private static final String GET_TIME = "SELECT * FROM tblTime";
    private static final String FIND_FREE_TIME = "SELECT * FROM tblTime WHERE tblTime.TimeID NOT IN (\n"
            + "	SELECT Booking.TimeID  FROM Booking WHERE ChildrenPitchID = ? AND BookingDate = ?\n"
            + ")";

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
                    String timeRent = rs.getString("TimeRent");
                    list.add(new Booking(bookingID, childrenPitchID, userID, bookingDate, timeID,timeRent));
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

    public List<Time> getTime() throws SQLException {
        List<Time> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_TIME);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String timeID = rs.getString("TimeID");
                    String timeRent = rs.getString("TimeRent");
                    list.add(new Time(timeID, timeRent));
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
    
    public List<Time> getFreeTime(String ChildrenPitchID, Date BookingDate) throws SQLException {
        List<Time> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(FIND_FREE_TIME);
                stm.setString(1, ChildrenPitchID);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                stm.setString(2, df.format(BookingDate));
                rs = stm.executeQuery();
                while (rs.next()) {
                    String timeID = rs.getString("TimeID");
                    String timeRent = rs.getString("TimeRent");
                    list.add(new Time(timeID, timeRent));
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
        BookingDAO dao = new BookingDAO();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-27");
        List<Booking> list = dao.findTime("C01", date);
        for (Booking booking : list) {
            System.out.println(booking.getTimeRent());
        }
    }
}
