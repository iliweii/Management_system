package servlet.coursemanage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import course.action.CourseAction;
import course.entity.Course;

/**
 * Servlet implementation Course CourseManageQueryByCnoServlet
 */
@WebServlet("/CourseManageQueryByCnoServlet")
public class CourseManageQueryByCnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseAction action = BeanFactory.getInstance(CommonUtils.CourseInfo.COURSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseManageQueryByCnoServlet() {
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
		// 获取课程编号
		String Cno = request.getParameter("cno");
		if (Cno == null) {
			out.print("empty");
			return;
		}

		// 获取返回值
		Course back = action.queryCourseByCno(Cno);

		if (back == null) {
			out.print("failed");
		} else {
			// 将信息转化为json
			Gson gson = new Gson();
			String json = gson.toJson(back);
			out.print(json);
		}
	}

}
