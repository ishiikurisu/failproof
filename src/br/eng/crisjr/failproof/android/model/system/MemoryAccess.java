package br.eng.crisjr.failproof.android.model.system;

import android.content.Context;
import br.eng.crisjr.failproof.android.model.entity.Checklist;
import br.eng.crisjr.failproof.android.model.entity.Memory;

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
     * @return the lists in virtual memory format, or null if there are no lists on memory.
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
     * @return An array containing pairs "Address\nTitle"
     */
    public String[] loadStuff()
    {
        Checklist[] lists = Memory.retrieveAllLists(context);
        String[] codes = Memory.retrieveAllCodes(context);
        String[] outlet = null;

        if (lists != null) {
            int limit = lists.length;
            outlet = new String[limit];
            for (int i = 0; i < limit; ++i) {
                outlet[i] = lists[i].getTitle() + ":" + codes[i];
            }
        }

        return outlet;
    }

    /**
     * Adds a new checklist to memory
     * @param list the list to be saved as provided by the internet
     */
    public void createList(String list) {
        Checklist checklist = new Checklist(list);
        Memory.store(this.context, checklist);
    }

    /**
     * Erases everything in memory
     */
    public void resetMemory() {
        Memory.resetMemory(context);
    }

    /**
     * Stores raw data on a given memory address
     *
     * @param address where to store
     * @param raw     what to store
     */
    public void store(String address, String raw) {
        Checklist checklist = new Checklist(raw);
        Memory.store(context, address, checklist);
    }

    /**
     * Deletes data from memory
     *
     * @param address Memory reference
     */
    public void remove(String address) {
        Memory.remove(context, address);
    }

    /**
     * Adds the given checklist to the next empty memory address
     *
     * @param raw The raw data
     */
    public String append(String raw) {
        String address = Memory.next(context);
        store(address, raw);
        return address;
    }
}
