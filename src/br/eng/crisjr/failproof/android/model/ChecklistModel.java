package br.eng.crisjr.failproof.android.model;

import br.eng.crisjr.failproof.android.model.entity.Checklist;
import br.eng.crisjr.failproof.android.model.system.MemoryAccess;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * Removes an item from the checklist
     *
     * @param rawChecklist the raw checklist
     * @param index        the index of the item to be removed, 0-indexed
     * @return the new checklist
     */
    public String removeItem(String rawChecklist, int index) {
        // Building data
        Checklist inlet = new Checklist(rawChecklist);
        List<String> data = new LinkedList<String>();
        int limit = inlet.getItems().length;
        data.add(inlet.getTitle());
        if (limit == 1) {
            data.add("New item");
        } else {
            String[] items = inlet.getItems();
            for (int i = 0; i < limit; i++) {
                if (index != i) {
                    data.add(items[i]);
                }
            }
        }

        // Converting data to valid output
        String[] filtered = new String[data.size()];
        for (int i = 0; i < filtered.length; i++) {
            filtered[i] = data.get(i);
        }
        Checklist outlet = new Checklist(filtered);

        return outlet.toString();
    }
}
