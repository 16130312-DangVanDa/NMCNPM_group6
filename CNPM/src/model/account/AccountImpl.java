package model.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

			while (re.next()) {
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

	//test demo
	public static void main(String[] args) {
		System.out.println(new AccountImpl().check("DangDa", "111"));
		// neu khong co du lieu se tra ve null
	}

}
