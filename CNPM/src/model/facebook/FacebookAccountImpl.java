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
//3.2.10. Yêu cầu chuỗi truy cập để lấy thông tin tài khoản từ "code" xác thực : getToken(code)
	public String getToken(final String code) throws ClientProtocolException, IOException {
		String link = String.format("https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&code=%s&redirect_uri=%s", "1057645477779580","1ead8bdf218bad746e12429c7097a79a",code,"https://localhost:8443/CNPM/Control_Login?action=facebook");
		String response = Request.Get(link).execute().returnContent().asString();
		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
		//3.2.11. Trả về  mã chuỗi truy cập : accessToken
		return accessToken;
	}
//3.2.12. Yêu cầu thông tin tài khoản Facebook từ chuỗi truy cập  :  getUserInfo(accessToken)
	@Override
	public User getUserInfo(String accessToken) {
		//3.2.13.Tạo ra máy con kết nối mã truy cập và mã bảo mật ứng dụng Facebook : 
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, "1ead8bdf218bad746e12429c7097a79a", Version.LATEST);
		//3.2.14.  Tìm kiếm User "me" trong session của máy con và trả về: 
		return facebookClient.fetchObject("me", User.class);

	}

}
