package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import br.eng.crisjr.failproof.android.LinkActivity;
import br.eng.crisjr.failproof.android.R;
import br.eng.crisjr.failproof.android.SearchActivity;
import br.eng.crisjr.failproof.android.controller.SearchController;
import br.eng.crisjr.failproof.web;

/**
 * Updates the search screen
 */
public class SearchView
{
    protected SearchActivity activity;
    protected SearchController controller;

    public SearchView(SearchActivity activity) {
        this.activity = activity;
    }

    public SearchController setController(SearchController controller) {
        this.controller = controller;
        return this.controller;
    }

    public void updateStuff(String[] lists, String[] links) {
        ScrollView scroll = createScroll(lists, links);
        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.linearSearch);
        scroll.setId(R.id.scrollSearch);
        MainView.replaceScroll(layout, scroll);
    }

    /* VIEWS STUFF */

    public ScrollView createScroll(String[] lists, String[] links) {
        Context context = activity.getApplicationContext();
        ScrollView scroll = new ScrollView(context);
        LinearLayout stuff = new LinearLayout(context);
        int howMany = lists.length;

        stuff.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < howMany; ++i) {
            String list = lists[i];
            String link = links[i];
            TextView tv = new TextView(context);
            tv.setText(list);
            tv.setTextSize(20);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_onClick(link);
                }
            });
            stuff.addView(tv);
        }

        scroll.addView(stuff);

        return scroll;
    }

    /**
     * Callback to each link item.
     *
     * @param link the link to the checklist.
     */
    public void list_onClick(String link) {
        Intent intent = new Intent(activity, LinkActivity.class);
        intent.putExtra("link", link);
        activity.startActivityForResult(intent, LinkView.LINK_REQUEST);
    }

    /**
     * Method to receive the results of the child activities
     *
     * @param requestCode The code to the requested activity
     * @param resultCode  The result of the operation
     * @param intent      The intent sent to the activity
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Better organize this code.
        switch (resultCode) {
            case LinkView.CANCEL_PLEASE:
                Toast.makeText(activity.getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                break;
            case LinkView.SAVE_PLEASE:
                activity.setResult(MainView.SAVE_REQUEST);
                activity.finish();
                break;
        }
    }
}