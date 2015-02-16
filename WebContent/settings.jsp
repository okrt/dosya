<%@page import="dosya.DataBase"%>
<%@page import="dosya.FormControls"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="dosya.FormControls.*" %>
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
          <h2>Kontrol paneline hoş geldiniz, Ferhat YEŞİLTARLA</h2>
          <h2>Lütfen yan taraftan yönetmek istediğiniz bölümü seçiniz.<!-- Paylaşımların Sonu -->        </h2>
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
