/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BookingDAO;
import daos.ChildrenPitchDAO;
import daos.PitchDAO;
import daos.UserDAO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Booking;
import models.ChildrenPitch;
import models.Comment;
import models.District;
import static models.GoogleAccess.getToken;
import static models.GoogleAccess.getUserInfo;
import models.Pitch;
import models.SendEmail;
import models.User;
import models.UserGoogleDto;
import models.Ward;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

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
            case "bookingList":
                //Xu ly
                bookingList(request, response);

                break;
            case "login":
                //Xu ly
                login(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "register":
                register(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                //request.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(request, response);
                break;
            case "loginGoogle":
                loginGoogle(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            case "createAccount":
                createAccount(request, response);
                break;
            case "checkLogin":
                checkLogin(request, response);
                break;
            case "verify":
                verify(request, response);
                break;
            case "goToComment":
                goToComment(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "comment":
                comment(request, response);
                break;
            case "goToBecomingOwnerPage":
                goToBecomingOwnerPage(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "confirmBecomingOwner":
                confirmBecomingOwner(request, response);
                break;
            default:
                request.setAttribute("action", "error");
        }
        //Chon view de hien ket qua

    }

    private void bookingList(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("/WebsiteOrderStadium/user/login.do");
            } else {
                SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
                BookingDAO bd = new BookingDAO();
                PitchDAO pd = new PitchDAO();
                ChildrenPitchDAO cpd = new ChildrenPitchDAO();
                UserDAO ud = new UserDAO();
                String userID = request.getParameter("userID");
                Date date = new Date();

                session.removeAttribute("listNo");
                session.removeAttribute("countN");
                session.removeAttribute("listP1");
                session.removeAttribute("listCP1");

                List<Pitch> listP1 = pd.getAllPitch();
                List<ChildrenPitch> listCP = cpd.getChildrenPitch();
                List<Booking> listN = bd.getNotification(userID, date, smt.format(date), true);
                List<Booking> listPlayedBefore = bd.getUserBookingPlayedBefore(userID, date);
                List<Booking> listPlayedAfter = bd.getUserBookingPlayedAfter(userID, date);
                List<Booking> listPlayedEqualAfter = bd.getUserBookingPlayedEqualAfter(userID, date, smt.format(date));
                List<Booking> listPlayedEqualBefore = bd.getUserBookingPlayedEqualBefore(userID, date, smt.format(date));
                List<User> listU = ud.getAllUser();

                request.setAttribute("listU", listU);
                request.setAttribute("listPlayedBefore", listPlayedBefore);
                request.setAttribute("listPlayedAfter", listPlayedAfter);
                request.setAttribute("listPlayedEqualAfter", listPlayedEqualAfter);
                request.setAttribute("listPlayedEqualBefore", listPlayedEqualBefore);
                request.setAttribute("listP1", listP1);
                request.setAttribute("listCP1", listCP);
                request.setAttribute("listNo", listN);
                request.setAttribute("countN", listN.size());
                for (Booking booking : listPlayedAfter) {
                    System.out.println("-------------------------------");
                    System.out.println(booking.getBookingID());
                }
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {

    }

    private void loginGoogle(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
            UserDAO dao = new UserDAO();
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            PitchDAO pd = new PitchDAO();
            BookingDAO bd = new BookingDAO();
            List<User> list = dao.getAllUser();
            String userID = raiseProductId(list);
            String pitchID = (String) session.getAttribute("pitchID");
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            UserGoogleDto userGoogle = getUserInfo(accessToken);
            System.out.println(userGoogle);
            System.out.println(userID);
            User user = dao.checkUserEmailLoginGoogle(userGoogle.getEmail());
            Date date = new Date();

            List<ChildrenPitch> listCP = cpd.getChildrenPitch();
            List<Pitch> listP = pd.getAllPitch();

            if (user != null) {
                if (user.getRoleID().equals("US")) {
                    List<Booking> listN = bd.getNotification(user.getUserID(), date, smt.format(date), true);
                    session.setAttribute("listP", listP);
                    session.setAttribute("listNo", listN);
                    session.setAttribute("listCP1", listCP);
                    session.setAttribute("countN", listN.size());
                }
                session.setAttribute("user", user);
                //response.sendRedirect("/WebsiteOrderStadium/home/index.do");
                if (pitchID != null) {
                    response.sendRedirect("/WebsiteOrderStadium/stadium/detail.do?pitchID=" + pitchID);
                } else {
                    response.sendRedirect("/WebsiteOrderStadium/home/index.do");
                }
                session.removeAttribute("pitchID");
            } else {
                User u = new User(userID, "US", null, null, userGoogle.getEmail(), "", userGoogle.getName(), "", "", userGoogle.getEmail(), userGoogle.getPicture(), false, true);
                if (dao.insertUser(u)) {
                    session.setAttribute("user", u);
                    //response.sendRedirect("/WebsiteOrderStadium/home/index.do");
                    if (pitchID != null) {
                        response.sendRedirect("/WebsiteOrderStadium/stadium/detail.do?pitchID=" + pitchID);
                    } else {
                        response.sendRedirect("/WebsiteOrderStadium/home/index.do");
                    }
                    session.removeAttribute("pitchID");
                } else {
                    response.sendRedirect("/WebsiteOrderStadium/user/login.do");
                }
            }
            //response.sendRedirect("/WebsiteOrderStadium/home/index.do");
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) {
        try {
            PitchDAO pd = new PitchDAO();
            List<District> listD = pd.getDistrict();
            List<Ward> listWard = pd.getAllWard();
            request.setAttribute("listWard", listWard);
            request.setAttribute("listD", listD);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/WebsiteOrderStadium/home/index.do");
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            UserDAO dao = new UserDAO();
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String districtID = request.getParameter("districtID");
            String wardID = request.getParameter("ward");
            if (!password.equals(confirmPassword)) {
                request.setAttribute("action", "register");
                request.setAttribute("error", "Xác nhận mật khẩu không đúng");
            } else {
                User user = null;
                user = dao.checkDuplicateUsername(username);
                if (user != null) {
                    request.setAttribute("action", "register");
                    request.setAttribute("error", "Tên đăng nhập đã tồn tại");
                } else {
                    user = dao.checkUserEmail(email);
                    if (user != null) {
                        request.setAttribute("action", "register");
                        request.setAttribute("error", "Email đã tồn tại");
                    } else {
                        HttpSession session = request.getSession();
                        List<User> list = dao.getAllUser();
                        String userID = raiseProductId(list);
                        SendEmail sm = new SendEmail();
                        String code = sm.getRandom();
                        user = new User(userID, "US", wardID, districtID, username, password, fullname, phone, address, email, "/WebsiteOrderStadium/images/user.jpg", false, true);
                        boolean sendEmail = sm.sendEmail(user, code);
                        System.out.println(districtID + " , " + wardID);
                        session.setAttribute("code", code);
                        request.setAttribute("action", "verify");
                    }
                }
            }
            PitchDAO pd = new PitchDAO();
            List<District> listD = pd.getDistrict();
            List<Ward> listWard = pd.getAllWard();
            List<Ward> listW = pd.getWard(districtID);
            request.setAttribute("listWard", listWard);
            request.setAttribute("listD", listD);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("confirmPassword", confirmPassword);
            request.setAttribute("fullname", fullname);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("districtID", districtID);
            request.setAttribute("ward", wardID);
            request.setAttribute("listW", listW);
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");
            String pitchID = request.getParameter("pitchID");
            SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
            UserDAO dao = new UserDAO();
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            PitchDAO pd = new PitchDAO();
            BookingDAO bd = new BookingDAO();
            User user = dao.checkLogin(userName, password);
            if (user == null) {
                request.setAttribute("username", userName);
                request.setAttribute("password", password);
                request.setAttribute("error", "failed");
                request.setAttribute("action", "login");
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                Date date = new Date();
                List<Booking> listN = bd.getNotification(user.getUserID(), date, smt.format(date), true);
                List<ChildrenPitch> listCP = cpd.getChildrenPitch();
                List<Pitch> listP = pd.getAllPitch();
                if (user.getRoleID().equals("US")) {
                    session.setAttribute("listP1", listP);
                    session.setAttribute("listNo", listN);
                    session.setAttribute("listCP1", listCP);
                    session.setAttribute("countN", listN.size());
                }
//                for (int i = 0; i < listN.size(); i++) {
//                    for (int j = 0; j < listCP.size(); j++) {
//                        for (int k = 0; k < listP.size(); k++) {
//                            if (listN.get(i).getChildrenPitchID().equals(listCP.get(j).getChildrenPitchID()) && listCP.get(j).getPitchID().equals(listP.get(k).getPitchID())) {
//                                System.out.println(listN.get(i).getBookingID());
//                            }
//                        }
//
//                    }
//                }
//                for (Booking booking : listN) {
//                    for (ChildrenPitch ChildrenPitch : listCP) {
//                        for (Pitch pitch : listP) {
//                            if (booking.getChildrenPitchID().equals(ChildrenPitch.getChildrenPitchID()) && ChildrenPitch.getPitchID().equals(pitch.getPitchID())) {
//                                System.out.println(booking.getBookingID());
//                            }
//                        }
//                    }
//                }
                if (remember == null) {
                    Cookie[] cookies = request.getCookies();
                    // Delete all the cookies
                    if (cookies != null) {
                        for (int i = 0; i < cookies.length; i++) {
                            Cookie cookie = cookies[i];
                            cookies[i].setValue(null);
                            cookies[i].setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }

                    if (pitchID != null) {
                        response.sendRedirect("/WebsiteOrderStadium/stadium/detail.do?pitchID=" + pitchID);
                    } else {
                        if (user.getRoleID().equals("OW")) {
                            response.sendRedirect("/WebsiteOrderStadium/owner/index.do");
                        } else if (user.getRoleID().equals("AD")) {
                            response.sendRedirect("/WebsiteOrderStadium/admin/index.do");
                        } else {
                            response.sendRedirect("/WebsiteOrderStadium/home/index.do");
                        }
                    }
                } else {
                    if (remember.equals("1")) {
                        //Save in cookie
                        Cookie cookieUname = new Cookie("cookUname", userName);
                        cookieUname.setMaxAge(60 * 60 * 24 * 365 * 10);
                        Cookie cookiePass = new Cookie("cookPass", password);
                        cookiePass.setMaxAge(60 * 60 * 24 * 365 * 10);
                        Cookie cookieRemember = new Cookie("cookRem", remember);
                        cookieRemember.setMaxAge(60 * 60 * 24 * 365 * 10);
                        response.addCookie(cookieUname);
                        response.addCookie(cookiePass);
                        response.addCookie(cookieRemember);
                        if (pitchID != null) {
                            response.sendRedirect("/WebsiteOrderStadium/stadium/detail.do?pitchID=" + pitchID);
                        } else {
                            if (user.getRoleID().equals("OW")) {
                                response.sendRedirect("/WebsiteOrderStadium/owner/index.do");
                            } else if (user.getRoleID().equals("AD")) {
                                response.sendRedirect("/WebsiteOrderStadium/admin/index.do");
                            } else {
                                response.sendRedirect("/WebsiteOrderStadium/home/index.do");
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verify(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            UserDAO dao = new UserDAO();
            List<User> list = dao.getAllUser();
            HttpSession session = request.getSession();
            String num1 = request.getParameter("num1");
            String num2 = request.getParameter("num2");
            String num3 = request.getParameter("num3");
            String num4 = request.getParameter("num4");
            String num5 = request.getParameter("num5");
            String num6 = request.getParameter("num6");
            String userID = raiseProductId(list);
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String districtID = request.getParameter("districtID");
            String wardID = request.getParameter("ward");
            String finalNum = num1 + num2 + num3 + num4 + num5 + num6;
            String code = (String) session.getAttribute("code");
            if (finalNum.equals(code)) {
                request.setAttribute("action", "login");
                User user = new User(userID, "US", wardID, districtID, username, password, fullname, phone, address, email, "/WebsiteOrderStadium/images/user.jpg", false, true);
                dao.insertUser(user);
                session.removeAttribute("code");
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
            } else {
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("fullname", fullname);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("districtID", districtID);
                request.setAttribute("ward", wardID);
                request.setAttribute("action", "verify");
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
            }
        } catch (Exception ex) {

        }
    }

    private String raiseProductId(List<User> arrayList) throws Exception {
        try {
            UserDAO dao = new UserDAO();
            String result = "";
            int maxIndex = 1;
            if (arrayList == null) {
                maxIndex = 1;
                result = "U" + String.format("%02d", maxIndex);
            } else {
                maxIndex = arrayList.size() + 1;
                result = "U" + String.format("%02d", maxIndex);
                User temp = dao.getUser(result);
                while (temp != null) {
                    maxIndex += 1;
                    result = "U" + String.format("%02d", maxIndex);
                    temp = dao.getUser(result);;
                }
            }
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void goToComment(HttpServletRequest request, HttpServletResponse response) {
        String userID = request.getParameter("userID");
        String pitchID = request.getParameter("pitchID");
        request.setAttribute("userID", userID);
        request.setAttribute("pitchID", pitchID);
    }

    private void comment(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            String userID = request.getParameter("userID");
            String pitchID = request.getParameter("pitchID");
            String rating = request.getParameter("rating");
            String content = request.getParameter("content");
            UserDAO ud = new UserDAO();
            PitchDAO pd = new PitchDAO();
            List<Comment> listComment = ud.getAllComment();
            String commentID = raiseCommentId(listComment);
            Date date = new Date();
            int sum = 0;
            int pitchRating = 0;
            List<Comment> sumRating = ud.getComment(pitchID);
            for (int i = 0; i < sumRating.size(); i++) {
                sum = sum + sumRating.get(i).getRating();
            }
            pitchRating = (sum + Integer.parseInt(rating)) / (sumRating.size() + 1);
            System.out.println(pitchRating);
            Comment comment = new Comment(commentID, pitchID, userID, date, content, Integer.parseInt(rating));
            if (ud.insertComment(comment)) {
                pd.updatetEstimation(pitchRating, pitchID);
                response.sendRedirect("/WebsiteOrderStadium/stadium/detail.do?pitchID=" + pitchID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void goToBecomingOwnerPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            PitchDAO pd = new PitchDAO();
            List<District> listD = pd.getDistrict();
            List<Ward> listWard = pd.getAllWard();
            request.setAttribute("listWard", listWard);
            request.setAttribute("listD", listD);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void confirmBecomingOwner(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            UserDAO dao = new UserDAO();
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String districtID = request.getParameter("districtID");
            String wardID = request.getParameter("ward");
            if (!password.equals(confirmPassword)) {
                request.setAttribute("action", "goToBecomingOwnerPage");
                request.setAttribute("error", "Xác nhận mật khẩu không đúng");
            } else {
                User user = null;
                user = dao.checkDuplicateUsername(username);
                if (user != null) {
                    request.setAttribute("action", "goToBecomingOwnerPage");
                    request.setAttribute("error", "Tên đăng nhập đã tồn tại");
                } else {
                    user = dao.checkUserEmail(email);
                    if (user != null) {
                        request.setAttribute("action", "goToBecomingOwnerPage");
                        request.setAttribute("error", "Email đã tồn tại");
                    } else {
                        List<User> list = dao.getAllUser();
                        String userID = raiseProductId(list);
                        user = new User(userID, "OW", wardID, districtID, username, password, fullname, phone, address, email, "/WebsiteOrderStadium/images/user.jpg", true, false);
                        dao.insertUser(user);
                        request.setAttribute("success", "Đăng ký chở thành chủ sân thành công. Xin vui lòng chờ Admin xét duyệt!");
                        request.setAttribute("disabled", "disabled");
                        request.setAttribute("action", "goToBecomingOwnerPage");
                    }
                }
            }
            PitchDAO pd = new PitchDAO();
            List<District> listD = pd.getDistrict();
            List<Ward> listWard = pd.getAllWard();
            List<Ward> listW = pd.getWard(districtID);
            request.setAttribute("listWard", listWard);
            request.setAttribute("listD", listD);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("confirmPassword", confirmPassword);
            request.setAttribute("fullname", fullname);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("districtID", districtID);
            request.setAttribute("ward", wardID);
            request.setAttribute("listW", listW);
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String raiseCommentId(List<Comment> arrayList) throws Exception {
        try {
            UserDAO dao = new UserDAO();
            String result = "";
            int maxIndex = 1;
            if (arrayList == null) {
                maxIndex = 1;
                result = "M" + String.format("%02d", maxIndex);
            } else {
                maxIndex = arrayList.size() + 1;
                result = "M" + String.format("%02d", maxIndex);
                Comment temp = dao.getAComment(result);
                while (temp != null) {
                    maxIndex += 1;
                    result = "U" + String.format("%02d", maxIndex);
                    temp = dao.getAComment(result);
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
