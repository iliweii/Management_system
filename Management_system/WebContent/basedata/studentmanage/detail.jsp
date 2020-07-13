<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!DOCTYPE html>
<html lang="zh-CN">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生信息维护 | 基本数据管理 | <%=systemName %></title>

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
    <script src="../../js/basedata.studentmanage.detail.js"></script>

</head>
<body class="bg-light">
    
    <!-- header start -->
    <jsp:include page="../../head.jsp" flush="false">
	    <jsp:param name="active" value="1"/>
	</jsp:include>

    <!-- second nav start -->
    <jsp:include page="../../secondnav.jsp" flush="false">
    	<jsp:param name="second" value="basedata"/>
	    <jsp:param name="active" value="2"/>
	</jsp:include>

    <!-- body start -->
    <section class="body">
        <div class="inner">

            <!-- 主体部分：输入组 -->
            <div class="formbody border border-success rounded shadow px-5 py-5">

                <p class="text-left form-tip">基本数据管理->学生信息维护 查看|修改</p>

                <!-- 学号 -->
                <div class="col-md-12 mb-3">
                    <label for="Sno">学号 <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="Sno" required>
                    <div id="SnoFeedback" class="valid-feedback"></div>
                </div>
                
                <!-- 班级 -->
                <div class="col-md-12 mb-3">
				    <label for="Cno">班级 <span class="text-danger">*</span></label>
				    <select class="form-control" name="Cno" id="Cno">
				    	<option>--请选择班级--</option>
				    </select>
				</div>

                <!-- 姓名 -->
                <div class="col-md-12 mb-3">
                    <label for="Sname">姓名 <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="Sname" required>
                    <div id="SnameFeedback" class="valid-feedback"></div>
                </div>

                <!-- 密码 -->
                <div class="col-md-12 mb-3">
                    <label for="Spwd">密码 <span class="text-danger">*</span></label>
                    <input type="password" class="form-control" id="Spwd" required>
                    <div id="SpwdFeedback" class="invalid-feedback"></div>
                </div>
                
               <!--  性别 -->
                <div class="col-md-4 mb-3">
                    <label>性别</label>
                    <div style="display:flex; align-item: center;">
                    	<span>男</span>
                    	<input type="radio" class="form-control" name="Sex" value="1">
                    	<span class="ml-3">女</span>
                    	<input type="radio" class="form-control" name="Sex" value="2">
                    </div>
                </div>
                
                <!-- 年龄-->
                <div class="col-md-12 mb-3">
                    <label for="Sage">年龄</label>
                    <input type="number" class="form-control" id="Sage">
                </div>
                
                <!-- 邮箱-->
                <div class="col-md-12 mb-3">
                    <label for="Email">邮箱</label>
                    <input type="email" class="form-control" id="Email">
                </div>
                
                <!-- 手机号-->
                <div class="col-md-12 mb-3">
                    <label for="Phone">手机号</label>
                    <input type="text" class="form-control" id="Phone">
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