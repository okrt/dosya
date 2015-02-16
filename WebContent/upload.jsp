	<%
	Object uid=session.getAttribute("userid");
	if(uid==null || session.getAttribute("userid").toString().equals("error") || uid.toString().isEmpty() ){
		
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
<%@page import="dosya.Categories"%>
<%@ page import="dosya.Categories.*" %>
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
			<h2>Dosya Yükle</h2>

			<form action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data"
				name="form1">
				<table width="600" border="0" align="left">
					<tr>
						<td width="300">Dosya Adı:</td>
						<td width="300"><label for="dosyaadi"></label> <input
							class="registerformelement" type="text" name="dosyaadi"
							id="dosyaadi"></td>
					</tr>
					<tr>
						<td>Kategori Seçimi:</td>
						<td><label for="kategori"></label> <select
							style="background-color: #333; color: #FFF;" name="kategori"
							id="kategori">
								<% out.print(Categories.GetCategoriesUsingForUpload()); %>
						</select></td>
					</tr>
					<tr>
						<td>Dosya Açıklaması:</td>
						<td><label for="aciklama"></label> <textarea
								class="registerformelement" name="aciklama" id="aciklama"></textarea></td>
					</tr>
					<tr>
						<td>Dosya:</td>
						<td><label for="dosya"></label>
						<input id="file" type="file" name="fileName">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><p>Bu dosyayı yükleyerek
								Kullanım Şartları ve Gizlilik Sözleşmesi'ni kabul ediyorsunuz.</p>
							<p>
								<input id="upload" type="submit" name="upload" value="Upload">
							</p></td>
					</tr>
				</table>
			</form>
			<p>&nbsp;</p>
			<h2>
				<!-- Paylaşımların Sonu -->
			</h2>
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
