package com.baasbox.android.authentication;

import android.content.Context;



public class AccountConfig {

    /**
     * Account type id - tells android this account belongs to you (e.g. distinguish google vs. firefox vs. FB )
     */
    public static final String ACCOUNT_TYPE = "BaaSBoxAcc-change-to-you-app";//TODO Mast be in sync with authenticator.xml :accountType 

    /**
     * Account name
     */
    public static final String ACCOUNT_NAME = "BAASBoxAcc";//

    /**
     * Auth token types. READ_ONLY - just for example, not supported as of BB v. 6.0
     */
    public static final String AUTHTOKEN_TYPE_READ_ONLY = "Read only";
    public static final String AUTHTOKEN_TYPE_READ_ONLY_LABEL = "Read only access";

    public static final String AUTHTOKEN_TYPE_FULL_ACCESS = "Full access";
    public static final String AUTHTOKEN_TYPE_FULL_ACCESS_LABEL = "Full access ";

    private static BAASBoxServerAuthenticateServices server =null;// new BAASBoxServerAuthenticateServices();
  
    public static final BAASBoxServerAuthenticateServices getServer(Context ctx){
    	if(server==null){
    		server= new BAASBoxServerAuthenticateServices( ctx);
    	}
    	return server;
    }
    
}
