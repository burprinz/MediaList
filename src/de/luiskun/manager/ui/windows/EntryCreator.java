package de.luiskun.manager.ui.windows;

public class EntryCreator extends Window {

    EntryInformation ei;

    public EntryCreator() {
        super();
    }

    @Override
    public void initComponents() {
        ei = new EntryInformation(this);
        ei.setEntry(currentEntries.get(0));
        this.add(ei);
    }

}
