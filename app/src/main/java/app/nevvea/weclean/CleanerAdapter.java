package app.nevvea.weclean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by anna on 4/10/15.
 *
 * Adapter for popping cleaners' list
 *
 */
public class CleanerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> cleanerslist;

    // constructor
    public CleanerAdapter(Context context, ArrayList<User> cleanerslist) {
        this.context = context;
        this.cleanerslist = cleanerslist;
    }

    /**
     *
     * setup view of each cleaner's cell in cleaners' list
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {

    }


}
