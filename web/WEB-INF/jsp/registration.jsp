<%-- 
    Document   : login
    Created on : Aug 18, 2012, 7:58:51 PM
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
                                          if(v[i].id==="tst"&&r==="Student"){
                                              v[i].disabled=false;
                                          	   
										  }
                                           
                                           if(v[i].id==="tst"&&r==="Teacher"){
                                              v[i].disabled=true;
					      v[i].value="";
                                              
                                          }
                                      }
                                      
                                 }	
                                 
                                 
</script>
    <style type="text/css">
    #apDiv1 {
	position: absolute;
	left: 748px;
	top: 105px;
	width: 328px;
	height: 192px;
	z-index: 1;
}
    </style>
    </head>
    <body>
    <h1 align="center">Login Page</h1>
    <p align="center">&nbsp;</p>
    <div align="center">
      <form:form modelAttribute="reg" name="frm" method="POST" enctype="multipart/form-data" commandName="reg">
        <div align="left"></div>
        <div align="center">
          <table width="366" height="200">
            <tr>
              <th width="176" scope="row"><div align="right">Username:</div></th>
              <spring:bind path="username"><td width="178"><input name="${status.expression}"  value="${status.value}" type="text" id="Username"></td></spring:bind>
            </tr>
            <tr>
              <th scope="row"><div align="right">Password:</div></th>
              <spring:bind path="password"><td><input name="${status.expression}" type="password" id="Password" value="${status.value}"></td></spring:bind>
            </tr>
            <tr>
              <th height="25" scope="row"><div align="right">Re-enter Password:</div></th>
              <spring:bind path="repassword"><td><input name="${status.expression}"  value="${status.value}" type="password" id="Repassword"></td></spring:bind>
            </tr>
            <tr>
              <th height="43" scope="row"><div align="right">Student ID:</div></th>
            <spring:bind path="studentid"><td><input name="${status.expression}"  value="${status.value}" type="text" id="tst"></td></spring:bind>
            </tr>
            <tr>
              <th height="59" scope="row"><div align="right">I Am a:</div></th>
              <td><form name="form2" method="post" action="">
                <p align="justify">
                  <label>
                    <spring:bind path="mark">
                      <input type="radio"   onclick="Validate()" name="mark" value="Teacher" id="RadioGroup1_0">
                    </spring:bind>
                    Teacher</label>
                  <br>
                  <label>
                    <spring:bind path="mark">
                      <input name="mark"  type="radio" id="RadioGroup1_1"  onclick="Validate()" value="Student" checked="CHECKED" >
                    </spring:bind>
                    Student</label>
                </p>
              </form></td>
            </tr>
          </table>
        <p style="color:#F00">${message}</p>        </div>
      </form:form>
      <p align="center">
        <input type="submit" name="button" id="button" value="Submit">
      </p>
      <p align="left">&nbsp;</p>
      <p align="left">:      </p>
      <p align="left">:      </p>
      <div align="center"></div>
      <p>&nbsp;</p>
    </div>
        
</body>
</html>
