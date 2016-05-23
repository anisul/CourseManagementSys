<%-- 
    Document   : validation
    Created on : Aug 22, 2012, 1:48:06 PM
    Author     : Tariq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello validation!</h1>
        <form:form method="POST" commandName="ta" modelAttribute="ta" enctype="multipart/form-data">
            Username:<spring:bind path="userName"><input type="text" name="${status.expression}" value="${status.value}"></spring:bind></br>
            Password:<spring:bind path="password"><input type="text" name="${status.expression}" value="${status.value}"></spring:bind></br>
            PIN:<spring:bind path="pin"><input type="text" name="${status.expression}" value="${status.value}"></spring:bind></br>
            
            <input type="submit"/>
        </form:form>
        
       
    </body>
</html>
