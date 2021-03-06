<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生成绩管理 | 学生成绩管理 | <%=systemName %></title>

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
    <script src="../../js/studentscore.studentresult.main.js"></script>

</head>

<body class="bg-light">

    <!-- header start -->
    <jsp:include page="../../head.jsp" flush="false">
	    <jsp:param name="active" value="3"/>
	</jsp:include>

    <!-- second nav start -->
    <jsp:include page="../../secondnav.jsp" flush="false">
    	<jsp:param name="second" value="studentscore"/>
	    <jsp:param name="active" value="1"/>
	</jsp:include>

    <!-- body start -->
    <section class="body">
        <div class="inner">

            <!-- 主体部分 -->
            <div class="formbody border border-success rounded shadow px-5 py-5">

                <p class="text-left form-tip">学生成绩管理->学生成绩管理</p>

                <!-- 查询栏 -->
                <div class="form-inline">
                    <input class="Searchtext form-control mr-sm-2" type="search" placeholder="学号、课程号" aria-label="学生成绩管理">
                    <button class="Searchbtn btn btn-outline-success my-2 my-sm-0" type="submit">查询</button>
                    <div class="d-flex flex-row ml-4">
                        <label class="pr-2">按班级查询 </label>
                        <select class="form-control Clno" name="Clno">
					    	<option value="">--请选择班级--</option>
					    </select>
                     </div>
                     <div class="d-flex flex-row ml-4">
                        <label class="pr-2">按课程查询 </label>
                        <select class="form-control Cono" name="Cono">
					    	<option value="">--请选择课程--</option>
					    </select>
                     </div>
                     <button class="Clearbtn btn btn-outline-info my-2 my-sm-0" type="submit">清空</button>
                </div>

                <!-- 数据栏 -->
                <table class="table table-bordered table-hover mt-3">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">课程编号</th>
                            <th scope="col">课程名称</th>
                            <th scope="col">学号</th>
                            <th scope="col">姓名</th>
                            <th scope="col">班级</th>
                            <th scope="col">任课教师</th>
                            <th scope="col">成绩</th>
                            <th scope="col">操作</th>
                        </tr>
                    </thead>
                    <tbody class="tbody">
                        <!-- table tbody start -->
                        
                    </tbody>
                </table>

            </div>

        </div>
    </section>

    <!-- footer start -->
    <%@ include file="../../foot.jsp" %>

</body>

</html>