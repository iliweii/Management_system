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
 * Servlet implementation class TeacherManageQueryServlet
 */
@WebServlet("/TeacherManageQueryServlet")
public class TeacherManageQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherAction action = BeanFactory.getInstance(CommonUtils.TeacherInfo.TEACHERACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherManageQueryServlet() {
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
		String TId = request.getParameter("tid");
		if (TId == null) {
			out.print("empty");
			return;
		}

		Integer tid = Integer.parseInt(TId);
		// 获取返回值
		Teacher back = action.queryTeacherById(tid);

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
