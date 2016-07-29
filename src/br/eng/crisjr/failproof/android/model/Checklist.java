package br.eng.crisjr.failproof.android.model;

/**
 * Class to model checklists
 */
public class Checklist
{
    private String title;
    private String[] items;

    public Checklist(String[] list)
    {
        int limit = list.length - 1;

        title = list[0];
        items = new String[limit];
        for (int i = 1; i < limit; ++i) {
            items[i - 1] = list[i];
        }
    }


    public String toString()
    {
        String outlet = title;

        for (String item: items)
            outlet += "\n" + item;

        return outlet;
    }
}
