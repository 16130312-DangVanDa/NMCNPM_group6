package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

		// USE CASE: Login by Account
		if (action.equals("account")) {
			// 2.2.6: lay du lieu gui tu client len
			String username = request.getParameter("Username");
			String pass = request.getParameter("Password");

			// 2.2.7. khoi tao hình thức đăng nhập là đăng nhập với account
			login = new LoginAccount();

			// 2.2.8. phuong thuc check(username, password)
			String result = login.account.check(username, pass);

			if (result != null) {
				// ********** đúng username, passowrd
				// 2.2.8.1: Respone("Đăng nhập thành công")
				out.println("Đăng nhập thành công");
				out.println(result);
			} else {
				// 2.2.8.2: luu request de gui lai client
				request.setAttribute("username", username);
				request.setAttribute("password", pass);
				request.setAttribute("login_fail", "Có cái gì đó không hợp lệ! Mời nhập lại");
				// chuyen ve trang login
				String url = "/login.jsp";
				RequestDispatcher re = getServletContext().getRequestDispatcher(url);
				re.forward(request, response);
			}
			// USE CASE: LOGIN BY FACEBOOK
		} else if (action.equals("facebook")) {
			login = new LoginFacebook();
			// code here
			// goi phuong thuc ra de thuc hien: login.facebook.method()

			// USE CASE: LOGIN BY EMAIL
		} else if (action.equals("gmail")) {
			login = new LoginGoogle();

			// code here
			// goi phuong thuc ra de thuc hien: login.gmail.method()

			// Use case: FORGOT PASSWORD
		} else if (action.equals("forgotpass")) {

			// Gửi yêu cầu là email người dùng nhập
			String email = request.getParameter("emailforgot");
			// 6.2.5.Gọi hình thức đăng nhập là Account
			login = new LoginAccount();
			// 6.2.6.hasExistEmail(String email)
			boolean exist = login.account.hasExistEmail(email);
			if (exist) {
				// 6.2.8. Thông báo gửi mail thành công
				out.println(
						"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
				out.println(
						"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
				out.println("<script type=\"text/javascript\">");
				out.println("	$(document).ready(function(){");
				out.println("swal ( \"SUCCESS\" ,  \"CHECK YOUR AND LOGIN AGIAN\" ,  \"success\" )");
				out.println("});");
				out.println("</script>");

			} else {
				// ALT

				// 6.3.1.1. Hiện thông báo Email không tồn tại
				out.println(
						"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
				out.println(
						"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
				out.println("<script type=\"text/javascript\">");
				out.println("	$(document).ready(function(){");
				out.println("swal ( \"ERROR\" ,  \"YOUR EMAIL DON'T EXIST !!!\" ,  \"error\" )");
				out.println("});");
				out.println("</script>");
			}
			// chuyen trang ve
			String url = "/login.jsp";
			RequestDispatcher re = getServletContext().getRequestDispatcher(url);
			re.include(request, response);

		} else {
			out.println("NOT FOUND ACTION");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
