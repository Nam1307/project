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
import models.Ward;
import utils.DBUtils;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class PitchDAO {
    private static final String GET_DISTRICT = "SELECT * FROM tblDistrict";
    private static final String GET_WARD = "SELECT * FROM tblWard WHERE DistrictID = ?";
    
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
    
    public static void main(String[] args) throws SQLException {
        PitchDAO dao = new PitchDAO();
        List<Ward> list = dao.getWard("1");
        for (Ward ward : list) {
            System.out.println(ward.getWardName());
        }
    }
}
