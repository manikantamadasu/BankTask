<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div align="center">
        <h2>Balance Information</h2>
        <%!
            // Helper functions to get the current date, month, and year in the format required for report URLs
            public String getCurrentDate() {
                java.util.Date currentDate = new java.util.Date();
                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.format(currentDate);
            }

            public int getCurrentMonth() {
                java.util.Calendar cal = java.util.Calendar.getInstance();
                return cal.get(java.util.Calendar.MONTH) + 1;
            }

            public int getCurrentYear() {
                java.util.Calendar cal = java.util.Calendar.getInstance();
                return cal.get(java.util.Calendar.YEAR);
            }
        %>

        <%
            String accountNumber = "123"; // Account number for which balance is to be displayed

            try {
                // JDBC setup
                String jdbcUrl = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
                String dbUsername = "root";
                String dbPassword = "Ram11@ya";

                // Establishing the database connection
                Connection conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

                // Creating the SQL query to retrieve balance information
                String query = "SELECT balance FROM personal_accounts WHERE accountNumber = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, accountNumber);

                // Executing the query and processing the result
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    %>
                    <p>Account Number: <%= accountNumber %></p>
                    <p>Balance: <%= balance %></p>
                    <a href="dailyreport.jsp?accountType=personal&date=<%= getCurrentDate() %>">Daily Report</a><br>
                    <a href="monthlyreport.jsp?accountType=personal&month=<%= getCurrentMonth() %>&year=<%= getCurrentYear() %>">Monthly Report</a><br>
                    <a href="yearlyreport.jsp?accountType=personal&year=<%= getCurrentYear() %>">Yearly Report</a>
                    <%
                } else {
                    %>
                    <p>Balance information not found for the given account number.</p>
                    <%
                }

                // Closing the resources
                rs.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </div>
</body>
</html>
