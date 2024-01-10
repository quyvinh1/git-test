/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Categories;
import model.Product;

/**
 *
 * @author FPTSHOP
 */
public class ProductServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        DAO d = new DAO();

        List<Categories> listCategory = d.getAllCategories();
        String key = request.getParameter("key");
        String cid_raw = request.getParameter("id");
        String[] pp = {"Under 100$",
                        "From 100$ to 300$",
                        "From 300$ to 600$",
                        "From 600$ to 1000$",
                        "Higher than 1000$"} ;
        boolean[] pb = new boolean [pp.length+1];
        pb[0] = true;
//        String[] id_raw = request.getParameterValues("id");
//        int[] id = null;
//        if (id_raw != null) {
//            id = new int[id_raw.length];
//            for (int i = 0; i < id.length; i++) {
//                id[i] = Integer.parseInt(id_raw[i]);
//            }
//        }
        int cid,numperpage = 9;
        if (cid_raw == null) {
            cid = 0;
        } else {
            cid = Integer.parseInt(cid_raw);
        }
        
        List<Product> list1 = d.getProductByCid(cid);
        List<Product> list2 = new ArrayList<>();
//        List<Product> list3 = new ArrayList<>();
        int page;
        int size = list1.size();
        
        if (key != null) {
            list2 = d.SearchProduct(key);
            size = list2.size();
        }
//        if (id_raw != null) {
//            list3 = d.checkProduct(id);
//            size = list3.size();
//        }
//        boolean[] cid1 = new boolean[listCategory.size()];
//        for (int i = 0; i < cid1.length; i++) {
//            if (ischeck(listCategory.get(i).getId(), id)) {
//                cid1[i] = true;
//            } else {
//                cid1[i] = false;
//            }
//        }
        int num = (size % numperpage == 0 ? (size / numperpage) : ((size / numperpage) + 1));
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Product> list = new ArrayList<>();
        list = d.getListByPage(list1, start, end);
        if (key != null) {
            list = d.getListByPage(list2, start, end);
        }
//        if(id_raw!=null){
//            list = d.getListByPage(list3, start, end);
//        }
        boolean[] chid = new boolean[list.size()+1];
        chid[0] = true;
        request.setAttribute("data", list);
        request.setAttribute("category", listCategory);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("id", cid);
        request.setAttribute("cid", 0);
        request.setAttribute("key", key);
        request.setAttribute("pp", pp);
        request.setAttribute("pb", pb);
        request.setAttribute("chid", chid);
//        request.setAttribute("cid", cid1);
        request.getRequestDispatcher("store.jsp").forward(request, response);
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
        String sort = request.getParameter("sort");
        DAO d = new DAO();

        List<Categories> listCategory = d.getAllCategories();
        String key = request.getParameter("key");
        String cid_raw = request.getParameter("id");
        String[] pp = {"Under 100$",
                        "From 100$ to 300$",
                        "From 300$ to 600$",
                        "From 600$ to 1000$",
                        "Higher than 1000$"} ;
        boolean[] pb = new boolean [pp.length+1];
        pb[0] = true;
//        String[] id_raw = request.getParameterValues("id");
//        int[] id = null;
//        if (id_raw != null) {
//            id = new int[id_raw.length];
//            for (int i = 0; i < id.length; i++) {
//                id[i] = Integer.parseInt(id_raw[i]);
//            }
//        }
        int cid,numperpage = 9;
        if (cid_raw == null) {
            cid = 0;
        } else {
            cid = Integer.parseInt(cid_raw);
        }
        
        List<Product> list1 = d.SortProductByPrice(sort);
        List<Product> list2 = new ArrayList<>();
//        List<Product> list3 = new ArrayList<>();
        int page;
        int size = list1.size();
        
        if (key != null) {
            list2 = d.SearchProduct(key);
            size = list2.size();
        }
//        if (id_raw != null) {
//            list3 = d.checkProduct(id);
//            size = list3.size();
//        }
//        boolean[] cid1 = new boolean[listCategory.size()];
//        for (int i = 0; i < cid1.length; i++) {
//            if (ischeck(listCategory.get(i).getId(), id)) {
//                cid1[i] = true;
//            } else {
//                cid1[i] = false;
//            }
//        }
        int num = (size % numperpage == 0 ? (size / numperpage) : ((size / numperpage) + 1));
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Product> list = new ArrayList<>();
        list = d.getListByPage(list1, start, end);
        if (key != null) {
            list = d.getListByPage(list2, start, end);
        }
//        if(id_raw!=null){
//            list = d.getListByPage(list3, start, end);
//        }
        boolean[] chid = new boolean[list.size()+1];
        chid[0] = true;
        request.setAttribute("data", list);
        request.setAttribute("category", listCategory);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("id", cid);
        request.setAttribute("cid", 0);
        request.setAttribute("key", key);
        request.setAttribute("pp", pp);
        request.setAttribute("pb", pb);
        request.setAttribute("chid", chid);
        request.setAttribute("sort", sort);
//        request.setAttribute("cid", cid1);
        request.getRequestDispatcher("store.jsp").forward(request, response);
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
