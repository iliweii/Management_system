package servlet.adminmanage;

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
import user.action.UserAction;
import user.entity.Tbuser;

/**
 * Servlet implementation class AdminManageCheckuserServlet
 */
@WebServlet("/AdminManageCheckuserServlet")
public class AdminManageCheckuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAction action = BeanFactory.getInstance(CommonUtils.UserInfo.USERACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminManageCheckuserServlet() {
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
		// 获取管理员登录名
		String tbuser = request.getParameter("tbuser");
		if (tbuser == null) {
			out.print("empty");
			return;
		}
		// 个人身份查询
		String self = request.getParameter("self");
		if ("self".equals(self)) {
			// 获取返回值
			Tbuser back = action.queryUserByUser(tbuser);
			// 将信息转化为json
			Gson gson = new Gson();
			String json = gson.toJson(back);
			// 输出信息
			out.print(json);
			return;
		}

		// 获取返回值
		Tbuser back = action.queryUserByUser(tbuser);
		if (back == null) {
			out.print("norepeat");
		} else {
			out.print("repeat");
		}
	}

}
