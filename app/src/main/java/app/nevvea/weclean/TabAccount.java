package app.nevvea.weclean;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;


public class TabAccount extends ActionBarActivity {

    private static final int SPLASH = 0;
    private static final int SELECTION = 1;
    private static final int FRAGMENT_COUNT = 2;

    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];

    private boolean isResumed = false;
    private boolean userSkippedLogin = false;
    private AccessTokenTracker accessTokenTracker;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_tab_account);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                if (isResumed) {
                    FragmentManager manager = getSupportFragmentManager();
                    int backStackSize = manager.getBackStackEntryCount();
                    for (int i = 0; i < backStackSize; i++) {
                        manager.popBackStack();
                    }
                    if (newAccessToken != null) {
                        // showFragment(SELECTION, false);
                    } else {
                        //showFragment(SPLASH, false);
                    }
                }
            }
        };

        FragmentManager fm = getSupportFragmentManager();
//        LoginFragment loginFragment = (LoginFragment) fm.findFragmentById(R.id.loginFragment);

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
