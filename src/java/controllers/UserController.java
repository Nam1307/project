/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.PitchDAO;
import daos.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import models.District;
import static models.GoogleAccess.getToken;
import static models.GoogleAccess.getUserInfo;
import models.Pitch;
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
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "login":
                //Xu ly
                login(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "register":
                register(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
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
            default:
                request.setAttribute("action", "error");
        }
        //Chon view de hien ket qua

    }

    private void bookingList(HttpServletRequest request, HttpServletResponse response) {

    }

    private void login(HttpServletRequest request, HttpServletResponse response) {

    }

    private void loginGoogle(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            List<User> list = dao.getAllUser();
            String userID = raiseProductId(list);
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            UserGoogleDto userGoogle = getUserInfo(accessToken);
            System.out.println(userGoogle);
            System.out.println(userID);
            User user = dao.checkUserEmail(userGoogle.getEmail());
            if (user != null) {
                session.setAttribute("user", user);
                response.sendRedirect("/WebsiteOrderStadium/home/index.do");
            } else {
                User u = new User(userID, "US", null, null, userGoogle.getEmail(), "", userGoogle.getName(), "", "", userGoogle.getEmail(), userGoogle.getPicture());
                if (dao.insertUser(u)) {
                    session.setAttribute("user", u);
                    response.sendRedirect("/WebsiteOrderStadium/home/index.do");
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
            request.setAttribute("test", "aaa");
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            response.sendRedirect("/WebsiteOrderStadium/home/index.do");
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response) {
        try {
            String districtID = request.getParameter("districtID");
            String wardID = request.getParameter("ward");
            System.out.println(districtID + " , " + wardID);
            response.sendRedirect("/WebsiteOrderStadium/home/index.do");
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");
            UserDAO dao = new UserDAO();
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
                    response.sendRedirect("/WebsiteOrderStadium/home/index.do");
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
                        response.sendRedirect("/WebsiteOrderStadium/home/index.do");
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
