<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('https://www.itsolutions-inc.com/assets/uploads/news/delete_button_large.jpg');
            background-size: cover;
            background-repeat: no-repeat;
        }
        .container {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .card {
            width: 300px;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
        }
        .form-group {
            margin-bottom: 20px;
        }
    </style>
    <title>Delete User</title>
</head>
<body>
    <div class="container">
        <div class="card">
            <h2 class="text-center">Delete User</h2>
            <form action="DeleteUserServlet" method="post">
                <div class="form-group">
                    <label for="accountNumber" class="form-label">Account Number:</label>
                    <input type="text" id="accountNumber" name="accountNumber" class="form-control" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-danger">Delete User</button>
                </div>
            </form>
            <div class="text-center mt-3">
                <a href="managerDashboard.jsp" class="btn btn-secondary">Go to Manager Dashboard</a>
            </div>
        </div>
    </div>
</body>
</html>
