package com.baasbox.android.authentication;

import static com.baasbox.android.authentication.AccountConfig.getServer;
import static com.baasbox.android.authentication.AuthenticatorActivity.ARG_ACCOUNT_TYPE;
import static com.baasbox.android.authentication.AuthenticatorActivity.KEY_ERROR_MESSAGE;
import static com.baasbox.android.authentication.AuthenticatorActivity.PARAM_USER_PASS;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * In charge of the Sign up process. Since it's not an AuthenticatorActivity decendent,
 * it returns the result back to the calling activity, which is an AuthenticatorActivity,
 * and it return the result back to the Authenticator
 */
public class SignUpActivity extends Activity {

    private String TAG = getClass().getSimpleName();
    private String mAccountType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAccountType = getIntent().getStringExtra(ARG_ACCOUNT_TYPE);

        setContentView(R.layout.act_register);

        findViewById(R.id.alreadyMember).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {

        // Validation!

        new AsyncTask<String, Void, Intent>() {

            String name = ((TextView) findViewById(R.id.name)).getText().toString().trim();
            String accountName = ((TextView) findViewById(R.id.accountName)).getText().toString().trim();
            String accountPassword = ((TextView) findViewById(R.id.accountPassword)).getText().toString().trim();

            @Override
            protected Intent doInBackground(String... params) {

                Log.d(TAG, " Started authenticating");

                String authtoken = null;
                Bundle data = new Bundle();
                try {
                    authtoken = getServer(getBaseContext()).userSignUp(name, accountName, accountPassword, AccountConfig.AUTHTOKEN_TYPE_FULL_ACCESS);

                    data.putString(AccountManager.KEY_ACCOUNT_NAME, accountName);
                    data.putString(AccountManager.KEY_ACCOUNT_TYPE, mAccountType);
                    data.putString(AccountManager.KEY_AUTHTOKEN, authtoken);
                    data.putString(PARAM_USER_PASS, accountPassword);
                } catch (Exception e) {
                    data.putString(KEY_ERROR_MESSAGE, e.getMessage());
                    Log.e(TAG, " Fail authenticating",e);
                }

                final Intent res = new Intent();
                res.putExtras(data);
                return res;
            }

            @Override
            protected void onPostExecute(Intent intent) {
                if (intent.hasExtra(KEY_ERROR_MESSAGE)) {
                    Toast.makeText(getBaseContext(), intent.getStringExtra(KEY_ERROR_MESSAGE), Toast.LENGTH_SHORT).show();
                } else {
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
