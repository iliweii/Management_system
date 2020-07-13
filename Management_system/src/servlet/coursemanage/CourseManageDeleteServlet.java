package servlet.coursemanage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import course.action.CourseAction;

/**
 * Servlet implementation Course CourseManageDeleteServlet
 */
@WebServlet("/CourseManageDeleteServlet")
public class CourseManageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseAction action = BeanFactory.getInstance(CommonUtils.CourseInfo.COURSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseManageDeleteServlet() {
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
		// 获取课程id
		String CourseId = request.getParameter("cid");
		if (CourseId == null) {
			out.print("empty");
			return;
		}

		Integer cid = Integer.parseInt(CourseId);
		// 获取返回值
		int back = action.deleteCourse(cid);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
