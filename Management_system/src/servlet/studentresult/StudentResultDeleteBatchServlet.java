package servlet.studentresult;

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

import choose.action.ChooseAction;
import choose.entity.Choose;
import comm.CommonUtils;
import comm.properties.factory.BeanFactory;

/**
 * Servlet implementation class StudentResultDeleteBatchServlet
 */
@WebServlet("/StudentResultDeleteBatchServlet")
public class StudentResultDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChooseAction action = BeanFactory.getInstance(CommonUtils.ChooseInfo.CHOOSEACTION);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentResultDeleteBatchServlet() {
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
		String json = request.getParameter("scid");
		if (json == null) {
			out.print("empty");
			return;
		}

		Gson gson = new Gson();
		int[] gsonarr = gson.fromJson(json, int[].class);
		List<Choose> chooses = new ArrayList<Choose>();
		for (int i = 0; i < gsonarr.length; i++) {
			Choose sc = new Choose();
			sc.setScid(gsonarr[i]);
			chooses.add(sc);
		}

		// 获取返回值
		int back = action.updateChoosesBatch(chooses);

		if (back == 0) {
			out.print("failed");
		} else {
			out.print("success");
		}
	}

}
