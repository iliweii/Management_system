package servlet.studentresult;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import choose.action.ChooseAction;
import choose.entity.Choose;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

/**
 * Servlet implementation class StudentResultUpdateServlet
 */
@WebServlet("/StudentResultUpdateServlet")
public class StudentResultUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChooseAction action = BeanFactory.getInstance(CommonUtils.ChooseInfo.CHOOSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentResultUpdateServlet() {
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
		// 获取scid
		String scId = request.getParameter("scid");
		String Grade = request.getParameter("grade");
		if (scId == null) {
			out.print("empty");
			return;
		}

		Integer scid = Integer.parseInt(scId);
		Double grade = Double.parseDouble(Grade);
		// 获取返回值
		Choose choose = new Choose();
		choose.setScid(scid);
		choose.setGrade(grade);
		int back = action.updateChoose(choose);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
