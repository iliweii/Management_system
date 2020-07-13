<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!DOCTYPE html>
<html lang="zh-CN">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员维护 | 基本数据管理 | <%=systemName %></title>

    <!-- link bootstrap4 css -->
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <!-- link style css -->
    <link rel="stylesheet" href="../../css/style.css">

    <!-- script bootstrap4 js -->
    <script src="../../js/jquery-3.5.1.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/popper.min.js"></script>
    <!-- script sweetalert js -->
    <script src="../../js/sweetalert.min.js"></script>
    <!-- script main js -->
    <script src="../../js/basedata.adminmanage.detail.js"></script>

</head>
<body class="bg-light">
    
    <!-- header start -->
    <jsp:include page="../../head.jsp" flush="false">
	    <jsp:param name="active" value="1"/>
	</jsp:include>

    <!-- second nav start -->
    <jsp:include page="../../secondnav.jsp" flush="false">
    	<jsp:param name="second" value="basedata"/>
	    <jsp:param name="active" value="1"/>
	</jsp:include>

    <!-- body start -->
    <section class="body">
        <div class="inner">

            <!-- 主体部分：输入组 -->
            <div class="formbody border border-success rounded shadow px-5 py-5">

                <p class="text-left form-tip">基本数据管理->管理员维护 查看|修改</p>

                <!-- 管理员登录名 -->
                <div class="col-md-12 mb-3">
                    <label for="Tbuser">管理员登录名 <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="Tbuser" required>
                    <div id="tbuserFeedback" class="valid-feedback"></div>
                </div>

                <!-- 管理员姓名 -->
                <div class="col-md-12 mb-3">
                    <label for="Tbname">管理员姓名 <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="Tbname" required>
                    <div id="tbnameFeedback" class="valid-feedback"></div>
                </div>

                <!-- 管理员密 码 -->
                <div class="col-md-12 mb-3">
                    <label for="Tbpwd">管理员密码 <span class="text-danger">*</span></label>
                    <input type="password" class="form-control" id="Tbpwd" required>
                    <div id="tbpwdFeedback" class="invalid-feedback"></div>
                </div>

                <!-- 提交按钮 和 重置按钮 -->
                <input id="Submitbtn" class="btn btn-outline-primary ml-3" type="submit" value="提交">
                <input id="Resetbtn" class="btn btn-outline-secondary mx-2" type="reset" value="重置">

            </div>

        </div>
    </section>

    <!-- footer start -->
    <%@ include file="../../foot.jsp" %>


</body>
</html>