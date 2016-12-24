package br.eng.crisjr.failproof.android.model;

import br.eng.crisjr.failproof.android.model.system.MemoryAccess;

/**
 * Model class to store LinkActivity information.
 */
public class LinkModel {
    protected String link;
    protected String[] list = null;
    protected MemoryAccess memory = null;

    public LinkModel(MemoryAccess access, String link) {
        memory = access;
        this.link = link;
    }

    public String getLink() {
        return this.link;
    }

    public String[] getList() {
        return this.list;
    }

    public String[] setList(String[] list) {
        if (this.list == null) {
            this.list = list;
        }
        return getList();
    }

    public void store() {
        memory.createList(list);
    }
}
