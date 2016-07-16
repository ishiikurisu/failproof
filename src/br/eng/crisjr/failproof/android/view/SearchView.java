package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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

    public ScrollView createScroll(Context context, String[] lists)
    {
        ScrollView scroll = new ScrollView(context);

        // TODO populate scroll with lists. Do not forget their callback!

        return scroll;
    }
}
