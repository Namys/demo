<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<li>${node.name} <a href="${pageContext.request.contextPath}/addEmployee?unitName=${node.name}"> добавить пользователя</a> <a
        href="${pageContext.request.contextPath}/delUnit?unitName=${node.name}">удалить</a>
    <c:if test="${node.employees.size()>0}">
        <ul>
            <c:forEach var="employee" items="${node.employees}">
                <li>${employee.id}, ${employee.fio}, ${employee.position}, ${employee.email}, ${employee.tel}, ${employee.tel2}, ${employee.room}
                    <a href="${pageContext.request.contextPath}/updateEmployee?id=${employee.id}"> редактировать</a>
                    <a href="${pageContext.request.contextPath}/deleteEmployee?id=${employee.id}"> удалить</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <br>
    <c:if test="${node.units.size()>0}">
        <ul>
            <c:forEach var="child" items="${node.units}">
                <c:set var="node" value="${child}" scope="request"/>
                <jsp:include page="recursive.jsp"/>
            </c:forEach>
        </ul>
    </c:if>
    <br>
</li>