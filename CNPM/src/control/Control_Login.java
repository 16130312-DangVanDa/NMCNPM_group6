package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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

		action = request.getParameter("action");// nhận hành động
		PrintWriter out = response.getWriter();

		// USE CASE: Login by Account
		if (action.equals("account")) {
			// 1. Nhận dữ liệu gửi từ client lên gồm username và password
			String username = request.getParameter("Username");
			String pass = request.getParameter("Password");
			// 2. Khởi tạo hình thức login bằng account
			login = new LoginAccount();
			// 3. Gọi phương thức kiểm tra tính đúng đắn của username và password, trả về
			// chuỗi kết quả
			String result = login.account.check(username, pass);
			if (result != null) {
				// ** Nhánh 4.1: đúng username, passowrd

				// 4.1.1. Tạo form thông báo đăng nhập thành công và hiện thông tin tài khoản.
				out.println(
						"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
				out.println(
						"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
				out.println("<script type=\"text/javascript\">");
				out.println("	$(document).ready(function(){");
				out.println("swal ( \"Login success\" ,  \"" + result + "\" ,  \"success\" )");
				out.println("});");
				out.println("</script>");

				// 4.1.2. Chuyển trang Index
				String url = "/index.jsp";
				RequestDispatcher re = getServletContext().getRequestDispatcher(url);
				re.include(request, response);

			} else {
				// ** Nhánh 4.2: sai username hoac password

				// 4.2.1. Lưu thông tin username, password và thông báo "Something invalid!
				// Please check again" xuống request
				request.setAttribute("username", username);
				request.setAttribute("password", pass);
				request.setAttribute("login_fail", "Something invalid! Please check again");

				// 4.2.2. chuyển về trang Login
				String url = "/login.jsp";
				RequestDispatcher re = getServletContext().getRequestDispatcher(url);
				re.forward(request, response);
			}

			// USE CASE: LOGIN BY FACEBOOK
			// Từ 3.2.1 đến 3.2.7 được thực hiện bên hệ thống trang Facebook
		} else if (action.equals("facebook")) {
			// 3.2.8. Nhận "code" xác thực
			String code = request.getParameter("code");
			// 3.2.8. Khởi tạo lớp đăng nhập facebook
			login = new LoginFacebook();
			
			if (code == null || code.isEmpty()) {
				RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
				dis.forward(request, response);
			} else {
				// 3.2.10. Yêu cầu chuỗi truy cập để lấy thông tin tài khoản từ "code" xác thực 
				String accessToken = login.facebook.getToken(code);
				// 3.2.12  Yêu cầ thông tin tài khoản Facebook thông qua accesssToken
				User user = login.facebook.getUserInfo(accessToken);

				request.setAttribute("id", user.getId());
				request.setAttribute("name", user.getName());
				// 3.2.15 Trả về view kết quả thành công !
				out.println(
						"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
				out.println(
						"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
				out.println("<script type=\"text/javascript\">");
				out.println("	$(document).ready(function(){");
				out.println("swal ( \"SUCCESS\" ,  \"id: " + user.getId() + ", name: " + user.getName()
						+ "\" ,  \"success\" )");
				out.println("});");
				out.println("</script>");

				String url = "/index.jsp";
				RequestDispatcher re = getServletContext().getRequestDispatcher(url);
				re.include(request, response);
			}

			// USE CASE: LOGIN BY EMAIL
		} else if (action.equals("gmail")) {
			login = new LoginGoogle();
			//code here

			// USE CASE: FORGOT PASSWORD
		} else if (action.equals("forgotpass")) {

			// 1. Nhận dữ liệu gửi từ client gửi lên gồm email
			String email = request.getParameter("emailforgot");
			// 2. Khởi tạo hình thức login bằng account
			login = new LoginAccount();

			// 3. Gọi phương thức kiểm tra email có tồn tại trong hệ thống
			// 6.2.6.hasExistEmail(String email): sequence
			boolean exist = login.account.hasExistEmail(email);
			if (exist) {
				// ** Nhánh 4.2
				// 4.2.1. Tạo form thông báo thành công "Your password is send to your email."
				out.println(
						"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
				out.println(
						"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
				out.println("<script type=\"text/javascript\">");
				out.println("	$(document).ready(function(){");
				out.println("swal ( \"SUCCESS\" ,  \"Your password is send to your email.\" ,  \"success\" )");
				out.println("});");
				out.println("</script>");
			} else {
				// **Nhanh 4.1

				// 4.1.1. Tạo form thông báo "Your account with email don't exist !!!"
				out.println(
						"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
				out.println(
						"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
				out.println("<script type=\"text/javascript\">");
				out.println("	$(document).ready(function(){");
				out.println("swal ( \"ERROR\" ,  \"Your account with email don't exist !!!\" ,  \"error\" )");
				out.println("});");
				out.println("</script>");
			}
			// 5. Chuyển về trang login
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
