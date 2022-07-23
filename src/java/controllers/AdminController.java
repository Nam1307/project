/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AdminDAO;
import daos.PitchDAO;
import daos.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Booking;
import models.Comment;
import models.District;
import models.SendEmail;
import models.User;
import models.Ward;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

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
            case "userManagement":
                //Xu ly
                userManagement(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "viewBecomingOwner":
                //Xu ly
                viewBecomingOwner(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "getBecomingOwnerInfo":
                //Xu ly
                getBecomingOwnerInfo(request, response);
                break;
            case "confirmOwner":
                //Xu ly
                confirmOwner(request, response);
                break;
            case "denyOwner":
                //Xu ly
                denyOwner(request, response);
                break;
            case "deleteUser":
                //Xu ly
                deleteUser(request, response);
                break;
            case "commentManagement":
                //Xu ly
                commentManagement(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "deleteComment":
                //Xu ly
                deleteComment(request, response);
                break;
            default:
                request.setAttribute("action", "error");
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            AdminDAO ad = new AdminDAO();
            UserDAO ud = new UserDAO();

            List<Comment> listC = ud.getAllComment();
            List<User> listNoAdmin = ad.getUserForBecomingOwner();
            int numUS = ad.getNumberOfUserByRole("US");
            int numOW = ad.getNumberOfUserByRole("OW");

            request.setAttribute("listC", listC);
            request.setAttribute("listNoAdmin", listNoAdmin);
            request.setAttribute("countNoAdmin", listNoAdmin.size());
            request.setAttribute("numUS", numUS);
            request.setAttribute("numOW", numOW);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void userManagement(HttpServletRequest request, HttpServletResponse response) {
        try {
            AdminDAO ad = new AdminDAO();
            List<Booking> countCancel = ad.countCancel();
            List<User> listU = ad.getUserActive();
            List<User> listNoAdmin = ad.getUserForBecomingOwner();
            request.setAttribute("listNoAdmin", listNoAdmin);
            request.setAttribute("countNoAdmin", listNoAdmin.size());
            request.setAttribute("listU", listU);
            request.setAttribute("countCancel", countCancel);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewBecomingOwner(HttpServletRequest request, HttpServletResponse response) {
        try {
            AdminDAO ad = new AdminDAO();
            List<User> listU = ad.getUserForBecomingOwner();
            List<User> listNoAdmin = ad.getUserForBecomingOwner();
            request.setAttribute("listNoAdmin", listNoAdmin);
            request.setAttribute("countNoAdmin", listNoAdmin.size());
            request.setAttribute("listU", listU);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getBecomingOwnerInfo(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            String userID = request.getParameter("UserID");
            PitchDAO pd = new PitchDAO();
            UserDAO ud = new UserDAO();

            User user = ud.getUser(userID);
            List<Ward> listW = pd.getAllWard();
            List<District> listD = pd.getDistrict();
            if (user.getDistrictID() != null && user.getWardID() != null) {
                for (District district : listD) {
                    for (Ward ward : listW) {
                        if (user.getDistrictID().equals(district.getDistrictID()) && user.getWardID().equals(ward.getWardID())) {
                            out = response.getWriter();
                            out.println("<form>\n"
                                    + "    <div class=\"mb-3\">\n"
                                    + "        <label for=\"exampleInputEmail1\" class=\"form-label\">Họ tên người chờ xét duyệt</label>\n"
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
                                    + "    <div class=\"mb-3\">\n"
                                    + "        <label for=\"exampleInputEmail1\" class=\"form-label\">Địa chỉ</label>\n"
                                    + "        <input type=\"email\" value=\"" + user.getUserAddress() + ", " + ward.getWardName() + ", " + district.getDistrictName() + "\" class=\"form-control\" id=\"exampleInputEmail1\" aria-describedby=\"emailHelp\" disabled=\"\">\n"
                                    + "    </div>\n"
                                    + "</form>");
                        }
                    }
                }
            } else {
                out = response.getWriter();

                out.println("<form>\n"
                        + "    <div class=\"mb-3\">\n"
                        + "        <label for=\"exampleInputEmail1\" class=\"form-label\">Họ tên người chờ xét duyệt</label>\n"
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
                        + "    <div class=\"mb-3\">\n"
                        + "        <label for=\"exampleInputEmail1\" class=\"form-label\">Địa chỉ</label>\n"
                        + "        <input type=\"email\" value=\"\" class=\"form-control\" id=\"exampleInputEmail1\" aria-describedby=\"emailHelp\" disabled=\"\">\n"
                        + "    </div>\n"
                        + "</form>");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void confirmOwner(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userID = request.getParameter("Id");
            UserDAO ud = new UserDAO();
            AdminDAO ad = new AdminDAO();
            User user = ud.getUser(userID);
            SendEmail sm = new SendEmail();

            if (ad.updateBecomingOwner(userID)) {
                sm.sendEmailConfirmOwner(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void denyOwner(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            String userID = request.getParameter("Id");
            String reason = request.getParameter("Reason");
            UserDAO ud = new UserDAO();

            User user = ud.getUser(userID);
            SendEmail sm = new SendEmail();
            sm.sendEmailDenyOwner(user, reason);
        } catch (UnsupportedEncodingException | SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            String userId = request.getParameter("Id");
            String reason = request.getParameter("Reason");
            AdminDAO ad = new AdminDAO();
            UserDAO ud = new UserDAO();
            SendEmail se = new SendEmail();

            User user = ud.getUser(userId);

            if (ad.deleteUser(userId)) {
                se.sendEmailDeleteUser(user, reason);
            }
        } catch (UnsupportedEncodingException | SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void commentManagement(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserDAO ud = new UserDAO();
            List<Comment> listC = ud.getAllComment();
            List<User> listU = ud.getAllUser();
            request.setAttribute("listC", listC);
            request.setAttribute("listU", listU);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pitchID = request.getParameter("pitchID");
            String commentID = request.getParameter("commentID");
            UserDAO ud = new UserDAO();
            AdminDAO ad = new AdminDAO();
            PitchDAO pd = new PitchDAO();

            if (ad.deleteComment(commentID)) {
                int sum = 0;
                int pitchRating = 0;
                List<Comment> sumRating = ud.getComment(pitchID);
                if (sumRating.size() > 1) {
                    for (int i = 0; i < sumRating.size(); i++) {
                        sum = sum + sumRating.get(i).getRating();
                    }
                    pitchRating = (sum / (sumRating.size())); 
                }else{
                    pitchRating = 5;
                }
                pd.updatetEstimation(pitchRating, pitchID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
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
