package br.eng.crisjr.failproof.android.model;

import br.eng.crisjr.failproof.android.model.system.MemoryAccess;

/**
 * Stores information for ListActivity.
 */
public class ChecklistModel {
    protected MemoryAccess memoryAccess;
    protected String address;

    public ChecklistModel(MemoryAccess ma, String ad) {
        memoryAccess = ma;
        address = ad;
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
     * Loads the list that is stored on this model.
     *
     * @return The checklist on API format.
     */
    public String loadList() {
        return loadList(this.address);
    }

    /**
     * Stores a checklist to a address on memory.
     *
     * @param address   Where to store the checklist.
     * @param checklist Data to be stored.
     */
    public void store(String address, String checklist) {
        memoryAccess.store(address, checklist);
    }
}
