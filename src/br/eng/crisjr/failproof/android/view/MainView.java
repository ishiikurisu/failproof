package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.R;

/**
 * Monster to create views and controllers for main screen
 */
public class MainView
{
    /**
     * Creates a brand new scroll, only with a message signaling it is empty
     *
     * @param context The application's context
     * @return the new scroll
     */
    public ScrollView resetScroll(Context context)
    {
        ScrollView scroll = new ScrollView(context);
        TextView tv = new TextView(context);

        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                         ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText(context.getResources().getString(R.string.empty_lists));
        ScrollView.LayoutParams lp = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                 ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        scroll.setLayoutParams(lp);
        scroll.addView(tv);

        return scroll;
    }

    // TODO create static method for adding a list of titles to a scroll

    /**
     * Places the newly created scroll in the main layout.
     * @param linear The main layout on the screen
     * @param scroll The layout to store the lists
     * @return The new main layout
     */
    public LinearLayout replaceScroll(LinearLayout linear, ScrollView scroll)
    {
        int howMany = linear.getChildCount();
        View[] views = new View[howMany];

        for (int i = 0; i < howMany; ++i)
        {
            views[i] = linear.getChildAt(i);
            if (views[i].getClass() == ScrollView.class)
                views[i] = scroll;
        }

        linear.removeAllViews();
        for (int i = 0; i < howMany; ++i)
        {
            linear.addView(views[i]);
        }

        return linear;
    }
}
