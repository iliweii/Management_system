<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>基本数据管理 | <%=systemName %></title>

    <!-- link bootstrap4 css -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <!-- link style css -->
    <link rel="stylesheet" href="../css/style.css">
    
    <!-- script JQuery -->
    <script src="../js/jquery-3.5.1.js"></script>

</head>
<body class="bg-light">
    
    <!-- header start -->
    <jsp:include page="../head.jsp" flush="false">
	    <jsp:param name="active" value="1"/>
	</jsp:include>

    <!-- second nav start -->
    <jsp:include page="../secondnav.jsp" flush="false">
    	<jsp:param name="second" value="basedata"/>
	    <jsp:param name="active" value="0"/>
	</jsp:include>

    <!-- body start -->
    <section class="body">
        <div class="inner">

            <h1 class="body-h2">基本数据管理</h1>

        </div>
    </section>

    <!-- footer start -->
    <%@ include file="../foot.jsp" %>

</body>
</html>