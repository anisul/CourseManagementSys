<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head><title>Change Password</title></head>
        
    <body>
        <h1>Change Password</h1>
        <h4>${Message}</h4>
        <form:form modelAttribute="pd" name="frm" method="POST" enctype="multipart/form-data" commandName="pd">
            <h5>Old Password:</h5><spring:bind path="oldPassword"><td width="178"><input name="${status.expression}"  value="${status.value}" type="password" id="Username"></td></spring:bind></br>
        <h5>New Password:</h5><spring:bind path="password"><td width="178"><input name="${status.expression}"  value="${status.value}" type="password" id="Username"></td></spring:bind></br>
            <h5>Re-enter Password:</h5><spring:bind path="repassword"><td width="178"><input name="${status.expression}"  value="${status.value}" type="password" id="Username"></td></spring:bind></br>
            
            <input type="submit" value="Submit"/>
        </form:form>
        
        
    </body>
    
    
    
</html>