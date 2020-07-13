<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页 | <%=systemName %></title>

    <!-- link bootstrap4 css -->
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <!-- link style css -->
    <link rel="stylesheet" href="./css/style.css">
    
    <!-- script JQuery -->
    <script src="./js/jquery-3.5.1.js"></script>

</head>
<body class="bg-light">
    
    <!-- header start -->
    <jsp:include page="./head.jsp" flush="false">
	    <jsp:param name="active" value="0"/>
	</jsp:include>

    <!-- body start -->
    <section class="body">
        <div class="inner" style="display: flex;flex-direction: column;align-items: center;">

			<a class="link link--kukuri" href="#" data-letters="易管">易管</a>
            <h1 class="body-h1" style="margin-top: 5px;">信息管理系统</h1>

        </div>
    </section>

    <!-- footer start -->
	<%@ include file="foot.jsp" %>


</body>
</html>