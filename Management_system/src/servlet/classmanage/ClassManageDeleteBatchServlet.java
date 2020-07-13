package servlet.classmanage;

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
import classes.action.ClassAction;
import classes.entity.Class;

/**
 * Servlet implementation class ClassManageDeleteBatchServlet
 */
@WebServlet("/ClassManageDeleteBatchServlet")
public class ClassManageDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassAction action = BeanFactory.getInstance(CommonUtils.ClassInfo.CLASSACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassManageDeleteBatchServlet() {
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
		// 获取班级id
		String json = request.getParameter("cid");
		if (json == null) {
			out.print("empty");
			return;
		}

		Gson gson = new Gson();
		int[] gsonarr = gson.fromJson(json, int[].class);
		List<Class> Classes = new ArrayList<Class>();
		for (int i = 0; i < gsonarr.length; i++) {
			Class c = new Class();
			c.setCid(gsonarr[i]);
			Classes.add(c);
		}

		// 获取返回值
		int back = action.deleteClasssBatch(Classes);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
