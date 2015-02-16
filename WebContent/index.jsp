<%@page import="dosya.DataBase"%>
<%@page import="dosya.FormControls"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="dosya.FormControls.*" %>
<%@ page import="dosya.DataBase.*" %>
<%@ page import="dosya.Registration.*" %>

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
			<h1>Hoş Geldiniz!</h1>
			<p>Dosya Paylaşım Sitesi projemize hoş geldiniz!</p>
		<h2>Yeni Nesil Dosya Paylaşımı</h2>
		  <p>Dosyalarınızı istediğiniz kategoriye yükleyin ve tüm dünyayla paylaşın.</p>
			<h2>Kayıt Olmak ve Kullanmak Ücretsiz!</h2>
			<p>Dosya yüklemek için tek yapmanız gereken kayıt olmak, dosyaları görüntülemek içinse herhangi bir işlem yapmanıza gerek kalmıyor.</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
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
