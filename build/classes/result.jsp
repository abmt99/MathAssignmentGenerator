
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="scheme" scope="page" value="${pageContext.request.scheme}"/>
<c:set var="serverName" scope="page" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" scope="page" value="${pageContext.request.serverPort}"/>
<c:set var="contextPath" scope="page" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Example in JSP and Servlet - Java web application</title>
    </head>
   <a href="QuestionControllerServlet">Back to List</a>
    <body> 
        <div id="result">

        </div>
			 
	<c:forEach var="tempQuestion" items="${message}" varStatus="status">
 
      
	<h2 align="left"> Click the link to download file- </h2>
		<br/>

	<br/>
	
	<a href="file:///${message}">${scheme}://${serverName}:${serverPort}${contextPath}/${message}</a>
	
	<br/>		
		</c:forEach>
 
</body>
</html>