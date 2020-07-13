package servlet.studentapply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import choose.action.ChooseAction;

/**
 * Servlet implementation Choose StudentApplyDeleteServlet
 */
@WebServlet("/StudentApplyDeleteServlet")
public class StudentApplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChooseAction action = BeanFactory.getInstance(CommonUtils.ChooseInfo.CHOOSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentApplyDeleteServlet() {
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
		// 获取课程id
		String ChooseId = request.getParameter("scid");
		if (ChooseId == null) {
			out.print("empty");
			return;
		}

		Integer scid = Integer.parseInt(ChooseId);
		// 获取返回值
		int back = action.deleteChoose(scid);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
