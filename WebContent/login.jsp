<%@page import="dosya.DataBase"%>
<%@page import="dosya.FormControls"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="dosya.*"%>
<%@ page import="dosya.FormControls.*"%>
<%@ page import="dosya.DataBase.*"%>
<%@ page import="dosya.Registration.*"%>
<%@ page import="dosya.BCrypt.*"%>
<%@ page import="dosya.BCrypt"%>
<%
	String userid = request.getParameter("username");
	String pwd = request.getParameter("password");
	
	if (session.getAttribute("userid") == null || session.getAttribute("userid") == "error") {
		
		
		Connection con = dosya.DataBase.getConnection();
		Statement st = con.createStatement();
		ResultSet rs;
		rs = st.executeQuery("select kullanici_id,sifre, admin_status, is_banned from kullanicilar where kadi='"
				+ userid + "'");
		if(rs.next()) {
		String passwd = rs.getString("sifre");
		int kullanici_id = rs.getInt("kullanici_id");
		int admin_status= rs.getInt("admin_status");
		int is_banned= rs.getInt("is_banned");
			if (BCrypt.checkpw(pwd, passwd)&& is_banned==0) {
			session.setAttribute("userid", userid);
			session.setAttribute("admin_status", admin_status);
			session.setAttribute("kullanici_id", kullanici_id);
			response.sendRedirect("index.jsp");
			}
			else {
			//out.println("Girdiğiniz kullanıcı adı veya şifre hatalı.</a>"); 
			session.setAttribute("userid", "error");
			response.sendRedirect("index.jsp");
			}
		}
		else {
			//out.println("Girdiğiniz kullanıcı adı veya şifre hatalı.</a>");
			session.setAttribute("userid", "error");
			response.sendRedirect("index.jsp");
			}
		}
		%>