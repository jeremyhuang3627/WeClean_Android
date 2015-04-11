package app.nevvea.weclean;

import android.app.ActionBar;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class MainActivity extends ActionBarActivity {
    LocalActivityManager mLocalActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup three tabs at the bottom of screen
        tabSetup(savedInstanceState);

    }

    private void tabSetup(Bundle savedInstanceState) {
        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);

        // get the TabHost Object and setup
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup(mLocalActivityManager);

        // build specific tabs
        TabHost.TabSpec tabSpecCleaners = tabHost.newTabSpec("tab_cleaners")
                .setIndicator("Cleaners")
                .setContent(new Intent(this, TabCleaners.class));

        TabHost.TabSpec tabSpecMessages = tabHost.newTabSpec("tab_messages")
                .setIndicator("Messages")
                .setContent(new Intent(this, TabMessages.class));

        TabHost.TabSpec tabSpecAccount = tabHost.newTabSpec("tab_account")
                .setIndicator("Account")
                .setContent(new Intent(this, TabAccount.class));

        // add tabs to TabHost
        tabHost.addTab(tabSpecCleaners);
        tabHost.addTab(tabSpecMessages);
        tabHost.addTab(tabSpecAccount);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onPause() {
        super.onPause();
        mLocalActivityManager.dispatchPause(!isFinishing());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocalActivityManager.dispatchResume();
    }
}
