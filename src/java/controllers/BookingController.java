/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BookingDAO;
import daos.ChildrenPitchDAO;
import daos.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Booking;
import models.ChildrenPitch;
import models.Time;
import models.User;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
@WebServlet(name = "BookingController", urlPatterns = {"/booking"})
public class BookingController extends HttpServlet {

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
        String action = request.getAttribute("action").toString();
        switch (action) {
            case "goToConfirmBooking":
                //Xu ly
                goToConfirmBooking(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "confirmBooking":
                //Xu ly
                confirmBooking(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("action", "error");
        }
    }

    private void goToConfirmBooking(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String pitchID = request.getParameter("pitchID");
            if (user == null) {
                session.setAttribute("pitchID", pitchID);
                request.setAttribute("pitchID", pitchID);
                request.setAttribute("controller", "user");
                request.setAttribute("action", "login");
            } else {
                ChildrenPitchDAO cpd = new ChildrenPitchDAO();
                BookingDAO bd = new BookingDAO();
                String type = request.getParameter("cpType");
                String op = request.getParameter("op");
                String dateBooking = request.getParameter("dateBooking");
                List<ChildrenPitch> listCP = cpd.getType(pitchID);
                List<Time> listT = bd.getTime();
                request.setAttribute("listCP", listCP);
                request.setAttribute("type", type);
                request.setAttribute("time", op);
                request.setAttribute("dateBooking", dateBooking);
                request.setAttribute("user", user);
                request.setAttribute("listT", listT);
                request.setAttribute("pitchID", pitchID);
            }
        } catch (Exception ex) {

        }
    }

    private void confirmBooking(HttpServletRequest request, HttpServletResponse response) {
        try {
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            UserDAO ud = new UserDAO();
            BookingDAO bd = new BookingDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String phone = request.getParameter("phone");
            String childrenPitchID = request.getParameter("childrenPitchID");
            String date = request.getParameter("dateBooking");
            Date dateBooking = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            String timeRent = request.getParameter("timeRent");
            String pitchID = request.getParameter("pitchID");
            String bookingID = raiseBookingId(bd.getAllBooking());
            if (user.getPhone().equals("")) {
                ud.updatetUserPhone(user.getUserID(), phone);
                User userUpdated = ud.getUser(user.getUserID());
                session.setAttribute("user", userUpdated);
                System.out.println("updated");
            }
            Booking booking = new Booking(bookingID, childrenPitchID, user.getUserID(), dateBooking, timeRent);
            if (bd.insertBooking(booking)) {
                user = (User) session.getAttribute("user");
                Date dateToNotify = new Date();
                List<Booking> listN = bd.getNotification(user.getUserID(), dateToNotify);
                List<ChildrenPitch> listCP = cpd.getType(pitchID);
                List<Time> listT = bd.getTime();
                session.setAttribute("listN", listN);
                session.setAttribute("countNotify", listN.size());
                request.setAttribute("listCP", listCP);
                request.setAttribute("type", childrenPitchID);
                request.setAttribute("time", timeRent);
                request.setAttribute("dateBooking", date);
                request.setAttribute("user", user);
                request.setAttribute("listT", listT);
                request.setAttribute("success", "Đặt sân thành công");
                request.setAttribute("action", "goToConfirmBooking");
                request.setAttribute("disabled", "disabled");
            }
            System.out.println(user.getUserID());
            System.out.println(phone);
            System.out.println(childrenPitchID);
            System.out.println(dateBooking);
            System.out.println(timeRent);
            System.out.println(bookingID);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String raiseBookingId(List<Booking> arrayList) throws Exception {
        try {
            BookingDAO dao = new BookingDAO();
            String result = "";
            int maxIndex = 1;
            if (arrayList == null) {
                maxIndex = 1;
                result = "B" + String.format("%02d", maxIndex);
            } else {
                maxIndex = arrayList.size() + 1;
                result = "B" + String.format("%02d", maxIndex);
                Booking temp = dao.getABooking(result);
                while (temp != null) {
                    maxIndex += 1;
                    result = "B" + String.format("%02d", maxIndex);
                    temp = dao.getABooking(result);;
                }
            }
            return result;
        } catch (Exception ex) {
            throw ex;
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
