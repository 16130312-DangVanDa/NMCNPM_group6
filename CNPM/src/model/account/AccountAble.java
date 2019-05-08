package model.account;

public interface AccountAble {

	//2.2.8: phuong thuc kiem tra username và password
	public String check(String username, String pass);

	
	//6.2.6: phuong thuc kiem tra email co ton tai trong he thong khong
	public boolean hasExistEmail(String email);
	

}
