	<%
	Object uid=session.getAttribute("userid");
	if(uid==null || session.getAttribute("userid")=="error" || uid.toString().isEmpty() ){
		
		response.sendRedirect("index.jsp");
		
		
		
	}
	else if(!(session.getAttribute("admin_status").toString().equals("2")||session.getAttribute("admin_status").toString().equals("1")))
	{
		response.sendRedirect("index.jsp");
		
	}
	%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dosya.DataBase"%>
<%@page import="dosya.Categories"%>
<%@page import="dosya.Files"%>
<%@page import="dosya.FormControls"%>
<%@ page import="java.sql.*"%>
<%@ page import="dosya.FormControls.*"%>
<%@ page import="dosya.DataBase.*"%>
<%@ page import="dosya.Files.*"%>
<%@ include file="header.jsp"%>
<body>
	<div id="main">
		<div id="up">
			<%@ include file="up.jsp"%>
		</div>
		<!--Up div sonu-->
		<div id="middle">
			<div id="middleleft">
				<%@ include file="middleleft.jsp"%>
			</div>
			<div id="middleright">
				<h2>Yönetim Paneline Hoş Geldiniz!</h2>
					<%

	if(uid==null || session.getAttribute("userid")=="error" || uid.toString().isEmpty() ){
		
		response.sendRedirect("index.jsp");
		
		
		
	}
	else if(session.getAttribute("admin_status").toString().equals("2"))
	{
		out.print("<p><a href=\"s_categories.jsp\">Kategori Yönetimi</a></p><p><a href=\"s_files.jsp\">Dosya Yönetimi</a></p><p><a href=\"s_users.jsp\">Kullanıcı Yönetimi</a></p>");
		
	}
	if(session.getAttribute("admin_status").toString().equals("1")||session.getAttribute("admin_status").toString().equals("2"))
	{
		out.print("<p><a href=\"s_abuse.jsp\">Şikayet Yönetimi</a></p>");
	}
	%>
				
				
				<p></p>
				<!-- Paylaşımların Sonu -->
			</div>

			<!--middle (orta) divin sonu -->
		</div>
		<div id="footer">
			<%@ include file="footer.jsp"%>
		</div>
		<!-- Ana divin sonu -->
	</div>
</body>

</html>
