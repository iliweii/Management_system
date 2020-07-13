package servlet.teachermanage;

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
import teacher.action.TeacherAction;
import teacher.entity.Teacher;

/**
 * Servlet implementation class TeacherManageDeleteBatchServlet
 */
@WebServlet("/TeacherManageDeleteBatchServlet")
public class TeacherManageDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherAction action = BeanFactory.getInstance(CommonUtils.TeacherInfo.TEACHERACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherManageDeleteBatchServlet() {
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
		// 获取教师id
		String json = request.getParameter("tid");
		if (json == null) {
			out.print("empty");
			return;
		}

		Gson gson = new Gson();
		int[] gsonarr = gson.fromJson(json, int[].class);
		List<Teacher> teachers = new ArrayList<Teacher>();
		for (int i = 0; i < gsonarr.length; i++) {
			Teacher t = new Teacher();
			t.setTid(gsonarr[i]);
			teachers.add(t);
		}

		// 获取返回值
		int back = action.deleteTeachersBatch(teachers);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
