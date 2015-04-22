package app.nevvea.weclean.message;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.nevvea.weclean.R;

/**
 * Created by Anna on 4/22/15.
 *
 * This adapter is used specifically to deal with the chat message list
 */
public class CustomFirebaseListAdapter extends BaseAdapter{

    private String currentUserID;
    private String otherUserID;

    private Query mRef;
    private int mLayout;
    private ChildEventListener mListener;
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String, String>> mModels;
    private Map<String, HashMap<String, String>> mModelKeys;
    private HashMap<String, String> model;

    public CustomFirebaseListAdapter(Query mRef, int mLayout, Activity activity, String currentUserID, String otherUserID) {

        this.currentUserID = currentUserID;
        this.otherUserID = otherUserID;

        this.mRef = mRef;
        this.mLayout = mLayout;
        this.mInflater = activity.getLayoutInflater();

        mModels = new ArrayList<HashMap<String, String>>();
        mModelKeys = new HashMap<String, HashMap<String, String>>();

        mListener = this.mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                model = (HashMap) dataSnapshot.getValue();
                mModelKeys.put(dataSnapshot.getKey(), model);

                // Insert into the correct location, based on previousChildName
                if (previousChildName == null) {
                    mModels.add(0, model);
                } else {
                    HashMap<String, String> previousModel = mModelKeys.get(previousChildName);
                    int previousIndex = mModels.indexOf(previousModel);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == mModels.size()) {
                        mModels.add(model);
                    } else {
                        mModels.add(nextIndex, model);
                    }
                }

                notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // One of the mModels changed. Replace it in our list and name mapping
                String modelName = dataSnapshot.getKey();
                HashMap<String, String> oldModel = mModelKeys.get(modelName);
                HashMap<String, String> newModel = (HashMap) dataSnapshot.getValue();
                int index = mModels.indexOf(oldModel);

                mModels.set(index, newModel);
                mModelKeys.put(modelName, newModel);

                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // A model was removed from the list. Remove it from our list and the name mapping
                String modelName = dataSnapshot.getKey();
                HashMap<String, String> oldModel = mModelKeys.get(modelName);
                mModels.remove(oldModel);
                mModelKeys.remove(modelName);
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

                // A model changed position in the list. Update our list accordingly
                String modelName = dataSnapshot.getKey();
                HashMap<String, String> oldModel = mModelKeys.get(modelName);
                HashMap<String, String> newModel = (HashMap) dataSnapshot.getValue();
                int index = mModels.indexOf(oldModel);
                mModels.remove(index);
                if (previousChildName == null) {
                    mModels.add(0, newModel);
                } else {
                    HashMap<String, String> previousModel = mModelKeys.get(previousChildName);
                    int previousIndex = mModels.indexOf(previousModel);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == mModels.size()) {
                        mModels.add(newModel);
                    } else {
                        mModels.add(nextIndex, newModel);
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

                Log.e("FirebaseListAdapter", "Listen was cancelled, no more updates will occur");
            }
        });
    }
    public void cleanup() {
        // We're being destroyed, let go of our mListener and forget about all of the mModels
        mRef.removeEventListener(mListener);
        mModels.clear();
        mModelKeys.clear();
    }

    @Override
    public int getCount() {
        return mModels.size();
    }

    @Override
    public Object getItem(int i) {
        return mModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mInflater.inflate(mLayout, viewGroup, false);
        }

        HashMap<String, String> model = mModels.get(i);
        // Call out to subclass to marshall this model into the provided view
        populateView(view, model);
        return view;
    }
    protected void populateView(View view, HashMap<String, String> model){
        String from = model.get("from");
        String message = "";
        if (model.containsKey("message")){
            message = model.get("message");
        } else {
            message = model.get("image");
        }

        ProfilePictureView profilePictureView = (ProfilePictureView) view.findViewById(R.id.chat_message_user_pic);
        if (from.equals("me")){
            profilePictureView.setProfileId(currentUserID);
        } else {
            profilePictureView.setProfileId(otherUserID);
        }

        TextView currentMessage = (TextView) view.findViewById(R.id.chat_message);
        currentMessage.setText(message);

    }
}
