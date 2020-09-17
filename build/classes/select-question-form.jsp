<html>
<head>
	<%-- jsp directives --%>
	<%@ page isELIgnored="false" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page 	import="com.itextpdf.text.*,java.io.FileNotFoundException,
	java.io.FileOutputStream, com.itextpdf.text.Document, com.itextpdf.text.DocumentException, 
	com.itextpdf.text.Paragraph, com.itextpdf.text.pdf.PdfWriter,
	com.itextpdf.text.pdf.PdfPCell, com.itextpdf.text.*,com.itextpdf.text.pdf.PdfPTable"
 %>

	<title>Maths Question Bank</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
<br/>
   <a href="QuestionControllerServlet">Back to List</a>
     
 <br/>
  <br/>
 <hr/>     
     
	<input type="button" value="Click button to open PDF" 
				   onclick="window.location.href='C:\\Downloads\\QuestionSet.pdf'; return false;"
				   class="pdf-link "
			/>
			

   <br/>
   <br/>

<div id="wrapper">
		<div id="header">
			<h2 align="center"> Maths Question Bank </h2>
		</div>
	</div>


	<table border="1" cellpadding="5">
	<ul>
		<%
	 
	      Document document = new Document();
	  try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Downloads\\QuestionSet.pdf"));
         document.open();
         document.add(new Paragraph("         Assignment 2     "));
         document.add(new Paragraph("        "));
         PdfPTable table = new PdfPTable(2);
         table.addCell(new PdfPCell(new Phrase("QID")));
         table.addCell(new PdfPCell(new Phrase("QUESTION")));
         table.completeRow();
         //document.add(table);
   
			String[] langs = request.getParameterValues("questions");

		    if (langs == null) 
		    {
		    	out.println("No Questions Selected");
		    }
		    else
		    {
		  %>
		  <%@ page import = "java.sql.*" %>
		<%
		      Connection conn = DriverManager.getConnection(
		          "jdbc:mysql://localhost:3306/question", "root", ""); // <== Check!
		      // Connection conn =
		      //    DriverManager.getConnection("jdbc:odbc:eshopODBC");  // Access
		      Statement stmt = conn.createStatement();
		 
		      String sqlStr = "SELECT qid, question FROM question WHERE qid IN (";
		      sqlStr += "'" + langs[0] + "'";  // First author
		      for (int i = 1; i < langs.length; ++i) {
		         sqlStr += ", '" + langs[i] + "'";  // Subsequent authors need a leading commas
		      }
		      sqlStr += ")  ORDER BY qid DESC";
		 
		      // for debugging
		      System.out.println("Query statement is " + sqlStr);
		      ResultSet rset = stmt.executeQuery(sqlStr);
		  %>
		      <hr>
		      <form method="get" action="select-question-form.jsp">
	 
		        <table border=1 cellpadding=5>
		    	<tr>
					<th scope="col">QID</th>
					<th scope="col">Question</th>
            	</tr>      
		  <%
		      while (rset.next()) {
		       
		  %>
		          <tr>
		           
		            <td align="left"><%= rset.getString("qid") %></td>
		            <td  align="left" style="height:100px;width:1000px"><%= rset.getString("question") %></td>
	
		          </tr>
		    
		  <%
		    table.addCell(new PdfPCell(new Phrase(rset.getString("qid"))));
	         table.addCell(new PdfPCell(new Phrase(rset.getString("question"))));
	         table.completeRow();
	     
		      }
		    document.add(table);
   	        rset.close();
		      stmt.close();
		      conn.close();
	        document.close();
	         writer.close();
	     }
  
		    }
		    catch (DocumentException e)
	    	  {
	    	     e.printStackTrace();
	    	  } catch (FileNotFoundException e)
	    	  {
	        	e.printStackTrace();
	     	 }
	
		  %>
		     </table>
		        <br>
		      
		      </form>
		</body>
		</html>