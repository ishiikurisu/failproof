package br.eng.crisjr.failproof.android.controller;

import android.content.Context;
import br.eng.crisjr.failproof.android.model.*;

/**
 * Access to phone's memory, looking for data
 */
public class MemoryAccess
{
    private Context context;

    public MemoryAccess(Context context)
    {
        this.context = context;
    }

    /**
     * Loads all lists saved on memory
     *
     * @return the lists in virtual memory format
     */
    public String[] loadLists()
    {
        Checklist[] lists = Memory.retrieveAllLists(context);
        String[] outlet = null;

        if (lists != null) {
            outlet = new String[lists.length];
            for (int i = 0; i < lists.length; ++i) {
                if (lists[i] != null) {
                    outlet[i] = lists[i].toString();
                } else {
                    return null;
                }
            }
        }

        return outlet;
    }

    /**
     * Adds a new checklist to memory
     * @param list the list to be saved as provided by the internet
     */
    public void createList(String[] list) {
        Checklist checklist = new Checklist(list);
        Memory.store(this.context, checklist);
    }

    public void resetMemory() {
        Memory.resetMemory(context);
    }
}
