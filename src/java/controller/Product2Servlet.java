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
 * @author Dell Inspiron
 */
public class Product2Servlet extends HttpServlet {

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
            out.println("<title>Servlet Product2Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Product2Servlet at " + request.getContextPath() + "</h1>");
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
        List<Product> list1 = new ArrayList<>();
        String[] pp = {"Under 100$",
                        "From 100$ to 300$",
                        "From 300$ to 600$",
                        "From 600$ to 1000$",
                        "Higher than 1000$"} ;
        boolean[] pb = new boolean [pp.length+1];
        pb[0] = true;
        boolean[] chid = new boolean[listCategory.size()+1];
        String cid_raw = request.getParameter("cid");
        String[] cidd_raw = request.getParameterValues("cidd");
        String[] price = request.getParameterValues("price");
        int cid = 0;
        int[] cidd = null;
        if(cid_raw !=null){
            cid = Integer.parseInt(cid_raw);
            list1 = d.getProductByCid(cid);
            if(cid==0){
                chid[0] = true;
            }
        }
        if(cidd_raw != null) {
            cidd = new int[cidd_raw.length];
            for(int i=0;i<cidd.length;i++){
                cidd[i] = Integer.parseInt(cidd_raw[i]);
            }
            list1 = d.searchByCheck(cidd);
        }
        if(price!=null){
            double from = 0,to = 0;
            for(int i=0;i<price.length;i++){
                List<Product> temp = new ArrayList<>();
                if(price[i].equals("0")) {
                    from = 0;
                    to = 5000;
                    list1 = d.getProductByPrice(from, to);
                    pb[0] = true;
                    break;
                } else {
                    pb[0] = false;
                    if(price[i].equals("1")) {
                    from = 0;
                    to = 100;
                    temp = d.getProductByPrice(from, to);
                    list1.addAll(temp);
                    pb[1] = true;
                    
                    }
                    if(price[i].equals("2")) {
                    from = 100;
                    to = 300;
                    temp = d.getProductByPrice(from, to);
                    list1.addAll(temp);
                    pb[2] = true;
                    }
                    if(price[i].equals("3")) {
                    from = 300;
                    to = 600;
                    temp = d.getProductByPrice(from, to);
                    list1.addAll(temp);
                    pb[3] = true;
                    }
                    if(price[i].equals("4")) {
                    from = 600;
                    to = 1000;
                    temp = d.getProductByPrice(from, to);
                    list1.addAll(temp);
                    pb[4] = true;
                    }
                    if(price[i].equals("5")) {
                    from = 1000;
                    to = 5000;
                    temp = d.getProductByPrice(from, to);
                    list1.addAll(temp);
                    pb[5] = true;
                    }
                }
            }
        }
        if(price == null){
            pb[0] = true;
        }
        if(cid_raw==null){
            chid[0] = true;
        }
        if( (cidd_raw!=null) && (cidd[0]!=0)){
            chid[0]=false;
            for(int i=1;i<chid.length;i++){
                if(ischeck(listCategory.get(i-1).getId(), cidd)){
                    chid[i] = true;
                } else {
                    chid[i] = false;
                }
            }
        }
        request.setAttribute("data", list1);
        request.setAttribute("category", listCategory);
        request.setAttribute("cid", 0);
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
    private boolean ischeck(int d, int[] id) {
        if (id == null) {
            return false;
        } else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == d) {
                    return true;
                }
            }
            return false;
        }
    }
}
