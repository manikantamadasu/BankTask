import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchAccountServlet")
public class SearchAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "Ram11@ya";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the background image
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Account Details</title>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
        out.println("<style>body { background-image: url('https://pimages.toolbox.com/wp-content/uploads/2021/12/14163643/working_culture_is_the_part_of_business_success_5d8cd5099e53a.jpg'); background-size: cover; background-repeat: no-repeat; }</style>");
        out.println("</head><body>");

        String accountNumber = request.getParameter("accountNumber");

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            // Create the SQL query to search for the account by account number
            String sql = "SELECT * FROM users WHERE account_number=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);

            // Execute the query
            rs = pstmt.executeQuery();

            // Check if the account was found
            if (rs.next()) {
                // Account found, retrieve user details
                String accountType = rs.getString("account_type");
                String username = rs.getString("username");
                String password = rs.getString("password");
                double balance = rs.getDouble("balance");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");

                // Display the user details in a centered card layout
                out.println("<div class='container d-flex align-items-center justify-content-center mt-5'>");
                out.println("<div class='card' style='width: 20rem;'>");
                out.println("<div class='card-header text-center'><h2>Account Details</h2></div>");
                out.println("<div class='card-body text-center'>");
                out.println("<p><strong>Account Type:</strong> " + accountType + "</p>");
                out.println("<p><strong>Username:</strong> " + username + "</p>");
                out.println("<p><strong>Password:</strong> " + password + "</p>");
                out.println("<p><strong>Balance:</strong> " + balance + "</p>");
                out.println("<p><strong>Address:</strong> " + address + "</p>");
                out.println("<p><strong>Phone Number:</strong> " + phoneNumber + "</p>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
            } else {
                // Account not found
                out.println("<h2>Account not found.</h2>");
            }

            // Add the link to the manager dashboard page
            out.println("<div class='container mt-3 text-center'><a href='managerDashboard.jsp' class='btn btn-primary'>Go to Manager Dashboard</a></div>");
            out.println("</body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<h2>An error occurred.</h2>");
        } finally {
            // Close the resources
            try {
                if (rs != null) {
                    rs.close();
                }
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
