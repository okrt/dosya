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
<%@page import="dosya.DataBase"%>
<%@page import="dosya.FormControls"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="dosya.FormControls.*" %>
<%@ page import="dosya.DataBase.*" %>
<%@ include file="header.jsp"%>
<%@ page import="dosya.abuse.*" %>
<%@ page import="dosya.abuse" %>
<%@ page import="dosya.Files.*" %>
<%@ page import="dosya.Files" %>
<%@ page import="dosya.Users.*" %>
<%@ page import="dosya.Users" %>


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
          <h2>Şikayet Kontrolü</h2><p></p>
		  <form name="form1" method="post" action="">
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
			  boolean result=Files.deleteFile(id);
			  if(result){
				  out.print("<div class=\"highlight\">İşlem tamam</div>");
			  }
			  else{out.print("<div class=\"error\">İşlem başarısız</div>");}
		  }

		  else if(FormControls.notempty(action)&& action.equals("ignore")){
			  String id = request.getParameter("id");
			  boolean result=abuse.ignoreAbuse(id);
			  if(result){
				  out.print("<div class=\"highlight\">İşlem tamam</div>");
			  }
			  else{out.print("<div class=\"error\">İşlem başarısız</div>");
			  }
		  }
			  
			 else if(FormControls.notempty(action)&& action.equals("ban")){
				  String id = request.getParameter("id");
				  boolean result=Users.banUser(id);
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
		    <td><strong>Neden</strong></td>
		    <td><strong>Tarih</strong></td>
		    <td><strong>Yoksay</strong></td>
		    <td><strong>Sil</strong></td>
		    <td><strong>Engelle</strong></td>
	      </tr>
		  <% 
		  String p = request.getParameter("page");
		  String a = request.getParameter("ara");
		  if(FormControls.notempty(p))
		  {
			  out.print(abuse.getAbuseList(p));
			  
		  }
		  else{
				  out.print(abuse.getAbuseList("0"));

			  }
		  %>
	  </table>
		<% if(FormControls.notempty(action)&&action.equals("Ara")&&!a.equals("")){
			//arama için ayrı bir sıralama implemente etmeyeceğim o yüzden aramayı zaten max items per page ile sınırladım
		}else{
			out.print(abuse.showPageIterator()); }%>
		
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
