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

    public void list_onClick(String link) {
        // TODO Start new activity for this link
        Toast.makeText(activity.getApplicationContext(), link, Toast.LENGTH_SHORT).show();
    }


}