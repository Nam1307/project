/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BookingDAO;
import daos.ChildrenPitchDAO;
import daos.PitchDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Booking;
import models.ChildrenPitch;
import models.District;
import models.Pitch;
import models.User;
import models.Ward;

/**
 *
 * @author DELL
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

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
            case "index":
                //Xử lý trang index
                index(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            //Xử lý trang about
            case "about":
                about(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            case "ward":
                ward(request, response);
                break;
            case "search":
                search(request, response);
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("action", "error");
        }
        //Chon view de hien ket qua
    }

    private void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            PitchDAO pd = new PitchDAO();
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            BookingDAO bd = new BookingDAO();
            HttpSession session = request.getSession();
            session.removeAttribute("listNo");
            session.removeAttribute("countN");
            session.removeAttribute("listP1");
            session.removeAttribute("listCP1");

//            int pageSize = 8;//Kich thuoc trang                        
//
//            //Xac dinh so thu tu cua trang hien tai
//            Integer page = (Integer) session.getAttribute("page");
//            if (page == null) {
//                page = 1;
//            }
//
//            //Xac dinh tong so trang
//            Integer totalPage = (Integer) session.getAttribute("totalPage");
//            int count = pd.getNumberOfPitch();//Dem so luong records
//            totalPage = (int) Math.ceil((double) count / pageSize);//Tinh tong so trang
//
//            String op = request.getParameter("op");
//            if (op == null) {
//                op = "FirstPage";
//            }
//            switch (op) {
//                case "FirstPage":
//                    page = 1;
//                    break;
//                case "PreviousPage":
//                    if (page > 1) {
//                        page--;
//                    }
//                    break;
//                case "NextPage":
//                    if (page < totalPage) {
//                        page++;
//                    }
//                    break;
//                case "LastPage":
//                    page = totalPage;
//                    break;
//                case "GotoPage":
//                    page = Integer.parseInt(request.getParameter("gotoPage"));
//                    System.out.println(page);
//                    if (page <= 0) {
//                        page = 1;
//                    } else if (page > totalPage) {
//                        page = totalPage;
//                    }
//                    break;
//            }
//
//            //Lay trang du lieu duoc yeu cau
//            int n1 = (page - 1) * pageSize;//Vi tri mau tin dau trang
//            List<Pitch> listP = pd.getPitch(n1, pageSize);
//
//            //Luu thong tin vao session va request
//            session.setAttribute("page", page);
//            session.setAttribute("totalPage", totalPage);
            int pageSize = 8;
            int n1;
            List<Pitch> listP = null;
            User user = (User) session.getAttribute("user");
            Date date = new Date();
            SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
            List<Booking> listN = null;
            if (user != null) {
                if (user.getRoleID().equals("US")) {
                    listN = bd.getNotification(user.getUserID(), date, smt.format(date), true);
                    n1 = pagination(request, response, pd.getNumberOfPitch());
                    listP = pd.getPitch(n1, pageSize);
                    request.setAttribute("listNo", listN);
                    request.setAttribute("countN", listN.size());
                } else if (user.getRoleID().equals("OW")) {
                    n1 = pagination(request, response, pd.getNumberOfPitchOwner(user.getUserID()));
                    listP = pd.getPitchOwner(user.getUserID(), n1, pageSize);
                }
            } else {
                n1 = pagination(request, response, pd.getNumberOfPitch());
                listP = pd.getPitch(n1, pageSize);
            }
            List<District> listD = pd.getDistrict();
            List<Ward> listWard = pd.getAllWard();
            List<Pitch> listHighRate = pd.getHighRatePitch();
            List<Pitch> listP1 = pd.getAllPitch();
            List<ChildrenPitch> listCP = cpd.getChildrenPitch();
            List<ChildrenPitch> listMaxPrice = cpd.getMaxPrice();
            List<ChildrenPitch> listMinPrice = cpd.getMinPrice();
            List<ChildrenPitch> listFinalMinPrice = new ArrayList<>();
            for (int i = 0; i < listMaxPrice.size(); i++) {
                for (int j = 0; j < listMinPrice.size(); j++) {
                    if (!Objects.equals(listMaxPrice.get(i).getPrice(), listMinPrice.get(j).getPrice()) && listMaxPrice.get(i).getPitchID().equals(listMinPrice.get(j).getPitchID())) {
                        listFinalMinPrice.add(listMinPrice.get(j));
                    }
                }
            }
            request.setAttribute("listMinP", listFinalMinPrice);
            request.setAttribute("listMaxP", listMaxPrice);
            request.setAttribute("listCP", listCP);
            request.setAttribute("listHR", listHighRate);
            request.setAttribute("listWard", listWard);
            request.setAttribute("listP", listP);
            request.setAttribute("listP1", listP1);
            request.setAttribute("listCP1", listCP);
            request.setAttribute("listD", listD);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int pagination(HttpServletRequest request, HttpServletResponse response, int numberOfPitch) {
        int n1 = 0;
        HttpSession session = request.getSession();
        int pageSize = 8;//Kich thuoc trang
        //Xac dinh so thu tu cua trang hien tai
        Integer page = (Integer) session.getAttribute("page");
        if (page == null) {
            page = 1;
        }
        //Xac dinh tong so trang
        Integer totalPage = (Integer) session.getAttribute("totalPage");
        int count = numberOfPitch;//Dem so luong records
        totalPage = (int) Math.ceil((double) count / pageSize);//Tinh tong so trang
        String op = request.getParameter("op");
        if (op == null) {
            op = "FirstPage";
        }
        switch (op) {
            case "FirstPage":
                page = 1;
                break;
            case "PreviousPage":
                if (page > 1) {
                    page--;
                }
                break;
            case "NextPage":
                if (page < totalPage) {
                    page++;
                }
                break;
            case "LastPage":
                page = totalPage;
                break;
            case "GotoPage":
                page = Integer.parseInt(request.getParameter("gotoPage"));
                System.out.println(page);
                if (page <= 0) {
                    page = 1;
                } else if (page > totalPage) {
                    page = totalPage;
                }
                break;
        }
        //Lay trang du lieu duoc yeu cau
        n1 = (page - 1) * pageSize;//Vi tri mau tin dau trang
        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        return n1;
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

    private void about(HttpServletRequest request, HttpServletResponse response) {

    }

    private void ward(HttpServletRequest request, HttpServletResponse response) {
        try {
            PitchDAO pd = new PitchDAO();
            String districtID = request.getParameter("districtID");
            List<Ward> listW = pd.getWard(districtID);
            PrintWriter out = response.getWriter();
            for (Ward ward : listW) {
                out.println("<input type=\"hidden\" name=\"districtID\" value=\"" + ward.getDistrictID() + "\"/>\n"
                        + "                            <option value=\"" + ward.getWardID() + "\">" + ward.getWardName() + "</option>");
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
            User user = (User) session.getAttribute("user");
            session.removeAttribute("listNo");
            session.removeAttribute("countN");
            session.removeAttribute("listP1");
            session.removeAttribute("listCP1");
            Date date = new Date();
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            PitchDAO pd = new PitchDAO();
            BookingDAO bd = new BookingDAO();
            String districtID = request.getParameter("districtID");
            String wardID = request.getParameter("ward");

            if (districtID == null || wardID == null) {
                index(request, response);
                request.setAttribute("action", "index");
            } else {
                int pageSize = 8;//Kich thuoc trang                        
                int n1;
                List<Pitch> listP = null;
//                //Xac dinh so thu tu cua trang hien tai
//                Integer page = (Integer) session.getAttribute("page");
//                if (page == null) {
//                    page = 1;
//                }
//
//                //Xac dinh tong so trang
//                Integer totalPage = (Integer) session.getAttribute("totalPage");
//                int count = pd.getNumberOfPitchAterSearching(districtID, wardID);//Dem so luong records
//                totalPage = (int) Math.ceil((double) count / pageSize);//Tinh tong so trang
//
//                String op = request.getParameter("op");
//                if (op == null) {
//                    op = "FirstPage";
//                }
//                switch (op) {
//                    case "FirstPage":
//                        page = 1;
//                        break;
//                    case "PreviousPage":
//                        if (page > 1) {
//                            page--;
//                        }
//                        break;
//                    case "NextPage":
//                        if (page < totalPage) {
//                            page++;
//                        }
//                        break;
//                    case "LastPage":
//                        page = totalPage;
//                        break;
//                    case "GotoPage":
//                        page = Integer.parseInt(request.getParameter("gotoPage"));
//                        if (page <= 0) {
//                            page = 1;
//                        } else if (page > totalPage) {
//                            page = totalPage;
//                        }
//                        break;
//                }
//
//                //Lay trang du lieu duoc yeu cau
//                int n1 = (page - 1) * pageSize;//Vi tri mau tin dau trang
//                List<Pitch> listP = pd.getPitchAfterSearch(districtID, wardID, n1, pageSize);

                //Luu thong tin vao session va request
//                session.setAttribute("page", page);
//                session.setAttribute("totalPage", totalPage);
//                n1 = pagination(request, response, pd.getNumberOfPitchAterSearching(districtID, wardID));
//                listP = pd.getPitchAfterSearch(districtID, wardID, n1, pageSize);
                

                List<Ward> listWard = pd.getAllWard();
                List<District> listD = pd.getDistrict();
                List<Ward> listW = pd.getWard(districtID);
                List<Pitch> listHighRate = pd.getHighRatePitch();
                List<ChildrenPitch> listCP = cpd.getChildrenPitch();
                List<ChildrenPitch> listMaxPrice = cpd.getMaxPrice();
                List<ChildrenPitch> listMinPrice = cpd.getMinPrice();
                List<Pitch> listP1 = pd.getAllPitch();
                List<ChildrenPitch> listFinalMinPrice = new ArrayList<>();
                for (int i = 0; i < listMaxPrice.size(); i++) {
                    for (int j = 0; j < listMinPrice.size(); j++) {
                        if (!Objects.equals(listMaxPrice.get(i).getPrice(), listMinPrice.get(j).getPrice()) && listMaxPrice.get(i).getPitchID().equals(listMinPrice.get(j).getPitchID())) {
                            listFinalMinPrice.add(listMinPrice.get(j));
                        }
                    }
                }
                if (user != null) {
                    if (user.getRoleID().equals("US")) {
                        SimpleDateFormat smt = new SimpleDateFormat("HH:mm:ss");
                        List<Booking> listN = bd.getNotification(user.getUserID(), date, smt.format(date), true);
                        n1 = pagination(request, response, pd.getNumberOfPitchAterSearching(districtID, wardID));
                        listP = pd.getPitchAfterSearch(districtID, wardID, n1, pageSize);
                        request.setAttribute("listNo", listN);
                        request.setAttribute("countN", listN.size());
                    } else if (user.getRoleID().equals("OW")) {
                        n1 = pagination(request, response, pd.getNumberOfPitchAterSearchingOwner(districtID, wardID, user.getUserID()));
                        listP = pd.getPitchAfterSearchOwner(user.getUserID(), districtID, wardID, n1, pageSize);
                    }
                } else {
                    n1 = pagination(request, response, pd.getNumberOfPitchAterSearching(districtID, wardID));
                    listP = pd.getPitchAfterSearch(districtID, wardID, n1, pageSize);
                }
                request.setAttribute("listP", listP);
                request.setAttribute("listMinP", listFinalMinPrice);
                request.setAttribute("listMaxP", listMaxPrice);
                request.setAttribute("listCP", listCP);
                request.setAttribute("listD", listD);
                request.setAttribute("listW", listW);
                request.setAttribute("district", districtID);
                request.setAttribute("ward", wardID);
                request.setAttribute("listWard", listWard);
                request.setAttribute("listHR", listHighRate);
                request.setAttribute("listP1", listP1);
                request.setAttribute("listCP1", listCP);
                request.setAttribute("action", "index");
                System.out.println(listP);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
