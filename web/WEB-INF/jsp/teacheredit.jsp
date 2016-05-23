<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <h1>Edit Profile</h1>
        <h6>${Message}</h6>
        <form:form method="POST"  enctype="multipart/form-data" modelAttribute="tf" commandName="tf">
            Name:<input type="text" value="${teacherData.name}" name="name" id="name"></br>
            Office No:<input type="text" value="${teacherData.office}" name="office" id="office"></br>
            Designation:<input type="text" value="${teacherData.designation}" name="designation" id="designation"></br>
            Address:<input type="text" value="${teacherData.address}" name="address" id="address"></br>
            Email:<input type="text" value="${teacherData.email}" name="email" id="email"></br>
            Telephone:<input type="text" value="${teacherData.telephone}" name="telephone" id="telephone"></br>
            <fieldset >
                
                </br>
                <form:label for="fileData" path="fileData">File</form:label>
                </br>
                <form:input path="fileData" id="image" type="file"></form:input>
                </br>
                
            </fieldset>
            
        <input type="submit"/>
        </form:form>
        <a href="home.htm">Home</a>
        
    </body>
    
    
    
    
    
</html>