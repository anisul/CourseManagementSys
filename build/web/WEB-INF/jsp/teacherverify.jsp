<%@page contentType="text/html;charset=UTF-8"%>

<%@page pageEncoding="UTF-8"%>

<%@ page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher Authentication</title>
    </head>
    <body>
        <h1>Hello Authentication!</h1>
        
        <c:forEach items="${list}" var="list" varStatus="counter">
            <c:out value="${counter.index+1}" />
            <c:out value="${list.userName}"/>
            <c:out value="${list.pin}"/>
            </br>
            
            
        </c:forEach>
        
    </body>
</html>
