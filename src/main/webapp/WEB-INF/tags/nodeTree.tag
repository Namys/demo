<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="display the whole nodeTree" pageEncoding="UTF-8" %>
<%@attribute name="node" type="com.example.demo.Unit" required="true" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<li>${node.name} <a href="/addEmployee?unitName=${node.name}"> добавить пользователя</a> <a
        href="/delUnit?unitName=${node.name}">удалить</a>
    <c:if test="${node.employees.size()>0}">
        <ul>
            <c:forEach var="employee" items="${node.employees}">
                <li>${employee.id}, ${employee.fio}, ${employee.position}, ${employee.email}, ${employee.tel}, ${employee.tel2}, ${employee.room}
                    <a href="/updateEmployee?id=${employee.id}"> редактировать</a>
                    <a href="/deleteEmployee?id=${employee.id}"> удалить</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <br>
    <c:if test="${node.units.size()>0}">
        <ul>
            <c:forEach var="child" items="${node.units}">
                <template:nodeTree node="${child}"/>
            </c:forEach>
        </ul>
    </c:if>
    <br>
</li>