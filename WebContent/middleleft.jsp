<%@page import="dosya.Categories"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="dosya.FormControls.*" %>
<%@ page import="dosya.DataBase.*" %>
<%@ page import="dosya.Categories.*" %>

<ul id="menu">
<% out.print(Categories.GetCategories(false)); %>
</ul>


