<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	Date d = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy");
	String year = df.format(d);
%>

<%! 
	String project = "Management-system";
	String systemName = "易管 信息管理系统";
%>

<footer class="foot">

	<!-- 底部版权信息 -->
	<div class="bottom">
		<p>CopyRight © <%=year %></p>
		<p>山东理工大学 计算机科学与技术学院 软件工程</p>
	</div>

</footer>