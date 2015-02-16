	<%
	Object uid=session.getAttribute("userid");
	if(uid==null || session.getAttribute("userid").toString().equals("error") || uid.toString().isEmpty() ){
		
		response.sendRedirect("index.jsp");
			
	}
	%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	
<%@page import="dosya.DataBase"%>
<%@ page import="java.sql.*" %>
<%@ page import="dosya.FormControls" %>
<%@ page import="dosya.FormControls.*" %>
<%@ page import="dosya.DataBase.*" %>
<%@ include file="header.jsp"%>
<%@ page import="dosya.Fileview" %>
<%@ page import="dosya.Fileview.*" %>
<%@ page import="dosya.Registration"%>

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
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Yüklediklerim</a></li>
				<li><a href="#tabs-2">Hesabım</a></li>
			</ul>
			<div id="tabs-1">
				<% 
				String kullanici_id;
				try{
				kullanici_id = session.getAttribute("kullanici_id").toString();
				}catch(Exception ex){
					kullanici_id="0";
				}
				out.println(Fileview.showUserItems(kullanici_id));
				%>
			</div>
			<div id="tabs-2">
				<h2>Bilgilerim</h2>
				<%
				String sent = request.getParameter("formsent");
				if (FormControls.notempty(sent) && sent.equals("y")) {
					String ad = request.getParameter("ad");
					String soyad = request.getParameter("soyad");
					String kadi = request.getParameter("kadi");
					String yenisifre = request.getParameter("yenisifre");
					String yenisifretekrar = request.getParameter("yenisifretekrar");
					String eposta = request.getParameter("eposta");
					String eskisifre = request.getParameter("eskisifre");
					
					String r=Registration.changeInfo(ad, soyad, kadi, yenisifre, yenisifretekrar, eposta, eskisifre, kullanici_id);
					if(r.equals("OK")){
						  out.print("<div class=\"highlight\">Değişiklikleriniz yapılmıştır.</div>");
					  }
					  else{out.print("<div class=\"error\">"+r+"</div>");}
				}
				%>
				<form name="form2" method="post" action="userscreen.jsp">
				<table width="333" border="0" align="center">
					<tr>
						<td width="159">Ad:</td>
						<td width="164"><label for="ad"></label> <input
							class="registerformelement" type="text" name="ad" id="ad"></td>
					</tr>
					<tr>
						<td>Soyad:</td>
						<td><input class="registerformelement" type="text" name="soyad"
							id="soyad"></td>
					</tr>
					<tr>
						<td>Kullanıcı Adı:</td>
						<td><input class="registerformelement" type="text" name="kadi"
							id="kadi"></td>
					</tr>
					<tr>
						<td>Şifrenizi Belirleyiniz:</td>
						<td><input class="registerformelement" type="password"
							name="yenisifre" id="yenisifre"></td>
					</tr>
					<tr>
						<td>Şifrenizi Tekrar Giriniz:</td>
						<td><input class="registerformelement" type="password"
							name="yenisifretekrar" id="yenisifretekrar"></td>
					</tr>
					<tr>
						<td>E-posta:</td>
						<td><input class="registerformelement" type="text" name="eposta"
							id="eposta"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="center">Değişiklikleri kaydetmek için
							mevcut şifrenizi giriniz:<br> <input type="password"
							name="eskisifre" id="eskisifre">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="hidden" name="formsent" value="y">
						<input type="submit" name="kayditamamla" id="btnKaydiTamamla"
							value="Değişiklikleri Kaydet"></td>
					</tr>
				</table>
				</form>
			</div>

		</div>

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
