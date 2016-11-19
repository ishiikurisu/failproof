package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.MainActivity;
import br.eng.crisjr.failproof.android.R;
import br.eng.crisjr.failproof.android.controller.MemoryAccess;
import br.eng.crisjr.failproof.web;

/**
 * Monster to create views and controllers for main screen
 */
public class MainView
{
    /*
     * ====================
     * STATELESS PROCEDURES
     * ====================
     */

    public static final int SAVE_REQUEST = 0;

    /**
     * Load all lists from memory.
     *
     * @param context The application's context.
     * @return returns an array of strings with the lists' names if they exist, otherwise null.
     */
    public static String[] getLists(Context context) {
        return new MemoryAccess(context).loadLists();
    }

    /**
     * Load basic information from memory
     *
     * @param context The application's context
     * @return An array containing pairs "Title\nAddress"
     */
    public static String[] getStuff(Context context) {
        return new MemoryAccess(context).loadStuff(context);
    }

    /**
     * Creates a brand new scroll, only with a message signaling it is empty
     * @param context The application's context
     * @return the new scroll
     */
    public static ScrollView resetScroll(Context context)
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

    /**
     * Creates a scroll with many lists
     *
     * @param context The application's context
     * @param stuff   The lists available
     * @return The scroll populated with the lists
     */
    public static ScrollView createScroll(Context context, MainActivity activity, String[] stuff) {
        ScrollView scroll = new ScrollView(context);
        LinearLayout box = new LinearLayout(context);
        MainView view = activity.getView();
        int limit = stuff.length;

        view.setStuff(stuff);
        box.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < limit; ++i) {
            TextView tv = new TextView(context);
            String title = view.getNthTitle(i);
            String address = view.getNthAddress(i);
            tv.setText(title);
            tv.setTextSize(20);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.textTitle_onClick(address);
                }
            });
            box.addView(tv);
        }

        scroll.addView(box);
        return scroll;
    }

    /**
     * Places the newly created scroll in the main layout.
     * @param linear The main layout on the screen
     * @param scroll The layout to store the lists
     * @return The new main layout
     */
    public static LinearLayout replaceScroll(LinearLayout linear, ScrollView scroll)
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

    /**
     * Delete all lists in memory
     * @param context The application's context
     */
    public static void resetMemory(Context context) {
        new MemoryAccess(context).resetMemory();
    }

    /**
     * Creates a scroll that enables the destruction of lists.
     *
     * @param context  The application's context.
     * @param activity The current activity.
     * @param stuff    The lists.
     * @return null because this method is implemented yet.
     */
    public static ScrollView createKillerScroll(Context context, MainActivity activity, String[] stuff) {
        // TODO Implement this creator
        return null;
    }

    /*
     * =============
     * STATE METHODS
     * =============
     */
    private boolean mode = false;
    private String[] titles = null;
    private String[] addresses = null;

    public boolean getMode() {
        return mode;
    }

    public boolean setMode(boolean mode) {
        this.mode = mode;
        return getMode();
    }

    public void setStuff(String[] stuff) {
        this.titles = web.toLists(stuff);
        this.addresses = web.toLinks(stuff);
    }

    public String getNthTitle(int n) {
        return this.titles[n];
    }

    public String getNthAddress(int n) {
        return this.addresses[n];
    }

}
