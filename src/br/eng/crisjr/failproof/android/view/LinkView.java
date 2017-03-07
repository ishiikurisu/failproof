package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.LinkActivity;
import br.eng.crisjr.failproof.android.R;
import br.eng.crisjr.failproof.android.controller.LinkController;
import br.eng.crisjr.failproof.android.model.system.MemoryAccess;

/**
 * View class for the Link activity
 */
public class LinkView {
    public static final int LINK_REQUEST = 1;
    public static final int SAVE_PLEASE = 2;
    public static final int CANCEL_PLEASE = 3;

    protected LinkActivity activity;
    protected LinkController controller;

    public LinkView(LinkActivity a) {
        activity = a;
    }

    public LinkController setController(LinkController c) {
        controller = c;
        return controller;
    }

    /**
     * Draws the given checklist on screen.
     *
     * @param list A raw checklist, as described on API.
     */
    public void updateStuff(String list) {
        // TODO Make save button available only after list is downloaded
        ScrollView scrollView = createScroll(activity.getApplicationContext(), list);
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.linearInfo);
        scrollView.setId(R.id.scrollInfo);
        replaceScroll(linearLayout, scrollView);
    }

    /* VIEW STUFF */

    /**
     * Creates a new scroll to add the possible list
     * @param context The application's context
     * @param list    The list, as provided by the internet
     * @return The scroll view with the layout and the
     */
    protected ScrollView createScroll(Context context, String rawList) {
        String[] list = rawList.split("\n");
        ScrollView scroll = new ScrollView(context);
        LinearLayout stuff = new LinearLayout(context);

        // Preparing linear layout
        stuff.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        stuff.setOrientation(LinearLayout.VERTICAL);

        // Adding title
        TextView title = new TextView(context);
        title.setText(list[0]);
        title.setTextSize(30);
        title.setTextColor(context.getResources().getColor(R.color.white));
        // TODO Make title appear on center
        title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        stuff.addView(title);

        // Adding items
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        for (int i = 1; i < list.length; ++i) {
            TextView tv = new TextView(context);
            tv.setText(list[i].substring(1));
            tv.setTextSize(20);
            tv.setTextColor(context.getResources().getColor(R.color.white));
            tv.setLayoutParams(lp);
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
    protected LinearLayout replaceScroll(LinearLayout linear, ScrollView scroll) {
        return MainView.replaceScroll(linear, scroll);
    }

    /**
     * Returns to the main activity after storing the checklist on memory.
     */
    public void returnToMainActivity() {
        activity.setResult(SAVE_PLEASE);
        activity.finish();
    }
}
