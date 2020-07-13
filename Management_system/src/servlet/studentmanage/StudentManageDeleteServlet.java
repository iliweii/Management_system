package servlet.studentmanage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import student.action.StudentAction;

/**
 * Servlet implementation class StudentManageDeleteServlet
 */
@WebServlet("/StudentManageDeleteServlet")
public class StudentManageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentAction action = BeanFactory.getInstance(CommonUtils.StudentInfo.STUDENTACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentManageDeleteServlet() {
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
		String studentId = request.getParameter("sid");
		if (studentId == null) {
			out.print("empty");
			return;
		}

		Integer sid = Integer.parseInt(studentId);
		// 获取返回值
		int back = action.deleteStudent(sid);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
