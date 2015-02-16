	<%
	Object uid=session.getAttribute("userid");
	if(uid==null || session.getAttribute("userid")=="error" || uid.toString().isEmpty() ){
		
		response.sendRedirect("index.jsp");
		
		
		
	}
	if(!session.getAttribute("admin_status").toString().equals("2"))
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
				<h2>Dosya Yönetimi</h2>
				<p></p>
				<% 
				String action = request.getParameter("action");
				  if(FormControls.notempty(action)&& action.equals("delete")){
					  String id = request.getParameter("id");
					  boolean result=Files.deleteFile(id);
					  if(result){
						  out.print("<div class=\"highlight\">İşlem tamam</div>");
					  }
					  else{out.print("<div class=\"error\">İşlem başarısız</div>");}
				  }
				%>
				<form name="form1" method="post" action="s_files.jsp">
		  <label for="ara"></label>
		  Dosya Adı: 
		  <input type="text" name="ara" id="ara">
	      <input type="submit" name="action" id="action" value="Ara">
	  </form>
				<div align="left"></div>
				<br />
			
				<table border="1">
		  <tr>
		    <td><strong>Dosya Adı</strong></td>
		    <td><strong>Kategori</strong></td>
		    <td><strong>Tür</strong></td>
		    <td><strong>Boyut</strong></td>
		    <td><strong>Yükleyen</strong></td>
		    <td><strong>Sil</strong></td>
	      </tr>
		  <% 
		  String p = request.getParameter("page");
		  String a = request.getParameter("ara");
		  if(FormControls.notempty(p))
		  {
			  out.print(Files.GetFileList(p));
			  
		  }else{
			  if(FormControls.notempty(action)&&action.equals("Ara")&&!a.equals("")){
		  		out.print(Files.SearchFileList(a));
			  }
			  else{
				  out.print(Files.GetFileList("0"));
			  }
			  }
		  %>
	  </table>
				<% if(FormControls.notempty(action)&&action.equals("Ara")&&!a.equals("")){
			//arama için ayrı bir sıralama implemente etmeyeceğim o yüzden aramayı zaten max items per page ile sınırladım
		}else{
			out.print(Files.showPageIterator()); }%>
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
