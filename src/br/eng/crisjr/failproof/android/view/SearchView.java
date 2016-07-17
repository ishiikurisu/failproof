package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Updates the search screen
 */
public class SearchView
{
    public SearchView()
    {

    }

    public LinearLayout replaceScroll(LinearLayout linear, ScrollView scroll)
    {
        return new MainView().replaceScroll(linear, scroll);
    }

    public ScrollView createScroll(Context context, String[] lists) {
        ScrollView scroll = new ScrollView(context);
        LinearLayout stuff = new LinearLayout(context);
        int howMany = lists.length;

        stuff.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < howMany; ++i) {
            String list = lists[i];
            TextView tv = new TextView(context);
            tv.setText(list);
            tv.setTextSize(20);
            // TODO add callback to text
            stuff.addView(tv);
        }

        scroll.addView(stuff);
        return scroll;
    }

}