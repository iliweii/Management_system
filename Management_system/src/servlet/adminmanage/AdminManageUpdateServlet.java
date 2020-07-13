package servlet.adminmanage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import user.action.UserAction;
import user.entity.Tbuser;

/**
 * Servlet implementation class AdminManageUpdateServlet
 */
@WebServlet("/AdminManageUpdateServlet")
public class AdminManageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAction action = BeanFactory.getInstance(CommonUtils.UserInfo.USERACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminManageUpdateServlet() {
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
		String tbuser = request.getParameter("tbuser");
		String tbname = request.getParameter("tbname");
		String tbpwd = request.getParameter("tbpwd");
		String tbid = request.getParameter("tbid");

		if (tbuser == null || tbname == null || tbpwd == null || tbid == null) {
			out.print("empty");
			return;
		}
		int id = Integer.parseInt(tbid);

		Tbuser User = new Tbuser();
		// 把用户名、密码封装在Tbuser对象中
		User.setTbuser(tbuser);
		User.setTbname(tbname);
		User.setTbpwd(tbpwd);
		User.setTbid(id);
		// 把user对象传给updateUser方法
		int back = action.updateUser(User);
		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
