package br.eng.crisjr.failproof.android.model;

/**
 * Class to model checklists
 */
public class Checklist
{
    private String title;
    private String[] items;
    private boolean[] checked;

    public Checklist(String[] list)
    {
        int limit = list.length - 1;

        title = list[0];
        items = new String[limit];
        checked = new boolean[limit];
        for (int i = 1; i < limit; ++i) {
            items[i - 1] = list[i];
            checked[i - 1] = false;
        }
    }

    public Checklist(String inlet) {
        String[] data = inlet.split("\n");
        int limit = data.length;
        title = data[0];
        items = new String[limit - 1];
        checked = new boolean[limit - 1];

        for (int i = 1; i < limit; ++i) {
            items[i - 1] = data[i].substring(1);
            if (data[i].charAt(0) == '*') {
                checked[i - 1] = true;
            } else {
                checked[i - 1] = false;
            }
        }
    }


    public String toString()
    {
        String outlet = title;
        int limit = items.length;

        for (int i = 0; i < limit; ++i) {
            String box = "\n";
            if (checked[i]) {
                box += "*";
            } else {
                box += "-";
            }
            outlet += box + items[i];
        }


        return outlet;
    }
    /*
    # Real checklist
    Title:
    [ ] Item 1
    [ ] Item 2
    [x] Checked item
    [ ] Another item

    # Virtual checklist
    Title
    -Item 1
    -Item 2
    *Checked item
    -Another item
     */
}
