package model.facebook;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
public class FacebookAccountImpl implements FacebookAble {

	@Override
	public String getToken(final String code) throws ClientProtocolException, IOException {
		String link = String.format("https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&code=%s&redirect_uri=%s", "1057645477779580","1ead8bdf218bad746e12429c7097a79a",code,"https://localhost:8443/CNPM/Control_Login?action=facebook");
		String response = Request.Get(link).execute().returnContent().asString();
		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
		return accessToken;
	}

	@Override
	public User getUserInfo(String accessToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, "1ead8bdf218bad746e12429c7097a79a", Version.LATEST);
		return facebookClient.fetchObject("me", User.class);

	}

}
