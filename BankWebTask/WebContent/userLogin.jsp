<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background: url("https://cloudfront-us-east-2.images.arcpublishing.com/reuters/H65MG5AM6RKVHAZ2ND6D2G2EHI.jpg");
            background-size: cover;
            background-position: center;
            height: 100vh;
            margin: 0;
        }
        .space-between-labels {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container-fluid h-100 d-flex align-items-center justify-content-center">
        <div class="card">
            <div class="card-body text-center">
                <h2 class="card-title">User Login</h2>
                <%-- Simulate successful database connection --%>
                <% 
                    try {
                        // Simulate database connection
                        // You should replace this with your actual database connection code
                          Class.forName("com.mysql.cj.jdbc.Driver");
                        String dbUrl = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
                        String dbUser = "root";
                        String dbPassword = "Ram11@ya";
                        
                        java.sql.Connection connection = java.sql.DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                        if (connection != null) {
                            out.println("<p style=\"color: green;\">Enter Username and Password</p>");
                            connection.close();
                        } else {
                            out.println("<p style=\"color: red;\">Database connection failed!</p>");
                        }
                    } catch (Exception e) {
                        out.println("<p style=\"color: red;\">Error while connecting to the database: " + e.getMessage() + "</p>");
                    }
                %>
                <form action="userloginprocess.jsp" method="post">
                    <label class="space-between-labels">Username:</label>
                    <input type="text" name="username" required><br>
                    <label class="space-between-labels">Password:</label>
                    <input type="password" name="password" required><br>
                    <label>Select account type:</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="accountType" value="personal" checked>
                        <label class="form-check-label">Personal Account</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="accountType" value="business">
                        <label class="form-check-label">Business Account</label>
                    </div><br>
                    <input type="submit" value="Login" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>
</body>
</html>
