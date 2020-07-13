package servlet.studentapply;

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
import choose.action.ChooseAction;
import choose.entity.Choose;
/**
 * Servlet implementation Choose StudentApplyQueryServlet
 */
@WebServlet("/StudentApplyQueryServlet")
public class StudentApplyQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChooseAction action = BeanFactory.getInstance(CommonUtils.ChooseInfo.CHOOSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentApplyQueryServlet() {
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
		// 获取选课id
		String SCId = request.getParameter("scid");
		if (SCId == null) {
			out.print("empty");
			return;
		}

		Integer scid = Integer.parseInt(SCId);
		// 获取返回值
		Choose back = action.queryChooseById(scid);

		if (back == null) {
			out.print("failed");
		} else {
			// 将信息转化为json
			Gson gson = new Gson();
			String json = gson.toJson(back);
			out.print(json);
		}
	}

}
