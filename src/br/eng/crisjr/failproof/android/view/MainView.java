package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import br.eng.crisjr.failproof.android.MainActivity;
import br.eng.crisjr.failproof.android.R;
import br.eng.crisjr.failproof.android.SearchActivity;
import br.eng.crisjr.failproof.android.controller.MainController;

/**
 * Monster to create views for main screen
 */
public class MainView {
    protected MainActivity activity = null;
    protected MainController controller = null;
    public static int SAVE_REQUEST = 1;
    public static int CANCEL_REQUEST = 2;

    public MainView(MainActivity activity) {
        this.activity = activity;
    }

    public MainController setController(MainController controller) {
        this.controller = controller;
        return this.controller;
    }

    /**
     * Creates an empty view with a message suggesting downloading new checklists.
     */
    public void drawEmptyView() {
        LinearLayout layoutMain = (LinearLayout) activity.findViewById(R.id.layoutMain);
        ScrollView scroll = resetScroll();
        scroll.setId(R.id.scrollLists);
        replaceScroll(layoutMain, scroll);
    }

    /**
     * Creates a view with the given titles on screen.
     *
     * @param titles The titles of each checklist.
     */
    public void drawFilledView(String[] titles) {
        LinearLayout layoutMain = (LinearLayout) activity.findViewById(R.id.layoutMain);
        ScrollView scrollLists = createScroll(titles);
        scrollLists.setId(R.id.scrollLists);
        replaceScroll(layoutMain, scrollLists);
    }

    /* BUILDING VIEWS */

    /**
     * Creates a brand new scroll, only with a message signaling it is empty
     *
     * @return the new scroll
     */
    public ScrollView resetScroll() {
        Context context = activity.getApplicationContext();
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
     * Places the newly created scroll in the main layout.
     *
     * @param linear The main layout on the screen
     * @param scroll The layout to store the lists
     * @return The new main layout
     */
    public static LinearLayout replaceScroll(LinearLayout linear, ScrollView scroll) {
        int howMany = linear.getChildCount();
        View[] views = new View[howMany];

        for (int i = 0; i < howMany; ++i) {
            views[i] = linear.getChildAt(i);
            if (views[i].getClass() == ScrollView.class)
                views[i] = scroll;
        }

        linear.removeAllViews();
        for (int i = 0; i < howMany; ++i) {
            linear.addView(views[i]);
        }

        return linear;
    }

    /**
     * Creates a scroll with many lists
     *
     * @param stuff The lists available
     * @return The scroll populated with the lists
     */
    public ScrollView createScroll(String[] stuff) {
        Context context = activity.getApplicationContext();
        ScrollView scroll = new ScrollView(context);
        LinearLayout box = new LinearLayout(context);
        int limit = stuff.length;

        box.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < limit; ++i) {
            TextView tv = new TextView(context);
            String[] fields = stuff[i].split("\n"); // TODO Discover if this can be replaced by API tools
            String address = fields[0];
            String title = fields[1];
            tv.setText(title);
            tv.setTextSize(20);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickNthTitle(address);
                }
            });
            box.addView(tv);
        }

        scroll.addView(box);
        return scroll;
    }

    /* OTHER ACTIVITIES */

    /**
     * Starts a new activity to download checklists.
     */
    public void searchNewLists() {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivityForResult(intent, SAVE_REQUEST);
    }

    public void onClickNthTitle(String address) {
        // TODO Start new activity depending on the given address
        Toast.makeText(activity, address, Toast.LENGTH_SHORT).show();
    }
}
