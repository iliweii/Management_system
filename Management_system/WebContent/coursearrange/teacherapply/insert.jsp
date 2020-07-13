<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>>教师授课安排 | 课程安排管理 | <%=systemName %></title>

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
    <script src="../../js/coursearrage.teacherapply.insert.js"></script>

</head>

<body class="bg-light">

    <!-- header start -->
    <jsp:include page="../../head.jsp" flush="false">
	    <jsp:param name="active" value="2"/>
	</jsp:include>

    <!-- second nav start -->
    <jsp:include page="../../secondnav.jsp" flush="false">
    	<jsp:param name="second" value="coursearrange"/>
	    <jsp:param name="active" value="2"/>
	</jsp:include>

    <!-- body start -->
    <section class="body">
        <div class="inner">

            <!-- 主体部分：输入组 -->
            <div class="formbody border border-success rounded shadow px-5 py-5">

                <p class="text-left form-tip">课程安排管理->教师授课安排 批量添加</p>

                <div class="insert-group">

                    <div class="insert-item">
                        <div class="col-md-5 mb-3">
                            <label>教师 <span class="text-danger">*</span></label>
                            <select class="form-control Tno" name="Tno">
						    	<option>--请选择教师--</option>
						    </select>
						    <div class="valid-feedback TnoFeedback"></div>
                        </div>

                        <div class="col-md-5 mb-3">
                            <label>课程 <span class="text-danger">*</span></label>
                            <select class="form-control Cno" name="Cno">
						    	<option>--请选择课程--</option>
						    </select>
						    <div class="valid-feedback CnoFeedback"></div>
                        </div>

                        <svg t="1592634353459" class="icon mx-3 Cutbtn" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="2123" width="30" height="30" style="cursor: pointer;">
                            <path
                                d="M512 1009.6C236.8 1009.6 14.4 785.6 14.4 512S236.8 14.4 512 14.4s497.6 224 497.6 497.6S787.2 1009.6 512 1009.6z m0-932.8C272 76.8 76.8 272 76.8 512S272 947.2 512 947.2 947.2 752 947.2 512 752 76.8 512 76.8z"
                                fill="#43484D" p-id="2124"></path>
                            <path
                                d="M705.6 556.8H313.6c-17.6 0-32-14.4-32-32s14.4-32 32-32h393.6c17.6 0 32 14.4 32 32s-14.4 32-33.6 32z"
                                fill="#229BFF" p-id="2125"></path>
                        </svg>
                    </div>

                </div>


                <!-- 提交按钮 和 +按钮 -->
                <input id="Submitbtn" class="btn btn-primary ml-3" type="submit" value="批量提交">
                <svg t="1592634101213" class="icon Addbtn mx-3" viewBox="0 0 1024 1024" version="1.1"
                    xmlns="http://www.w3.org/2000/svg" p-id="1998" width="36" height="36" style="cursor: pointer;">
                    <path
                        d="M515.2 987.2c-267.2 0-486.4-217.6-486.4-486.4C30.4 233.6 248 16 515.2 16s486.4 217.6 486.4 486.4c0 267.2-217.6 484.8-486.4 484.8z m0-908.8C283.2 78.4 92.8 268.8 92.8 502.4s190.4 422.4 422.4 422.4 422.4-190.4 422.4-422.4c1.6-233.6-188.8-424-422.4-424z"
                        fill="#43484D" p-id="1999"></path>
                    <path
                        d="M521.6 737.6c-17.6 0-32-14.4-32-32V310.4c0-17.6 14.4-32 32-32s32 14.4 32 32v395.2c0 17.6-14.4 32-32 32z"
                        fill="#229BFF" p-id="2000"></path>
                    <path
                        d="M713.6 545.6H331.2c-17.6 0-32-14.4-32-32s14.4-32 32-32h382.4c17.6 0 32 14.4 32 32s-14.4 32-32 32z"
                        fill="#229BFF" p-id="2001"></path>
                </svg>

            </div>

        </div>
    </section>

    <!-- footer start -->
    <%@ include file="../../foot.jsp" %>


</body>

</html>