package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginAbstract;
import model.LoginAccount;
import model.LoginFacebook;
import model.LoginGoogle;

@WebServlet("/Control_Login")
public class Control_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String action = null;
	private LoginAbstract login = null;

	public Control_Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		action = request.getParameter("action");
		PrintWriter out = response.getWriter();// su dung print writer de in ket qua

		if (action.equals("account")) {
			login = new LoginAccount();

		} else if (action.equals("facebook")) {
			login = new LoginFacebook();
			//code here
			// goi phuong thuc ra de thuc hien: login.facebook.method()

		} else if (action.equals("gmail")) {
			login = new LoginGoogle();
			// code here
			// goi phuong thuc ra de thuc hien: login.gmail.method()

		} else {
			out.println("NOT FOUND ACTION");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
