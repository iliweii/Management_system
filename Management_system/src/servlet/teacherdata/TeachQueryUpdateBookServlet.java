package servlet.teacherdata;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.CommonUtils;
import comm.properties.factory.BeanFactory;
import teaching.action.TeachingAction;
import teaching.entity.Teaching;

/**
 * Servlet implementation class TeachQueryUpdateBookServlet
 */
@WebServlet("/TeachQueryUpdateBookServlet")
public class TeachQueryUpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeachingAction action = BeanFactory.getInstance(CommonUtils.TeachingInfo.TEACHINGACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeachQueryUpdateBookServlet() {
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
		// 获取tcid
		String tcId = request.getParameter("tcid");
		String book = request.getParameter("book");
		if (tcId == null) {
			out.print("empty");
			return;
		}

		Integer tcid = Integer.parseInt(tcId);
		// 获取返回值
		Teaching teaching = new Teaching();
		teaching.setTcid(tcid);
		teaching.setBook(book);
		int back = action.updateTeaching(teaching);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
