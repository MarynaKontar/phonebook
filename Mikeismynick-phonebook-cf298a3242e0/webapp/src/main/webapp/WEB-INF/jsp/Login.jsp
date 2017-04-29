<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<jsp:include page="fragments/header.jsp"/>

<body>

<%--//todo modal window ?--%>
<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <form role="form" method="post" action="${pageContext.request.contextPath}/doLogin">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" name="login">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" name="password">
        </div>
        <div class="checkbox">
            <label><input type="checkbox" name="checkbox"> Remember me</label>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <%--<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/doLogin">--%>
    <%--<div>--%>
    <%--<label class="col-sm-2 control-label">Login</label>--%>
    <%--<div class="col-sm-10">--%>
    <%--<input type="text" id="login" placeholder="Enter Login" />--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div>--%>
    <%--<label class="col-sm-2 control-label">Password</label>--%>
    <%--<div class="col-sm-10">--%>
    <%--<input type="password" class="password" id="password" placeholder="Enter Password"/>--%>
    <%--</div>--%>
    <%--<div>--%>
    <%--<div class="button">--%>
    <%--<button type="submit" class="btn btn-default">Login</button>--%>
    <%--</div>--%>
    <%--<div class="checkbox">--%>
    <%--<label><input type="checkbox" name="checkbox">Remember me</label>--%>
    <%--</div>--%>
    <%--</form>--%>

</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
