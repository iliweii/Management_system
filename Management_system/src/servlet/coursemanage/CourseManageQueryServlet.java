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
 * Servlet implementation Course CourseManageQueryServlet
 */
@WebServlet("/CourseManageQueryServlet")
public class CourseManageQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseAction action = BeanFactory.getInstance(CommonUtils.CourseInfo.COURSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseManageQueryServlet() {
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
		// 获取班级id
		String CId = request.getParameter("cid");
		if (CId == null) {
			out.print("empty");
			return;
		}

		Integer cid = Integer.parseInt(CId);
		// 获取返回值
		Course back = action.queryCourseById(cid);

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
