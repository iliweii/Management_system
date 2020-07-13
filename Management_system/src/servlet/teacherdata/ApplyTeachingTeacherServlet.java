package servlet.teacherdata;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import teaching.action.TeachingAction;

/**
 * Servlet implementation class ApplyTeachingTeacherServlet
 */
@WebServlet("/ApplyTeachingTeacherServlet")
public class ApplyTeachingTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeachingAction action = BeanFactory.getInstance(CommonUtils.TeachingInfo.TEACHINGACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplyTeachingTeacherServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 获取授课数据
		String tid = request.getParameter("tid");
		String cno = request.getParameter("cono");
		if (tid == null || cno == null) {
			out.print("empty");
			return;
		}
		// 获取返回值
		int back = action.applyTeachingByTid(tid, cno);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
