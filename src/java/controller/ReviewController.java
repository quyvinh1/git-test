/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import model.Cart;
import model.Categories;
import model.Item;
import model.Product;
import model.Review;

/**
 *
 * @author Dell Inspiron
 */
public class ReviewController extends HttpServlet {

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
            out.println("<title>Servlet ReviewController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReviewController at " + request.getContextPath() + "</h1>");
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
        DAO d = new DAO();
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        String review = request.getParameter("review");
        String u = request.getParameter("username");
        String id_raw = request.getParameter("id");
        int id,uid;
        try {
            id = Integer.parseInt(id_raw);
            uid = d.getIdByUserName(u);
            Review r = new Review(0, review, date, String.valueOf(uid), String.valueOf(id));
            d.addReview(r);
            Product product = d.getproductById(id);
            List<Product> list = d.getAllProduct();
            List<Categories> category = d.getAllCategories();
            List<Review> reviewlist = d.getAllReviewForOneProduct(id);
            Cookie[] arr = request.getCookies();
            String txt="";
        if(arr!=null){
            for(Cookie o:arr){
                if(o.getName().equals("cart")){
                    txt+=o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, list);
        List<Item> listItem = cart.getItems();
        int n;
        if(listItem!=null){
            n = listItem.size();
        } else {
            n=0;
        }
            request.setAttribute("size", n);
            request.setAttribute("category", category);
            request.setAttribute("list", list);
            request.setAttribute("review", reviewlist);
            request.setAttribute("product", product);
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (Exception e) {
        }
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
