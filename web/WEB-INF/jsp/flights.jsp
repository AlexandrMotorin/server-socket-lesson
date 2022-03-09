<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Tickets</title>
</head>
<body>

<%@include file="header.jsp"%>


<h1>Список перелётов</h1>

<ul>
    <c:forEach var="flight" items="${requestScope.flights}">
        <li>
            <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.key}">${flight.value}</a>
        </li>
    </c:forEach>
</ul>



<%@include file="footer.jsp"%>
</body>
</html>
