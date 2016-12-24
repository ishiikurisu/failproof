package br.eng.crisjr.failproof.android.model;

/**
 * Model class to store LinkActivity information.
 */
public class LinkModel {
    protected String link;
    protected String[] list = null;

    public LinkModel(String link) {
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
}
