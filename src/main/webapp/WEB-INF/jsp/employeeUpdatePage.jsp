<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form:form method="POST" action="${pageContext.request.contextPath}/updateEmployee" modelAttribute="employee">
        <div>
            <form:label path="id">Табельный номер сотрудника</form:label>
            <div>
                <form:input path="id" disabled="true"/>
            </div>
        </div>
        <div>
            <form:label path="fio">ФИО</form:label>
            <div>
                <form:input path="fio"/>
            </div>
        </div>
        <div>
            <form:label path="position">Должность</form:label>
            <div>
                <form:input path="position"/>
            </div>
        </div>
        <div>
            <form:label path="email">Адрес электронной почты </form:label>
            <div>
                <form:input path="email" type="email"/>
            </div>
        </div>
        <div>
            <form:label path="tel">Номер телефона</form:label>
            <div>
                <form:input path="tel"/>
            </div>
        </div>
        <div>
            <form:label path="tel2">Доп. номер телефона</form:label>
            <div>
                <form:input path="tel2"/>
            </div>
        </div>
        <div>
            <form:label path="room">Номер кабинета</form:label>
            <div>
                <form:input path="room"/>
            </div>
        </div>
        <input type="submit">
    </form:form>
</div>
</body>
</html>
