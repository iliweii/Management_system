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
 * Servlet implementation Course CourseManageUpdateServlet
 */
@WebServlet("/CourseManageUpdateServlet")
public class CourseManageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseAction action = BeanFactory.getInstance(CommonUtils.CourseInfo.COURSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseManageUpdateServlet() {
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
		String json = request.getParameter("json");

		if (json == null) {
			out.print("empty");
			return;
		}
		
		Gson gson = new Gson();
		// 将json数据转化为Course对象
		Course course = gson.fromJson(json, Course.class);

		// 把Course对象传给updateStudent方法
		int back = action.updateCourse(course);
		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
