package model.account;

//interface qui dinh code co account
public interface AccountAble {

	//2.2.8: phuong thuc kiem tra username v� password
	public String check(String username, String pass);
	
	//6.2.6. Phuong thuc kiem tra email c� ton tai tron he thong
	public boolean hasExistEmail(String email);
	

}
