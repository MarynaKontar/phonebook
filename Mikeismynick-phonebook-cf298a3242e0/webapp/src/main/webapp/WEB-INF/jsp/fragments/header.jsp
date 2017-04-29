<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>PhoneBook</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link rel="icon" href="../../../resources/custom/icon/redBook.ico">

    <!-- css -->
    <spring:url value="/resources/css/bootstrap.min.css"
                var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
</head>

<spring:url value="/" var="urlHome"/>
<spring:url value="/edit/employee/add" var="urlAddEmployee"/>
<spring:url value="/edit/department/add" var="urlAddDepartment"/>
<spring:url value="/showDepartments" var="urlShowDepartments"/>
<spring:url value="/login" var="urlLogin"/>
<spring:url value="/doLogout" var="urlLogout"/>

<nav class="navbar navbar-inverse ">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlHome}">PhoneBook</a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-left">
                <li class="active">
                    <div class="input-group">
                        <form class="navbar-form navbar-left" role="search">
                            <div class="input-group navbar-searchbox">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-default">Go</button>
                                </div>
                                <input id="search" type="text" class="form-control" placeholder="Search for...">
                            </div>
                        </form>
                    </div>
                </li>
            </ul>
            <div class="navbar-form navbar-right">
                <c:if test="${not sessionScope.containsKey('login')}">
                    <button class="btn btn-success" onclick="location.href='${urlLogin}'">Login</button>
                </c:if>
                <c:if test="${sessionScope.containsKey('login')}">
                    <a href="#"><%= session.getAttribute("login")%>
                    </a>
                    <button class="btn btn-danger" onclick="location.href='${urlLogout}'">Logout</button>
                </c:if>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="${urlAddEmployee}">Add Employee</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="${urlAddDepartment}">Add Department</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="${urlShowDepartments}">Departments</a></li>
            </ul>
        </div>
    </div>
</nav>