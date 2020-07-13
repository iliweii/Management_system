package servlet.coursemanage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation Course CourseManageDeleteBatchServlet
 */
@WebServlet("/CourseManageDeleteBatchServlet")
public class CourseManageDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseAction action = BeanFactory.getInstance(CommonUtils.CourseInfo.COURSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseManageDeleteBatchServlet() {
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
		String json = request.getParameter("cid");
		if (json == null) {
			out.print("empty");
			return;
		}

		Gson gson = new Gson();
		int[] gsonarr = gson.fromJson(json, int[].class);
		List<Course> courses = new ArrayList<Course>();
		for (int i = 0; i < gsonarr.length; i++) {
			Course c = new Course();
			c.setCid(gsonarr[i]);
			courses.add(c);
		}

		// 获取返回值
		int back = action.deleteCoursesBatch(courses);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
