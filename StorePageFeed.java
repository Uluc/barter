//create java servlet to display the main page feed that shows the top 10 newest products

public class StorePageFeed extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Main Page Feed</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Main Page Feed</h1>");
        out.println("<h2>Top 10 Newest Products</h2>");
        
        //create a new database connection
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webstore", "root", "root");
            
            //create a statement to execute the query
            Statement stmt = conn.createStatement();
            
            String userid = request.getParameter("userid");
            //create a query to get the top 10 newest products
            String query = "SELECT * FROM products ORDER BY product_id WHERE userid =" + userid;
            
            //execute the query
            ResultSet rs = stmt.executeQuery(query);
            
            //create a table to display the products
            out.println("<table border=1>");
            out.println("<tr><th>Product ID</th><th>Product Name</th><th>Product Description</th><th>Product Price</th><th>Product Quantity</th></tr>");
            
            //loop through the results and display them in a table
            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("product_id") + "</td>");
                out.println("<td>" + rs.getString("product_name") + "</td>");
                out.println("<td>" + rs.getString("product_description") + "</td>");
                out.println("<td>" + rs.getDouble("product_price") + "</td>");
                out.println("<td>" + rs.getInt("product_quantity") + "</td>");
                out.println("</tr>");
            }
            
            //close the table
            out.println("</table>");

            //close the connection
            conn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage());
        }
    }
}
