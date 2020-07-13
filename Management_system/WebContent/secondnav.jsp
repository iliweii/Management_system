<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String active = request.getParameter("active");
	String second = request.getParameter("second");
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

<section class="second">
	<div class="inner">

		<!-- 次级导航栏 -->
		<nav class="second-nav nav-tabs">
			<ul class="nav">
				<%  // 基本数据管理 次级导航栏
					if ("basedata".equals(second)) {
				%>
				<c:if test="${cookie['identity'].value eq 'admin'}">
				<li class="nav-item"><a
					class="nav-link text-success <%=array[1]%>"
					href="/<%=project%>/<%=second%>/adminmanage/index.jsp">管理员维护</a></li>
				</c:if>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[2]%>"
					href="/<%=project%>/<%=second%>/studentmanage/index.jsp">学生信息维护</a></li>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[3]%>"
					href="/<%=project%>/<%=second%>/teachermanage/index.jsp">教师信息维护</a></li>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[4]%>"
					href="/<%=project%>/<%=second%>/classmanage/index.jsp">班级信息维护</a></li>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[5]%>"
					href="/<%=project%>/<%=second%>/coursemanage/index.jsp">课程信息维护</a></li>
				<%  // 课程安排管理 次级导航栏
					} else if ("coursearrange".equals(second)) {
				%>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[1]%>"
					href="/<%=project%>/<%=second%>/studentapply/index.jsp">学生选课安排</a></li>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[2]%>"
					href="/<%=project%>/<%=second%>/teacherapply/index.jsp">教师授课安排</a></li>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[3]%>"
					href="/<%=project%>/<%=second%>/publicapply/index.jsp">公选课安排</a></li>
				<%  // 学生成绩管理 次级导航栏
					} else if ("studentscore".equals(second)) {
				%>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[1]%>"
					href="/<%=project%>/<%=second%>/studentresult/index.jsp">学生成绩管理</a></li>
				<%  // 个人信息管理 次级导航栏
					} else if ("selfinfo".equals(second)) {
				%>
				<c:if test="${cookie['identity'].value ne 'admin'}">
				<li class="nav-item"><a class="nav-link text-success <%=array[1]%>"
					href="/<%=project%>/<%=second%>/update/index.jsp">修改个人信息</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link text-danger" href="#"
					id="logoutBtn">退出登录</a></li>
				<%  // 学生:基本信息管理 次级导航栏
					} else if ("studentdata".equals(second)) {
				%>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[1]%>"
					href="/<%=project%>/<%=second%>/resultquery/index.jsp">我的成绩查询</a></li>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[2]%>"
					href="/<%=project%>/<%=second%>/coursequery/index.jsp">我的选课查询</a></li>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[3]%>"
					href="/<%=project%>/<%=second%>/applycourse/index.jsp">申请新课程</a></li>
				<%  // 教师:基本信息管理 次级导航栏
					} else if ("teacherdata".equals(second)) {
				%>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[1]%>"
					href="/<%=project%>/<%=second%>/resultmanage/index.jsp">学生成绩管理</a></li>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[2]%>"
					href="/<%=project%>/<%=second%>/teachquery/index.jsp">我的授课查询</a></li>
				<li class="nav-item"><a
					class="nav-link text-success <%=array[3]%>"
					href="/<%=project%>/<%=second%>/teachapply/index.jsp">申请新授课</a></li>
				<%
					}
				%>
			</ul>
		</nav>

	</div>
</section>