/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.JsonObject;
import daos.BookingDAO;
import daos.ChildrenPitchDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Booking;
import models.ChildrenPitch;
import models.Time;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
@WebServlet(name = "StadiumController", urlPatterns = {"/stadium"})
public class StadiumController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Xu ly yeu cau dua vao action
        String action = request.getAttribute("action").toString();
        switch (action) {
            case "detail":
                //Xu ly
                detail(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "findNotDate":
                //Xu ly
                findNotDate(request, response);
                break;
            case "findDate":
                //Xu ly
                findDate(request, response);
                break;
            default:
                request.setAttribute("action", "error");
        }

    }

    private void detail(HttpServletRequest request, HttpServletResponse response) {
        try {
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            String pitchID = request.getParameter("pitchID");
            List<ChildrenPitch> listCP = cpd.getType(pitchID);
            request.setAttribute("listCP", listCP);
            System.out.println(pitchID);
        } catch (SQLException ex) {
            Logger.getLogger(StadiumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void findNotDate(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            BookingDAO bd = new BookingDAO();
            String childrenPitchID = request.getParameter("childrenPitchID");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
            List<Booking> bookingTime = bd.findTime(childrenPitchID, date);
            for (Booking booking : bookingTime) {
                out.print("<a href=\"#\" style=\"pointer-events: none;cursor: default;opacity:50%\">\n" +
"                            <button type=\"submit\" class=\"btn btn-outline-success btn-sm\" name=\"op\" value=\""+ booking.getTimeID() +"\"><i class=\"bi bi-check-circle\"></i> "+ booking.getTimeRent()+"</button>\n" +
"                        </a>");
            }
            System.out.println(childrenPitchID);
            System.out.println(bookingTime);
        } catch (ParseException ex) {
            Logger.getLogger(StadiumController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StadiumController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StadiumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void findDate(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            BookingDAO bd = new BookingDAO();
            String childrenPitchID = request.getParameter("childrenPitchID");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
            List<Time> time = bd.getFreeTime(childrenPitchID, date);
            for (Time time1 : time) {
                out.print("<a href=\"#\">\n"
                        + "<button type=\"submit\" class=\"btn btn-outline-success btn-sm\" name=\"op\" value=\"" + time1.getTimeID() + "\"><i class=\"bi bi-check-circle\"></i> " + time1.getTimeRent() + "</button>\n"
                        + "</a>");
            }
        } catch (ParseException ex) {
            Logger.getLogger(StadiumController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StadiumController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StadiumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
