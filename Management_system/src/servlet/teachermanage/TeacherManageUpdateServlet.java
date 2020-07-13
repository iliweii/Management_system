package servlet.teachermanage;

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
import teacher.action.TeacherAction;
import teacher.entity.Teacher;

/**
 * Servlet implementation class TeacherManageUpdateServlet
 */
@WebServlet("/TeacherManageUpdateServlet")
public class TeacherManageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherAction action = BeanFactory.getInstance(CommonUtils.TeacherInfo.TEACHERACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherManageUpdateServlet() {
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
		Teacher teacher = gson.fromJson(json, Teacher.class);

		// 把student对象传给updateStudent方法
		int back = action.updateTeacher(teacher);
		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
