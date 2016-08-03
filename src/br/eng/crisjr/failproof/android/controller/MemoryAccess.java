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
                outlet[i] = lists[i].toString();
            }
        }

        return outlet;
    }

    /**
     * Loads a checklist from memory
     *
     * @param address The list address
     * @return The chosen checklist
     */
    public Checklist loadList(String address) {
        return Memory.retrieveList(context, address);
    }

    /**
     * Loads basic information from memory
     *
     * @param context The application's context
     * @return An array containing pairs "Address\nTitle"
     */
    public String[] loadStuff(Context context)
    {
        Checklist[] lists = Memory.retrieveAllLists(context);
        String[] codes = Memory.retrieveAllCodes(context);
        String[] outlet = null;

        if (lists != null) {
            int limit = lists.length;
            outlet = new String[limit];
            for (int i = 0; i < limit; ++i) {
                outlet[i] = codes[i] + "\n" + lists[i].getTitle();
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

    /**
     * Erases everything in memory
     */
    public void resetMemory() {
        Memory.resetMemory(context);
    }
}
