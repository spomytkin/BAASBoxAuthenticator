package com.baasbox.android.authentication;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.baasbox.android.BAASBox;
import com.baasbox.android.BAASBoxClientException;
import com.baasbox.android.BAASBoxConfig;
import com.baasbox.android.BAASBoxConfig.AuthType;
import com.baasbox.android.BAASBoxConnectionException;
import com.baasbox.android.BAASBoxInvalidSessionException;
import com.baasbox.android.BAASBoxResult;
import com.baasbox.android.BAASBoxServerException;

/**
 * BAASBox calls 
 */
public class BAASBoxServerAuthenticateServices {
	
	private static BAASBox bbox;


	public BAASBoxServerAuthenticateServices(Context ctx ){
		BAASBoxConfig config = new BAASBoxConfig();
		config.API_DOMAIN = "10.0.2.2";//TODO change to real IP for device or remote server test. e.g.:
		//config.API_DOMAIN =  "192.168.0.105";
		config.AUTHENTICATION_TYPE = AuthType.SESSION_TOKEN;	
		bbox = new BAASBox(config, ctx);
	}
	
    public String userSignUp(String username, String email, String password, String authType) throws BAASBoxClientException, BAASBoxServerException, BAASBoxInvalidSessionException, BAASBoxConnectionException {

		JSONObject user = new JSONObject();
		JSONObject visibleByTheUser = new JSONObject();
		
		try {
			visibleByTheUser.put("email", email);
			user.put("username", username);
			user.put("password", password);
			user.put("visibleByTheUser", visibleByTheUser);
		} catch (JSONException e) {
			throw new Error(e);
		}
		
		BAASBoxResult<String> result = bbox.signup(user);
		return result.get();
  
        
    }

    public String userSignIn(String username, String password) throws Exception {
    	BAASBoxResult<String> rez = bbox.login(username, password);   	
        return  rez.get();
    }
}
