package br.eng.crisjr.failproof.android.view;

import android.content.Context;
import android.view.View;
import android.widget.*;
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
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Save checklist on memory after changes
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
     * Extracts checklist coded in view objects
     *
     * @param scroll The scroll containing the checklist
     * @return the string in virtual checklist format
     */
    public String extractChecklist(ScrollView scroll) {
        LinearLayout layout = (LinearLayout) scroll.getChildAt(0);
        String outlet = "";

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
