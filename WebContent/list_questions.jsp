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
			<!-- put new button: Add Question -->
			
			<input type="button" value="Add Question     " 
				   onclick="window.location.href='add-question-form.jsp'; return false;"
				   class="add-question-button"
			/>
			
            
            </form>          
		<input type="button" value="  Upload Question File    " 
				   onclick="window.location.href='upload-files-form.jsp'; return false;"
				   class="upload-question-button"
			/>
		<br/>
	

			<form action='select-question-form.jsp'>
			<BR/>
  	<input type="submit" name="Select"  value="Generate PDF Report"/>	
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
	<input type="checkbox" class="case" name="questions" value="${tempQuestion.qid}"/>  
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