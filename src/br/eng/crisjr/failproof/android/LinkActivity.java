package br.eng.crisjr.failproof.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import br.eng.crisjr.failproof.android.controller.AccessResultant;
import br.eng.crisjr.failproof.android.controller.DatabaseAccess;
import br.eng.crisjr.failproof.android.view.LinkView;

/**
 * Activity to display a list which can be saved to memory or not
 */
public class LinkActivity
       extends Activity
       implements AccessResultant
{
    LinkView view = new LinkView();
    String[] list = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link);

        // download list
        String link = getIntent().getStringExtra("link");
        DatabaseAccess access = new DatabaseAccess(this);
        access.setOperation(DatabaseAccess.GET_LIST);
        access.execute(link);
    }

    /**
     * Implementation of the required method to download lists from internet.
     * @param result the list title and items
     */
    public void receiveLists(String[] result)
    {
        int id = R.id.scrollInfo;
        ScrollView scrollInfo = (ScrollView) findViewById(id);
        LinearLayout linearInfo = (LinearLayout) findViewById(R.id.linearInfo);
        scrollInfo = view.createScroll(getApplicationContext(), result);
        scrollInfo.setId(id);
        view.replaceScroll(linearInfo, scrollInfo);
        list = result;
    }

    /**
     * Cancels the operation of saving the list and returns to the search screen.
     * @param view I still don't know why this is here. I guess this is the button?
     */
    public void onClick_buttonCancel(View view) {
        setResult(LinkView.CANCEL_PLEASE);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(LinkView.CANCEL_PLEASE);
        super.onBackPressed();
    }


    /**
     * Saves the current list on memory then returns to the main screen.
     *
     * @param v Ignoring this
     */
    public void onClick_buttonSave(View v) {
        if (list == null) return;
        view.saveList(getApplicationContext(), list);
        setResult(LinkView.SAVE_PLEASE);
        finish();
    }

}