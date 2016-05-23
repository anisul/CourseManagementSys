<%@page contentType="text/html;charset=UTF-8"%>

<%@page pageEncoding="UTF-8"%>

<%@ page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">




<html>
    <head>
        <title>New Student Information</title>
    </head>
    <body>
        <h1>${registrationData.username}</h1>
        
        <h4>${message}</h4>
        <form:form modelAttribute="sf" name="frm" method="POST" enctype="multipart/form-data" commandName="sf">
            Name:
            <spring:bind path="name">
                <input type="text" name="${status.expression}" value="${status.value}">
            </spring:bind>
               
            </br>
            Student ID:
            <spring:bind path="studentId">
                <input type="text" name="${status.expression}" value="${status.value}">
            </spring:bind>
               
            </br>

            Semester:
            <spring:bind path="semester">
                <input type="text" name="${status.expression}" value="${status.value}">
            </spring:bind>
               
            </br>
            
            Department:
            <spring:bind path="department">
                <input type="text" name="${status.expression}" value="${status.value}">
            </spring:bind>
               
            </br>
        
            Room No:
            <spring:bind path="room">
                <input type="text" name="${status.expression}" value="${status.value}">
            </spring:bind>
               
            </br>
            
            Address:
            <spring:bind path="address">
                <input type="text" name="${status.expression}" value="${status.value}">
            </spring:bind>
               
            </br>
        
            Email:
            <spring:bind path="email">
                <input type="text" name="${status.expression}" value="${status.value}">
            </spring:bind>
               
            </br>
            
            
            Telephone:
            <spring:bind path="telephone">
                <input type="text" name="${status.expression}" value="${status.value}">
            </spring:bind>
               
            </br>
            </br>
            
            </br>
            <form:label for="fileData" path="fileData">File</form:label>
            </br>
            <form:input path="fileData" id="image" type="file"></form:input>
            </br>
            
            <input type="Submit" value="Submit"/>
            
        </form:form>
    
    
    </body>





</html>