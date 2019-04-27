package model;

import model.account.AccountAble;
import model.facebook.FacebookAble;
import model.gmail.GmailAble;

public abstract class LoginAbstract {
	
	public AccountAble account;
	public FacebookAble facebook;
	public GmailAble gmail;

	public LoginAbstract() {
		super();
	}

	

}
