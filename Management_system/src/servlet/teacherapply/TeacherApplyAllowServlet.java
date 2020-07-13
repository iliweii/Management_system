package servlet.teacherapply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import teaching.action.TeachingAction;
import teaching.entity.Teaching;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

/**
 * Servlet implementation class TeacherApplyAllowServlet
 */
@WebServlet("/TeacherApplyAllowServlet")
public class TeacherApplyAllowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeachingAction action = BeanFactory.getInstance(CommonUtils.TeachingInfo.TEACHINGACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherApplyAllowServlet() {
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
		// 将json数据转化为Teaching对象
		Teaching teaching = gson.fromJson(json, Teaching.class);

		// 把Teaching对象传给allowStudent方法
		int back = action.allowTeaching(teaching);
		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
