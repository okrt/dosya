<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dosya.DataBase"%>
<%@page import="dosya.FormControls"%>
<%@ page import="java.sql.*"%>
<%@ page import="dosya.*"%>
<%@ page import="dosya.FormControls.*"%>
<%@ page import="dosya.DataBase.*"%>
<%@ page import="dosya.Registration"%>
<%@ include file="header.jsp"%>
<%@ page import="dosya.BCrypt.*"%>
<%@ page import="dosya.BCrypt"%>

<body>
	<div id="main">
		<%@ include file="up.jsp"%>
		<div id="middle">
			<div id="middleleft">
				<%@ include file="middleleft.jsp"%>
			</div>
			<div id="middleright">
				<!-- Kullanıcı kaydı formu başlangıcı -->
				<h2>Kullanıcı Kaydı</h2>
				<%
					String sent = request.getParameter("formsent");
					if (FormControls.notempty(sent) && sent.equals("y")) {
						String ad = request.getParameter("ad");
						String soyad = request.getParameter("soyad");
						String kadi = request.getParameter("kadi");
						String sifre = request.getParameter("sifre");
						String sifretekrar = request.getParameter("sifretekrar");
						String eposta = request.getParameter("eposta");
						String r=Registration.addUser(ad, soyad, kadi, sifre, sifretekrar, eposta);
						if(r.equals("OK")){
							  out.print("<div class=\"highlight\">Kaydınız tamamlandı, teşekkürler</div>");
						  }
						  else{out.print("<div class=\"error\">"+r+"</div>");}
					}
					%>
				<form name="form1" method="post" action="register.jsp">
					<table width="333" border="0" align="left">
						<tr>
							<td width="159">Ad:</td>
							<td width="164"><label for="ad"></label> <input
								class="registerformelement" type="text" name="ad" id="ad"></td>
						</tr>
						<tr>
							<td>Soyad:</td>
							<td><input class="registerformelement" type="text"
								name="soyad" id="ad2"></td>
						</tr>
						<tr>
							<td>Kullanıcı Adı:</td>
							<td><input class="registerformelement" type="text"
								name="kadi" id="ad3"></td>
						</tr>
						<tr>
							<td>Şifrenizi Belirleyiniz:</td>
							<td><input class="registerformelement" type="password"
								name="sifre" id="ad4"></td>
						</tr>
						<tr>
							<td>Şifrenizi Tekrar Giriniz:</td>
							<td><input class="registerformelement" type="password"
								name="sifretekrar" id="ad5"></td>
						</tr>
						<tr>
							<td>E-posta:</td>
							<td><input class="registerformelement" type="text"
								name="eposta" id="ad3"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<div align="left">
									<input type="hidden" name="formsent" value="y"> <input
										type="submit" name="kayditamamla" id="btnKaydiTamamla"
										value="Kaydı Tamamla">
								</div>
							</td>
						</tr>
					</table>
				</form>
				<!-- Kullanıcı kayıt formu bitişi -->
			</div>

			<!--middle (orta) divin sonu -->
		</div>
		<%@ include file="footer.jsp"%>
		<!-- Ana divin sonu -->
	</div>
</body>

</html>