package com.kyluandkylu.android.logiword.Authentication;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class AccountAuthentication extends AbstractAccountAuthenticator {

    private Context context;

    public AccountAuthentication(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        final Intent intent = new Intent(context, AccountAuthentication.class);

        intent.putExtra("Logi Word Account", accountType);
        intent.putExtra("full_access", authTokenType);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);

        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, android.accounts.Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, android.accounts.Account account, String authTokenType, Bundle options) throws NetworkErrorException {

        AccountManager accountManager = AccountManager.get(context);

        String authToken = accountManager.peekAuthToken(account, authTokenType);
        final Bundle result = new Bundle();
        result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
        result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
        result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
        return result;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, android.accounts.Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, android.accounts.Account account, String[] features) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    public static String getToken(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account[] accounts = accountManager.getAccountsByType("Logi Word Account");
        if (accounts.length == 0) {
            return null;
        }
        return accountManager.peekAuthToken(accounts[0], "full_access");
    }

    public class AuthenticatorService extends Service {
        private AccountAuthentication mAuthenticator;

        public AuthenticatorService() {
            super();
        }

        @Override
        public void onCreate() {
            mAuthenticator = new AccountAuthentication(context);
        }

        @Override
        public IBinder onBind(Intent intent) {
            return mAuthenticator.getIBinder();
        }
    }

}
