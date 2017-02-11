package br.eng.crisjr.failproof.android.model;

import br.eng.crisjr.failproof.android.model.system.MemoryAccess;
import br.eng.crisjr.failproof.web;

/**
 * Model to store the information for the MainActivity
 */
public class MainModel {
    protected boolean mode = false;
    protected String[] titles = null;
    protected String[] addresses = null;
    protected MemoryAccess memoryAccess;

    public MainModel(MemoryAccess ma) {
        memoryAccess = ma;
    }

    /**
     * The current MainActivity mode.
     *
     * @return true if the activity is on delete mode, false otherwise.
     */
    public boolean getMode() {
        return mode;
    }

    public boolean setMode(boolean mode) {
        this.mode = mode;
        return getMode();
    }

    public String[] getTitles() {
        return this.titles;
    }

    /**
     * Saves on RAM important stuff from HD.
     */
    public void retrieveStuff() {
        setStuff(memoryAccess.loadLists());
    }

    /**
     * Loads the pairs of titles and addresses from HD.
     *
     * @return An array containing pairs of title and addresses, as described on API.
     */
    public String[] loadStuff() {
        return memoryAccess.loadStuff();
    }

    /**
     * Sets the state based on inlet.
     * @param stuff An array containing pairs of title and addresses, as described on API.
     */
    public void setStuff(String[] stuff) {
        if (stuff != null) {
            this.titles = web.toLists(stuff);
            this.addresses = web.toLinks(stuff);
        }
    }

    /**
     * Loads a checklist from memory depending on its address.
     *
     * @param address The code to retrieve a list.
     * @return The checklist on API format.
     */
    public String loadList(String address) {
        return memoryAccess.loadList(address).toString();
    }

    /**
     * Deletes a checklist from memory
     *
     * @param address The checklist address on memory
     */
    public void removeList(String address) {
        memoryAccess.remove(address);
    }

    /**
     * Creates an empty checklist for edition
     *
     * @param template The checklist to be used as template in API descrition
     */
    public String createEmptyChecklist(String template) {
        return memoryAccess.append(template);
    }
}
