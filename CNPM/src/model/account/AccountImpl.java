package model.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.send_mail.SendMail;

public class AccountImpl implements AccountAble {

	// 2.2.8: phuong thuc kiem tra username và password có đăng kí trong DB chưa
	// nếu chưa sẽ trả về chuỗi null
	// nêu có rồi thì trả về chuỗi chứa thông tin tài khoản
	@Override
	public String check(String username, String pass) {
		String result = null;
		// mo ket noi toi DB
		Connection conn = new GetConnection().getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement(
					"select id, username, pass, email from userpass where username like ? and pass like ?");

			// thiet lap cac thong so do nguoi dung nhao vao
			pre.setString(1, username);
			pre.setString(2, pass);

			// thuc thi voi DB
			ResultSet re = pre.executeQuery();

			if (re.next()) {
				// lay tung thong tin cua tung record
				int id = re.getInt("id");
				String user = re.getString("username");
				String password = re.getString("pass");
				String email = re.getString("email");

				result = id + "\t" + user + "\t" + password + "\t" + email;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 6.2.6. sendMail(String to, String subject, String bodyMail, String mail,
	// String pass)
	@Override
	public boolean hasExistEmail(String email) {
		// mo ket noi toi DB
		Connection conn = new GetConnection().getConnection();
		try {
			PreparedStatement pre = conn.prepareStatement("select pass from userpass where email like ?");
			// thiet lap email do nguoi dung nhao vao
			pre.setString(1, email);
			// thuc thi voi DB
			ResultSet re = pre.executeQuery();
			//Neu co 
			if (re.next()) {
				String pass = re.getString("pass");
				//6.2.7: Gui mail
				SendMail.sendMail(email, "FORGOT PASSWORD", "YOUR PASSWORD: " + pass, "dangvanda.itnlu@gmail.com",
						"xxxxxxxxx");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
