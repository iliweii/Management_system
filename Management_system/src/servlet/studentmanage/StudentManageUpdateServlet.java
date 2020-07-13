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
 * Servlet implementation class StudentManageUpdateServlet
 */
@WebServlet("/StudentManageUpdateServlet")
public class StudentManageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentAction action = BeanFactory.getInstance(CommonUtils.StudentInfo.STUDENTACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentManageUpdateServlet() {
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
		// 将json数据转化为Student对象
		Student student = gson.fromJson(json, Student.class);

		// 把student对象传给updateStudent方法
		int back = action.updateStudent(student);
		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
