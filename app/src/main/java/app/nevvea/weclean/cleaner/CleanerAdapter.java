package app.nevvea.weclean.cleaner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Query;

import org.w3c.dom.Text;

import java.util.ArrayList;

import app.nevvea.weclean.FirebaseListAdapter;
import app.nevvea.weclean.R;
import app.nevvea.weclean.User;

/**
 * Created by anna on 4/10/15.
 *
 * Adapter for popping cleaners' list
 *
 */
public class CleanerAdapter extends FirebaseListAdapter<Cleaner> {
    private Context context;
    private ProfilePictureView cleanerPic;
    private TextView cleanerName;
    private TextView cleanerGender;
    private TextView cleanerDes;

    // constructor
    public CleanerAdapter(Query ref, int layout, Activity activity) {
        super(ref, Cleaner.class, layout, activity);

    }

    @Override
    protected void populateView(View v, Cleaner cleaner) {

        cleanerPic = (ProfilePictureView) v.findViewById(R.id.cleaner_pic);
        cleanerName = (TextView) v.findViewById(R.id.cleaner_name);
        //cleanerGender = (TextView) v.findViewById(R.id.cleaner_gender);
        cleanerDes = (TextView) v.findViewById(R.id.cleaner_description);

        cleanerPic.setProfileId(cleaner.getUid().replaceAll("\\D+", ""));
        cleanerName.setText(cleaner.getName());
        //cleanerGender.setText(cleaner.getGender());
        cleanerDes.setText(cleaner.getService());
    }
}
