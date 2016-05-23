<%@page contentType="text/html;charset=UTF-8"%>

<%@page pageEncoding="UTF-8"%>

<%@ page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<script language="JavaScript">
				function Validate()
				  {
                                      
                                      
                                      var v=document.getElementsByTagName('input');
                                      var r;
                                      for(var i=0;i<v.length;i++){
                                            if(v[i].type==="radio"&&v[i].checked){
                                                     r=v[i].value;
                                                     
                                            } 
                                      }
                                      var t;
                                      for(var i=0;i<v.length;i++){
                                          if(r==="Student"){
                                              var ch=document.getElementsByName('checkBox')
                                              ch[0].disabled=false;
                                              ch=document.getElementsByName('checkBox2');
                                              ch[0].disabled=true;
					}
                                           
                                           if(r==="Teacher"){
                                            var ch=document.getElementsByName('checkBox')
                                              ch[0].disabled=true;
                                              ch=document.getElementsByName('checkBox2');
                                              ch[0].disabled=false;
						
                                          }
                                      }
                                      
                                 }		
    
                                 function onLoad(){
                                     var ch=document.getElementsByName('checkBox')
                                              ch[0].disabled=false;
                                              ch=document.getElementsByName('checkBox2');
                                              ch[0].disabled=true;
					
                                 }
    
    
    
</script>


<html>
<head>
    <title>Show Data</title>
    
</head>
<body onload="onLoad()">
 
    <h1>Hello, ${studentData.name}!</h1>
    <h1>Room: ${studentData.room}!</h1>
    <h6>${Message}</h6>
    <a href="edit.htm">edit</a>
    </br>
    <a href="changepassword.htm">Change Password</a> 
    </br>
    <a href="profile.htm">Student Profiles</a> 
    </br>
     
    <a href="/CourseManagementProject/teacher/profile.htm">Teacher Profiles</a> 
    </br>
    <form:form method="POST"><fieldset  title="Search Field"><Legend>Search Field</Legend> 
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
    </br>
    </br>
    </br>
    </br>
    </br>
    <input type="submit" text="Search"></fieldset></form:form>
    </br>
    </br>
    </br>
    </br>
    <a href="">Search Student</a> 
    </br>
    <a href="">Search Teacher</a> 
    </br>
    <a href="">Downloads</a> 
    </br>
    <a href="">Notice Board</a> 
    </br>
    </br>
    </br>
    </br>
    </br>
    </br>
    
    
   <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
</body>
</html>