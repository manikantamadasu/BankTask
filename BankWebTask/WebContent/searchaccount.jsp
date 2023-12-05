<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <title>Search Account</title>
    <style>
        body {
            background-image: url('https://images.idgesg.net/images/article/2022/12/shutterstock_1453501478-100935325-large.jpg?auto=webp&quality=85,70');
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header text-center">
                <h2>Search Account</h2>
            </div>
            <div class="card-body">
                <form action="SearchAccountServlet" method="post" class="row">
                    <div class="col-7">
                        <div class="form-group">
                            <label for="accountNumber">Account Number:</label>
                            <input type="text" name="accountNumber" id="accountNumber" class="form-control" required>
                        </div>
                    </div>
                    <div class="col-3">
                        <button type="submit" class="btn btn-primary w-100">Search Account</button>
                    </div>
                </form>
            </div>
        </div>
       
    </div>
</body>
</html>
