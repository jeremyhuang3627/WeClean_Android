package app.nevvea.weclean.message;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;

import app.nevvea.weclean.FirebaseListAdapter;
import app.nevvea.weclean.R;

/**
 * Created by Anna on 4/18/15.
 */
public class ChatAdapter extends FirebaseListAdapter<ChatMessage> {

    private String mUsername;

    public ChatAdapter(Query ref, Activity activity, int layout, String mUsername) {
        super(ref, ChatMessage.class, layout, activity);
        this.mUsername = mUsername;
    }

    /**
     * Bind an instance of the <code>Chat</code> class to our view. This method is called by <code>FirebaseListAdapter</code>
     * when there is a data change, and we are given an instance of a View that corresponds to the layout that we passed
     * to the constructor, as well as a single <code>Chat</code> instance that represents the current data to bind.
     *
     * @param view A view instance corresponding to the layout we passed to the constructor.
     * @param chat An instance representing the current state of a chat message
     */
    @Override
    protected void populateView(View view, ChatMessage chat) {
        // Map a Chat object to an entry in our listview
        String author = chat.getAuthor();
        TextView authorText = (TextView) view.findViewById(R.id.chat_author);
        authorText.setText(author + ": ");
        // If the message was sent by this user, color it differently
        if (author != null && author.equals(mUsername)) {
            authorText.setTextColor(Color.RED);
        } else {
            authorText.setTextColor(Color.BLUE);
        }
        ((TextView) view.findViewById(R.id.chat_message)).setText(chat.getMessage());
    }
}
