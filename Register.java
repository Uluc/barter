//java servlet to register user with email, first name, last name, and password with confirmation

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Register() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String error = "";
        if (password.equals(confirmPassword)) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "root");
                Statement stmt = con.createStatement();
                String sql = "INSERT INTO user_db.user_info (email, first_name, last_name, password) VALUES ('" + email + "', '" + firstName + "', '" + lastName + "', '" + password + "')";
                stmt.executeUpdate(sql);
                con.close();
                response.sendRedirect("login.html");
            } catch (Exception e) {
                error = "Error: " + e.getMessage();
            }
        } else {
            error = "Passwords do not match";
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
