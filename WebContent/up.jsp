<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="up">
	<div id="upleft">
		<h1 id="ustbaslik" onclick="javascipt:gohome();">Dosya Paylaşım Sitesi</h1>
	</div>
	<div id="upright" class="ui-widget">
		<%
		    
			if(session.getAttribute("userid")!=null && session.getAttribute("userid")!="error")
			{ 
				String userid = session.getAttribute("userid").toString();
				%>
				<div align="right">
				<% out.print("Hosgeldiniz " + userid + "  "); %>
		<a href="logout.jsp" style="color: rgb(255,204,0)" >Cikis</a><br />
		<a href="upload.jsp">Dosya Yükle</a> | <a href="userscreen.jsp">Benim Sayfam</a>
		<% 	if(session.getAttribute("admin_status").toString().equals("2")||session.getAttribute("admin_status").toString().equals("1"))
		{
			 out.print("&nbsp;|&nbsp;<a href=\"s_admin.jsp\">Yönetim Paneli</a>"); 
			
		}
		%> 
		
		</div>
		<%
			}
			else
			{
				if(session.getAttribute("userid")=="error"){
					%>
					<div align="right">
					<% out.println("Girdiğiniz kullanıcı adı veya parola hatalı"); %>
					</div> 
					<form id="girisyap" action="login.jsp" method="POST">
			    <table width="270" border="0" align="right">
				<tr>
					<td width="93">Kullanıcı Adı :</td>
					<td width="167"><input id="kullaniciadigiris" name="username"
						type="text" size="20" /></td>
				</tr>
				<tr>
					<td>Şifre</td>
					<td><input id="sifregiris" name="password" type="password"
						size="20" /></td>
				</tr>
				<tr>
					<td colspan="2"><div align="right">
							<input id="giris" name="giris" type="submit" value="Giriş" /> 
							<input id="kayit" name="kayit" type="button" value="Kayıt Ol" onclick="javascipt:register();" />
						</div></td>
				</tr>
			</table>
			<br />
			</form>
			<% 
				}
				else
				{
				%>
				<form id="girisyap" action="login.jsp" method="POST">
			    <table width="270" border="0" align="right">
				<tr>
					<td width="93">Kullanıcı Adı :</td>
					<td width="167"><input id="kullaniciadigiris" name="username"
						type="text" size="20" /></td>
				</tr>
				<tr>
					<td>Şifre</td>
					<td><input id="sifregiris" name="password" type="password"
						size="20" /></td>
				</tr>
				<tr>
					<td colspan="2"><div align="right">
							<input id="giris" name="giris" type="submit" value="Giriş" /> 
							<input id="kayit" name="kayit" type="button" value="Kayıt Ol" onclick="javascipt:register();" />
						</div></td>
				</tr>
			</table>
			<br />
			</form>
			<%
				}
			}
		%>
	</div>
	<!--Up div sonu-->
</div>