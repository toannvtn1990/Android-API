package toan.net.android40.contactprovider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import toan.net.android40.R;


public class ContactListActivity extends AppCompatActivity {

    private boolean IsTwoPaneLayout = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_provider);

        IsTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);
    }
}
