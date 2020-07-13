package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import admin.action.AdminAction;
import admin.entity.Admin;
import student.action.StudentAction;
import student.entity.Student;
import teacher.action.TeacherAction;
import teacher.entity.Teacher;
import user.action.UserAction;
import user.entity.Tbuser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminAction adminAction = BeanFactory.getInstance(CommonUtils.AdminInfo.ADMINACTION);
	private UserAction userAction = BeanFactory.getInstance(CommonUtils.UserInfo.USERACTION);
	private StudentAction studentAction = BeanFactory.getInstance(CommonUtils.StudentInfo.STUDENTACTION);
	private TeacherAction teacherAction = BeanFactory.getInstance(CommonUtils.TeacherInfo.TEACHERACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// 获取前端传值
		String Name = request.getParameter("tbuser");
		String Pwd = request.getParameter("tbpwd");
		String Identity = request.getParameter("identity");

		// 判断是否为非法访问
		if (Name == null || Pwd == null || Identity == null) {
			out.print("empty");
			return;
		}

		// 高级管理员身份登录
		if ("admin".equals(Identity)) {
			Admin admin = new Admin();
			// 把用户名、密码封装在Admin对象中
			admin.setAname(Name);
			admin.setApwd(Pwd);
			// 把Admin对象传给login方法
			Admin a = adminAction.login(admin);
			if (a != null) {
				// 存储在Session中
				session.setAttribute("username", "admin" + Name);
				response.addCookie(new Cookie("identity", "admin".toString()));
				response.addCookie(new Cookie("aid", a.getAid().toString()));
				out.print("success");
			} else {
				out.print("failed");
			}
		}
		// 管理员身份登录
		else if ("user".equals(Identity)) {
			Tbuser User = new Tbuser();
			// 把用户名、密码封装在Tbuser对象中
			User.setTbuser(Name);
			User.setTbpwd(Pwd);
			// 把user对象传给login方法
			Tbuser u = userAction.login(User);
			if (u != null) {
				session.setAttribute("username", "user" + Name);
				response.addCookie(new Cookie("identity", "user".toString()));
				response.addCookie(new Cookie("uid", u.getTbid().toString()));
				out.print("success");
			} else {
				out.print("failed");
			}
		}
		// 学生身份登录
		else if ("student".equals(Identity)) {
			Student student = new Student();
			// 把学号、密码封装在Student对象中
			student.setSno(Name);
			student.setSpwd(Pwd);
			// 把student对象传给login方法
			Student s = studentAction.login(student);
			if (s != null) {
				session.setAttribute("username", "student" + Name);
				response.addCookie(new Cookie("identity", "student".toString()));
				response.addCookie(new Cookie("sid", s.getSid().toString()));
				out.print("success");
			} else {
				out.print("failed");
			}
		}
		// 教师身份登录
		else if ("teacher".equals(Identity)) {
			Teacher teacher = new Teacher();
			// 把教师号、密码封装在Teacher对象中
			teacher.setTno(Name);
			teacher.setTpwd(Pwd);
			// 把student对象传给login方法
			Teacher t = teacherAction.login(teacher);
			if (t != null) {
				session.setAttribute("username", "teacher" + Name);
				response.addCookie(new Cookie("identity", "teacher".toString()));
				response.addCookie(new Cookie("tid", t.getTid().toString()));
				out.print("success");
			} else {
				out.print("failed");
			}
		}
		// 无法识别的身份
		else {
			out.print("failed");
		}
	}

}
