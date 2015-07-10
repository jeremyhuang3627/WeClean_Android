package app.nevvea.weclean.cleaner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.ArrayList;

import app.nevvea.weclean.R;
import app.nevvea.weclean.User;
import app.nevvea.weclean.cleaner.CleanerAdapter;
import app.nevvea.weclean.cleaner.CleanerDetail;

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
    private AuthData mAuthData;
    private Firebase cleanersRef = new Firebase("https://dormcatchat.firebaseio.com/cleaners");

    private Context context;
    private CleanerAdapter cleanerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_cleaners);

        final ListView listView = (ListView) findViewById(R.id.listView_cleaners);
        CleanerAdapter cleanerAdapter = new CleanerAdapter(cleanersRef, R.layout.cell_cleaners, this);
        listView.setAdapter(cleanerAdapter);

        // call cleaner detail activity when a cleaner is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence text = listView.getItemAtPosition(position).toString();

                Intent intent = new Intent(getApplication(), CleanerDetail.class)
                        .putExtra(Intent.EXTRA_TEXT, text);
                startActivity(intent);
            }
        });
    }

}
