package br.eng.crisjr.failproof.android.view;

import android.content.Context;
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
}
