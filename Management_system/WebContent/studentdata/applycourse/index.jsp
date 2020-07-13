<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>申请新课程 | 基本信息管理 | <%=systemName %></title>

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
    <script src="../../js/studentdata.applycourse.main.js"></script>

</head>

<body class="bg-light">

    <!-- header start -->
    <jsp:include page="../../head.jsp" flush="false">
	    <jsp:param name="active" value="1"/>
	</jsp:include>

    <!-- second nav start -->
    <jsp:include page="../../secondnav.jsp" flush="false">
    	<jsp:param name="second" value="studentdata"/>
	    <jsp:param name="active" value="3"/>
	</jsp:include>

    <!-- body start -->
    <section class="body">
        <div class="inner">

            <!-- 主体部分：输入组 -->
            <div class="formbody border border-success rounded shadow px-5 py-5">

                <p class="text-left form-tip">基本信息管理->申请新课程</p>
                <p class="text-left form-tip">申请新课程：申请后请等待管理员审核</p>

                <div class="insert-group">

                    <div class="insert-item">

                        <div class="col-md-4 mb-3">
                            <label>课程 <span class="text-danger">*</span></label>
                            <select class="form-control Cono" name="Cono">
						    	<option value="">--请选择课程--</option>
						    </select>
						    <div class="valid-feedback ConoFeedback"></div>
                        </div>
                        
                    </div>

                </div>


                <!-- 提交按钮 -->
                <input id="Submitbtn" class="btn btn-primary ml-3" type="submit" value="申请">

            </div>

        </div>
    </section>

    <!-- footer start -->
    <%@ include file="../../foot.jsp" %>

</body>

</html>