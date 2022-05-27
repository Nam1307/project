/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ChildrenPitchDAO;
import daos.PitchDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ChildrenPitch;
import models.District;
import models.Pitch;
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
            HttpSession session = request.getSession();

            int pageSize = 8;//Kich thuoc trang                        

            //Xac dinh so thu tu cua trang hien tai
            Integer page = (Integer) session.getAttribute("page");
            if (page == null) {
                page = 1;
            }

            //Xac dinh tong so trang
            Integer totalPage = (Integer) session.getAttribute("totalPage");
            int count = pd.getNumberOfPitch();//Dem so luong records
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
            int n1 = (page - 1) * pageSize;//Vi tri mau tin dau trang
            List<Pitch> listP = pd.getPitch(n1, pageSize);

            //Luu thong tin vao session va request
            session.setAttribute("page", page);
            session.setAttribute("totalPage", totalPage);
            List<District> listD = pd.getDistrict();
            List<Ward> listWard = pd.getAllWard();
            List<Pitch> listHighRate = pd.getHighRatePitch();
            List<ChildrenPitch> listCP = cpd.getChildrenPitch();
            request.setAttribute("listCP", listCP);
            request.setAttribute("listHR", listHighRate);
            request.setAttribute("listWard", listWard);
            request.setAttribute("listP", listP);
            request.setAttribute("listD", listD);

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
            ChildrenPitchDAO cpd = new ChildrenPitchDAO();
            PitchDAO pd = new PitchDAO();
            String districtID = request.getParameter("districtID");
            String wardID = request.getParameter("ward");

            if (districtID == null || wardID == null) {
                index(request, response);
                request.setAttribute("action", "index");
            } else {
                int pageSize = 8;//Kich thuoc trang                        

                //Xac dinh so thu tu cua trang hien tai
                Integer page = (Integer) session.getAttribute("page");
                if (page == null) {
                    page = 1;
                }

                //Xac dinh tong so trang
                Integer totalPage = (Integer) session.getAttribute("totalPage");
                int count = pd.getNumberOfPitchAterSearching(districtID, wardID);//Dem so luong records
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
                        if (page <= 0) {
                            page = 1;
                        } else if (page > totalPage) {
                            page = totalPage;
                        }
                        break;
                }

                //Lay trang du lieu duoc yeu cau
                int n1 = (page - 1) * pageSize;//Vi tri mau tin dau trang
                List<Pitch> listP = pd.getPitchAfterSearch(districtID, wardID, n1, pageSize);

                //Luu thong tin vao session va request
                session.setAttribute("page", page);
                session.setAttribute("totalPage", totalPage);
                request.setAttribute("listP", listP);
                System.out.println(districtID);
                System.out.println(wardID);
                List<Ward> listWard = pd.getAllWard();
                List<District> listD = pd.getDistrict();
                List<Ward> listW = pd.getWard(districtID);
                List<Pitch> listHighRate = pd.getHighRatePitch();
                List<ChildrenPitch> listCP = cpd.getChildrenPitch();
                request.setAttribute("listCP", listCP);
                request.setAttribute("listD", listD);
                request.setAttribute("listW", listW);
                request.setAttribute("district", districtID);
                request.setAttribute("ward", wardID);
                request.setAttribute("listWard", listWard);
                request.setAttribute("listHR", listHighRate);
                request.setAttribute("action", "index");
                System.out.println(listP);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
