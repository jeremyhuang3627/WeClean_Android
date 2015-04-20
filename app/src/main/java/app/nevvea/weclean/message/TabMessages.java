package app.nevvea.weclean.message;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Iterator;

import app.nevvea.weclean.R;


public class TabMessages extends Activity {
    private AuthData mAuthData;
    private Firebase mainRef = new Firebase("https://dormcatchat.firebaseio.com/");
    private Firebase messageListRef = mainRef.child("chats_list");
    private String firebaseUID;
    private ArrayList<DataSnapshot> messageUIDList = new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_messages);

        context = this;
        mAuthData = null;
        Firebase.AuthStateListener authStateListener = new Firebase.AuthStateListener() {

            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    getMessageList();

                } else
                    Log.d("HEYYYYYYYYYYYY", "HEYYYYYYYYY");

            }
        };
        mainRef.addAuthStateListener(authStateListener);
    }

    private void getMessageList() {
        mAuthData = mainRef.getAuth();
        if (mAuthData != null) {
            firebaseUID = mAuthData.getUid();
            messageListRef = messageListRef.child(firebaseUID);

            // for debug
            ChildEventListener childEventListener = messageListRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if (messageUIDList.add(dataSnapshot)){
                        Log.d("size", Integer.toString(messageUIDList.size()));
                    }

                    Log.d("debugbugbugbugbug", dataSnapshot.getKey().toString());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            messageListRef.addChildEventListener(childEventListener);


            Log.d("lallalallallalallalla", mAuthData.getUid());
        } else
            Log.d("ERRRRRRRRRRR", "dontknowwhy");

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_messages, menu);
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
