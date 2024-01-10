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
import java.util.List;
import model.Categories;
import model.Product;

/**
 *
 * @author FPTSHOP
 */
public class AddServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String quatity_raw = request.getParameter("quantity");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String category = request.getParameter("category");
        int quantity;
        double price;
        DAO d = new DAO();
        try {
            quantity = Integer.parseInt(quatity_raw);
            price = Double.parseDouble(price_raw);
            Product p = new Product(0, description, image, name, price, quantity, category);
            if (p.addProduct()) {
                List<Product> list1 = d.getAllProduct();
                List<Categories> category1 = d.getAllCategories();
                int page, numperpage = 6;
                int size = list1.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6) + 1));
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<Product> list = d.getListByPage(list1, start, end);

                request.setAttribute("category", category1);
                request.setAttribute("data", list);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.getRequestDispatcher("Manager.jsp").forward(request, response);
            } else {
                List<Product> list1 = d.getAllProduct();
                List<Categories> category1 = d.getAllCategories();
                int page, numperpage = 6;
                int size = list1.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6) + 1));
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<Product> list = d.getListByPage(list1, start, end);

                request.setAttribute("category", category1);
                request.setAttribute("data", list);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.getRequestDispatcher("Manager.jsp").forward(request, response);
            }

        } catch (Exception e) {
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
