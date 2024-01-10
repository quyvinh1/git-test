package controller;

import dal.DAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class NewPassword
 */
public class NewPassword extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        RequestDispatcher dispatcher = null;
        DAO d = new DAO();
        int n=0;
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            n = d.resetPassword(newPassword, (String) session.getAttribute("email"));
            if(n>0){
                request.setAttribute("status", "Reset Success!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
            request.setAttribute("status", "Reset Failed");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
            
        } 
    }

}
