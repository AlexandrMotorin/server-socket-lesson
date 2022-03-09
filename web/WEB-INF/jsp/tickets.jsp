<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tickets</title>
</head>
<body>
<%@include file="header.jsp"%>


<h1>Купленные билеты</h1>
<ul>
    <c:forEach var="ticket" items="${requestScope.tickets}">
        <li>
            ${ticket.seatNo}
        </li>
    </c:forEach>

</ul>


<%@include file="footer.jsp"%>
</body>
</html>
