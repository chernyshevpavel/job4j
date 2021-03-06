<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <form method="post" action="${pageContext.servletContext.contextPath}/list" enctype="multipart/form-data">
        <input type="text" name="action" value="add" hidden>
        <input type="text" name="login" placeholder="Login">
        <input type="text" name="name" placeholder="Name">
        <input type="email" name="email" placeholder="Email">
        <input type="password" name="password" placeholder="Password">
        <select name="role">
        <c:forEach items="${roles}" var="role">
            <option value="<c:out value="${role.id}"></c:out>"><c:out value="${role.name}"></c:out></option>
        </c:forEach>
        </select>
        <input type="file" name="file" placeholder="Photo">
        <button type="submit">Create</button>
    </form>
</table>
</body>
</html>
