<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Include necessary stylesheets and scripts here -->
    <title>User Login</title>
</head>
<style>
    body {
        background: url("https://www.silive.com/resizer/aOqfcvjnBZ8t09i6wcV9iWI948k=/arc-anglerfish-arc2-prod-advancelocal/public/26ADCTEQQBAS5O3HKP272ANTRU.jpg");
        background-size: cover;
        background-position: center;
        height: 100vh;
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .card {
        width: 300px;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 8px;
    }

    .card-body {
        text-align: center;
    }
</style>
<body>
    <div class="container-fluid h-100 d-flex align-items-center justify-content-center">
        <div class="card">
            <div class="card-body">
                <h2>User Login</h2>
        <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean loggedIn = false;
            String accountType = null;
            String accountNumber = null;
            double balance = 0.0;
            String address = null;
            String phoneNumber = null;

            try {
                // JDBC setup
                Class.forName("com.mysql.cj.jdbc.Driver");
                String jdbcUrl = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
                String dbUsername = "root";
                String dbPassword = "Ram11@ya";

                // Establishing the database connection
                Connection conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

                // Creating the SQL query to retrieve user information
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);

                // Executing the query and processing the result
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    loggedIn = true;
                    accountType = rs.getString("account_type");
                    accountNumber = rs.getString("account_number");
                    balance = rs.getDouble("balance");
                    address = rs.getString("address");
                    phoneNumber = rs.getString("phone_number");
                }

                // Closing the resources
                rs.close();
                statement.close();
                conn.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        %>

        <%-- Display login status and user details --%>
        <% if (loggedIn) { %>
            <div class="card">
                <div class="card-body text-center bg-primary">
                    
                    <p>Account Type: <%= accountType %></p>
                </div>
            </div>

            <div class="card">
                <div class="card-body text-center bg-primary">
                    <p>Account Number: <%= accountNumber %></p>
                </div>
            </div>

            <div class="card">
                <div class="card-body text-center bg-primary">
                    <p>Balance: <%= balance %></p>
                </div>
            </div>

            <div class="card">
                <div class="card-body text-center bg-primary">
                    <p>Address: <%= address %></p>
                </div>
            </div>

            <div class="card">
                <div class="card-body text-center bg-primary">
                    <p>Phone Number: <%= phoneNumber %></p>
                </div>
            </div>

            <div class="card">
                <div class="card-body text-center bg-primary">
                    <a href="home.jsp">Go to Home Page</a>
                </div>
            </div>
        <% } else { %>
            <div class="card">
                <div class="card-body text-center bg-primary">
                    <h2>Login failed. Please try again.</h2>
                </div>
            </div>

            <div class="card">
                <div class="card-body text-center bg-primary">
                    <a href="home.jsp">Go back to Home page</a>
                </div>
            </div>
        <% } %>
        </div>
        </div>
       
    </div>
</body>
</html>