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
import models.District;
import models.Pitch;
import models.Ward;
import utils.DBUtils;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class PitchDAO {

    private static final String GET_DISTRICT = "SELECT * FROM tblDistrict";
    private static final String GET_WARD = "SELECT * FROM tblWard WHERE DistrictID = ?";
    private static final String GET_PITCH = "SELECT * FROM Pitch ORDER BY PitchID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String GET_ALL_WARD = "SELECT * FROM tblWard";
    private static final String NUMBER_PITCH = "SELECT COUNT(*) AS total FROM Pitch";
    private static final String GET_PITCH_SEARCH = "SELECT * FROM Pitch WHERE DistrictID = ? AND WardID = ? ORDER BY PitchID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String NUMBER_PITCH_SEARCH = "SELECT COUNT(*) AS total FROM Pitch WHERE DistrictID = ? AND WardID = ?";
    private static final String GET_HIGH_RATE_PITCH = "SELECT TOP(4) * FROM Pitch  WHERE Estimation = 5 order by NEWID() ;";

    public List<District> getDistrict() throws SQLException {
        List<District> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_DISTRICT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String DistrictID = rs.getString("DistrictID");
                    String DistrictName = rs.getString("DistrictName");
                    list.add(new District(DistrictID, DistrictName));
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

    public List<Ward> getWard(String districtID) throws SQLException {
        List<Ward> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_WARD);
                stm.setString(1, districtID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String wardID = rs.getString("WardID");
                    String wardName = rs.getString("WardName");
                    String DistrictID = rs.getString("DistrictID");
                    list.add(new Ward(wardID, DistrictID, wardName));
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

    public List<Pitch> getPitch(int num1, int num2) throws SQLException {
        List<Pitch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PITCH);
                stm.setInt(1, num1);
                stm.setInt(2, num2);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String pitchID = rs.getString("PitchID");
                    String wardID = rs.getString("WardID");
                    String districtID = rs.getString("DistrictID");
                    String userID = rs.getString("UserID");
                    String pitchName = rs.getString("PitchName");
                    String pitchAddress = rs.getString("PitchAddress");
                    int estimation = rs.getInt("Estimation");
                    list.add(new Pitch(pitchID, wardID, districtID, userID, pitchName, pitchAddress, estimation));
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

    public List<Ward> getAllWard() throws SQLException {
        List<Ward> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ALL_WARD);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String wardID = rs.getString("WardID");
                    String wardName = rs.getString("WardName");
                    String DistrictID = rs.getString("DistrictID");
                    list.add(new Ward(wardID, DistrictID, wardName));
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

    public int getNumberOfPitch() throws SQLException {
        int number = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(NUMBER_PITCH);
                rs = stm.executeQuery();
                if (rs.next()) {
                    number = rs.getInt("total");
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
        return number;
    }

    public List<Pitch> getPitchAfterSearch(String DistrictID, String WardID, int num1, int num2) throws SQLException {
        List<Pitch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PITCH_SEARCH);
                stm.setString(1, DistrictID);
                stm.setString(2, WardID);
                stm.setInt(3, num1);
                stm.setInt(4, num2);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String pitchID = rs.getString("PitchID");
                    String wardID = rs.getString("WardID");
                    String districtID = rs.getString("DistrictID");
                    String userID = rs.getString("UserID");
                    String pitchName = rs.getString("PitchName");
                    String pitchAddress = rs.getString("PitchAddress");
                    int estimation = rs.getInt("Estimation");
                    list.add(new Pitch(pitchID, wardID, districtID, userID, pitchName, pitchAddress, estimation));
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
    
    public int getNumberOfPitchAterSearching(String DistrictID, String WardID) throws SQLException {
        int number = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(NUMBER_PITCH_SEARCH);
                stm.setString(1, DistrictID);
                stm.setString(2, WardID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    number = rs.getInt("total");
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
        return number;
    }
    
    public List<Pitch> getHighRatePitch() throws SQLException {
        List<Pitch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_HIGH_RATE_PITCH);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String pitchID = rs.getString("PitchID");
                    String wardID = rs.getString("WardID");
                    String districtID = rs.getString("DistrictID");
                    String userID = rs.getString("UserID");
                    String pitchName = rs.getString("PitchName");
                    String pitchAddress = rs.getString("PitchAddress");
                    int estimation = rs.getInt("Estimation");
                    list.add(new Pitch(pitchID, wardID, districtID, userID, pitchName, pitchAddress, estimation));
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
        PitchDAO dao = new PitchDAO();
//        List<Pitch> listP = dao.getPitch(1, 1);
//        List<District> listD = dao.getDistrict();
//        List<Ward> listW = dao.getAllWard();
//        for (Pitch pitch : listP) {
//            System.out.println(pitch.getPitchName());
//            for (District district : listD) {
//                if (pitch.getDistrictID().equals(district.getDistrictID())) {
//                    System.out.println(district.getDistrictName());
//                }
//            }
//            for (Ward ward : listW) {
//                if (pitch.getWardID().equals(ward.getWardID())) {
//                    System.out.println(ward.getWardName());
//                }
//            }
//        }
//        System.out.println(dao.getNumberOfPitch());
        List<Pitch> listP = dao.getPitchAfterSearch("13", "176", 0, 1);
        for (Pitch pitch : listP) {
            System.out.println(pitch.getPitchName());
        }
        System.out.println(dao.getNumberOfPitchAterSearching("13", "176"));
    }
}
