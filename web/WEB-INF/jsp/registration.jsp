<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<img src="${pageContext.request.contextPath}/uploads/users/enot.jpg" alt="User image">

<form action="/registration" name="registration" method="post" enctype="multipart/form-data">
    <label for="name">Name: </label>
    <input type="text" name="name" id="name" required/>
    <br>
    <label for="birthday">Name: </label>
    <input type="date" name="birthday" id="birthday" required/>
    <br>
    <label for="image">Image: </label>
    <input type="file" name="image" id="image" required>
    <br>
    <label for="email">Email: </label>
    <input type="text" name="email" id="email"/>
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

    <c:if test="${not empty requestScope.errors}">
        <div>
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>

</form>

</body>
</html>
