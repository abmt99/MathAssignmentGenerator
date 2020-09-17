package com.umsl.web.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



/**
 * Servlet implementation class QuestionControllerServlet
 */
@WebServlet("/QuestionControllerServlet")
public class QuestionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final String UPLOAD_DIRECTORY = "C:\\uploads\\";

	private QuestionDbUtil questionDbUtil;
	
	@Resource(name="jdbc/question")

	private DataSource dataSource;
		
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		//create our question db util and pass in the conn pool /datasource
		try {
			questionDbUtil = new QuestionDbUtil(dataSource);
		}
		catch(Exception exec) {
			throw new ServletException(exec);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
		try {
			
		// read the command
		 String theCommand = request.getParameter("command");
		 //if command is missing
		 if (theCommand == null)
		 {
			 theCommand ="LIST";
		 }
		 
		 //route the page on appropriate method
		 switch (theCommand) {
		 case "LIST":
			 
				listQuestions(request,response);
				break;
		 case "ADD":
		 
			 	addQuestions(request,response);
			 	break;
		  default:
				listQuestions(request,response);
			 
		 }
	
		}
		catch(Exception exec)
		{
			throw new ServletException(exec);
		}
	}


	private void addQuestions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//read the question information from the form data
		
		String question = request.getParameter("question");
		//System.out.println("questionadd=> "+question);
				
		//create a new question  to object
		Question theQuestion = new Question(question);
		 
		//add question to the database
		questionDbUtil.addQuestion(theQuestion);
		//send back to the main page
		listQuestions(request,response);
	}

	private void listQuestions(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		
		//get the questions from db Util
		List<Question> questions = questionDbUtil.getQuestions();
		//add questions to the request
		request.setAttribute("QUESTIONS_LIST",questions);
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_questions.jsp");
		dispatcher.forward(request, response);
	}
	  
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		   //process only if its multipart content
		try {
			processPropertiesfiles(request,response);
		}
		catch(Exception exec)
		{
			throw new ServletException(exec);
		}
		 request.getRequestDispatcher("/result.jsp").forward(request, response);
}

	private void processPropertiesfiles(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		// TODO Auto-generated method stub
	  
		
		Document document = new Document();
   	    PdfPTable table = new PdfPTable(2);
     	PdfWriter writer = null;
     
        
		 if(ServletFileUpload.isMultipartContent(request)){
	            try {
	                List<FileItem> multiparts = new ServletFileUpload(
	                                         new DiskFileItemFactory()).parseRequest(request);
	               
	                for(FileItem item : multiparts){
	                    if(!item.isFormField()){
	                        String name = new File(item.getName()).getName();
	                    
	                        String file_name = UPLOAD_DIRECTORY + File.separator + name;
	                        
	                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
	               
	               try (InputStream input = new FileInputStream(file_name))
	               {
	                 Properties props = new Properties();
	                   FileInputStream fstream = new FileInputStream(file_name);
	            	   DataInputStream in = new DataInputStream(fstream);
	                  BufferedReader br = new BufferedReader(new InputStreamReader(in));
	                   String strLine;
	                   String key[] = null;
	                   
	                   String  assignmentno = "";
	           	       String  question ="";
	           	       String  type ="";
	           	       String  title ="";
	           	       String filename = "";
	           	        String  link ="";
	           	         
	           	       	           	    
	        		try {
	        		 
	        			int id = 0;
	        	     	        		       		     
	                   while ((strLine = br.readLine()) != null) 
	                   {
	             
	                       if (strLine.contains("=")) 
	                       {
	                    	   System.out.println("in = ");
	                           key = strLine.split("=");
	                           if ((key[0].contains("type")) && (key[1].contains("TEST")))
	                           {
	                             	   type = key[1];
	                         
	                            
	           	                  }
	                            else {
	                            	if ((key[0].contains("type")) && (key[1].contains("HOMEWORK")))
	                                {
	                             
	                              	   type = key[1];
	                       	        
	                                 }
	                    
	                           else {
	                        	   if ((key[0].contains("type")) && (key[1].contains("QUIZ")))
	                               {
	                         
	                             	   type = key[1];
	                             	
	                               }
	                        	   else 
	                               {
	                                   System.out.println(strLine);
	                               }
	                        	   
	                        
	                           if (key[0].contains("title"))
	                           {
	                        	 
	                         	   title = key[1];
	                         	 
	                            }
	                           
	                        
	                           if (key[0].contains("assignmentno"))
	                           {
	                      
	                         	   assignmentno = key[1];
	                       
	                         	
	                             }
	                      
	                           if (key[0].contains("title"))
	                           {
	                        	
	                           filename = UPLOAD_DIRECTORY + type + "_" + assignmentno + ".pdf";
	                       
	                             writer = PdfWriter.getInstance(document, new FileOutputStream((filename)));
		             
		                         link = filename;
			          	          document.open();
			          	        float[] columnWidths = new float[]{02f, 20f};
			                    table.setWidths(columnWidths);
			          	         String type_header ="   Maths:"+type+"_"+assignmentno;
			          	    //   String assign_header ="Assignment No :"+assignmentno;
			          	       String title_header  ="   Title:"+title;
			          	         document.add(new Paragraph( type_header));
		                     //    document.add(new Paragraph( assign_header));
		                         document.add(new Paragraph( title_header));
		                         document.add(new Paragraph(" "));
		                       
		                
		                         table.addCell("QID");
		                   	     table.addCell("QUESTION");
				        	     table.completeRow();
				         
				        	    
	                           }
	                      
	                          if (key[0].contains("question"))
	                          {
	     
	                           question = key[1];
                             
	                            Question theQuestion = new Question(question,assignmentno);
	                            questionDbUtil.loadQuestion(theQuestion);
	                            id++;   
	                       
	                            String temp =Integer.toString(id);
	                           
	                            
	                            table.addCell(temp);
	                            table.addCell(question);
	                            
	                            table.completeRow();
	                          
	                            
	                          }
	                           }

	                         }
	                           
	                     }
	                
	                           else 
	                       {
	                           System.out.println(strLine);
	                       }
	                   }
	                   props.load(in);
	                    in.close();
	                 document.add(table);
	              	 document.close();
	                 writer.close();
	            	                 
	        		}
	        		finally {
	        		// document.close();
	               //  writer.close();
 
	          			
	             	 link = UPLOAD_DIRECTORY + type + "_" + assignmentno + ".pdf";
	              
	         
                   
                     request.setAttribute("message",link);
	        		}
	              
	            }  
	                    }
	                }
	        	
	          		//request.setAttribute("message",link);
	          
	            }catch (Exception ex) {
	               request.setAttribute("message", "File Upload Failed due to " + ex);
	            }          
	     
	    }
	        else{
	            request.setAttribute("message",
	                                 "Sorry this Servlet only handles file upload request");
	        }
	
		}
}

	

