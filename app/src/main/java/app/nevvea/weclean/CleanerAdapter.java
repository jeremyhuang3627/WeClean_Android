package app.nevvea.weclean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View cellView;

        // these four lines look suspiciously useless
        if (convertView == null)
            cellView = new View(context);
        else
            cellView = convertView;

        // inflate cellView with layout
        cellView = inflater.inflate(R.layout.cell_cleaners, null);

        // pop cellView with user info

        User user = cleanerslist.get(position);

        TextView userName = (TextView) cellView.findViewById(R.id.cleaner_name);
        userName.setText(user.getName());

        ImageView userPic = (ImageView) cellView.findViewById(R.id.cleaner_pic);
        userPic.setImageResource(user.getPicid());

        // TODO: setup user gender pic

        TextView userDescription = (TextView) cellView.findViewById(R.id.cleaner_description);
        userDescription.setText(user.getDescription());

        return cellView;
    }

    @Override
    public int getCount() { return cleanerslist.size(); }

    @Override
    public Object getItem(int position) {
        return cleanerslist.get(position);
    }

    @Override
    public long getItemId(int position) { return 0; }

}
