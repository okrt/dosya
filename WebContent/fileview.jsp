<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="header.jsp"%>
<%@ page import="dosya.Fileview" %>
<%@ page import="dosya.Fileview.*" %>

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
		<h2>Dosya Görüntüle</h2>
			<% 
				String fileID = request.getParameter("fileID");
				out.println(Fileview.fetchFile(fileID));
			%>
		</table>
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
