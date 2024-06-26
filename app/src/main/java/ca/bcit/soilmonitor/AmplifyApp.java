package ca.bcit.soilmonitor;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;

public class AmplifyApp extends Application {
    public void onCreate() {
        super.onCreate();

        amplifyConfigure();
        amplifyFetchAuth();
    }

    private void amplifyFetchAuth() {
        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
    }

    private void amplifyConfigure() {
        String tag = "AmplifyApp";
        try {
            // Include Auth plugin
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Log.i(tag, "Included Auth plugin");

            Amplify.configure(getApplicationContext());
            Log.i(tag, "Initialized Amplify");

        } catch (AmplifyException error) {

            Log.e(tag, "Could not initialize Amplify", error);

        }
    }
}
