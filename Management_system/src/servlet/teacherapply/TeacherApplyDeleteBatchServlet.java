package servlet.teacherapply;

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

import teaching.action.TeachingAction;
import teaching.entity.Teaching;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

/**
 * Servlet implementation Teaching TeacherApplyDeleteBatchServlet
 */
@WebServlet("/TeacherApplyDeleteBatchServlet")
public class TeacherApplyDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeachingAction action = BeanFactory.getInstance(CommonUtils.TeachingInfo.TEACHINGACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherApplyDeleteBatchServlet() {
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
		// 获取授课id
		String json = request.getParameter("tcid");
		if (json == null) {
			out.print("empty");
			return;
		}

		Gson gson = new Gson();
		int[] gsonarr = gson.fromJson(json, int[].class);
		List<Teaching> teachings = new ArrayList<Teaching>();
		for (int i = 0; i < gsonarr.length; i++) {
			Teaching t = new Teaching();
			t.setTcid(gsonarr[i]);
			teachings.add(t);
		}

		// 获取返回值
		int back = action.deleteTeachingsBatch(teachings);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
