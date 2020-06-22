<%--validate.jsp--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String fname= request.getParameter("fname");
	String lname= request.getParameter("lname");
	System.out.println(fname+ "." + lname);
	if (fname== null || fname.length() == 0) {
%>
	<font color="red"><strong>First name needs a value.</strong></font><br/>
 <%
	}
	if (lname== null || lname.length() == 0) {
%>
	<font color="red"><strong>Last name needs a value.</strong></font><br/>
<%
	}
%>