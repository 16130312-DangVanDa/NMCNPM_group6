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

		// TH1: Login by Account
		if (action.equals("account")) {
			System.out.println("here...");
			// 2.2.6: lay du lieu gui tu client len
			String username = request.getParameter("Username");
			String pass = request.getParameter("Password");

			System.out.println("username: " + username);
			System.out.println("password: " + pass);
			
			// 2.2.7. khoi tao hình thức đăng nhập là đăng nhập với account
			login = new LoginAccount();
			System.out.println("khoi tao thanh cong");

			// 2.2.8. phuong thuc check(username, password)
//			String path= "D:\\Nong Lam University\\webprogramming\\CNPM2\\config\\config.properties";
//			String path=  getServletContext().getRealPath("/config/config.properties");
//			System.out.println("Path: "+path);
			
			String result = login.account.check(username, pass);
			System.out.println("result " + result);

			if (result != null) {
				// đúng username, passowrd
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

			// TH2:Login by facebook
		} else if (action.equals("facebook")) {
			login = new LoginFacebook();
			// code here
			// goi phuong thuc ra de thuc hien: login.facebook.method()

			// TH3: Login by Gmail
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
