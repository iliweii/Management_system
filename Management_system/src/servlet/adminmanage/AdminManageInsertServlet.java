package servlet.adminmanage;

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
import user.action.UserAction;
import user.entity.Tbuser;

/**
 * Servlet implementation class AdminManageInsertServlet
 */
@WebServlet("/AdminManageInsertServlet")
public class AdminManageInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAction action = BeanFactory.getInstance(CommonUtils.UserInfo.USERACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminManageInsertServlet() {
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
		// 获取所有管理员数据
		String json = request.getParameter("json");
		if (json == null) {
			out.print("empty");
			return;
		}

		Gson gson = new Gson();
		// 将json数据转化为Tbuser List
		List<Tbuser> user = gson.fromJson(json, new TypeToken<List<Tbuser>>() {
		}.getType());

		// 获取返回值
		int back = action.insertUsersBatch(user);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
