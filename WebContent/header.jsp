<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<title>Dosya Paylaşım Sitesi</title>
<link href="css/ui-darkness/jquery-ui-1.10.4.custom.css"
	rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui-1.10.4.custom.js"></script>
<script>
	$(function() {
		$("#menu").menu();
		$("#giris, #kayit").button();
		$(".juibutton").button();

		$("#kullaniciadigiris, #sifregiris").button().addClass('ui-textfield')
				.off('mouseenter').off('mousedown').off('keydown');
		$(".registerformelement").button().addClass('ui-textfield')
        .off('mouseenter').off('mousedown').off('keydown');
		$("#btnKaydiTamamla").button();
		$("#tabs").tabs({
			collapsible : true
		});
	});
	function register(){window.location="register.jsp";}
	function gohome(){window.location="index.jsp";}
</script>
<style>
.ui-menu {
	width: 150px;
}
</style>
<link href="style.css" rel="stylesheet">
</head>