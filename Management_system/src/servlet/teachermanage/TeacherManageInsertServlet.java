package servlet.teachermanage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import teacher.action.TeacherAction;
import teacher.entity.Teacher;

/**
 * Servlet implementation class TeacherManageInsertBatch
 */
@WebServlet("/TeacherManageInsertServlet")
public class TeacherManageInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherAction action = BeanFactory.getInstance(CommonUtils.TeacherInfo.TEACHERACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherManageInsertServlet() {
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
		// 获取所有教师数据
		String json = request.getParameter("json");
		if (json == null) {
			out.print("empty");
			return;
		}

		Gson gson = new Gson();
		// 将json数据转化为Teacher List
		List<Teacher> teachers = gson.fromJson(json, new TypeToken<List<Teacher>>() {
		}.getType());
		
		// 获取返回值
		int back = action.insertTeacherBatch(teachers);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
