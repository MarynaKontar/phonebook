<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false"%>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../fragments/header.jsp"/>
<link href="../../../resources/custom/css/style.css" rel="stylesheet"/>
<body>

<div class="container">

    <div class="panel panel-info">
        <div class="panel-heading">
            <h1>Employee Detail</h1>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3 col-lg-3 ">
                    <c:if test="${not empty employee.image}">
                        <c:set var="preview" scope="page" value="${employee.image.image}"/>
                    </c:if>
                    <div align="center">
                        <img src="${preview}" height="186" width="186" id="previewImage">
                    </div>
                </div>
                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> Last Name</td>
                            <td>${employee.lastName}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> First Name</td>
                            <td>${employee.firstName}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> Middle Name</td>
                            <td>${employee.middleName}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-calendar"></span> Birth Day</td>
                            <td>${employee.birthDay}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-tower"></span> Department</td>
                            <td>
                                <c:if test="${not empty employee.department}">

                                    <a href="<c:url value="/department/showInfo?id=${employee.department.id}"/>">${employee.department.name}</a>

                                </c:if>
                                <c:if test="${empty employee.department}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-tower"></span> Chief</td>
                            <td>
                                <c:if test="${not empty employee.chief}">

                                    <a href="<c:url value="/employee/showInfo?id=${employee.chief.id}"/>">${employee.chief.fullName}</a>

                                </c:if>
                                <c:if test="${empty employee.chief}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-comment"></span> ICQ</td>
                            <td>${employee.icq}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-headphones"></span> Skype</td>
                            <td>${employee.skype}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-envelope"></span> Email</td>
                            <td>${employee.email}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-globe"></span> Addresses</td>
                            <td>
                                <table class="table contact-table table-bordered table-hover table-sortable">
                                    <thead>
                                    <tr>
                                        <th class="text-center" width="45">#</th>
                                        <th class="text-center" width="120">City</th>
                                        <th class="text-center">Street</th>
                                        <th class="text-center" width="60">Hus.</th>
                                        <th class="text-center" width="60">Apt.</th>
                                        <th class="text-center" width="150">Type</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="address" items="${employee.addresses}" varStatus="addrIndex">
                                        <tr>
                                            <td>${addrIndex.count}</td>
                                            <td class="medium">${address.city}</td>
                                            <td>${address.street}</td>
                                            <td class="shorty">${address.house}</td>
                                            <td class="shorty">${address.apartment}</td>
                                            <td class="contact-type">${address.type}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <c:if test="${empty employee.addresses}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-phone"></span> Phones</td>
                            <td>
                                <table class="table contact-table table-bordered table-hover table-sortable">
                                    <thead>
                                    <tr>
                                        <th class="text-center" width="45">#</th>
                                        <th class="text-center">Phone</th>
                                        <th class="text-center" width="150">Type</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="phone" items="${employee.phones}" varStatus="phoneIndex">
                                        <tr>
                                            <td>${phoneIndex.count}</td>
                                            <td>${phone.phone}</td>
                                            <td class="contact-type">${phone.type}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <c:if test="${empty employee.phones}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="row" align="right">
                        <spring:url value="/edit/employee/delete?id=${employee.id}" var="deleteUrl"/>
                        <spring:url value="/edit/employee/update?id=${employee.id}" var="updateUrl"/>

                        <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                        <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
<script src="../../../resources/custom/js/imagePreviewAndSave.js"></script>
<c:if test="${empty employee.image}">
    <script>
        setPreview(null);
    </script>
</c:if>

</body>
</html>