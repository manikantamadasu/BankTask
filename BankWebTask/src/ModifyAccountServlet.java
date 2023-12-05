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

@WebServlet("/ModifyAccountServlet")
public class ModifyAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "Ram11@ya";

    // Method to create the "users" table if it doesn't exist
    private void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            // SQL query to create the "users" table
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "account_type VARCHAR(10) NOT NULL," +
                         "account_number VARCHAR(20) NOT NULL," +
                         "username VARCHAR(50) NOT NULL," +
                         "password VARCHAR(100) NOT NULL," +
                         "balance DOUBLE NOT NULL," +
                         "address VARCHAR(200) NOT NULL," +
                         "phone_number VARCHAR(20) NOT NULL," +
                         "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                         ")";

            // Create a PreparedStatement for the query
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Execute the query
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">");
        out.println("<title>Result</title>");
        out.println("<style>body { background-image: url('https://d1eipm3vz40hy0.cloudfront.net/images/AMER/customersuccess2.5.jpg'); background-size: cover; background-repeat: no-repeat; }</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container mt-5\">");
        out.println("<div class=\"row justify-content-center\">"); // Added "row justify-content-center" class to center the card
        out.println("<div class=\"col-6\">"); // Added "col-6" class to limit the card's width
        out.println("<div class=\"card p-3\">");
        out.println("<h2 class=\"mb-3\">Account Details</h2>");
        out.println("<h2 class=\"mb-3\">Account Details</h2>");

        // Create the table if it doesn't exist
        createTableIfNotExists();

        String accountNumber = request.getParameter("accountNumber");

        // Get the attribute to be modified and the new value from the form
        String modifiedAttribute = request.getParameter("modifiedAttribute");
        String newValue = request.getParameter("newValue");

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            // Create the SQL query to update user details
            String sql = "UPDATE users SET " + modifiedAttribute + "=? WHERE account_number=?";
            pstmt = conn.prepareStatement(sql);

            // Set parameters
            pstmt.setString(1, newValue);
            pstmt.setString(2, accountNumber);

            // Execute the query
            // Simulate a delay of 2 seconds for testing purposes (remove this in a real application)
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int rowsUpdated = pstmt.executeUpdate();

            // Check if the data was updated successfully and display the message accordingly
            if (rowsUpdated > 0) {
                out.println("<p>Account details updated successfully!</p>");
                // Display the modified details
                out.println("<p>Modified Attribute: " + modifiedAttribute + "</p>");
                out.println("<p>New Value: " + newValue + "</p>");

                // Retrieve the updated user details from the database
                String userDetailsSql = "SELECT * FROM users WHERE account_number=?";
                pstmt = conn.prepareStatement(userDetailsSql);
                pstmt.setString(1, accountNumber);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Display all user details
                    out.println("<h3>User Details After Modification:</h3>");
                    out.println("<p>Account Type: " + rs.getString("account_type") + "</p>");
                    out.println("<p>Account Number: " + rs.getString("account_number") + "</p>");
                    out.println("<p>Username: " + rs.getString("username") + "</p>");
                    out.println("<p>Password: " + rs.getString("password") + "</p>");
                    out.println("<p>Balance: " + rs.getDouble("balance") + "</p>");
                    out.println("<p>Address: " + rs.getString("address") + "</p>");
                    out.println("<p>Phone Number: " + rs.getString("phone_number") + "</p>");
                }

                rs.close();
            } else {
                out.println("<p>Account not found !!! .</p>");
            }

            // Add the link to the manager dashboard page
            out.println("<br><a class=\"btn btn-primary\" href='managerDashboard.jsp'>Go to Manager Dashboard</a>");
            out.println("</div></div>");
            out.println("</body></html>");

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