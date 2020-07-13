package servlet.classmanage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import classes.action.ClassAction;

/**
 * Servlet implementation class ClassManageDeleteServlet
 */
@WebServlet("/ClassManageDeleteServlet")
public class ClassManageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassAction action = BeanFactory.getInstance(CommonUtils.ClassInfo.CLASSACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassManageDeleteServlet() {
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
		String ClassId = request.getParameter("cid");
		if (ClassId == null) {
			out.print("empty");
			return;
		}

		Integer cid = Integer.parseInt(ClassId);
		// 获取返回值
		int back = action.deleteClass(cid);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
