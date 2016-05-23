<%-- 
    Document   : teacherhome
    Created on : Aug 22, 2012, 2:03:19 PM
    Author     : Tariq
--%>

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${teacherData.name}</h1>
        <h5>${teacherData.designation}</h5>
        <a href="edit.htm">Edit</a>
        </br>
        <a href="changepassword.htm">Change Password</a>
        </br>
        <a href="/CourseManagementProject/student/profile.htm">Student List</a>
        </br>
        <a href="profile.htm">Teacher List</a>   
        
   <form:form method="POST">
       <fieldset  title="Search Field"><Legend>Search Field</Legend> 
            <input type="text" name="serachtext">
        </br>
        <label>Teacher: <input type="radio" onclick="Validate()" name="mark" value="Teacher"></label></br>
        <label>Student: <input type="radio" name="mark" onclick="Validate()" value="Student" checked></label></br></br>
        <select  name="checkBox" size="1" id="checkBox">
        <option value="Semester">Semester</option>
        <option value="Student_ID">Student_ID</option>
        <option value="Name">Name</option>
         <option value="Department">Department</option>
        </select>
        </br>
         </br>
        <select name="checkBox2" id="checkBox2">
        <option value="Name">Name</option>
        <option value="Department">Department</option>
        <option value="Designation">Designation</option>
        </select>
    
        <input type="submit" text="Search">
        </fieldset>
   </form:form>
           
    </body>
</html>
