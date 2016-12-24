package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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

    public MainView(MainActivity activity) {
        this.activity = activity;
    }

    public MainController setController(MainController controller) {
        this.controller = controller;
        return this.controller;
    }

    public void drawEmptyView() {
        LinearLayout layoutMain = (LinearLayout) activity.findViewById(R.id.layoutMain);
        ScrollView scroll = resetScroll();
        scroll.setId(R.id.scrollLists);
        replaceScroll(layoutMain, scroll);
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

    /* OTHER ACTIVITIES */

    public void searchNewLists() {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }
}
