	<%-- jsp directives --%>
	<%@ page isELIgnored="false" %>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title > UMSL Question Bank </title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

<div id="wrapper">
		<div id="header">
			<h2 align="center"> Maths Question Bank </h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
			<!-- put new button: Add question -->
			
			<input type="button" value="Add Question    " 
				   onclick="window.location.href='add-question-form.jsp'; return false;"
				   class="add-question-button"
			/>
			
		<br/>
			<form action='QuestionControllerServlet' action='GET'>
			<BR/>
  	<input type="submit" name="question"  value="Generate Report           "/>	
     <input type="hidden" name="command" value="PDF" />
  	
			<table>
			
	<table border="1" cellpadding="5">
		
				<tr>
				 
	            <th scope="col">Select</th>
		  		<th scope="col">QID</th>
				<th scope="col">Question</th>
				
				</tr>	

	<br/>
	<br/>
	
		<c:forEach var="tempQuestion" items="${QUESTIONS_LIST}" varStatus="status">
	<tr>
	<td>
	<input type="checkbox" class="case" name="select" value="${tempQuestion.qid}"/>  
	 </td>	
		<td align="left">${tempQuestion.qid}</td>
		<td align="left">${tempQuestion.question}</td>
	
	</tr>	
		
	</c:forEach>
		</table>
		</table>

</form>

	</div>
	
	</div>
	
</body>
</html>