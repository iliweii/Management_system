package servlet.classmanage;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ClassManageSearchServlet
 */
@WebServlet("/ClassManageSearchServlet")
public class ClassManageSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassAction action = BeanFactory.getInstance(CommonUtils.ClassInfo.CLASSACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassManageSearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 获取用户输入的搜索关键词
		String q = request.getParameter("q");
		q = java.net.URLDecoder.decode(q,"utf-8");
		// 获取返回值
		List<Class> list = action.searchByKeyword(q);
		
		// 将信息集合转化为数组
		Gson gson = new Gson();
		String json = gson.toJson(list);
		// 输出信息
		out.print(json);
	}

}
