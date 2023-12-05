

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddAccountServlet")
public class AddAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "Ram11@ya";

    // Method to create the "users" table if it doesn't exist
    private void createTableIfNotExists() {
        // Your code to create the "users" table
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        createTableIfNotExists();
        String accountType = request.getParameter("accountType");
        String accountNumber = request.getParameter("accountNumber");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        double balance = Double.parseDouble(request.getParameter("balance"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String creationDate = request.getParameter("creationDate"); // Get the date of creation from the form

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            // Create the SQL query
            String sql = "INSERT INTO users (account_type, account_number, username, password, balance, address, phone_number, account_creation_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            // Set parameters
            pstmt.setString(1, accountType);
            pstmt.setString(2, accountNumber);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.setDouble(5, balance);
            pstmt.setString(6, address);
            pstmt.setString(7, phoneNumber);
            pstmt.setString(8, creationDate);

            // Execute the query
            int rowsInserted = pstmt.executeUpdate();

            // Redirect to the appropriate page based on the result
            out.println("<html><head><title>Result</title>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            out.println("<style>body { background-image: url('https://previews.123rf.com/images/iakovenko/iakovenko1506/iakovenko150600282/41848669-close-up-of-male-hands-giving-money-to-another-person-isolated-on-white-background.jpg'); background-size: cover; background-repeat: no-repeat; }</style>");
            out.println("</head><body>");
            out.println("<div class='container mt-5'>");
            out.println("<div class='card text-center'>");
            out.println("<div class='card-body'>");
            
            if (rowsInserted > 0) {
                out.println("<h2 class='card-title'>Account added successfully</h2>");
            } else {
                out.println("<h2 class='card-title'>Error adding the account. Please try again.</h2>");
            }
            
            out.println("<a href='managerDashboard.jsp' class='btn btn-primary mt-3'>Go to Manager Dashboard</a>");
            out.println("</div></div></div></body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<html><head><title>Result</title>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            out.println("<style>body{ background-image: url('https://previews.123rf.com/images/iakovenko/iakovenko1506/iakovenko150600282/41848669-close-up-of-male-hands-giving-money-to-another-person-isolated-on-white-background.jpg'); background-size: cover; background-repeat: no-repeat; }</style>");
            out.println("</head><body>");
            out.println("<div class='container mt-5'>");
            out.println("<div class='card text-center'>");
            out.println("<div class='card-body'>");
            out.println("<h2 class='card-title'>An error occurred while processing the request. Please try again later.</h2>");
            out.println("<a href='managerDashboard.jsp' class='btn btn-primary mt-3'>Go to Manager Dashboard</a>");
            out.println("</div></div></div></body></html>");
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
