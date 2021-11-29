//java servelt to create a product including the product name, price, and description of the product and image of the product
//store object in SQL database in table products with username as PK
public class CreateProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String productDescription = request.getParameter("productDescription");
        String productImage = request.getParameter("productImage");
        
        //store product in database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            Statement stmt = con.createStatement();
            String query = "INSERT INTO products (productName, productPrice, productDescription, productImage) VALUES ('" + productName + "', '" + productPrice + "', '" + productDescription + "', '" + productImage + "')";
            stmt.executeUpdate(query);
            
            out.println("<html><body>");
            out.println("<h1>Product Created</h1>");
            out.println("<a href='/test/index.html'>Home</a>");
            out.println("</body></html>");
            
            con.close();
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Product Not Created</h1>");
            out.println("<a href='/test/index.html'>Home</a>");
            out.println("</body></html>");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}