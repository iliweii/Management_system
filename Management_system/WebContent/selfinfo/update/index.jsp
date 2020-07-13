<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改个人信息 | 个人信息管理 | <%=systemName %></title>

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
    <script src="../../js/selfinfo.update.main.js"></script>

</head>
<body class="bg-light">
    
    <!-- header start -->
    <jsp:include page="../../head.jsp" flush="false">
	    <jsp:param name="active" value="4"/>
	</jsp:include>

    <!-- second nav start -->
    <jsp:include page="../../secondnav.jsp" flush="false">
    	<jsp:param name="second" value="selfinfo"/>
	    <jsp:param name="active" value="1"/>
	</jsp:include>

    <!-- body start -->
    <section class="body">
        <div class="inner">

			<c:if test="${cookie['identity'].value eq 'user'}">
            <!-- 主体部分：输入组 -->
            <div class="formbody border border-success rounded shadow px-5 py-5">

                <p class="text-left form-tip">个人信息管理->修改个人信息</p>

                <!-- 管理员登录名 -->
                <div class="input-group flex-nowrap">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="Tbuser">管理员登录名<span class="text-danger">*</span></span>
                    </div>
                    <input type="text" class="form-control" placeholder="管理员登录名" aria-label="管理员登录名" aria-describedby="Tbuser"  readonly>
                </div>

                <!-- 姓 名 -->
                <div class="input-group flex-nowrap">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="Tbname">姓 名<span class="text-danger">*</span></span>
                    </div>
                    <input type="text" class="form-control" placeholder="姓 名" aria-label="姓 名" aria-describedby="Tbname">
                </div>

                <!-- 密 码 -->
                <div class="input-group flex-nowrap">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="Tbpwd">密 码<span class="text-danger">*</span></span>
                    </div>
                    <input type="password" class="form-control" placeholder="密 码" aria-label="密 码" aria-describedby="Tbpwd">
                </div>

                <!-- 提交按钮 和 重置按钮 -->
                <input id="Loginbtn" class="btn btn-outline-primary mt-1" type="submit" value="保存" tbid="">
                <input id="Resetbtn" class="btn btn-outline-secondary mt-1 mx-2" type="reset" value="清空">

            </div>
            </c:if>
            
            <c:if test="${cookie['identity'].value eq 'student'}">
            <!-- 主体部分：输入组 -->
            <div class="formbody border border-success rounded shadow px-5 py-5">

                <p class="text-left form-tip">基本数据管理->学生信息维护 查看|修改</p>

                <!-- 学号 -->
                <div class="col-md-12 mb-3">
                    <label for="Sno">学号 <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="Sno" required readonly>
                    <div id="SnoFeedback" class="valid-feedback"></div>
                </div>
                
                <!-- 班级 -->
                <div class="col-md-12 mb-3">
				    <label for="Cno">班级 <span class="text-danger">*</span></label>
				    <select class="form-control" name="Cno" id="Cno" readonly disabled="disabled">
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
            </c:if>
            
            <c:if test="${cookie['identity'].value eq 'teacher'}">
            <!-- 主体部分：输入组 -->
            <div class="formbody border border-success rounded shadow px-5 py-5">

                <p class="text-left form-tip">基本数据管理->教师信息维护 查看|修改</p>

                <!-- 学号 -->
                <div class="col-md-12 mb-3">
                    <label for="Tno">教职工号 <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="Tno" required  readonly>
                    <div id="TnoFeedback" class="valid-feedback"></div>
                </div>

                <!-- 姓名 -->
                <div class="col-md-12 mb-3">
                    <label for="Tname">姓名 <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="Tname" required>
                    <div id="TnameFeedback" class="valid-feedback"></div>
                </div>

                <!-- 密码 -->
                <div class="col-md-12 mb-3">
                    <label for="Tpwd">密码 <span class="text-danger">*</span></label>
                    <input type="password" class="form-control" id="Tpwd" required>
                    <div id="TpwdFeedback" class="invalid-feedback"></div>
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
            </c:if>

        </div>
    </section>

    <!-- footer start -->
    <%@ include file="../../foot.jsp" %>


</body>
</html>