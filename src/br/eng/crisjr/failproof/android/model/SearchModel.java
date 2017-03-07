package br.eng.crisjr.failproof.android.model;

import br.eng.crisjr.failproof.web;

/**
 * Class to store downloaded checklists
 */
public class SearchModel {
    protected String[] lists = null;
    protected String[] links = null;

    public SearchModel() {

    }

    public void addLists(String[] rawData) {
        this.lists = web.toTitles(rawData);
        this.links = web.toLinks(rawData);
    }

    public String[] getLists() {
        return this.lists;
    }

    public String[] getLinks() {
        return this.links;
    }
}
