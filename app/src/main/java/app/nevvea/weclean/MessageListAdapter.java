package app.nevvea.weclean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;

/**
 * Created by Anna on 4/19/15.
 */
public class MessageListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> messageUIDList;
    private ProfilePictureView otherUserProfilePic;
    private TextView otherUserName;
    private TextView lastMessageTime;
    private TextView lastMessage;

    // constructor
    public MessageListAdapter(Context context, ArrayList<String> UIDList) {
        this.context = context;
        this.messageUIDList = UIDList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View cellView;

        // these four lines look suspiciously useless
        if (convertView == null)
            cellView = new View(context);
        else
            cellView = convertView;

        // inflate cellView with layout
        cellView = inflater.inflate(R.layout.cell_chat_list, null);

        String facebookID = messageUIDList.get(position);

        // GraphRequest graphRequest = new GraphRequest()


        return cellView;
    }

    @Override
    public int getCount() { return messageUIDList.size(); }

    @Override
    public Object getItem(int position) {
        return messageUIDList.get(position);
    }

    @Override
    public long getItemId(int position) { return 0; }
}
