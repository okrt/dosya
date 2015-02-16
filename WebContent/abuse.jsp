	<%
	Object uid=session.getAttribute("userid");
	if(uid==null || session.getAttribute("userid").toString().equals("error") || uid.toString().isEmpty() ){
		
		response.sendRedirect("index.jsp");
			
	}
	%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dosya.DataBase"%>
<%@page import="dosya.FormControls"%>
<%@ page import="java.sql.*"%>
<%@ page import="dosya.*"%>
<%@ page import="dosya.FormControls.*"%>
<%@ page import="dosya.DataBase.*"%>
<%@ include file="header.jsp"%>
<%@ page import="dosya.abuse"%>

<%@ include file="header.jsp"%>
<body>
	<div id="main">
		<div id="up">
			<%@ include file="up.jsp"%>
		</div>
		<!--Up div sonu-->
		<!-- Deneme Commit -->
	
	<div id="middle">
		<div id="middleleft">
			<%@ include file="middleleft.jsp"%>
		</div>
		<div id="middleright">
			<%
			String sent = request.getParameter("formsent");
			String sikayetedilendosya = request.getParameter("fileID");
			if (FormControls.notempty(sent) && sent.equals("y")) {
			String neden = request.getParameter("aciklama").toString();
			String sikayeteden = session.getAttribute("kullanici_id").toString();
			
			String r=abuse.createAbuse(sikayetedilendosya, sikayeteden, neden);
			if(r.equals("OK")){
				  out.print("<div class=\"highlight\">Şikayetiniz alındı.</div>");
			  }
			  else{out.print("<div class=\"error\">"+r+"</div>");
			  }
			}
			%>
			<form name="form1" method="post" action="abuse.jsp?fileID=<% out.println(sikayetedilendosya); %>">
					<table width="333" border="0" align="left">
						<tr>
							<td width="159">Şikayet edilen dosya:</td>
							<td width="164"><% out.println(abuse.dosyaadi(sikayetedilendosya)); %></td>
						</tr>
						<tr>
							<td>Açıklama:</td>
							<td>
								<textarea class="registerformelement" name="aciklama" cols=40 rows=6></textarea>
								</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<div align="left">
									<input type="hidden" name="formsent" value="y"> <input
										type="submit" name="sikayetet" id="juibutton"
										value="Şikayet et">
								</div>
							</td>
						</tr>
					</table>
				</form>

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
