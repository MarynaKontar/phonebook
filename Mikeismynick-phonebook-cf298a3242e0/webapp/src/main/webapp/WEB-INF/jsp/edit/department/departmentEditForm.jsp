<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false"%>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../../fragments/header.jsp"/>
<body>

<div class="container">

    <spring:url value="/showDepartments" var="actionUrl"/>

    <form:form class="form-horizontal" method="post" modelAttribute="departmentForm" action="${actionUrl}">
        <div class="panel panel-warning">
            <div class="panel-heading">
                <c:choose>
                    <c:when test="${departmentForm['new']}">
                        <h1>Add Department</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Update Department</h1>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class=" col-md-9 col-lg-9 ">
                        <table class="table table-department-information">
                            <tbody>
                            <tr>
                                <td><span class="glyphicon glyphicon-tower"></span> Name</td>
                                <td><form:input path="name" class="form-control" placeholder="Name"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-user"></span> Head</td>
                                <td>
                                    <select name="headId" class="form-control">
                                        <option value="0"
                                                <c:if test="${empty departmentForm.head}">
                                                    selected
                                                </c:if>
                                        >None
                                        </option>
                                        <c:forEach var="employee" items="${employees}">
                                            <option value="${employee.id}"
                                                    <c:if test="${not empty departmentForm.head && departmentForm.head.id == employee.id}">
                                                        selected
                                                    </c:if>
                                            >${employee.fullName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                        <span class="pull-right">
                            <c:choose>
                                <c:when test="${departmentForm['new']}">
                                    <button type="submit" class="btn-lg btn-primary pull-right"><i
                                            class="glyphicon glyphicon-floppy-disk"></i> Add
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn-lg btn-primary pull-right"><i
                                            class="glyphicon glyphicon-floppy-disk"></i> Update
                                    </button>
                                </c:otherwise>
                            </c:choose>
                            <form:hidden path="id"/>
                        </span>
                </div>
            </div>
        </div>
    </form:form>
</div>
<jsp:include page="../../fragments/footer.jsp"/>

</body>
</html>