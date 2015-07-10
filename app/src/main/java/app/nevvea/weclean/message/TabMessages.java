package app.nevvea.weclean.message;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import app.nevvea.weclean.R;


public class TabMessages extends Activity {
    public final static String currentUserUID = "Replace By currentUID";
    public final static String otherUserUID = "Replace By otherUID";
    public final static String otherUserName = "Replace By name";

    private AuthData mAuthData;
    private Firebase mainRef = new Firebase("https://dormcatchat.firebaseio.com/");
    private Firebase messageListRef = mainRef.child("chats_list");
    private String firebaseUID;
    private Context context;
    MessageListAdapter mMessageListAdapter;

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
                    messageListSetup();
                } else
                    Log.d("HEYYYYYYYYYYYY", "HEYYYYYYYYY");

            }
        };
        mainRef.addAuthStateListener(authStateListener);
    }

    private void messageListSetup() {
        mAuthData = mainRef.getAuth();
        if (mAuthData != null) {
            firebaseUID = mAuthData.getUid();
            Log.d("firebase uid", firebaseUID);
            messageListRef = messageListRef.child(firebaseUID);
            final ListView messageListView = (ListView) findViewById(R.id.messages_list);
            mMessageListAdapter = new MessageListAdapter(messageListRef, R.layout.cell_chat_list, this);
            messageListView.setAdapter(mMessageListAdapter);
            mMessageListAdapter.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    messageListView.setSelection(mMessageListAdapter.getCount() - 1);
                }
            });


            messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, ChatActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString(currentUserUID, firebaseUID);
                    MessageList messageList = (MessageList) mMessageListAdapter.getItem(position);
                    extras.putString(otherUserUID, messageList.getUid());
                    extras.putString(otherUserName, messageList.getName());
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });
        }

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
