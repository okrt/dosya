<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="dosya.FormControls" %>
	<%
	if(!(session.getAttribute("userid")!=null && session.getAttribute("userid")!="error")){
		response.sendRedirect("index.jsp");
		
	}%>
	