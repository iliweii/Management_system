package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 将Session 对象中的username 对象移除
		request.getSession().removeAttribute("username");
		// 清除本次使用的Cookie
		Cookie cookie = new Cookie("identity", null);
		cookie.setMaxAge(0);
		Cookie aid = new Cookie("aid", null);
		aid.setMaxAge(0);
		Cookie uid = new Cookie("uid", null);
		uid.setMaxAge(0);
		Cookie sid = new Cookie("sid", null);
		sid.setMaxAge(0);
		Cookie tid = new Cookie("tid", null);
		tid.setMaxAge(0);
		response.addCookie(cookie);
		response.addCookie(aid);
		response.addCookie(uid);
		response.addCookie(sid);
		response.addCookie(tid);
		out.print("success");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
