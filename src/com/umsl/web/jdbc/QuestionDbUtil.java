package com.umsl.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class QuestionDbUtil {
 
	private DataSource dataSource;
	

	public QuestionDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
		
	public List<Question> getQuestions() throws Exception{
		
		List<Question> questions = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		// get a connection
		//	Class.forName("com.mysql.cj.jdbc.Driver");
			myConn = dataSource.getConnection();
		//create sql statement
			String sql = "select * from question  order by qid desc";
	 
		    myStmt = myConn.createStatement();
			//execute query
		    myRs =myStmt.executeQuery(sql);
		//process result set
		    
		    while(myRs.next()) {
		    	//retrive data from result set row
		    	String qid = myRs.getString("qid");
		    	String question = myRs.getString("question");
		     	String assignmentno = myRs.getString("assignmentno");
		        //craete new question object
		     	Question tempQuestion = new Question(qid,question,assignmentno);
		 
		        // add it to the list of students

		    	questions.add(tempQuestion);
		   
		    }
		    
			return questions;
		}
		finally {
			//close JDBC objects
			close(myConn,myStmt,myRs);
		}
		
	}
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
	
		try {
			
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close();
			}
		}
		catch(Exception exec) {
			exec.printStackTrace();
		}
	}


	public void addQuestion(Question theQuestion) throws Exception {
		// TODO Auto-generated method stub
		//get the connection
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			//create SQL statement
			String sql = "insert into question"
					+"(question)" 
					+"values(?)";
			myStmt = myConn.prepareStatement(sql);
			
			
		   //set the param values for the questions
			
		//	myStmt.setString(1,theQuestion.getQid());
		
			//myStmt.setString(1,theQuestion.getAssignmentno());
			myStmt.setString(1,theQuestion.getQuestion());
			
	//		System.out.println("theQuestion.getQuestion()"+theQuestion.getQuestion());
					
		//	System.out.println("theQuestion.getAssignmentno()"+theQuestion.getAssignmentno());
			//execute SQL statement
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	
	}
 

	public List<Question> getQuestions1() throws SQLException {
		List<Question> questions = new ArrayList<>();
       
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		// get a connection
		//	Class.forName("com.mysql.cj.jdbc.Driver");
			myConn = dataSource.getConnection();
		//create sql statement
			String sql = "select * from question order by qid desc";
	 
		    myStmt = myConn.createStatement();
			//execute query
		    myRs =myStmt.executeQuery(sql);
		//process result set
		    
		    while(myRs.next()) {
		    	//retrive data from result set row
		    	String qid = myRs.getString("qid");
		    	String question = myRs.getString("question");
		    	String assignmentno = myRs.getString("assignmentno");
		        //craete new question object
		    	Question tempQuestion = new Question(question);
		        // add it to the list of students
		     
		    	questions.add(tempQuestion);
		   
		    }
		    
			return questions;
		}
		finally {
			//close JDBC objects
			close(myConn,myStmt,myRs);
		}
		
	}


	public void loadQuestion(Question theQuestion) throws Exception {
		// TODO Auto-generated method stub
		//get the connection
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			//create SQL statement
			
			String sql = "insert into question"
					+"(question,assignmentno)" 
					+"values(?,?)";
			myStmt = myConn.prepareStatement(sql);
			
			
		   //set the param values for the questions
			
		 	myStmt.setString(1,theQuestion.getQuestion());
		    myStmt.setString(2,theQuestion.getAssignmentno());
		    
		 	
			//execute SQL statement
			myStmt.execute();
			
			
		}
		finally {
			close(myConn,myStmt,null);
		}
	
	}
 

	

				
}
