package app.nevvea.weclean.message;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.nevvea.weclean.R;

public class ChatActivity extends ActionBarActivity {
    private String currentUserUID;
    private String otherUserUID;
    private Firebase chatsRef = new Firebase("https://dormcatchat.firebaseio.com/chats");
    private Firebase userChatsRef;
    private Firebase otherUserChatsRef;
    private Firebase currentMessageRef;
    private Firebase otherUserMessageRef;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        extras = intent.getExtras();

        currentUserUID = extras.getString(TabMessages.currentUserUID);
        otherUserUID = extras.getString(TabMessages.otherUserUID);
        userChatsRef = chatsRef.child(currentUserUID).child(otherUserUID);
        otherUserChatsRef = chatsRef.child(otherUserUID).child(currentUserUID);

        final ListView listView = (ListView) findViewById(R.id.chat_message_listView);
        // final ChatAdapter chatAdapter = new ChatAdapter(userChatsRef.limit(50), R.layout.chat_message, this, currentUserUID, otherUserUID);
        // listView.setAdapter(chatAdapter);
//        chatAdapter.registerDataSetObserver(new DataSetObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                listView.setSelection(chatAdapter.getCount() - 1);
//            }
//        });
//
//


        // Setup our input methods. Enter key on the keyboard or pushing the send button
        EditText inputText = (EditText) findViewById(R.id.chat_message_input);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    sendMessage();
                }
                return true;
            }
        });

        findViewById(R.id.chat_sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });


    }

    private void sendMessage() {
        EditText inputText = (EditText) findViewById(R.id.chat_message_input);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            // get current seconds since epoch
            String epoch = Long.toString(System.currentTimeMillis() / 1000);
            currentMessageRef = userChatsRef.child(epoch);
            otherUserMessageRef = otherUserChatsRef.child(epoch);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dateString = dateFormat.format(date);
            Log.d("time check!", epoch);
            Log.d("current ref", currentMessageRef.getPath().toString());
            // create an object for the current message
            ChatMessage chatMessage = new ChatMessage(dateString, "me", input, "read");
            ChatMessage otherUserChatMessage = new ChatMessage(dateString, "other", input, "unread");

            currentMessageRef.setValue(chatMessage);
            otherUserMessageRef.setValue(otherUserChatMessage);

            inputText.setText("");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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
