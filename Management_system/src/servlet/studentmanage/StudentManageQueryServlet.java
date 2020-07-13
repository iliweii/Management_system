package servlet.studentmanage;

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
import student.action.StudentAction;
import student.entity.Student;

/**
 * Servlet implementation class StudentManageQueryServlet
 */
@WebServlet("/StudentManageQueryServlet")
public class StudentManageQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentAction action = BeanFactory.getInstance(CommonUtils.StudentInfo.STUDENTACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentManageQueryServlet() {
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
		// 获取学生id
		String SId = request.getParameter("sid");
		if (SId == null) {
			out.print("empty");
			return;
		}

		Integer sid = Integer.parseInt(SId);
		// 获取返回值
		Student back = action.queryStudentById(sid);

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
