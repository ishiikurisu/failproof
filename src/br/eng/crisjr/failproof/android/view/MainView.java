package br.eng.crisjr.failproof.android.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import br.eng.crisjr.failproof.android.*;
import br.eng.crisjr.failproof.android.controller.MainController;

/**
 * Monster to create views for main screen
 */
public class MainView {
    protected MainActivity activity = null;
    protected MainController controller = null;
    public static final int SAVE_REQUEST = 1;
    public static final int CANCEL_REQUEST = 2;
    public static final int EDIT_REQUEST = 4;
    protected boolean deleteMode = false;
    public static final String[] OPTIONS = {
            "Create checklist",
            "Download checklist"
    };
    public static final int CREATE_OPTION = 0;
    public static final int DOWNLOAD_OPTION = 1;

    public MainView(MainActivity activity) {
        this.activity = activity;
    }

    public MainController setController(MainController controller) {
        this.controller = controller;
        return this.controller;
    }

    public boolean getDeleteMode() {
        return deleteMode;
    }

    public void switchMode() {
        deleteMode = !deleteMode;
        // TODO Change X button background color
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

        // TODO Make this text prettier
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText(context.getResources().getString(R.string.empty_lists));
        tv.setTextColor(context.getResources().getColor(R.color.white));
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
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        for (int i = 0; i < limit; ++i) {
            // TODO Make this button prettier
            TextView tv = createButton(context, stuff[i], deleteMode);
            tv.setLayoutParams(lp);
            box.addView(tv);
        }

        scroll.addView(box);
        return scroll;
    }

    protected TextView createButton(Context context, String info, boolean deleteMode) {
        TextView tv = new TextView(context);
        String[] fields = info.split("\n"); // TODO Discover if this can be replaced by API tools
        String address = fields[0];
        String title = fields[1];

        if (deleteMode) {
            title = "Delete \"" + title + "\"?";
        }

        tv.setText(title);
        tv.setTextSize(20);
        tv.setTextColor(context.getResources().getColor(R.color.white));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteMode) {
                    onDeleteNthTitle(address);
                } else {
                    onClickNthTitle(address);
                }
            }
        });

        return tv;
    }

    /* OTHER ACTIVITIES */

    /**
     * Starts a new activity to download checklists.
     */
    public void searchNewLists() {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivityForResult(intent, SAVE_REQUEST);
    }

    /**
     * Called when a checklist title is pressed and the view is not on delete mode
     *
     * @param address The checklist address
     */
    public void onClickNthTitle(String address) {
        Intent intent = new Intent(activity, ListActivity.class);
        intent.putExtra("address", address);
        activity.startActivityForResult(intent, EDIT_REQUEST);
    }

    /**
     * Called when a checklist title is pressed and the view is on delete mode
     * @param address The checklist address
     */
    public void onDeleteNthTitle(String address) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Are you sure?");
        builder.setMessage("Do you want to delete this checklist?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controller.deleteChecklist(address);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    /**
     * Asks what the user what to add to their checklist repository.
     */
    public void AskWhatToAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("How to add this?")
                .setItems(OPTIONS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case CREATE_OPTION:
                                controller.createChecklist();
                                break;
                            case DOWNLOAD_OPTION:
                                controller.searchChecklists();
                                break;
                        }
                    }
                });
        builder.create().show();
    }
}
