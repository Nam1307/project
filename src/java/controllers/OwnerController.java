/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BookingDAO;
import daos.ChildrenPitchDAO;
import daos.OwnerDAO;
import daos.PitchDAO;
import daos.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import models.Pitch;
import models.SendEmail;
import models.Time;
import models.User;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
@WebServlet(name = "OwnerController", urlPatterns = {"/owner"})
public class OwnerController extends HttpServlet {

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
            case "viewBooking":
                //Xu ly
                viewBooking(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "childrenPitch":
                //Xu ly
                childrenPitch(request, response);
                break;
            case "search":
                //Xu ly
                search(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "delete":
                //Xu ly
                delete(request, response);
                break;
            case "detailBooking":
                //Xu ly
                detailBooking(request, response);
                break;
            case "childrenPitchManagement":
                //Xu ly
                childrenPitchManagement(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "viewChildrenPitch":
                //Xu ly
                viewChildrenPitch(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "viewEdit":
                //Xu ly
                viewEdit(request, response);
                break;
            case "update":
                //Xu ly
                update(request, response);
                break;
            case "deleteChildrenPitch":
                //Xu ly
                deleteChildrenPitch(request, response);
                break;
            default:
                request.setAttribute("action", "error");
        }
    }

    private void viewBooking(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userID = request.getParameter("userID");
            OwnerDAO od = new OwnerDAO();
            List<Pitch> listP = od.getPitch(userID);
            request.setAttribute("listP", listP);
            request.setAttribute("userID", userID);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void childrenPitch(HttpServletRequest request, HttpServletResponse response) {
        try {
            OwnerDAO od = new OwnerDAO();
            String pitchID = request.getParameter("pitchID");
            List<ChildrenPitch> listCP = od.getChildrenPitch(pitchID);
            PrintWriter out = response.getWriter();
            for (ChildrenPitch cp : listCP) {
                out.println("<input type=\"hidden\" name=\"pitchID\" value=\"" + cp.getPitchID() + "\"/>\n"
                        + "                            <option value=\"" + cp.getChildrenPitchID() + "\">" + cp.getChildrenPitchName() + "</option>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date dateNow = new Date();
            OwnerDAO od = new OwnerDAO();
            BookingDAO bd = new BookingDAO();
            String userID = request.getParameter("userID");
            String pitchID = request.getParameter("pitchID");
            String childrenPitchID = request.getParameter("childrenPitchID");
            String bookingDate = request.getParameter("dateBooking");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDate);

            List<Pitch> listP = od.getPitch(userID);
            List<ChildrenPitch> listCP = od.getChildrenPitch(pitchID);
            List<Booking> listB = od.findTime(childrenPitchID, date);
            List<Time> listT = bd.getTime();
            if (bookingDate.equals(fmt.format(dateNow))) {
                List<Booking> listPlayedEqualAfter = od.getUserBookingPlayedEqualAfter(childrenPitchID, date, smt.format(dateNow));
                List<Booking> listPlayedEqualBefore = od.getUserBookingPlayedEqualBefore(childrenPitchID, date, smt.format(dateNow));
                request.setAttribute("listPlayedEqualAfter", listPlayedEqualAfter);
                request.setAttribute("listPlayedEqualBefore", listPlayedEqualBefore);
            }
            if (date.after(dateNow)) {
                List<Booking> listPlayedAfter = od.getUserBookingPlayedAfter(childrenPitchID, date);
                System.out.println(listPlayedAfter);
                for (Booking booking : listPlayedAfter) {
                    System.out.println(booking.getBookingID());
                }
                request.setAttribute("listPlayedAfter", listPlayedAfter);
            } else if (date.before(dateNow)) {
                List<Booking> listPlayedBefore = od.getUserBookingPlayedBefore(childrenPitchID, date);
                request.setAttribute("listPlayedBefore", listPlayedBefore);
            }

            request.setAttribute("listB", listB);
            request.setAttribute("listT", listT);
            request.setAttribute("listP", listP);
            request.setAttribute("listCP", listCP);
            request.setAttribute("pitchID", pitchID);
            request.setAttribute("cpID", childrenPitchID);
            request.setAttribute("dateBooking", bookingDate);
            request.setAttribute("userID", userID);
            request.setAttribute("action", "viewBooking");
            System.out.println(pitchID);
            System.out.println(childrenPitchID);
            System.out.println(bookingDate);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            String bookingID = request.getParameter("Id");
            String reason = request.getParameter("Reason");
            SimpleDateFormat smt = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            UserDAO ud = new UserDAO();
            BookingDAO bd = new BookingDAO();
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            OwnerDAO od = new OwnerDAO();
            PitchDAO pd = new PitchDAO();
            SendEmail sm = new SendEmail();

            Booking booking = bd.getABooking(bookingID);
            User user = ud.getUser(booking.getUserID());
            ChildrenPitch childrenPitch = od.getChildrenPitchEmail(booking.getChildrenPitchID());
            Pitch pitch = pd.getAPitch(childrenPitch.getPitchID());
            Time time = od.getTime(booking.getTimeID());
            String finalTime = smt.format(time.getTimeStart()) + "-" + smt.format(time.getTimeEnd());
            System.out.println(reason);
            sm.sendEmailDelete(user, finalTime, childrenPitch.getChildrenPitchName(), pitch.getPitchName(), dateFormat.format(booking.getBookingDate()), reason);
            bd.deleteBooking(bookingID, reason);

            System.out.println(bookingID);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void detailBooking(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            String bookingID = request.getParameter("BookingID");
            System.out.println(bookingID);
            BookingDAO bd = new BookingDAO();
            UserDAO ud = new UserDAO();

            Booking booking = bd.getABooking(bookingID);
            User user = ud.getUser(booking.getUserID());

            out = response.getWriter();
            out.println("<form>\n"
                    + "    <div class=\"mb-3\">\n"
                    + "        <label for=\"exampleInputEmail1\" class=\"form-label\">Họ tên người đặt</label>\n"
                    + "        <input type=\"text\" value=\"" + user.getFullName() + "\" class=\"form-control\" id=\"exampleInputEmail1\" aria-describedby=\"emailHelp\" disabled=\"\">\n"
                    + "    </div>\n"
                    + "    <div class=\"mb-3\">\n"
                    + "        <label for=\"exampleInputEmail1\" class=\"form-label\">Số điện thoại</label>\n"
                    + "        <input type=\"text\" value=\"" + user.getPhone() + "\" class=\"form-control\" id=\"exampleInputEmail1\" aria-describedby=\"emailHelp\" disabled=\"\">\n"
                    + "    </div>\n"
                    + "    <div class=\"mb-3\">\n"
                    + "        <label for=\"exampleInputEmail1\" class=\"form-label\">Email</label>\n"
                    + "        <input type=\"email\" value=\"" + user.getEmail() + "\" class=\"form-control\" id=\"exampleInputEmail1\" aria-describedby=\"emailHelp\" disabled=\"\">\n"
                    + "    </div>\n"
                    + "</form>");
        } catch (IOException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private void childrenPitchManagement(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userID = request.getParameter("userID");
            OwnerDAO od = new OwnerDAO();
            List<Pitch> listP = od.getPitch(userID);
            ChildrenPitch children = od.getChildrenPitchEmail("C01");

            request.setAttribute("children", children);
            request.setAttribute("listP", listP);
            request.setAttribute("userID", userID);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewChildrenPitch(HttpServletRequest request, HttpServletResponse response) {
        try {
            OwnerDAO od = new OwnerDAO();

            String userID = request.getParameter("userID");
            String pitchID = request.getParameter("pitchID");
            List<ChildrenPitch> listCP = od.getChildrenPitch(pitchID);
            List<Pitch> listP = od.getPitch(userID);

            request.setAttribute("pitchID", pitchID);
            request.setAttribute("userID", userID);
            request.setAttribute("listP", listP);
            request.setAttribute("listCP", listCP);
            request.setAttribute("action", "childrenPitchManagement");
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewEdit(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            String ChildrenPitchID = request.getParameter("ChildrenPitchID");
            OwnerDAO od = new OwnerDAO();

            ChildrenPitch cp = od.getChildrenPitchEmail(ChildrenPitchID);

            out = response.getWriter();
            if (cp.getChildrenPitchType().equals("7")) {
                out.println("<form>\n"
                        + "        <input class=\"form-control\" type=\"hidden\" id=\"childrenPitchID\" value=\"" + cp.getChildrenPitchID() + "\">\n"
                        + "    <div class=\"mb-3\">\n"
                        + "        <label for=\"message-text\" class=\"col-form-label\">Tên sân con:</label>\n"
                        + "        <input class=\"form-control\" id=\"childrenPitchName\" value=\"" + cp.getChildrenPitchName() + "\">\n"
                        + "        <div class=\"invalid-feedback\" id=\"invalid-feedback-0\">\n"
                        + "            Vui lòng điền tên.\n"
                        + "        </div>\n"
                        + "        <label for=\"message-text\" class=\"col-form-label\">Số người:</label>\n"
                        + "        <select class=\"form-select\" id=\"childrenPitchType\" aria-label=\"Default select example\">\n"
                        + "            <option value=\"7\" selected>7</option>\n"
                        + "            <option value=\"5\">5</option>\n"
                        + "        </select>\n"
                        + "        <label for=\"message-text\" class=\"col-form-label\">Giá:</label>\n"
                        + "        <input class=\"form-control\" id=\"price\" value=\"" + String.format("%.0f", cp.getPrice()) + "\">\n"
                        + "        <div class=\"invalid-feedback\" id=\"invalid-feedback-1\">\n"
                        + "            Vui lòng điền giá.\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "</form>");
            } else {
                out.println("<form>\n"
                        + "        <input class=\"form-control\" type=\"hidden\" id=\"childrenPitchID\" value=\"" + cp.getChildrenPitchID() + "\">\n"
                        + "    <div class=\"mb-3\">\n"
                        + "        <label for=\"message-text\" class=\"col-form-label\">Tên sân con:</label>\n"
                        + "        <input class=\"form-control\" id=\"childrenPitchName\" value=\"" + cp.getChildrenPitchName() + "\">\n"
                        + "        <div class=\"invalid-feedback\" id=\"invalid-feedback-0\">\n"
                        + "            Vui lòng điền tên.\n"
                        + "        </div>\n"
                        + "        <label for=\"message-text\" class=\"col-form-label\">Số người:</label>\n"
                        + "        <select class=\"form-select\" id=\"childrenPitchType\" aria-label=\"Default select example\">\n"
                        + "            <option value=\"7\">7</option>\n"
                        + "            <option value=\"5\" selected>5</option>\n"
                        + "        </select>\n"
                        + "        <label for=\"message-text\" class=\"col-form-label\">Giá:</label>\n"
                        + "        <input class=\"form-control\" id=\"price\" value=\"" + String.format("%.0f", cp.getPrice()) + "\">\n"
                        + "        <div class=\"invalid-feedback\" id=\"invalid-feedback-1\">\n"
                        + "            Vui lòng điền giá.\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "</form>");
            }
        } catch (IOException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            String childrenPitchID = request.getParameter("childrenPitchID");
            String childrenPitchName = request.getParameter("childrenPitchName");
            String childrenPitchType = request.getParameter("childrenPitchType");
            double price = Double.parseDouble(request.getParameter("price"));
            OwnerDAO od = new OwnerDAO();

            ChildrenPitch cp = od.getChildrenPitchEmail(childrenPitchID);
            System.out.println(childrenPitchID + "/ " + childrenPitchName + "/ " + childrenPitchType + "/ " + price);
            System.out.println(cp.getPrice());
            od.updateChildrenPitch(childrenPitchName, childrenPitchType, price, childrenPitchID);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteChildrenPitch(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
            String childrenPitchID = request.getParameter("Id");
            Date dateNow = new Date();
            OwnerDAO od = new OwnerDAO();
            BookingDAO bd = new BookingDAO();
            
            List<Booking> listPlayedEqualAfter = od.getUserBookingPlayedEqualAfter(childrenPitchID, dateNow, smt.format(dateNow));
            List<Booking> listPlayedAfter = od.getUserBookingPlayedAfterForCP(childrenPitchID, dateNow);
            
            for (Booking booking : listPlayedEqualAfter) {
                bd.deleteBooking(booking.getBookingID(), "Sân con đã bị xóa bởi chủ sân");
            }
            System.out.println("----------------------------------------------------");
            for (Booking booking : listPlayedAfter) {
                bd.deleteBooking(booking.getBookingID(), "Sân con đã bị xóa bởi chủ sân");
            }
            od.deleteChildrenPitch(childrenPitchID);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
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
