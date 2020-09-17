package com.umsl.web.jdbc;


public class Question {
	private String qid;
	private String question;
	private String assignmentno;
	private String[] question1;
	
	
	
//	public Question(String qid, String question) {
//	
//		this.question = qid;
//		this.question = question;
//	}
	public Question(String qid, String question, String assignmentno) {
	
		this.qid = qid;
		this.question = question;
		this.assignmentno = assignmentno;
	}
	
	public Question(String question, String assignmentno) {
		this.question = question;
		this.assignmentno = assignmentno;
	}
	
	public Question(String question) {
		// TODO Auto-generated constructor stub
		this.question = question;
	}
	
	public Question(String[] question1) {
		// TODO Auto-generated constructor stub
		this.question1 = question1;
	}
	public String getQid() {
		return qid;
		// return (qid!=null && qid.length()>0) ? qid : "N/A";
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAssignmentno() {
		return assignmentno;
	}
	public void setAssignmentno(String assignmentno) {
		this.assignmentno = assignmentno;
	}
	@Override
	public String toString() {
		return "Question [qid=" + qid + ", question=" + question + ", assignmentno=" + assignmentno + "]";
	}
	
	
	

}
