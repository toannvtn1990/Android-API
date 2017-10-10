package toan.net.android40;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import toan.net.android40.contactprovider.ContactListActivity;

public class MainActivity extends AppCompatActivity {

    private String[] mMenuItems = new String[]{"Social API contacts provider"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(android.R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mMenuItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Class<?> clazz = null;
                switch (position) {
                    case 0:
                        clazz = ContactListActivity.class;
                        break;
                    default:
                        break;
                }

                if (clazz == null) {
                    return;
                }

                startActivity(new Intent(getApplicationContext(), clazz));
            }
        });
    }
}
