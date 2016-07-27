package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import br.eng.crisjr.failproof.android.LinkActivity;
import br.eng.crisjr.failproof.android.SearchActivity;
import br.eng.crisjr.failproof.web;

/**
 * Updates the search screen
 */
public class SearchView
{
    private String[] lists;
    private String[] links;

    public SearchView()
    {

    }

    public LinearLayout replaceScroll(LinearLayout linear, ScrollView scroll)
    {
        return new MainView().replaceScroll(linear, scroll);
    }

    public ScrollView createScroll(Context context, SearchActivity activity, String[] raw)
    {
        ScrollView scroll = new ScrollView(context);
        LinearLayout stuff = new LinearLayout(context);
        int howMany = raw.length;

        lists = web.toLists(raw);
        links = web.toLinks(raw);
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
                    list_onClick(context, activity, link);
                }
            });
            stuff.addView(tv);
        }

        scroll.addView(stuff);
        return scroll;
    }

    private void list_onClick(Context context, SearchActivity activity, String link)
    {
        Intent intent = new Intent(context, LinkActivity.class);
        intent.putExtra("link", link);
        activity.startActivity(intent);
    }

}