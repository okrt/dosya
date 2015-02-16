	<%
	Object uid=session.getAttribute("userid");
	if(uid==null || session.getAttribute("userid")=="error" || uid.toString().isEmpty() ){
		
		response.sendRedirect("index.jsp");
		
		
	}
	else if(!(session.getAttribute("admin_status").toString().equals("2")))
	{
		response.sendRedirect("index.jsp");
		
	}
	%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dosya.Users"%>
<%@page import="dosya.DataBase"%>
<%@page import="dosya.FormControls"%>
<%@ page import="java.sql.*" %>
<%@ page import="dosya.FormControls.*" %>
<%@ page import="dosya.Users.*" %>
<%@ page import="dosya.DataBase.*" %>
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
		<h2>Kullanıcı Yönetimi</h2>
		<form name="form1" method="post" action="s_users.jsp">
		  <label for="ara"></label>
		  Kullanıcı Adı: 
		  <input type="text" name="ara" id="ara">
	      <input type="submit" name="action" id="action" value="Ara">
	  </form>
		<p></p>
		<% 
		String action = request.getParameter("action");
		  if(FormControls.notempty(action)&& action.equals("delete")){
			  String id = request.getParameter("id");
			  boolean result=Users.deleteUser(id);
			  if(result){
				  out.print("<div class=\"highlight\">İşlem tamam</div>");
			  }
			  else{out.print("<div class=\"error\">İşlem başarısız</div>");}
		  }
		  else if(FormControls.notempty(action)&& action.equals("ban")){
			  String id = request.getParameter("id");
			  boolean result=Users.banUser(id);
			  if(result){
				  out.print("<div class=\"highlight\">İşlem tamam</div>");
			  }
			  else{out.print("<div class=\"error\">İşlem başarısız</div>");}
		  }
		  else if(FormControls.notempty(action)&& action.equals("unban")){
			  String id = request.getParameter("id");
			  boolean result=Users.unbanUser(id);
			  if(result){
				  out.print("<div class=\"highlight\">İşlem tamam</div>");
			  }
			  else{out.print("<div class=\"error\">İşlem başarısız</div>");}
		  }
		  %>
		 
	<table border="1">
		  <tr>
		    <td><strong>Kullanıcı Adı</strong></td>
		    <td><strong>Ad</strong></td>
		    <td><strong>Soyad</strong></td>
		    <td><strong>E-posta</strong></td>
		    <td><strong>Engelle</strong></td>
		    <td><strong>Sil</strong></td>
	      </tr>
		  <% 
		  String p = request.getParameter("page");
		  String a = request.getParameter("ara");
		  if(FormControls.notempty(p))
		  {
			  out.print(Users.GetUserList(p));
			  
		  }else{
			  if(FormControls.notempty(action)&&action.equals("Ara")&&!a.equals("")){
		  		out.print(Users.SearchUserList(a));
			  }
			  else{
				  out.print(Users.GetUserList("0"));
			  }
			  }
		  %>
	  </table>
		<% if(FormControls.notempty(action)&&action.equals("Ara")&&!a.equals("")){
			//arama için ayrı bir sıralama implemente etmeyeceğim o yüzden aramayı zaten max items per page ile sınırladım
		}else{
			out.print(Users.showPageIterator()); }%>
		
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
