package br.eng.crisjr.failproof.android.model;

import java.util.ArrayList;

public class Checklist {
    private String name;
    private ArrayList<String> stuff = new ArrayList<>();
    private ArrayList<Boolean> checks = new ArrayList<>();

    public Checklist(String inlet) {
        String[] data = inlet.split("\n");
        checks = new ArrayList<>();
        name = data[0];
        for (int i = 1; i < data.length; ++i) {
            stuff.add(data[i]);
            checks.add(false);
        }
    }

    public String getName() {
        return this.name;
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
