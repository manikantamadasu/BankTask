import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/YearlyReportServlet")
public class YearlyReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "Ram11@ya";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get the current year
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            // Create the SQL query to fetch yearly report data
            String sql = "SELECT * FROM users WHERE YEAR(account_creation_date) = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentYear);

            // Execute the query
            rs = pstmt.executeQuery();

            // Process the result set and generate the report
            List<String> reportData = new ArrayList<>();
            while (rs.next()) {
                String accountNumber = rs.getString("account_number");
                String username = rs.getString("username");
                // Add more fields as needed for your report

                // Format the data as needed and add to the reportData list
                String reportEntry = "<tr><td>" + accountNumber + "</td><td>" + username + "</td></tr>";
                reportData.add(reportEntry);
            }

            // Display the report
            out.println("<html><head><title>Yearly Report</title>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            out.println("<style>");
            out.println("body {");
            out.println("background-image: url('https://img.freepik.com/free-vector/abstract-geometric-wireframe-background_52683-59421.jpg');");
            out.println("background-repeat: no-repeat;");
            out.println("background-size: cover;");
            out.println("}");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='container mt-5'>");
            out.println("<h2 class='mb-4'>Yearly Report for Year " + currentYear + "</h2>");
            if (reportData.isEmpty()) {
                out.println("<p>No accounts were created in this year.</p>");
            } else {
                out.println("<table class='table table-bordered'><thead><tr><th>Account Number</th><th>Username</th></tr></thead><tbody>");
                for (String entry : reportData) {
                    out.println(entry);
                }
                out.println("</tbody></table>");
            }
            out.println("<a href='managerDashboard.jsp' class='btn btn-primary'>Go Back to Dashboard</a>");
            out.println("</div></body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h2>An error occurred while generating the report. Please try again later.</h2>");
            out.println("<p>SQL Error: " + e.getMessage() + "</p>");
            out.println("<a href='managerDashboard.jsp' class='btn btn-primary'>Go Back to Dashboard</a>");
            out.println("</body></html>");
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
