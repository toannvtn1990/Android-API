package toan.net.android40.contactsProvider;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import toan.net.android40.BuildConfig;
import toan.net.android40.R;
import toan.net.android40.utils.Utils;


public class ContactListActivity extends FragmentActivity implements ContactsListFragment.OnContactsInteractionListener{

    private boolean isTwoPaneLayout;
    private boolean isSearchResultView = false;
    private ContactDetailFragment mContactDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);

        // set main content view. On smaller screen devices this is a single pane view which one fragment.
        // One larger screen devices this is a two pane view with two fragment
        setContentView(R.layout.activity_contacts_provider);

        // Check if two pane bool is set based on resource directories
        isTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);

        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            // fetch query from intent and notify the fragment that it should display search
            // result instead of all contacts.
            String searchQuery = getIntent().getStringExtra(SearchManager.QUERY);
            ContactsListFragment contactsListFragment = (ContactsListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.contact_list);
            isSearchResultView = true;
            contactsListFragment.setSearchQuery(searchQuery);

            setTitle(getString(R.string.contacts_list_search_results_title));
        }

        if (isTwoPaneLayout) {
            mContactDetailFragment = (ContactDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contact_detail);
        }
    }

    @Override
    public void contactSelectedUri(Uri contactUri) {
        if (isTwoPaneLayout && mContactDetailFragment != null) {
            mContactDetailFragment.setContactUri(contactUri);
        } else {
            Intent intent = new Intent(this, ContactDetailActivity.class);
            intent.setData(contactUri);
            startActivity(intent);
        }
    }

    @Override
    public void onSelectionCleared() {
        if (isTwoPaneLayout && mContactDetailFragment != null) {
            mContactDetailFragment.setContactUri(null);
        }
    }

    @Override
    public boolean onSearchRequested() {
        return !isSearchResultView && super.onSearchRequested();
    }
}
