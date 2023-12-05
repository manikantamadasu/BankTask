import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "Ram11@ya";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Print that the database is connected
        response.setContentType("text/html");
        response.getWriter().println("<html><head><title>Delete User</title></head><body>");
        response.getWriter().println("<style>body { background-image: url('https://blog.hubspot.com/hs-fs/hubfs/297_Delete%20Your%20Facebook.jpg?width=595&height=400&name=297_Delete%20Your%20Facebook.jpg'); background-size: cover; background-repeat: no-repeat; }</style>");
        response.getWriter().println("<div align='center'>");
        response.getWriter().println("<div class='card' style='width: 300px; padding: 20px; background-color: white; border-radius: 10px;'>");
        response.getWriter().println("<h2 class='text-center'>Delete User</h2>");

        String accountNumber = request.getParameter("accountNumber");

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            // Create the SQL query to delete the user by account number
            String sql = "DELETE FROM users WHERE account_number=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);

            // Execute the query
            // Simulate a delay of 2 seconds for testing purposes (remove this in a real application)
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int rowsDeleted = pstmt.executeUpdate();

            // Redirect to the appropriate page based on the result
            if (rowsDeleted > 0) {
                response.getWriter().println("<p>User with account number " + accountNumber + " deleted successfully.</p>");
            } else {
                response.getWriter().println("<p>Account not found.</p>");
            }

            // Add the link to the manager dashboard page
            response.getWriter().println("<div class='text-center mt-3'>");
            response.getWriter().println("<a href='managerDashboard.jsp' class='btn btn-secondary'>Go to Manager Dashboard</a>");
            response.getWriter().println("</div>");
            response.getWriter().println("</div></div></body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("managerDashboard.jsp");
        } finally {
            // Close the resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
