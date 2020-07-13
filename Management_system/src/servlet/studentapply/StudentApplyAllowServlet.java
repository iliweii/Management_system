package servlet.studentapply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import choose.action.ChooseAction;
import choose.entity.Choose;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

/**
 * Servlet implementation class StudentApplyAllowServlet
 */
@WebServlet("/StudentApplyAllowServlet")
public class StudentApplyAllowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChooseAction action = BeanFactory.getInstance(CommonUtils.ChooseInfo.CHOOSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentApplyAllowServlet() {
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
		// 将json数据转化为Choose对象
		Choose choose = gson.fromJson(json, Choose.class);

		// 把Choose对象传给allowStudent方法
		int back = action.allowChoose(choose);
		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
