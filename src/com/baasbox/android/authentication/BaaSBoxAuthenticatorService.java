package com.baasbox.android.authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Service to handle Account authentication. It instantiates the authenticator
 * and returns its IBinder.
 * from The Android Open Source Project
 * https://android.googlesource.com/platform/development/+/master/samples/SampleSyncAdapter/src/com/example/android/samplesync/authenticator/
 * https://android.googlesource.com/platform/development/+/00bf0f0296b6691a9ed93fa46eccf316f7b0222e/samples/SampleSyncAdapter/src/com/example/android/samplesync/syncadapter/
 */
public class BaaSBoxAuthenticatorService extends Service {
	   private BaaSBoxAuthenticator mAuthenticator;
	    private static final String TAG = "AuthenticationService";
	    @Override
	    public void onCreate() {
	        if (Log.isLoggable(TAG, Log.VERBOSE)) {
	            Log.v(TAG, "SampleSyncAdapter Authentication Service started.");
	        }
	        mAuthenticator = new BaaSBoxAuthenticator(this);
	    }

	    @Override
	    public void onDestroy() {
	        if (Log.isLoggable(TAG, Log.VERBOSE)) {
	            Log.v(TAG, "SampleSyncAdapter Authentication Service stopped.");
	        }
	    }
    @Override
    public IBinder onBind(Intent intent) {
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG,
                "getBinder()...  returning the AccountAuthenticator binder for intent "
                    + intent);
        }
        return mAuthenticator.getIBinder();
    }
}
