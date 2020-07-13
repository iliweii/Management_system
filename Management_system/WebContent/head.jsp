<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
	String active = request.getParameter("active");
	int acti = 0;
	if (active != null) {
		acti = Integer.parseInt(active);
	}
	String array[] = new String[10];
	array[acti] = "active";
%>

<%! 
	String project = "Management-system";
	String systemName = "易管 信息管理系统";
%>
    
<header class="head">
	<div class="inner">
	
		<!-- 头部信息栏 -->
		<div class="headinfo text-body">
			<p>
				当前时间：<span class="nowtime text-primary mr-2">XX-XX-XX xx:xx:xx</span> 
				<iframe frameborder="0" scrolling="no" hspace="0" width="230" height="20" src="https://i.tianqi.com/?c=code&a=getcode&id=26&h=60&w=610"></iframe>
			</p>
			<c:if test="${cookie['identity'].value ne 'admin'}">
			<p class="mr-5">欢迎你，
				<c:if test="${cookie['identity'].value eq 'user'}">管理员</c:if>
				<c:if test="${cookie['identity'].value eq 'student'}">学生</c:if>
				<c:if test="${cookie['identity'].value eq 'teacher'}">教师</c:if>
			：<span class="username text-info">XXXX</span></p>
			</c:if>
		</div>
	
		<!-- 头部导航栏 -->
		<nav class="head-nav nav nav-pills nav-fill bg-light">
		    <a class="nav-item nav-link btn-success <%=array[0] %>" href="/<%=project %>/index.jsp">首页</a>
		    <c:if test="${(cookie['identity'].value eq 'user') || (cookie['identity'].value eq 'admin')}">
		    <a class="nav-item nav-link btn-success <%=array[1] %>" href="/<%=project %>/basedata/index.jsp">基本数据管理</a>
		    <a class="nav-item nav-link btn-success <%=array[2] %>" href="/<%=project %>/coursearrange/index.jsp">课程安排管理</a>
		    <a class="nav-item nav-link btn-success <%=array[3] %>" href="/<%=project %>/studentscore/index.jsp">学生成绩管理</a>
		    <a class="nav-item nav-link btn-success <%=array[4] %>" href="/<%=project %>/selfinfo/index.jsp">个人信息管理</a>
		    </c:if>
		    <c:if test="${cookie['identity'].value eq 'student'}">
		    <a class="nav-item nav-link btn-success <%=array[1] %>" href="/<%=project %>/studentdata/index.jsp">基本信息管理</a>
		    <a class="nav-item nav-link btn-success <%=array[4] %>" href="/<%=project %>/selfinfo/index.jsp">个人信息管理</a>
		    </c:if>
		    <c:if test="${cookie['identity'].value eq 'teacher'}">
		    <a class="nav-item nav-link btn-success <%=array[1] %>" href="/<%=project %>/teacherdata/index.jsp">基本信息管理</a>
		    <a class="nav-item nav-link btn-success <%=array[4] %>" href="/<%=project %>/selfinfo/index.jsp">个人信息管理</a>
		    </c:if>
		</nav>
		
		
		<!-- 导航栏下方标识线 -->
		<div class="head-line"></div>
		
		<script type="text/javascript">
			var project = "/Management-system";
			// 刷新当前时间
		    NowTime();
		    setInterval("NowTime()", 1000);
		    // 获取管理员uid
		    var uid = getCookie("uid");
		 	// 获取学生sid
		    var sid = getCookie("sid");
		 	// 获取教师tid
		    var tid = getCookie("tid");
		 	if (uid) {
		 		$.ajax({
			 		type: "post",
			 		url: project + "/AdminManageQueryServlet",
			 		data: {
			 			tbid: uid
			 		},
			 		success: function(e) {
			 			var user = JSON.parse(e);
			 			$(".username").text(user['tbname']);
			 		}
			 	});
		 	} else if (sid) {
		 		$.ajax({
			 		type: "post",
			 		url: project + "/StudentManageQueryServlet",
			 		data: {
			 			sid: sid
			 		},
			 		success: function(e) {
			 			var stu = JSON.parse(e);
			 			$(".username").text(stu['sname']);
			 		}
			 	});
		 	} else if (tid) {
		 		$.ajax({
			 		type: "post",
			 		url: project + "/TeacherManageQueryServlet",
			 		data: {
			 			tid: tid
			 		},
			 		success: function(e) {
			 			var tea = JSON.parse(e);
			 			$(".username").text(tea['tname']);
			 		}
			 	});
		 	}
	     	// 获取当前时间并输出
	        function NowTime() {
	            //获取年月日
	            var time = new Date();
	            var year = time.getFullYear();
	            var month = time.getMonth();
	            var day = time.getDate();

	            //获取时分秒
	            var h = time.getHours();
	            var m = time.getMinutes();
	            var s = time.getSeconds();

	            //检查是否小于10
	            h = check(h);
	            m = check(m);
	            s = check(s);
	            $('.nowtime').text(year + "年" + month + "月" + day + "日  " + h + ":" + m + ":" + s);
	        }
	     	// 时间数字小于10，则在之前加个“0”补位。
	        function check(i) {
	            var num;
	            i < 10 ? num = "0" + i : num = i;
	            return num;
	        }
	      	//读取cookies
	        function getCookie(name) {
	            var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

	            if (arr = document.cookie.match(reg))

	                return unescape(arr[2]);
	            else
	                return null;
	        }
		</script>
	
	</div>
</header>