package model.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.send_mail.SendMail;

//inplement
public class AccountImpl implements AccountAble {

	// 2.2.8: phuong thuc kiem tra username và password có đăng kí trong DB chưa
	@Override
	public String check(String username, String pass) {
		String result = null;

		// 1. Mở kết nối tới database chickennlu_demo
		Connection conn = new GetConnection().getConnection();
		try {
			// 2. Tìm thông tin tài khoản dựa vào username và password
			PreparedStatement pre = conn.prepareStatement(
					"select username, pass, email from userpass where username like ? and pass like ? and isRemove=0");
			// thiet lap cac thong so do nguoi dung nhao vao
			pre.setString(1, username);
			pre.setString(2, pass);

			// 3. Nhận result set gồm 1 record
			ResultSet re = pre.executeQuery();

			if (re.next()) {
				// 4.2.1. Trả về chuỗi chứa thông tin tài khoản (user + "\t" + password+"\t" +email)
				String user = re.getString("username");
				String password = re.getString("pass");
				String email = re.getString("email");

				result = user + "\t" + password + "\t" + email;
			}

			// 5. Đóng kết nối
			re.close();
			pre.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 6.2.6. Phuong thuc kiem tra email có ton tai tron he thong
	@Override
	public boolean hasExistEmail(String email) {
		// 1. Mở kết nối tới database chickennlu_demo
		Connection conn = new GetConnection().getConnection();
		boolean result = false;
		try {
			//2. Tìm password dựa vào username tại các tài khoản chưa bị xóa
			PreparedStatement pre = conn.prepareStatement("select pass from userpass where email like ? and isRemove=0");
			// thiet lap cac thong so do nguoi dung nhao vao
			pre.setString(1, email);
			// 3. Nhận result set gồm 1 record
			ResultSet re = pre.executeQuery();
			if (re.next()) {
				// 4.2.1.  Gửi mail kèm theo password tìm được về email người dùng nhập vào
				String pass = re.getString("pass");
				
				//** 6.2.7. sendMail(String to, String subject, String bodyMail, String mail,
				// String pass)
				SendMail.sendMail(email, "FORGOT PASSWORD", "YOUR PASSWORD: " + pass, "dangvanda.itnlu@gmail.com",
						"Da10a21998321123");
				//4.2.2. Trả về true
				result = true;
			}
	
			//5. Đóng kết nối
			re.close();
			pre.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
