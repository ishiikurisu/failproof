package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import br.eng.crisjr.failproof.android.ListActivity;
import br.eng.crisjr.failproof.android.R;
import br.eng.crisjr.failproof.android.controller.ChecklistController;
import br.eng.crisjr.failproof.android.model.system.MemoryAccess;
import br.eng.crisjr.failproof.android.model.entity.Checklist;

/**
 * View class for ListActivity
 */
public class ChecklistView {
    protected ListActivity activity;
    protected ChecklistController controller;
    protected boolean editMode = false;

    public ChecklistView(ListActivity activity) {
        this.activity = activity;
    }

    /**
     * Determines once the controller for this view.
     *
     * @param controller The candidate for controller.
     * @return The set controller.
     */
    public ChecklistController setController(ChecklistController controller) {
        if (this.controller == null) {
            this.controller = controller;
        }
        return this.controller;
    }

    /**
     * Changes the current display mode of this view.
     */
    public void toggleMode() {
        editMode = !editMode;
    }

    /**
     * Gets the current edit mode.
     */
    public boolean getEditMode() {
        return editMode;
    }

    /**
     * Draws the given checklist on screen.
     *
     * @param rawChecklist The string on android format.
     */
    public void setChecklist(String rawChecklist) {
        Checklist checklist = new Checklist(rawChecklist);

        // Setting edit/save button
        // TODO Change edit/save button text depending

        // Adding title
        View title = createTitle(checklist);
        LinearLayout header = (LinearLayout) activity.findViewById(R.id.layoutChecklistHeader);
        header.removeAllViews();
        header.addView(title);

        // Adding items
        // TODO Enable checklist items' edition
        LinearLayout stuff = (editMode) ? editChecklist(checklist) : drawChecklist(checklist);
        stuff.setId(R.id.layoutChecklist);
        ScrollView scroll = (ScrollView) activity.findViewById(R.id.scrollChecklist);
        scroll = replaceScroll(scroll, stuff);
    }

    /**
     * Creates a title depending on the current edition mode.
     *
     * @return An appropriate title.
     */
    protected View createTitle(Checklist checklist) {
        if (editMode) {
            EditText edit = new EditText(activity.getApplicationContext());
            edit.setText(checklist.getTitle());
            edit.setTextSize(30);
            edit.setTextColor(activity.getApplicationContext().getResources().getColor(R.color.white));
            // TODO Make title appear on the center
            return edit;
        } else {
            TextView title = new TextView(activity.getApplicationContext());
            title.setText(checklist.getTitle());
            title.setTextSize(30);
            title.setTextColor(activity.getApplicationContext().getResources().getColor(R.color.white));
            // TODO Make title appear on the center
            return title;
        }
    }

    /**
     * @return Gets the current title
     */
    protected String getTitle() {
        String title;
        LinearLayout header = (LinearLayout) activity.findViewById(R.id.layoutChecklistHeader);

        if (editMode) {
            EditText edit = (EditText) header.getChildAt(0);
            title = edit.getText().toString();
        } else {
            TextView text = (TextView) header.getChildAt(0);
            title = text.getText().toString();
        }

        return title;
    }

    /**
     * Loads the chosen checklist from memory
     *
     * @param context The application's context
     * @param address The list address on memory
     * @return The chosen checklist
     */
    public Checklist loadList(Context context, String address) {
        return new MemoryAccess(context).loadList(address);
    }

    /**
     * Creates a visual representation for the checklist
     *
     * @param checklist The checklist to be added
     * @return The layout containing the checklist stuff
     */
    public LinearLayout drawChecklist(Checklist checklist) {
        Context context = activity.getApplicationContext();
        LinearLayout layout = new LinearLayout(context);
        String[] items = checklist.getItems();
        boolean[] checked = checklist.getChecked();
        int limit = items.length;
        int i;

        // Setting up parent view
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        // Setting up each line
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        for (i = 0; i < limit; ++i) {
            RadioButton button = new RadioButton(context);
            button.setChecked(checked[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                // TODO Call method from view, not from activity
                public void onClick(View v) {
                    activity.radio_onClick(v);
                }
            });
            button.setTextSize(20);
            button.setTextColor(context.getResources().getColor(R.color.white));
            TextView text = new TextView(context);
            text.setText(items[i]);
            text.setTextSize(20);
            text.setTextColor(context.getResources().getColor(R.color.white));
            LinearLayout line = new LinearLayout(context);
            line.setLayoutParams(lp);
            line.addView(button);
            line.addView(text);
            layout.addView(line);
        }

        return layout;
    }

    /**
     * Creates a layout that enables the edition of checklist items
     *
     * @param checklist The current checklist items
     * @return The layout containing the editablec checklist
     */
    public LinearLayout editChecklist(Checklist checklist) {
        Context context = activity.getApplicationContext();
        LinearLayout layout = new LinearLayout(context);
        String[] items = checklist.getItems();
        int limit = items.length;
        int i;

        // Setting up parent view
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        // Setting up each line
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        for (i = 0; i < limit; ++i) {
            TextView cross = new TextView(context);
            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Delete current item from checklist
                }
            });
            cross.setText("X ");
            cross.setTextSize(20);
            cross.setTextColor(context.getResources().getColor(R.color.white));
            EditText text = new EditText(context);
            text.setText(items[i]);
            text.setTextSize(20);
            text.setTextColor(context.getResources().getColor(R.color.white));
            LinearLayout line = new LinearLayout(context);
            line.setLayoutParams(lp);
            line.addView(cross);
            line.addView(text);
            layout.addView(line);
        }

        // Adding + button
        Button buttonAdd = new Button(context);
        buttonAdd.setText("+");
        buttonAdd.setTextSize(20);
        buttonAdd.setTextColor(context.getResources().getColor(R.color.white));
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Delete current item from checklist
            }
        });
        layout.addView(buttonAdd);

        return layout;
    }

    /**
     * Replaces the layout inside scroll to a new one
     *
     * @param scroll The parent view
     * @param layout The new child view
     * @return The parent with its new child
     */
    public ScrollView replaceScroll(ScrollView scroll, LinearLayout layout) {
        scroll.removeAllViews();
        scroll.addView(layout);
        return scroll;
    }

    /**
     * Retrieves the checklist that is represented on the current screen.
     *
     * @return The checklist on API format.
     */
    public String retrieveChecklist() {
        String title = getTitle();
        String outlet = title + "\n";
        ScrollView scroll = (ScrollView) activity.findViewById(R.id.scrollChecklist);
        LinearLayout layout = (LinearLayout) scroll.getChildAt(0);
        int limit = layout.getChildCount();

        for (int i = 0; i < limit; ++i) {
            LinearLayout line = (LinearLayout) layout.getChildAt(i);
            RadioButton button = (RadioButton) line.getChildAt(0);
            TextView text = (TextView) line.getChildAt(1);

            outlet += (button.isChecked()) ? "*" : "-";
            outlet += text.getText().toString();
            outlet += "\n";
        }

        return outlet;
    }
}
