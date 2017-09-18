<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: Namys
  Date: 17.09.2017
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="treeDiv1">
    <form action="${pageContext.request.contextPath}/addUnit" method="get">
        <label for="unitMasterName"></label>
        <select id="unitMasterName" name="unitMasterName">
            <option value="">Сделать основным подразделением</option>
            <c:forEach items="${allUnits}" var="unitSelect">
                <option value="${unitSelect}">${unitSelect}</option>
            </c:forEach>
        </select>
        <label for="unitName"></label><input id="unitName" name="unitName">
        <input type="submit">
    </form>
    <ul>
        <c:forEach var="unit" items="${employees.units}">
            <c:set var="node" value="${unit}" scope="request"/>
            <jsp:include page="recursive.jsp"/>
        </c:forEach>
    </ul>
</div>
</body>
</html>
