package br.eng.crisjr.failproof.android.model;

import br.eng.crisjr.failproof.web;

/**
 * Model to store the information for the MainActivity
 */
public class MainModel {
    protected boolean mode = false;
    protected String[] titles = null;
    protected String[] addresses = null;

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

    public void setStuff(String[] stuff) {
        this.titles = web.toLists(stuff);
        this.addresses = web.toLinks(stuff);
    }

    public String getNthTitle(int n) {
        return this.titles[n];
    }

    public String getNthAddress(int n) {
        return this.addresses[n];
    }
}
