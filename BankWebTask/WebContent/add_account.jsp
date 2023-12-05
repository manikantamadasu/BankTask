<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Add Account</title>
    <style>
        body {
            background: url("https://www.shutterstock.com/shutterstock/photos/713811001/display_1500/stock-photo-project-manager-looking-at-ar-screen-with-gantt-chart-schedule-or-planning-showing-tasks-and-713811001.jpg");
            background-size: cover;
            background-position: center;
            height: 100vh;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .card {
            max-width: 400px;
            padding: 20px;
            border-radius: 8px;
            background-color: #ffffff;
        }

        .form-label {
            font-weight: bold;
        }

        .form-input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
        }

        .row-wise-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <h2 class="text-center">Add Account</h2>
            <form action="AddAccountServlet" method="post">
                <label class="form-label">Account Type:</label>
                <select name="accountType" class="form-input">
                    <option value="personal">Personal Account</option>
                    <option value="business">Business Account</option>
                </select>

                <label class="form-label">Account Number:</label>
                <input type="text" name="accountNumber" class="form-input" required>

                <label class="form-label">Username:</label>
                <input type="text" name="username" class="form-input" required>

                <label class="form-label">Password:</label>
                <input type="password" name="password" class="form-input" required>

                <label class="form-label">Balance:</label>
                <input type="number" name="balance" class="form-input" required>

                <label class="form-label">Address:</label>
                <input type="text" name="address" class="form-input" required>

                <label class="form-label">Phone Number:</label>
                <input type="text" name="phoneNumber" class="form-input" required>
                
                <label class="form-label">Creation Date (YYYY-MM-DD):</label>
                <input type="text" name="creationDate" class="form-input" required>

                <div class="row-wise-buttons">
                    <input type="submit" value="Add Account" class="btn btn-primary">
                    <a href="ManagerHome.jsp" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
