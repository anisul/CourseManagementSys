<%@page contentType="text/html;charset=UTF-8"%>

<%@page pageEncoding="UTF-8"%>

<%@ page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <title>Show Data</title>
    
</head>
<body>
    <h3 align="center">Students Info</h3>
    <div align="center">
      <table width="624" height="202" border="2" align="center" cellpadding="1">
        <tr>
          <th width="59">name</th>
          <th width="135">Student Id</th>
          <th width="135">Address</th>
          <th width="135">Email</th>
          <th width="135">Telephone</th>
          <th width="135">Room</th>
          <th width="130">Image</th>
          <th width="129">Link</th>
          </tr>
        
          <tr>
            <td height="126"><div align="center">
              <c:out value="${sf.name}" />
              </div></td>
            <td><div align="center">
              <c:out value="${sf.studentId}"/>
              </div></td>
            <td><div align="center">
              <c:out value="${sf.address}"/>
              </div></td>
            <td><div align="center">
              <c:out value="${sf.email}"/>
              </div></td>
            <td><div align="center">
              <c:out value="${sf.telephone}"/>
              </div></td>
            
              
              <td><div align="center">
              <c:out value="${sf.room}"/>
              </div></td>
            
            <td><img src="${pageContext.request.contextPath}/Images/${sf.userName}${sf.fileName}" width="130" height="106" align="top"/></td>
            <td><div align="center"><a href="/CourseManagementProject/download/${sf.userName}.htm" target="_Blank">Download</a></div>
              
              </hr>
            </tr>
          
       
        </table>
        </br>
        <a href="${message}">Home</a>
</div>
    
    
    
   
   </br>
    
</body>
</html>