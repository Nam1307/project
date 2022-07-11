/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AdminDAO;
import daos.BookingDAO;
import daos.ChildrenPitchDAO;
import daos.OwnerDAO;
import daos.PitchDAO;
import daos.UserDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Booking;
import models.ChildrenPitch;
import models.District;
import models.Pitch;
import models.SendEmail;
import models.Time;
import models.User;
import models.Ward;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
@MultipartConfig
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
            case "index":
                //Xu ly
                index(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
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
            case "createChildrenPitch":
                createChildrenPitch(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "create":
                create(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "pitchManagement":
                pitchManagement(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "editPitch":
                //Xu ly
                editPitch(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "updatePitch":
                //Xu ly
                updatePitch(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "goToLoadPicture":
                //Xu ly
                goToLoadPicture(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "createPitch":
                //Xu ly
                createPitch(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "goToLoadPictureCreate":
                //Xu ly
                goToLoadPictureCreate(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "createNewPitch":
                //Xu ly
                createNewPitch(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "deletePitch":
                //Xu ly
                deletePitch(request, response);
                break;
            default:
                request.setAttribute("action", "error");
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            OwnerDAO od = new OwnerDAO();
            
            int june = od.getDataForChart("2022/06/01", "2022/06/30", user.getUserID());
            int july = od.getDataForChart("2022/07/01", "2022/07/31", user.getUserID());
            int august = od.getDataForChart("2022/08/01", "2022/08/31", user.getUserID());
            int september = od.getDataForChart("2022/09/01", "2022/09/30", user.getUserID());
            int october = od.getDataForChart("2022/10/01", "2022/10/31", user.getUserID());
            int november = od.getDataForChart("2022/11/01", "2022/11/30", user.getUserID());
            int december = od.getDataForChart("2022/12/01", "2022/12/31", user.getUserID());
            int allChildrenPitch = od.getAllChildrenPitchForOwner(user.getUserID());
            int allBooking = od.getAllBookingForOwner(user.getUserID());
            List<Pitch> listP = od.getPitch(user.getUserID());

            request.setAttribute("june", june);
            request.setAttribute("july", july);
            request.setAttribute("august", august);
            request.setAttribute("september", september);
            request.setAttribute("october", october);
            request.setAttribute("november", november);
            request.setAttribute("december", december);
            request.setAttribute("allChildrenPitch", allChildrenPitch);
            request.setAttribute("allBooking", allBooking);
            request.setAttribute("listP", listP);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewBooking(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userID = request.getParameter("userID");
            OwnerDAO od = new OwnerDAO();
            BookingDAO bd = new BookingDAO();
            PitchDAO pd = new PitchDAO();
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            Date dateNow = new Date();
            SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
//            List<Pitch> listP = od.getPitch(userID);
//            request.setAttribute("listP", listP);
            List<Pitch> listP = pd.getAllPitch();
            List<ChildrenPitch> listCP = cpd.getChildrenPitch();
            List<Time> listT = bd.getTime();
            List<Booking> listPlayedEqualAfter = od.getUserBookingPlayedEqualAfter(dateNow, smt.format(dateNow),userID);
            List<Booking> listPlayedEqualBefore = od.getUserBookingPlayedEqualBefore(dateNow, smt.format(dateNow), userID);
//            List<Booking> listPlayedAfter = od.getUserBookingPlayedAfter(dateNow);
//            List<Booking> listPlayedBefore = od.getUserBookingPlayedBefore(dateNow);

            request.setAttribute("listP", listP);
            request.setAttribute("listCP", listCP);
            request.setAttribute("listT", listT);
//            request.setAttribute("listPlayedBefore", listPlayedBefore);
//            request.setAttribute("listPlayedAfter", listPlayedAfter);
            request.setAttribute("listPlayedEqualAfter", listPlayedEqualAfter);
            request.setAttribute("listPlayedEqualBefore", listPlayedEqualBefore);
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
            HttpSession session = request.getSession();
            SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date dateNow = new Date();
            OwnerDAO od = new OwnerDAO();
            BookingDAO bd = new BookingDAO();
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            PitchDAO pd = new PitchDAO();
            User user = (User) session.getAttribute("user");
//            String userID = request.getParameter("userID");
//            String pitchID = request.getParameter("pitchID");
//            String childrenPitchID = request.getParameter("childrenPitchID");
            String bookingDate = request.getParameter("dateBooking");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDate);

//            List<Pitch> listP = od.getPitch(userID);
//            List<ChildrenPitch> listCP = od.getChildrenPitch(pitchID);
//            List<Booking> listB = od.findTime(childrenPitchID, date);
            List<Pitch> listP = pd.getAllPitch();
            List<ChildrenPitch> listCP = cpd.getChildrenPitch();
            List<Time> listT = bd.getTime();
            if (bookingDate.equals(fmt.format(dateNow))) {
                List<Booking> listPlayedEqualAfter = od.getUserBookingPlayedEqualAfter(date, smt.format(dateNow), user.getUserID());
                List<Booking> listPlayedEqualBefore = od.getUserBookingPlayedEqualBefore(date, smt.format(dateNow), user.getUserID());
                request.setAttribute("listPlayedEqualAfter", listPlayedEqualAfter);
                request.setAttribute("listPlayedEqualBefore", listPlayedEqualBefore);
            } else {
                if (date.after(dateNow)) {
                    List<Booking> listPlayedAfter = od.getUserBookingPlayedAfter(date, user.getUserID());
                    System.out.println(listPlayedAfter);
                    for (Booking booking : listPlayedAfter) {
                        System.out.println(booking.getBookingID());
                    }
                    request.setAttribute("listPlayedAfter", listPlayedAfter);
                } else if (date.before(dateNow)) {
                    List<Booking> listPlayedBefore = od.getUserBookingPlayedBefore(date, user.getUserID());
                    request.setAttribute("listPlayedBefore", listPlayedBefore);
                }
            }
            request.setAttribute("listP", listP);
            request.setAttribute("listCP", listCP);
//            request.setAttribute("listB", listB);
            request.setAttribute("listT", listT);
//            request.setAttribute("listP", listP);
//            request.setAttribute("listCP", listCP);
//            request.setAttribute("pitchID", pitchID);
//            request.setAttribute("cpID", childrenPitchID);
            request.setAttribute("dateBooking", bookingDate);
//            request.setAttribute("userID", userID);
            request.setAttribute("action", "viewBooking");
//            System.out.println(pitchID);
//            System.out.println(childrenPitchID);
//            System.out.println(bookingDate);
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
            PitchDAO pd = new PitchDAO();

            List<ChildrenPitch> listCP = od.getChildrenPitchForOwner(userID);
            List<Pitch> listP = pd.getAllPitch();
//            List<Pitch> listP = od.getPitch(userID);

            request.setAttribute("listP", listP);
            request.setAttribute("listCP", listCP);
            request.setAttribute("userID", userID);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void viewChildrenPitch(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            OwnerDAO od = new OwnerDAO();
//
//            String userID = request.getParameter("userID");
//            String pitchID = request.getParameter("pitchID");
////            List<ChildrenPitch> listCP = od.getChildrenPitch(pitchID);
////            List<Pitch> listP = od.getPitch(userID);
//
//            request.setAttribute("pitchID", pitchID);
//            request.setAttribute("userID", userID);
//            request.setAttribute("listP", listP);
//            request.setAttribute("listCP", listCP);
//            request.setAttribute("action", "childrenPitchManagement");
//        } catch (SQLException ex) {
//            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
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

            List<Booking> listPlayedEqualAfter = od.getUserBookingPlayedEqualAfterForCP(childrenPitchID, dateNow, smt.format(dateNow));
            List<Booking> listPlayedAfter = od.getUserBookingPlayedAfterForCP(childrenPitchID, dateNow);

            for (Booking booking : listPlayedEqualAfter) {
                bd.deleteBooking(booking.getBookingID(), "Sân con đã bị xóa bởi chủ sân");
            }
            for (Booking booking : listPlayedAfter) {
                bd.deleteBooking(booking.getBookingID(), "Sân con đã bị xóa bởi chủ sân");
            }
            od.deleteChildrenPitch(childrenPitchID);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createChildrenPitch(HttpServletRequest request, HttpServletResponse response) {
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

    private void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            OwnerDAO od = new OwnerDAO();
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            String pitchID = request.getParameter("pitchID");
            String cpName = request.getParameter("cpName");
            String cpPrice = request.getParameter("cpPrice");
            String cpType = request.getParameter("cpType");
            List<ChildrenPitch> listChildrenPitch = cpd.getChildrenPitch();
            String cpID = raiseChildrenPitchId(listChildrenPitch);

            ChildrenPitch childrenPitch = new ChildrenPitch(cpID, pitchID, cpName, cpType, Double.parseDouble(cpPrice), true);

            if (od.insertChildrenPitch(childrenPitch)) {
                request.setAttribute("success", "Thêm sân con thành công");
            }
            request.setAttribute("action", "createChildrenPitch");
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String raiseChildrenPitchId(List<ChildrenPitch> arrayList) throws Exception {
        try {
            ChildrenPitchDAO dao = new ChildrenPitchDAO();
            String result = "";
            int maxIndex = 1;
            if (arrayList == null) {
                maxIndex = 1;
                result = "C" + String.format("%02d", maxIndex);
            } else {
                maxIndex = arrayList.size() + 1;
                result = "C" + String.format("%02d", maxIndex);
                ChildrenPitch temp = dao.getAChildrenPitch(result);
                while (temp != null) {
                    maxIndex += 1;
                    result = "C" + String.format("%02d", maxIndex);
                    temp = dao.getAChildrenPitch(result);
                }
            }
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void pitchManagement(HttpServletRequest request, HttpServletResponse response) {
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

    private void editPitch(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pitchID = request.getParameter("PitchID");
            PitchDAO pd = new PitchDAO();

            Pitch pitch = pd.getAPitch(pitchID);
            List<District> listD = pd.getDistrict();
            List<Ward> listW = pd.getWard(pitch.getDistrictID());

            request.setAttribute("listD", listD);
            request.setAttribute("listW", listW);
            request.setAttribute("p", pitch);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void goToLoadPicture(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        String pitchName = request.getParameter("pitchName");
        String pitchID = request.getParameter("pitchID");
        String districtID = request.getParameter("districtID");
        String wardID = request.getParameter("ward");
        String pitchAddress = request.getParameter("address");
        String pitchDescription = request.getParameter("description");
        Pitch pitch = new Pitch(pitchID, wardID, districtID, "", pitchName, pitchAddress, 0, pitchDescription, true);
        System.out.println(pitchName + "-" + pitchDescription);
        session.setAttribute("pitchUpdate", pitch);
        request.setAttribute("pitchID", pitchID);
        request.setAttribute("action", "uploadPitchPicture");
    }

    private void updatePitch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Pitch pitch = (Pitch) session.getAttribute("pitchUpdate");
            String pitchID = pitch.getPitchID();
            System.out.println(pitch.getPitchID());
            OwnerDAO od = new OwnerDAO();
            DiskFileItemFactory factory = new DiskFileItemFactory();

            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            ServletFileUpload upload = new ServletFileUpload(factory);

            List<FileItem> items = upload.parseRequest(request);

            Iterator<FileItem> iter = items.iterator();

            HashMap<String, String> fields = new HashMap<>();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {
                    fields.put(item.getFieldName(), item.getString());
                    String name = item.getFieldName();
                    String value = item.getString();
                    System.out.println(name);
                    System.out.println(value);
                } else {
                    String filename = item.getName();
                    System.out.println("filename: " + filename);
                    if (filename == null || filename.equals("")) {
                        od.updatePitch(pitch.getWardID(), pitch.getDistrictID(), pitch.getPitchName(), pitch.getPitchAddress(), pitch.getPitchDescription(), pitch.getPitchID());
                    } else {
                        Path path = Paths.get(filename);
                        String realPath = request.getServletContext().getInitParameter("file-upload");
                        Path source = Paths.get(realPath + "/" + pitchID + ".jpg");
                        Files.move(source, source.resolveSibling(pitchID + "_d.jpg"));
                        Files.delete(Paths.get(realPath + "/" + pitchID + "_d.jpg"));
                        File f = new File(realPath + "/" + pitchID + ".jpg");
                        if (!f.exists()) {
                            String fileName = Paths.get(path.getFileName().toString().replaceAll(path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf(".")), fields.get("pitchID"))).getFileName().toString();
                            if (!Files.exists(Paths.get(realPath))) {
                                Files.createDirectory(Paths.get(realPath));
                            }
                            File uploadFile = new File(realPath + "/" + fileName);
                            System.out.println(fileName);
                            item.write(uploadFile);
                            od.updatePitch(pitch.getWardID(), pitch.getDistrictID(), pitch.getPitchName(), pitch.getPitchAddress(), pitch.getPitchDescription(), pitch.getPitchID());
                        }
                    }
                }
            }
            request.setAttribute("success", "Cập nhật thành công");
            request.setAttribute("pitchID", pitchID);
            session.removeAttribute("pitchUpdate");
            request.setAttribute("action", "uploadPitchPicture");
        } catch (FileUploadException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createPitch(HttpServletRequest request, HttpServletResponse response) {
        try {
            PitchDAO pd = new PitchDAO();

            List<District> listD = pd.getDistrict();

            request.setAttribute("listD", listD);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void goToLoadPictureCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PitchDAO pd = new PitchDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            List<Pitch> list = pd.getAllPitch();

            String pitchName = request.getParameter("pitchName");
            String pitchID = raisePitchId(list);
            String districtID = request.getParameter("districtID");
            String wardID = request.getParameter("ward");
            String pitchAddress = request.getParameter("address");
            String pitchDescription = request.getParameter("description");
            Pitch pitch = new Pitch(pitchID, wardID, districtID, user.getUserID(), pitchName, pitchAddress, 5, pitchDescription, true);

            System.out.println(pitchName + "-" + pitchDescription + "-" + pitchID + "-" + districtID);
            session.setAttribute("pitchCreate", pitch);
            request.setAttribute("pitchID", pitchID);
            request.setAttribute("action", "uploadPitchPictureCreate");
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String raisePitchId(List<Pitch> arrayList) throws Exception {
        try {
            PitchDAO dao = new PitchDAO();
            String result = "";
            int maxIndex = 1;
            if (arrayList == null) {
                maxIndex = 1;
                result = "P" + String.format("%02d", maxIndex);
            } else {
                maxIndex = arrayList.size() + 1;
                result = "P" + String.format("%02d", maxIndex);
                Pitch temp = dao.getAPitch(result);
                while (temp != null) {
                    maxIndex += 1;
                    result = "C" + String.format("%02d", maxIndex);
                    temp = dao.getAPitch(result);
                }
            }
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void createNewPitch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Pitch pitch = (Pitch) session.getAttribute("pitchCreate");
            OwnerDAO od = new OwnerDAO();
            DiskFileItemFactory factory = new DiskFileItemFactory();

            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            ServletFileUpload upload = new ServletFileUpload(factory);

            List<FileItem> items = upload.parseRequest(request);

            Iterator<FileItem> iter = items.iterator();

            HashMap<String, String> fields = new HashMap<>();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {
                    fields.put(item.getFieldName(), item.getString());
                } else {
                    String filename = item.getName();
                    System.out.println("filename: " + filename);
                    if (filename == null || filename.equals("")) {
                        od.updatePitch(pitch.getWardID(), pitch.getDistrictID(), pitch.getPitchName(), pitch.getPitchAddress(), pitch.getPitchDescription(), pitch.getPitchID());
                    } else {
                        Path path = Paths.get(filename);
                        String realPath = request.getServletContext().getInitParameter("file-upload");
                        String fileName = Paths.get(path.getFileName().toString().replaceAll(path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf(".")), pitch.getPitchID())).getFileName().toString();
                        if (!Files.exists(Paths.get(realPath))) {
                            Files.createDirectory(Paths.get(realPath));
                        }
                        File uploadFile = new File(realPath + "/" + fileName);
                        System.out.println(fileName);
                        item.write(uploadFile);
                        od.insertPitch(pitch);
                    }
                }
            }
            request.setAttribute("success", "Thêm mới thành công");
            session.removeAttribute("pitchCreate");
            request.setAttribute("action", "uploadPitchPictureCreate");
        } catch (FileUploadException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deletePitch(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
            Date dateNow = new Date();
            String pitchID = request.getParameter("Id");
            System.out.println(pitchID);
            AdminDAO ad = new AdminDAO();
            OwnerDAO od = new OwnerDAO();
            BookingDAO bd = new BookingDAO();
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();

            if (ad.deleteCommentByPitchID(pitchID)) {
                List<ChildrenPitch> listCP = cpd.getType(pitchID);
                for (ChildrenPitch childrenPitch : listCP) {
                    List<Booking> listPlayedEqualAfter = od.getUserBookingPlayedEqualAfterForCP(childrenPitch.getChildrenPitchID(), dateNow, smt.format(dateNow));
                    for (Booking booking : listPlayedEqualAfter) {
                        bd.deleteBooking(booking.getBookingID(), "Sân con đã bị xóa bởi chủ sân");
                    }
                    List<Booking> listPlayedAfter = od.getUserBookingPlayedAfterForCP(childrenPitch.getChildrenPitchID(), dateNow);
                    for (Booking booking : listPlayedAfter) {
                        bd.deleteBooking(booking.getBookingID(), "Sân con đã bị xóa bởi chủ sân");
                    }
                    od.deleteChildrenPitch(childrenPitch.getChildrenPitchID());
                }
            }
            od.deletePitch(pitchID);

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
