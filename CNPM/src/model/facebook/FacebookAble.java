package model.facebook;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.restfb.types.User;

public interface FacebookAble {
	
	public String getToken(final String code) throws ClientProtocolException, IOException;

	public User getUserInfo(String accessToken);

}
