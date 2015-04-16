package app.nevvea.weclean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.Profile;

import org.json.JSONObject;


public class TabAccount extends FragmentActivity {

    private static final int SPLASH = 0;
    private static final int SELECTION = 1;
    private static final int FRAGMENT_COUNT = 2;

    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];

    private boolean isResumed = false;
    private boolean userSkippedLogin = false;
    private AccessTokenTracker accessTokenTracker;
    private CallbackManager callbackManager;
    private JSONObject user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_account);

        Button button = (Button) findViewById(R.id.jumptologin_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
            }
        });



        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                fetchUserName();

            }
        };
        callbackManager = CallbackManager.Factory.create();
    }

    private void fetchUserName() {
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            Profile profile = Profile.getCurrentProfile();
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(profile.getFirstName());
        } else user = null;
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
