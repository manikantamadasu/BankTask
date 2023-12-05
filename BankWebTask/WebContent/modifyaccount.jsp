<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Modify Account</title>
</head>
<body style="background-image: url('https://static.vecteezy.com/system/resources/previews/008/089/095/original/adjustments-modifying-investment-portfolio-from-stock-market-crash-making-bullish-run-concept-businessman-climbing-up-on-a-ladder-to-adjust-an-uptrend-graph-chart-on-a-wall-vector.jpg'); background-size: cover; background-repeat: no-repeat;">
    <div class="container d-flex align-items-center justify-content-center" style="height: 100vh;">
        <div class="card" style="width: 20rem;">
            <div class="card-header text-center">
                <h2>Modify Account</h2>
            </div>
            <div class="card-body">
                <form action="ModifyAccountServlet" method="post">
                    <div class="mb-3">
                        <label for="accountNumber" class="form-label">Account Number:</label>
                        <input type="text" name="accountNumber" id="accountNumber" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="modifiedAttribute" class="form-label">Choose Attribute to Modify:</label>
                        <select name="modifiedAttribute" id="modifiedAttribute" class="form-select">
                            <option value="account_type">Account Type</option>
                            <option value="username">Username</option>
                            <option value="password">Password</option>
                            <option value="balance">Balance</option>
                            <option value="address">Address</option>
                            <option value="phone_number">Phone Number</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="newValue" class="form-label">New Value:</label>
                        <input type="text" name="newValue" id="newValue" class="form-control" required>
                    </div>
                    <div class="text-center">
                        <input type="submit" value="Modify Account" class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
