package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import br.eng.crisjr.failproof.android.controller.AccessResultant;
import br.eng.crisjr.failproof.android.controller.DatabaseAccess;
import br.eng.crisjr.failproof.android.view.LinkView;
import br.eng.crisjr.failproof.android.view.MainView;
import br.eng.crisjr.failproof.android.view.SearchView;

/**
 * Activity to show available lists
 */
public class SearchActivity
       extends Activity
       implements AccessResultant
{
    private SearchView view = new SearchView();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        // download content from internet
        DatabaseAccess access = new DatabaseAccess(this);
        access.setOperation(DatabaseAccess.GET_STUFF);
        access.execute();
    }

    /**
     * Implementation of the required method to download.
     * @param result an array of strings containing pairs "List\nLink"
     */
    public void receiveLists(String[] result)
    {
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollSearch);
        LinearLayout linearSearch = (LinearLayout) findViewById(R.id.linearSearch);

        if (result != null) {
            scroll = view.createScroll(getApplicationContext(), this, result);
        }

        scroll.setId(R.id.scrollSearch);
        view.replaceScroll(linearSearch, scroll);
    }

    /**
     * Method to receive the results of the child activities
     *
     * @param requestCode The code to the requested activity
     * @param resultCode  The result of the operation
     * @param intent      The intent sent to the activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (resultCode) {
            case LinkView.CANCEL_PLEASE:
                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                break;
            case LinkView.SAVE_PLEASE:
                setResult(MainView.SAVE_REQUEST);
                finish();
                break;
        }
    }
}