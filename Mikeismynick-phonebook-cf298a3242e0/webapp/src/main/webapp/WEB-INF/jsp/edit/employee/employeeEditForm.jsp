<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false"%>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../../fragments/header.jsp"/>
<link href="../../../../resources/custom/css/style.css" rel="stylesheet"/>
<link href="../../../../resources/datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
<body>

<%--//todo front-end validation requered--%>
<div class="container">

    <spring:url value="/showEmployees" var="actionUrl"/>

    <form:form class="form-horizontal" method="post" modelAttribute="employeeForm" action="${actionUrl}">
        <div class="panel panel-warning">
            <div class="panel-heading">
                <c:choose>
                    <c:when test="${employeeForm['new']}">
                        <h1>Add Employee</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Update Employee</h1>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-3 col-lg-3 ">
                            <%--//todo reuse image if changes not detected --%>
                        <c:if test="${not empty employeeForm.image.image}">
                            <c:set var="preview" scope="page" value="${employeeForm.image.image}"/>
                        </c:if>
                        <div align="center">
                            <img src="${preview}" height="186" width="186" id="previewImage">
                        </div>
                        <div style="width:250px;margin: 15px;" align="center">
                            <div style="float: left; width: 124px" align="center">
                                <input type="file" class="filestyle" data-buttonName="btn-primary"
                                       data-input="false" data-badge="false" data-buttonText="" id="inputImage"
                                       onchange="previewFile()">
                        </div>
                            <div style="float: right; width: 124px" align="center">
                                <button class="btn btn-danger" type="button" onclick="setPreview(null)">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                            </div>
                    </div>
                        <form:input path="image.image" class="hidden" id="imageForm" value=""/>
                    </div>
                    <div class=" col-md-9 col-lg-9 ">
                        <table class="table table-user-information">
                            <tbody>
                            <tr>
                                <td><span class="glyphicon glyphicon-user"></span> Last Name</td>
                                <td><form:input path="lastName" class="form-control"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-user"></span> First Name</td>
                                <td><form:input path="firstName" class="form-control"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-user"></span> Middle Name</td>
                                <td><form:input path="middleName" class="form-control"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-calendar"></span> Birth Day</td>
                                <td>
                                    <div id="sandbox-container">
                                            <%--<div class="input-group date">--%>
                                            <%--<input name="birthDay" value="${employeeForm.customDateFormat(employeeForm.birthDay, "yyyy/MM/dd")}" type="text" class="form-control" />--%>
                                        <form:input path="birthDay" type="text" class="form-control"/>
                                            <%--<span class="input-group-addon"></span>--%>
                                            <%--</div>--%>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-tower"></span> Department</td>
                                <td>
                                    <select name="departmentId" class="form-control">
                                        <option value="0"
                                                <c:if test="${empty employeeForm.department}">
                                                    selected
                                                </c:if>
                                        >None
                                        </option>
                                    <c:forEach var="department" items="${departments}">
                                        <option value="${department.id}"
                                                <c:if test="${not empty employeeForm.department && employeeForm.department.id == department.id}">
                                                    selected
                                                </c:if>
                                        >${department.name}</option>
                                    </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-user"></span> Chief</td>
                                <td>
                                    <select name="chiefId" class="form-control">
                                        <option value="0"
                                                <c:if test="${empty employeeForm.chief}">
                                                    selected
                                                </c:if>
                                        >None
                                        </option>
                                        <c:forEach var="chief" items="${chiefs}">
                                            <option value="${chief.id}"
                                                    <c:if test="${not empty employeeForm.chief && employeeForm.chief.id == chief.id}">
                                                        selected
                                                    </c:if>
                                            >${chief.fullName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-comment"></span> ICQ</td>
                                <td><form:input path="icq" class="form-control"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-headphones"></span> Skype</td>
                                <td><form:input path="skype" class="form-control"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-envelope"></span> Email</td>
                                <td><form:input path="email" class="form-control"/></td>
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
                                            <th class="text-center" width="60">
                                                <button class="btn btn-add btn-success" type="button">
                                                    <span class="glyphicon glyphicon-plus"></span>
                                                </button>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr class="hidden">
                                            <td>#</td>
                                            <td class="medium">
                                                <input class="form-control contact" type="text" name='addresses[0].city'
                                                       placeholder='City'/>
                                            </td>
                                            <td>
                                                <input class="form-control contact" type="text"
                                                       name='addresses[0].street'
                                                       placeholder='Street'/>
                                            </td>
                                            <td class="shorty">
                                                <input class="form-control contact" type="text"
                                                       name='addresses[0].house'
                                                       placeholder='№'/>
                                            </td>
                                            <td class="shorty">
                                                <input class="form-control contact" type="text"
                                                       name='addresses[0].apartment'
                                                       placeholder='№'/>
                                            </td>
                                            <td class="contact-type">
                                                <select class="form-control contact" name="addresses[0].type">
                                                    <c:forEach var="type" items="${contactTypes}">
                                                        <option value="${type.toString()}">${type.toString()}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <button class="btn btn-danger btn-remove" type="button" id="btn-del">
                                                    <span class="glyphicon glyphicon-minus"></span>
                                                </button>
                                            </td>
                                        </tr>
                                        <c:forEach var="address" items="${employeeForm.addresses}"
                                                   varStatus="addrIndex">
                                            <tr>
                                                <td>${addrIndex.count}</td>
                                                <td class="medium">
                                                    <input class="form-control contact" type="text"
                                                           name='addresses[${addrIndex.count}].city'
                                                           value="${address.city}" placeholder='City'/>
                                                </td>
                                                <td>
                                                    <input class="form-control contact" type="text"
                                                           name='addresses[${addrIndex.count}].street'
                                                           value="${address.street}" placeholder='Street'/>
                                                </td>
                                                <td class="shorty">
                                                    <input class="form-control contact" type="text"
                                                           name='addresses[${addrIndex.count}].house'
                                                           value="${address.house}" placeholder='№'/>
                                                </td>
                                                <td class="shorty">
                                                    <input class="form-control contact" type="text"
                                                           name='addresses[${addrIndex.count}].apartment'
                                                           value="${address.apartment}" placeholder='№'/>
                                                </td>
                                                <td class="contact-type">
                                                    <select class="form-control contact"
                                                            name='addresses[${addrIndex.count}].type'>
                                                            <%--<c:set var="contactSelect" value=" selected"/>--%>
                                                        <c:forEach var="type" items="${contactTypes}">
                                                            <option
                                                                    <c:if test="${type.equals(address.type)}">
                                                                        selected
                                                                        <%--<c:set var="contactSelect" value=""/>--%>
                                                                    </c:if>
                                                                    value="${type.toString()}">${type.toString()}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <button class="btn btn-remove btn-danger" type="button">
                                                        <span class="glyphicon glyphicon-minus"></span>
                                                    </button>
                                                </td>
                                                <input type="hidden" name="addresses[${addrIndex.count}].id"
                                                       value="${address.id}"/>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
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
                                            <th class="text-center" width="60">
                                                <button class="btn btn-add btn-success" type="button">
                                                    <span class="glyphicon glyphicon-plus"></span>
                                                </button>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr class="hidden">
                                            <td>#</td>
                                            <td>
                                                <input class="form-control contact" type="text" name='phones[0].phone'
                                                       placeholder='Phone'/>
                                            </td>
                                            <td class="contact-type">
                                                <select class="form-control contact" name='phones[0].type'>
                                                <c:forEach var="type" items="${contactTypes}">
                                                    <option value="${type.toString()}">${type.toString()}</option>
                                                </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <button class="btn btn-remove btn-danger" type="button">
                                                    <span class="glyphicon glyphicon-minus"></span>
                                                </button>
                                            </td>
                                        </tr>
                                        <c:forEach var="phone" items="${employeeForm.phones}" varStatus="phoneIndex">
                                        <tr>
                                            <td>${phoneIndex.count}</td>
                                            <td>
                                                <input type="text" name='phones[${phoneIndex.count}].phone'
                                                       value='${phone.phone}' class="form-control contact"/>
                                            </td>
                                            <td class="contact-type">
                                                <select class="form-control contact"
                                                        name='phones[${phoneIndex.count}].type'>
                                                    <c:forEach var="type" items="${contactTypes}">
                                                        <option
                                                                <c:if test="${type.equals(phone.type)}"> selected
                                                                </c:if>
                                                                value="${type.toString()}">${type.toString()}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <button class="btn btn-remove btn-danger" type="button">
                                                    <span class="glyphicon glyphicon-minus"></span>
                                                </button>
                                            </td>
                                            <input type="hidden" name="phones[${phoneIndex.count}].id"
                                                   value="${phone.id}"/>
                                        </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
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
                                <c:when test="${employeeForm['new']}">
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
<script src="../../../../resources/js/bootstrap-filestyle.min.js"></script>
<script src="../../../../resources/datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="../../../../resources/custom/js/contactAddAndDelete.js"></script>
<script src="../../../../resources/custom/js/imagePreviewAndSave.js"></script>
<c:if test="${empty employeeForm.image.image}">
    <script>
        setPreview(null);
    </script>
</c:if>
<c:if test="${not empty employeeForm.image.image}">
    <script>
        setPreview("${employeeForm.image.image}");
    </script>
</c:if>
<script>
    $(function () {
        $('#sandbox-container input').datepicker({
//            format: "yyyy/mm/dd",
            format: "dd-mm-yyyy",
            weekStart: 1,
//            endDate: "31/12/2001",
            startView: 2,
            clearBtn: true,
            multidate: false,
            autoclose: true
        })
    });
</script>


</body>
</html>