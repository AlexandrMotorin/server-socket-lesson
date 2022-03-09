<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/registration" name="registration" method="post">
    <label for="name">Name: </label>
    <input type="text" name="name" id="name"/>
    <br>
    <label for="birthday">Name: </label>
    <input type="date" name="birthday" id="birthday"/>
    <br>
    <label for="password">Password: </label>
    <input type="password" name="password" id="password"/>
    <br>
    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select>
    <br>
    <c:forEach var="gender" items="${requestScope.genders}">
        <input type="radio" name="gender" value="${gender}"> ${gender}
        <br>
    </c:forEach>
    <br>
    <button type="submit">Send</button>
</form>

</body>
</html>
