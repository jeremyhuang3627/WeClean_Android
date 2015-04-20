package app.nevvea.weclean.message;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import org.w3c.dom.Text;

import java.util.ArrayList;

import app.nevvea.weclean.FirebaseListAdapter;
import app.nevvea.weclean.R;

/**
 * Created by Anna on 4/19/15.
 */
public class MessageListAdapter extends FirebaseListAdapter<MessageList> {
    private Context context;
    private ProfilePictureView otherUserProfilePic;
    private TextView otherUserName;
    private TextView lastMessageTime;
    private TextView lastMessage;
    private String facebookID;


    public MessageListAdapter(Query ref, int layout, Activity activity){
        super(ref, MessageList.class, layout, activity);
    }


    protected void populateView(View view, MessageList messageList) {
        otherUserName = (TextView) view.findViewById(R.id.chatcell_user_name);
        lastMessage = (TextView) view.findViewById(R.id.chatcell_last_message);
        lastMessageTime = (TextView) view.findViewById(R.id.chatcell_last_time);
        otherUserProfilePic = (ProfilePictureView) view.findViewById(R.id.chatcell_user_pic);

        otherUserName.setText(messageList.getName());
        lastMessage.setText(messageList.getMessage());
        lastMessageTime.setText(messageList.getDate());
        otherUserProfilePic.setProfileId(messageList.getUid());
    }
}
