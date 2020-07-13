package servlet.adminmanage;

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
import user.action.UserAction;
import user.entity.Tbuser;

/**
 * Servlet implementation class AdminManageDeleteBatchServlet
 */
@WebServlet("/AdminManageDeleteBatchServlet")
public class AdminManageDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAction action = BeanFactory.getInstance(CommonUtils.UserInfo.USERACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminManageDeleteBatchServlet() {
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
		// 获取管理员id
		String json = request.getParameter("tbid");
		if (json == null) {
			out.print("empty");
			return;
		}

		Gson gson = new Gson();
		int[] gsonarr = gson.fromJson(json, int[].class);
		List<Tbuser> user = new ArrayList<Tbuser>();
		for (int i = 0; i < gsonarr.length; i++) {
			Tbuser u = new Tbuser();
			u.setTbid(gsonarr[i]);
			user.add(u);
		}

		// 获取返回值
		int back = action.deleteUsersBatch(user);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
