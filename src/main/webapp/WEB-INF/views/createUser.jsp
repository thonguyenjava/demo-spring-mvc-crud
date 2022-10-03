<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add New User Form</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <style>
        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>
<div class="container">
    <h2>User Form</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">${success}</div>
    </c:if>

    <form:form action="/" method="POST" cssClass="form-horizontal" modelAttribute="user" >
        <form:input type="hidden" path="id" id="id" />
        <div class="form-group">
            <label for="firstName">First Name</label>
            <form:input path="firstName" id="firstName" cssClass="form-control" />
        </div>

        <div class="form-group">
            <label for="lastName">Last Name</label>
            <form:input path="lastName" id="lastName" cssClass="form-control" />
        </div>

        <div class="form-group">
            <label for="dob">DOB</label>
                <form:input path="dob" id="dob" cssClass="form-control" type="date"/>
        </div>

        <div class="form-group">
            <label for="loginId">Login</label>
            <form:input path="loginId" id="loginId" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label for="mobileNo">Mobile No</label>
            <form:input path="mobileNo" id="mobileNo" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label for="gender">Gender</label>
            <form:select path="gender" id="gender" cssClass="form-control">
                <form:option value="0">MALE</form:option>
                <form:option value="1">FEMALE</form:option>
            </form:select>
        </div>
        <hr />
        <c:choose>
            <c:when test="${edit}">
                <button type="submit" class="btn btn-primary">Update</button>
                <a class="btn btn-primary" href="/">Create New</a>
            </c:when>
            <c:otherwise>
                <button type="submit" class="btn btn-primary">Save</button>
            </c:otherwise>
        </c:choose>

        <a class="btn btn-info" href="/listUsers">List of All Users</a>

    </form:form>
</div>
</body>
</html>