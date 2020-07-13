package servlet.studentdata;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import choose.action.ChooseAction;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

/**
 * Servlet implementation class ApplyCourseStudentServlet
 */
@WebServlet("/ApplyCourseStudentServlet")
public class ApplyCourseStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChooseAction action = BeanFactory.getInstance(CommonUtils.ChooseInfo.CHOOSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplyCourseStudentServlet() {
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
		// 获取选课数据
		String sid = request.getParameter("sid");
		String cno = request.getParameter("cono");
		if (sid == null || cno == null) {
			out.print("empty");
			return;
		}
		// 获取返回值
		int back = action.applyChooseBySid(sid, cno);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
