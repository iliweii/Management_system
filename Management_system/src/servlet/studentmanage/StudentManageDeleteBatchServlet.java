package servlet.studentmanage;

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
import student.action.StudentAction;
import student.entity.Student;

/**
 * Servlet implementation class StudentManageDeleteBatchServlet
 */
@WebServlet("/StudentManageDeleteBatchServlet")
public class StudentManageDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentAction action = BeanFactory.getInstance(CommonUtils.StudentInfo.STUDENTACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentManageDeleteBatchServlet() {
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
		String json = request.getParameter("sid");
		if (json == null) {
			out.print("empty");
			return;
		}

		Gson gson = new Gson();
		int[] gsonarr = gson.fromJson(json, int[].class);
		List<Student> students = new ArrayList<Student>();
		for (int i = 0; i < gsonarr.length; i++) {
			Student s = new Student();
			s.setSid(gsonarr[i]);
			students.add(s);
		}

		// 获取返回值
		int back = action.deleteStudentsBatch(students);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
