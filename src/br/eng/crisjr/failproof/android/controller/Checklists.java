package br.eng.crisjr.failproof.android.controller;

import br.eng.crisjr.failproof.android.model.Checklist;

import java.util.ArrayList;
import java.util.HashMap;

public class Checklists {
    private ArrayList<String> stuff = new ArrayList<>();
    private HashMap<String, Checklist> table = new HashMap<>();

    public Checklists() {

    }

    public void addChecklist(String inlet) {
        Checklist item = new Checklist(inlet);
        String what = item.getName();
        stuff.add(what);
        table.put(what, item);
    }

    public String[] getStuff() {
        int limit = this.stuff.size();
        String[] outlet = new String[limit];

        for (int i = 0; i < limit; ++i) {
            outlet[i] = this.stuff.get(i);
        }

        return outlet;
    }
}
