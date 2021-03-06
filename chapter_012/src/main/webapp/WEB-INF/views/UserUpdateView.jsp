<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <form method="post" action="<%= request.getContextPath()%>/list" enctype="multipart/form-data">
        <input type="text" name="action" value='update' hidden>
        <input type="text" name="id" value='<c:out value="${user.id}"></c:out>' hidden>
        <input type="text" name="login" placeholder="Login" value='<c:out value="${user.login}"></c:out>'>
        <input type="text" name="name" placeholder="Name" value='<c:out value="${user.name}"></c:out>'>
        <input type="email" name="email" placeholder="Email" value='<c:out value="${user.email}"></c:out>'>
        <c:if test="${roles != null}">
        <select name="role">
            <c:forEach items="${roles}" var="role">
                <option <c:if test="${userRole.id == role.id}"><c:out value="selected"></c:out></c:if> value="<c:out value="${role.id}"></c:out>"><c:out value="${role.name}"></c:out></option>
            </c:forEach>
        </select>
        </c:if>
        <button type="submit">Update</button>
    </form>
</table>
</body>
</html>
