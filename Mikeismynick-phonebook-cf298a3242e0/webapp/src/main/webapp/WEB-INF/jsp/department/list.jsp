<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">

<jsp:include page="../fragments/header.jsp"/>

<body>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <div class="panel panel-info">
        <div class="panel-heading">
            <h1>Departments List</h1>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Department</th>
                <th>Head</th>
                <th>Action</th>
            </tr>
            </thead>

            <c:forEach var="department" items="${departments}">
                <tr>
                    <td>${department.name}</td>
                    <td>
                        <c:if test="${not empty department.head}">
                            <a href="<c:url value="/employee/showInfo?id=${department.head.id}"/>">${department.head.fullName}</a>
                        </c:if>
                        <c:if test="${empty department.head}">
                            <p>None</p>
                        </c:if>
                    </td>
                    <td>
                        <spring:url value="/department/showInfo?id=${department.id}" var="infoUrl"/>
                        <spring:url value="/edit/department/delete?id=${department.id}" var="deleteUrl"/>
                        <spring:url value="/edit/department/update?id=${department.id}" var="updateUrl"/>

                        <button class="btn btn-warning" onclick="location.href='${infoUrl}'">Show info</button>
                        <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                        <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>