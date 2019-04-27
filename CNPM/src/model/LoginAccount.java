package model;

import model.account.AccountImpl;

public class LoginAccount extends LoginAbstract {
	
	public LoginAccount() {
		this.account = new AccountImpl();
	}

}
