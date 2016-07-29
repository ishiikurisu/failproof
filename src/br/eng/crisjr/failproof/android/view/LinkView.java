package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.controller.MemoryAccess;

/**
 * View class for the Link activity
 */
public class LinkView {
    public static final int LINK_REQUEST = 1;
    public static final int SAVE_PLEASE = 2;
    public static final int CANCEL_PLEASE = 3;

    /**
     * Creates a new scroll to add the possible list
     * @param context The application's context
     * @param list    The list, as provided by the internet
     * @return The scroll view with the layout and the
     */
    public ScrollView createScroll(Context context, String[] list) {
        ScrollView scroll = new ScrollView(context);
        LinearLayout stuff = new LinearLayout(context);

        // Preparing linear layout
        stuff.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        stuff.setOrientation(LinearLayout.VERTICAL);

        // Adding title
        TextView title = new TextView(context);
        title.setText(list[0]);
        title.setTextSize(40);
        title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        stuff.addView(title);

        // Adding items
        for (int i = 1; i < list.length; ++i) {
            TextView tv = new TextView(context);
            tv.setText(list[i]);
            title.setTextSize(30);
            title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            stuff.addView(tv);
        }

        // Finalizing construction
        scroll.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.WRAP_CONTENT));
        scroll.addView(stuff);
        return scroll;
    }

    /**
     * Replaces the old scroll in the main layout with a brand new one.
     *
     * @param linear The main layout on screen
     * @param scroll The newly created scroll
     * @return the main layout with the appropriate replacements
     */
    public LinearLayout replaceScroll(LinearLayout linear, ScrollView scroll) {
        return new MainView().replaceScroll(linear, scroll);
    }

    /**
     * Saves this list on local memory
     *
     * @param list the list as provided by the internet
     */
    public void saveList(Context context, String[] list) {
        MemoryAccess card = new MemoryAccess(context);
        card.createList(list);
    }

}
