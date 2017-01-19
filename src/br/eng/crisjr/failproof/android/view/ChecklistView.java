package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.view.View;
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
     * Draws the given checklist on screen.
     *
     * @param rawChecklist The string on android format.
     */
    public void setChecklist(String rawChecklist) {
        Checklist checklist = new Checklist(rawChecklist);
        TextView title = (TextView) activity.findViewById(R.id.textChecklistTitle);
        title.setText(checklist.getTitle());
        LinearLayout layout = drawChecklist(checklist);
        layout.setId(R.id.layoutChecklist);
        ScrollView scroll = (ScrollView) activity.findViewById(R.id.scrollChecklist);
        scroll = replaceScroll(scroll, layout);
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
            TextView text = new TextView(context);
            text.setText(items[i]);
            LinearLayout line = new LinearLayout(context);
            line.addView(button);
            line.addView(text);
            layout.addView(line);
        }

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
        TextView title = (TextView) activity.findViewById(R.id.textChecklistTitle);
        String outlet = title.getText() + "\n";
        ScrollView scroll = (ScrollView) activity.findViewById(R.id.scrollChecklist);
        LinearLayout layout = (LinearLayout) scroll.getChildAt(0);

        for (int i = 0; i < layout.getChildCount(); ++i) {
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
