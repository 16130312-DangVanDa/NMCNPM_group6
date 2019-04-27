package model;

import model.facebook.FacebookAccountImpl;

public class LoginFacebook extends LoginAbstract {
	public LoginFacebook() {
		this.facebook= new FacebookAccountImpl();
	}
}
