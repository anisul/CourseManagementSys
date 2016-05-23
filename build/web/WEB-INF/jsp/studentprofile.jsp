<%@page contentType="text/html;charset=UTF-8"%>

<%@page pageEncoding="UTF-8"%>

<%@ page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
          <th width="41">Sl. No.</th>
          <th width="59">ID</th>
          <th width="135">Name</th>
          <th width="130">Image</th>
          <th width="129">Link</th>
          </tr>
        <c:forEach items="${students}" var="s" varStatus="counter">
          <tr>
            <td height="126"><div align="center">
              <c:out value="${counter.index+1}" />
              </div></td>
            <td><div align="center">
              <c:out value="${s.studentId}"/>
              </div></td>
            <td><div align="center">
              <a href="${s.userName}.htm"><c:out value="${s.name}"/></a>
              </div></td>
            
            <td><img src="${pageContext.request.contextPath}/Images/${s.userName}${s.fileName}" width="130" height="106" align="top"/></td>
            <td><div align="center"><a href="/CourseManagementProject/download/${s.userName}.htm" target="_Blank">Download</a></div>
              
              </hr>
            </tr>
          
        </c:forEach>
        </table>
        </br>
        <a href="${message}" >Home</a>
        
</div>
    
    
    
   
   </br>
    
</body>
</html>