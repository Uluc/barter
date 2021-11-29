//update an existing product in the database

public class UpdateProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productPrice = request.getParameter("productPrice");
        String productQuantity = request.getParameter("productQuantity");
        String productCategory = request.getParameter("productCategory");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "");
            
            String query = "update products set productName = ?, productDescription = ?, productPrice = ?, productQuantity = ?, productCategory = ? where productId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, productName);
            ps.setString(2, productDescription);
            ps.setString(3, productPrice);
            ps.setString(4, productQuantity);
            ps.setString(5, productCategory);
            ps.setString(6, productId);
            
            int i = ps.executeUpdate();
            
            if(i > 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Product updated successfully');");
                out.println("location='admin.jsp';");
                out.println("</script>");
            }
            else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Product not updated');");
                out.println("location='admin.jsp';");
                out.println("</script>");
            }
        }
        catch(Exception e) {
            out.println(e);
        }
    }

    public UpdateProduct() {
        super();
    }

}

