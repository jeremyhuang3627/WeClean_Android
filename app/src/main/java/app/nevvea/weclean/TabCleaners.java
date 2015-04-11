package app.nevvea.weclean;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by anna on 4/10/15.
 *
 * This class is used for layout and activity setup for the first, "cleaners" tab which displays
 * a list of cleaners nearby.
 *
 * It calls the CleanerDetail activity when an item in the list is clicked.
 *
 */
public class TabCleaners extends Activity {
    private ArrayList<User> cleanerlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_cleaners);

        final ListView listView = (ListView) findViewById(R.id.listView_cleaners);
        cleanerlist.add(new User("Anna", 0, R.drawable.pic_anna, "lallalalla"));
        cleanerlist.add(new User("Anna", 0, R.drawable.pic_anna, "lallalalla"));
        cleanerlist.add(new User("Anna", 0, R.drawable.pic_anna, "lallalalla"));
        cleanerlist.add(new User("Anna", 0, R.drawable.pic_anna, "lallalalla"));
        cleanerlist.add(new User("Anna", 0, R.drawable.pic_anna, "lallalalla"));
        cleanerlist.add(new User("Anna", 0, R.drawable.pic_anna, "lallalalla"));
        cleanerlist.add(new User("Anna", 0, R.drawable.pic_anna, "lallalalla"));
        cleanerlist.add(new User("Anna", 0, R.drawable.pic_anna, "lallalalla"));


    }

}
