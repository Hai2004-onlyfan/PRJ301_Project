<%-- 
    Document   : home
    Created on : Oct 29, 2024, 11:11:45 AM
    Author     : Admin
--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ABC Company</title>
        <style>
            body {
                background: url('https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/03/hinh-nen-desktop.jpg') no-repeat center center fixed;
                background-size: cover;
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                color: black;
            }
            .card {
                border-radius: 20px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
                background: rgba(255, 255, 255, 0.9);
            }
            .btn-primary {
                background-color: #2575fc;
                border: none;
            }
            .btn-primary:hover {
                background-color: #1e60d4;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card p-4">
                        <div class="card-body">
                            <h3 class="text-center mb-4">Đăng Nhập</h3>
                            <form action="login" method="post">
                                <div class="mb-3">
                                    <label for="username" class="form-label">Tài Khoản</label>
                                    <input type="text" class="form-control" id="username" name="username" placeholder="Nhập tài khoản" required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Mật Khẩu</label>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
                                </div>
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <a href="forgot-password.jsp" class="text-decoration-none" style="color: black;">Quên mật khẩu?</a>
                                    <a href="register.jsp" class="text-decoration-none" style="color: black;">Tạo tài khoản</a>
                                </div>
                                <div class="d-grid">
                                    <button type="submit" class="btn btn-primary">Đăng Nhập</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>

