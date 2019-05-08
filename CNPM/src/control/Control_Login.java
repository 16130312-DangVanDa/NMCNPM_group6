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

import com.restfb.types.User;

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

			// 2.2.7. khoi tao hÃ¬nh thá»©c Ä‘Äƒng nháº­p lÃ  Ä‘Äƒng nháº­p vá»›i account
			login = new LoginAccount();
		
			// 2.2.8. phuong thuc check(username, password)
			String result = login.account.check(username, pass);

			if (result != null) {
				// ********** Ä‘Ãºng username, passowrd
				// 2.2.8.1: Respone("Ä�Äƒng nháº­p thÃ nh cÃ´ng")
				out.println("Ä�Äƒng nháº­p thÃ nh cÃ´ng");
				out.println(result);
			} else {
				// 2.2.8.2: luu request de gui lai client
				request.setAttribute("username", username);
				request.setAttribute("password", pass);
				request.setAttribute("login_fail", "CÃ³ cÃ¡i gÃ¬ Ä‘Ã³ khÃ´ng há»£p lá»‡! Má»�i nháº­p láº¡i");
				// chuyen ve trang login
				String url = "/login.jsp";
				RequestDispatcher re = getServletContext().getRequestDispatcher(url);
				re.forward(request, response);
			}
			
		}
		// USE CASE: LOGIN BY FACEBOOK
		// 3.2.2 Hệ thống gửi yêu cầu xác thực với Facebook
		// 3.2.3 Hệ thống hiện form yêu cầu đăng nhập tài khoản
		// 3.2.4 Người dùng đăng nhập.
		// 3.2.5 Hệ thống chứng thực Facebook yêu cầu xác nhận các quyền truy cập thông tin tài khoản.
		// 3.2.6 Người dùng cấp quyền cho ứng dụng cá nhân đó.
		// 3.2.7 Facebook chuyển mã code và action đến hệ thống 

		// 3.2.8  Kiểm tra action và lấy parameter code về nếu action là facebook !
		else if (action.equals("facebook")) {
			String code = request.getParameter("code");
			// khởi tạo lớp đăng nhập facebook 
			login = new LoginFacebook();
			// kiểm tra lỗi
			if (code == null || code.isEmpty()) {
				RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
				dis.forward(request, response);
			} else {
				// 3.2.8 lấy accessToken của Facebook về .
				String accessToken = login.facebook.getToken(code);
				// 3.2.9 lấy thông tin tài khoản Facebook thông qua accesssToken
				User user = login.facebook.getUserInfo(accessToken);
		

				request.setAttribute("id", user.getId());
				request.setAttribute("name", user.getName());
				// 3.2.10 Trả về view kết quả thành công !
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
				dis.forward(request, response);
			}

			// USE CASE: LOGIN BY EMAIL
		} else if (action.equals("gmail")) {
			login = new LoginGoogle();
			
			// code here
			// goi phuong thuc ra de thuc hien: login.gmail.method()

			// Use case: FORGOT PASSWORD
		} else if (action.equals("forgotpass")) {

			// Gá»­i yÃªu cáº§u lÃ  email ngÆ°á»�i dÃ¹ng nháº­p
			String email = request.getParameter("emailforgot");
			// 6.2.5.Gá»�i hÃ¬nh thá»©c Ä‘Äƒng nháº­p lÃ  Account
			login = new LoginAccount();
			// 6.2.6.hasExistEmail(String email)
			boolean exist = login.account.hasExistEmail(email);
			if (exist) {
				// 6.2.8. ThÃ´ng bÃ¡o gá»­i mail thÃ nh cÃ´ng
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

				// 6.3.1.1. Hiá»‡n thÃ´ng bÃ¡o Email khÃ´ng tá»“n táº¡i
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
