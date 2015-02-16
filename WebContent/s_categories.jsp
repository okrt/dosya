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
<%@ page import="java.sql.*" %>
<%@ page import="dosya.*" %>
<%@ include file="header.jsp"%>

<body>
<script type="text/javascript">

function prompter(id) {
var reply = prompt("Yeni ismi giriniz:", "");
var enc=encodeURIComponent(reply);
window.location="s_categories.jsp?action=rename&id="+id+"&name="+enc;
}
//-->
</script>
    <div id="main">
        <div id="up">
			<%@ include file="up.jsp"%>
		</div>
        <div id="middle">
            <div id="middleleft">
			<%@ include file="middleleft.jsp"%>
		</div>
        <div id="middleright">
          <h2>Kategori Düzenleme</h2><p></p>
		  <p></p>
		  <!-- Bu div eğer bir hata varsa burada gösterecek. -->
		  <% 
		  String action = request.getParameter("action");
		  if(FormControls.notempty(action)&& action.equals("delete")){
			  String id = request.getParameter("id");
			  boolean result=Categories.deleteCategory(id);
			  if(result){
				  out.print("<div class=\"highlight\">İşlem tamam</div>");
			  }
			  else{out.print("<div class=\"error\">İşlem başarısız</div>");}
		  }
		  else if(FormControls.notempty(action)&& action.equals("add")){
			  String name = request.getParameter("katisim");
			  String id = request.getParameter("ustkategori");
			  boolean result=Categories.addCategory(name, id);
			  if(result){
				  out.print("<div class=\"highlight\">İşlem tamam</div>");
			  }
			  else{out.print("<div class=\"error\">İşlem başarısız</div>");}
		  }
		  else if(FormControls.notempty(action)&& action.equals("rename")){
			  String name = request.getParameter("name");
			  String id = request.getParameter("id");
			  boolean result=Categories.renameCategory(name, id);
			  if(result){
				  out.print("<div class=\"highlight\">İşlem tamam</div>");
			  }
			  else{out.print("<div class=\"error\">İşlem başarısız</div>");}
		  }
		  
		  %>
		  		    
      	<br />
		
		
		<%
		out.print(Categories.GetCategories(true));
		%>

	 	<p>&nbsp;</p>
        <form name="form1" method="post" action="s_categories.jsp">
	 	<p><strong>Yeni Kategori Ekle:</strong></p>
	 	<table width="252" height="177" border="0">
	 	  <tr>
	 	    <td width="111" height="47">Üst Kategori Seçiniz:</td>
	 	    <td width="131"><label>
	 	    <select name="ustkategori" id="ustkategori">
	 	      <% out.print(Categories.GetCategoriesUsingForUpload()); %>
	 	      </select>
	 	      </label>
	 	   
	 	      </td>
 	      </tr>
	 	  <tr>
	 	    <td height="61">Kategori Adı :</td>
	 	    <td><label>
	 	      <input id="katisim" name="katisim" type="text" size="20"/>
	 	      </label></td>
 	      </tr>
	 	  <tr>
	 	    <td height="61" colspan="2">
	 	      <input name="action" type="hidden" id="action" value="add">
	 	      <input class="juibutton" type="submit" name="yeniKatKaydet" id="yeniKatKaydet" value="Kategori Ekle">
 	        </td>
 	      </tr>
	 	  </table>
          </form>
	 	<p>&nbsp;</p>          
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
