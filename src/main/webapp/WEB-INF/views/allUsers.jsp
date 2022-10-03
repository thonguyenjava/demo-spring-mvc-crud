<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>All Users</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <style type="text/css">
        tr:first-child {
            font-weignt: bold;
            background-color: #c6c9c4
        }
    </style>
</head>
<body>
<div class="container">
    <h2>All Registered Users</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">${success}</div>
    </c:if>

    <hr />
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <td>First Name</td>
            <td>Last Name</td>
            <td>DOB</td>
            <td>Login</td>
            <td>Mobile No</td>
            <td>Gender</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.dob}</td>
            <td>${user.loginId}</td>
            <td>${user.mobileNo}</td>
            <td>${user.gender == 0 ? "MALE" : "FEMALE" }</td>
            <td><a href="<c:url value='/${user.id}' />">Edit</a></td>
            <td><a href="<c:url value='/delete/${user.id}' />">Delete</a></td>
        </tr>
        </c:forEach>
    </table>
    <hr />
    <div class="form-group">
        <a class="btn btn-secondary" href="/">Add New User</a>
    </div>

</div>
</body>
</html>