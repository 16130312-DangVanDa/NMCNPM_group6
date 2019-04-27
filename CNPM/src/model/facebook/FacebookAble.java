package model.facebook;

import com.restfb.types.User;

public interface FacebookAble {
	
	public String getToken(String code);

	public User getUserInfo(String accessToken);

}
