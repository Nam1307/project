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
import models.ChildrenPitch;
import models.District;
import utils.DBUtils;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class ChildrenPitchDAO {

    private static final String GET_CHILDRENPITCH = "SELECT * FROM ChildrenPitch";
    private static final String GET_TYPE = "SELECT * FROM ChildrenPitch WHERE PitchID = ?";

    public List<ChildrenPitch> getChildrenPitch() throws SQLException {
        List<ChildrenPitch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_CHILDRENPITCH);
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

    public List<ChildrenPitch> getType(String PitchID) throws SQLException {
        List<ChildrenPitch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_TYPE);
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

    public static void main(String[] args) throws SQLException {
        ChildrenPitchDAO dao = new ChildrenPitchDAO();
        List<ChildrenPitch> list = dao.getType("P02");
        for (ChildrenPitch childrenPitch : list) {
            System.out.println(childrenPitch.getPrice());
        }
    }
}
