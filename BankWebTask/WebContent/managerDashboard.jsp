<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Manager Home</title>
    <style>
        body {
            background: url("https://www.itarian.com/images/device-manager.jpg");
            background-size: cover;
            background-position: center;
            height: 100vh;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }

        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 10px;
            max-width: 800px;
            margin-top: 20px;
        }

        .card {
            flex-basis: calc(33.33% - 20px);
            padding: 20px;
            border-radius: 8px;
            background-color: #ffffff;
        }
    </style>
</head>
<body>
    <div class="container">
        
            <h1 class="text-center mb-4">Welcome Manager</h1>
        
        
            <h2 class="text-center">Choose an action:</h2>
        </div>
        <div class="card-container">
            <a href="add_account.jsp" class="btn btn-primary card">Add Account</a>
            <a href="searchaccount.jsp" class="btn btn-primary card">Search Account</a>
            <a href="modifyaccount.jsp" class="btn btn-primary card">Modify Account</a>
            <a href="deleteuser.jsp" class="btn btn-primary card">Delete User</a>
            <a href="DailyReportServlet?date=2023-07-30" class="btn btn-primary card">Daily Report</a>
            <a href="MonthlyReportServlet?date=2023-07" class="btn btn-primary card">Monthly Report</a>
            <a href="YearlyReportServlet?date=2023" class="btn btn-primary card">Yearly Report</a>
        </div>
   
</body>
</html>
