package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import br.eng.crisjr.failproof.android.ListActivity;
import br.eng.crisjr.failproof.android.controller.MemoryAccess;
import br.eng.crisjr.failproof.android.model.Checklist;

/**
 * View class for ListActivity
 */
public class ChecklistView {
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
     * @param context   The application's context
     * @param activity  The checklist activity
     * @param checklist The checklist to be added
     * @return The layout containing the checklist stuff
     */
    public LinearLayout drawChecklist(Context context, ListActivity activity, Checklist checklist) {
        LinearLayout layout = new LinearLayout(context);
        String[] items = checklist.getItems();
        boolean[] checked = checklist.getChecked();
        int limit = items.length;

        // Setting up parent view
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        // Setting up each line
        for (int i = 0; i < limit; ++i) {
            // TODO make each line good looking
            RadioButton button = new RadioButton(context);
            button.setChecked(checked[i]);
            TextView text = new TextView(context);
            text.setText(items[i]);
            LinearLayout line = new LinearLayout(context);
            line.addView(button);
            line.addView(text);
            layout.addView(line);
            // TODO Save checklist on memory after changes
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
}
