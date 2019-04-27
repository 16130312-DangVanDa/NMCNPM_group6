package model;

import model.gmail.GmailAccountImpl;

public class LoginGoogle extends LoginAbstract{
	public LoginGoogle() {
		this.gmail = new GmailAccountImpl();
	}

}
