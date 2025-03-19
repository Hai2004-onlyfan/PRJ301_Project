<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Sách Mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center"> Thêm Sách Mới</h2>
        <form action="saveBook" method="POST">
            <div class="mb-3">
                <label for="bname" class="form-label">Tên Sách</label>
                <input type="text" class="form-control" id="bname" name="bname" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Tác Giả</label>
                <div>
                    <c:forEach items="${authors}" var="author">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="authorIds" value="${author.id}" id="author_${author.id}">
                            <label class="form-check-label" for="author_${author.id}">${author.name}</label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <button type="submit" class="btn btn-primary w-100">Lưu Sách</button>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
