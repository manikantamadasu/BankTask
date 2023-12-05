<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Manager Login Process</title>
</head>
<body>
    <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
            String dbUsername = "root";
            String dbPassword = "Ram11@ya";
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String query = "SELECT * FROM managers WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Manager login successful
                session.setAttribute("managerLoggedIn", true);
                response.sendRedirect("managerDashboard.jsp");
            } else {
                // Manager login failed
                out.println("<h2>Invalid username or password.</h2>");
                out.println("<a href=\"managerLogin.jsp\">Try Again</a>");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</body>
</html>
