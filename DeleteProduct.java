//java servlet to delete product from database
public class DeleteProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
        
        String productId = request.getParameter("productId");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
            Statement stmt = con.createStatement();
            
            String query = "delete from product where productId = '" + productId + "'";
            
            int result = stmt.executeUpdate(query);
            
            if(result == 1) {
                response.sendRedirect("admin.jsp");
            }
            else {
                response.sendRedirect("admin.jsp");
            }
            
            con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
