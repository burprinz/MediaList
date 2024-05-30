package de.luiskun.manager.ui.windows;

import de.luiskun.manager.dataholder.Category;
import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.dataholder.Episode;
import de.luiskun.manager.ui.Konstanten;
import de.luiskun.manager.ui.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EntryInformation extends JPanel implements ActionListener {

    Window w;

    /**
     * Basic Editor
     */
    JTextField entryName, newCategoryName, newTagName;
    JComboBox categorySelector;
    JButton addNewCategory, addNewTag, resetTag;
    JSpinner amountRewatched, ratingSetter;

    /**
     * Tag Editor
     */
    JScrollPane tagScroll;
    JPanel tagPanel;
    List<JCheckBox> tagBoxes;

    /**
     * Episodes
     */
    JCheckBox withCategories, sameLength;
    JPanel categoryContent;
    JScrollPane categoryScroll;
    JSpinner sameMin, sameSec;
    JPanel lenPanel;
    List<JTextField> names;
    List<NameAmTimePicker> cats;
    JButton addNewCatOrName;

    Entry e;

    public EntryInformation(Window w) {
        this.w = w;
        e = new Entry();

        this.setLayout(null);
        this.setBounds(10, 10, Screen.WIDTH - 20, Screen.HEIGHT - 50);
        this.setBorder(Konstanten.BLACKBORDER);

        int x = 10, y = 10;

        JLabel basicInfosInfo = new JLabel("<html><u>Basic Information</u></html>");
        basicInfosInfo.setFont(basicInfosInfo.getFont().deriveFont(Font.BOLD, 20));
        basicInfosInfo.setBounds(x, y, 200, 25);
        this.add(basicInfosInfo);

        y += 30;

        JPanel basicInfosPanel = new JPanel();
        basicInfosPanel.setBounds(x, y, 320, 270);
        basicInfosPanel.setBorder(Konstanten.BLACKBORDER);
        basicInfosPanel.setLayout(null);
        this.add(basicInfosPanel);

        Font ueberschriftFont = basicInfosInfo.getFont();
        Font basicFont = basicInfosPanel.getFont().deriveFont(Font.BOLD, 15);

        {
            x = 10;
            y = 10;

            JLabel entryNameInfo = new JLabel("Entry Name");
            entryNameInfo.setBounds(x, y, 300, 15);
            entryNameInfo.setFont(basicFont);
            basicInfosPanel.add(entryNameInfo);
            y += 20;

            entryName = new JTextField();
            entryName.setBounds(x, y, 300, 25);
            basicInfosPanel.add(entryName);
            y += 50;

            JLabel categoryInfo = new JLabel("Type");
            categoryInfo.setBounds(x, y, 300, 15);
            categoryInfo.setFont(basicFont);
            basicInfosPanel.add(categoryInfo);
            x += 210;

            JLabel newCatNameInfo = new JLabel("New type Name");
            newCatNameInfo.setBounds(x, y, 90, 15);
            basicInfosPanel.add(newCatNameInfo);
            y += 20;
            x = 10;

            categorySelector = new JComboBox(w.currentCats.toArray());
            categorySelector.setBounds(x, y, 100, 25);
            basicInfosPanel.add(categorySelector);
            x += 105;

            addNewCategory = new JButton("< Add new");
            addNewCategory.setBounds(x, y, 100, 25);
            addNewCategory.addActionListener(list -> {
                String newCat = newCategoryName.getText();
                if (newCat.length() > 0) {
                    boolean isNew = true;
                    for (String s : w.currentCats) {
                        if (s.toLowerCase().equals(newCat.toLowerCase())) {
                            isNew = false;
                            break;
                        }
                    }
                    if (isNew) {
                        w.currentCats.add(newCat);
                        categorySelector.addItem(newCat);
                    }
                }
            });
            basicInfosPanel.add(addNewCategory);
            x += 105;

            newCategoryName = new JTextField();
            newCategoryName.setBounds(x, y, 90, 25);
            basicInfosPanel.add(newCategoryName);
            x = 10;
            y += 50;

            JLabel rewatchInfo = new JLabel("Rewatched");
            rewatchInfo.setBounds(x, y, 145, 15);
            rewatchInfo.setFont(basicFont);
            basicInfosPanel.add(rewatchInfo);
            y += 20;

            amountRewatched = new JSpinner();
            amountRewatched.setBounds(x, y, 145, 25);
            amountRewatched.addChangeListener(list -> {
                int val = (int) amountRewatched.getValue();
                if (val < 0) {
                    val = 0;
                    amountRewatched.setValue(val);
                }
            });
            basicInfosPanel.add(amountRewatched);
            y += 50;

            JLabel ratingInfo = new JLabel("Rating (1-10)");
            ratingInfo.setBounds(x, y, 145, 15);
            ratingInfo.setFont(basicFont);
            basicInfosPanel.add(ratingInfo);
            y += 20;

            ratingSetter = new JSpinner();
            ratingSetter.setBounds(x, y, 145, 25);
            ratingSetter.setValue(5);
            ratingSetter.addChangeListener(list -> {
                int val = (int) ratingSetter.getValue();
                if (val < 1) {
                    val = 1;
                    ratingSetter.setValue(val);
                } else if (val > 10) {
                    val = 10;
                    ratingSetter.setValue(val);
                }
            });
            basicInfosPanel.add(ratingSetter);
        }

        y = 320;
        JLabel tagsInfo = new JLabel("<html><u>Tags</u></html>");
        tagsInfo.setFont(ueberschriftFont);
        tagsInfo.setBounds(x, y, 200, 25);
        this.add(tagsInfo);
        y += 30;

        JPanel tagsPanel = new JPanel();
        tagsPanel.setBounds(x, y, 320, 290);
        tagsPanel.setBorder(Konstanten.BLACKBORDER);
        tagsPanel.setLayout(null);
        this.add(tagsPanel);

        {
            x = 10;
            y = 10;

            tagPanel = new JPanel();
            tagPanel.setLayout(new GridLayout(0, 1));

            tagScroll = new JScrollPane(tagPanel);
            tagScroll.setBounds(x, y, 300, 240);
            tagsPanel.add(tagScroll);
            y += 245;

            newTagName = new JTextField();
            newTagName.setBounds(x, y, 155, 25);
            tagsPanel.add(newTagName);
            x += 160;

            addNewTag = new JButton("Add");
            addNewTag.setBounds(x, y, 60, 25);
            addNewTag.addActionListener(list -> {
                String netTag = newTagName.getText();
                if (netTag.length() > 0) {
                    boolean isNew = true;
                    for (String s : w.currentTags) {
                        if (s.toLowerCase().equals(netTag.toLowerCase())) {
                            isNew = false;
                            break;
                        }
                    }
                    if (isNew) {
                        w.currentTags.add(netTag);
                        resetTagButtons();
                    }
                }
            });
            tagsPanel.add(addNewTag);
            x += 65;

            resetTag = new JButton("Reset");
            resetTag.setBounds(x, y, 70, 25);
            resetTag.addActionListener(list -> {
                e.getTags().clear();
                for (JCheckBox c : tagBoxes) {
                    c.setSelected(false);
                }
            });
            tagsPanel.add(resetTag);
        }
        resetTagButtons();

        x = 340;
        y = 10;

        JLabel seasonInfo = new JLabel("<html><u>Categories</u></html>");
        seasonInfo.setFont(ueberschriftFont);
        seasonInfo.setBounds(x, y, 200, 25);
        this.add(seasonInfo);
        y += 30;

        JPanel category = new JPanel();
        category.setBounds(x, y, 530, 600);
        category.setBorder(Konstanten.BLACKBORDER);
        category.setLayout(null);
        this.add(category);

        {
            x = 10;
            y = 10;

            withCategories = new JCheckBox("With categories");
            withCategories.setBounds(x, y, 120, 25);
            withCategories.addChangeListener(list -> {
                if (e.isWithCategories() != withCategories.isSelected()) {
                    updateCategories(e.isSameLength(), withCategories.isSelected());
                }
            });
            category.add(withCategories);

            x += 130;
            sameLength = new JCheckBox("Same length");
            sameLength.setBounds(x, y, 100, 25);
            sameLength.addChangeListener(list -> {
                if (sameLength.isSelected() != e.isSameLength()) {
                    updateCategories(sameLength.isSelected(), e.isWithCategories());
                }
            });
            category.add(sameLength);
            x += 110;

            lenPanel = new JPanel();
            lenPanel.setBounds(x, y, 230, 25);
            lenPanel.setLayout(null);
            category.add(lenPanel);

            x = 0;

            JLabel lenInfo = new JLabel("Length:");
            lenInfo.setBounds(x, 0, 50, 25);
            lenPanel.add(lenInfo);
            x += 50;

            sameMin = new JSpinner();
            sameMin.setBounds(x, 0, 50, 25);
            sameMin.addChangeListener(list -> {
                int val = (int) sameMin.getValue();
                if (val < 0) {
                    val = 0;
                    sameMin.setValue(val);
                }
            });
            lenPanel.add(sameMin);
            x += 55;

            JLabel minInfo = new JLabel("min");
            minInfo.setBounds(x, 0, 30, 25);
            lenPanel.add(minInfo);
            x += 30;

            sameSec = new JSpinner();
            sameSec.setBounds(x, 0, 50, 25);
            sameSec.addChangeListener(list -> {
                int val = (int) sameSec.getValue();
                if (val < 0) {
                    val = 0;
                    sameSec.setValue(val);
                }
            });
            lenPanel.add(sameSec);
            x += 55;

            JLabel secInfo = new JLabel("sec");
            secInfo.setBounds(x, 0, 30, 25);
            lenPanel.add(secInfo);

            x = 10;
            y += 30;

            categoryContent = new JPanel();
            categoryContent.setLayout(new GridLayout(0, 1));

            categoryScroll = new JScrollPane(categoryContent);
            categoryScroll.setBounds(x, y, 510, 550);
            category.add(categoryScroll);

            names = new ArrayList<>();
            cats = new ArrayList<>();
        }

        updateCategories(withCategories.isSelected(), sameLength.isSelected());

    }

    private void resetTagButtons() {
        tagPanel.removeAll();
        if (tagBoxes != null) {
            tagBoxes.clear();
        }
        tagBoxes = new ArrayList<>();

        tagPanel.setPreferredSize(new Dimension(200, w.currentTags.size()*25));

        for (String tag : w.currentTags) {
            JCheckBox box = new JCheckBox(tag);
            tagPanel.add(box);
            tagBoxes.add(box);
        }

        if (e != null && e.getTags() != null) {
            for (String sel : e.getTags()) {
                for (JCheckBox b : tagBoxes) {
                    if (b.getText().equals(sel)) {
                        b.setSelected(true);
                        break;
                    }
                }
            }
        }

        tagPanel.revalidate();
        tagPanel.repaint();

    }

    private void updateCategories(boolean len, boolean cat) {
        lenPanel.setVisible(len);


        e.setSameLength(len);
        e.setWithCategories(cat);

        categoryContent.removeAll();

        if (e.isSameLength()) {
            if (e.isWithCategories()) {
                updateSameWith();
            } else {
                updateSameWithout();
            }
        } else {
            if (e.isWithCategories()) {
               updateNotWith();
            } else {
                updateNotWithout();
            }
        }
    }

    private void updateSameWith() {
        // Kategorienamen und Anzahl
        int id = 1;

        categoryContent.removeAll();
        if (cats != null) {
            cats.clear();
        }
        cats = new ArrayList<>();

        int size = 1;
        if (e != null && e.getCategories() != null)  {
            for (Category c : e.getCategories()) {
                NameAmTimePicker n = new NameAmTimePicker(id, this);
                n.setBorder(Konstanten.BLACKBORDER);
                n.name.setText(c.getName());
                n.amount.setValue(c.getAmEpisodes());
                cats.add(n);
                categoryContent.add(n);
            }
            size += e.getCategories().size();
        }

        addNewCatOrName = new JButton("New");
        categoryContent.add(addNewCatOrName);

        categoryContent.setPreferredSize(new Dimension(500, 30*size));
        categoryContent.setBorder(Konstanten.BLACKBORDER);
        categoryContent.repaint();
        categoryContent.revalidate();

    }

    private void updateSameWithout() {
        // Namen

        categoryContent.removeAll();
        if (names != null) {
            names.clear();
        }
        names = new ArrayList<>();

        int am = 1;

        if (e != null && e.getEpisodes() != null)  {
            for (Episode e : e.getEpisodes()) {
                JTextField jtf = new JTextField();
                jtf.setText(e.getName());
                names.add(jtf);
                categoryContent.add(jtf);
            }
            am += e.getEpisodes().size();
        }

        addNewCatOrName = new JButton("New");
        categoryContent.add(addNewCatOrName);

        categoryContent.setPreferredSize(new Dimension(500, am*25));
    }

    private void updateNotWith() {
        // Kategoriennamen, Anzahl, Laenge
        int id = 0;

        categoryContent.removeAll();
        if (cats != null) {
            cats.clear();
        }
        cats = new ArrayList<>();

        int size = 1;
        if (e != null && e.getCategories() != null)  {
            for (Category c : e.getCategories()) {
                NameAmTimePicker n = new NameAmTimePicker(id, this);
                n.setBorder(Konstanten.BLACKBORDER);
                n.name.setText(c.getName());
                n.amount.setValue(c.getAmEpisodes());
                int secs = c.getLength()%60;
                int mins = c.getLength()/60;
                n.minutes.setValue(mins);
                n.seconds.setValue(secs);
                cats.add(n);
                categoryContent.add(n);
            }
            size += e.getCategories().size();
        }

        addNewCatOrName = new JButton("New");
        categoryContent.add(addNewCatOrName);

        categoryContent.setPreferredSize(new Dimension(500, 50*size));
        categoryContent.setBorder(Konstanten.BLACKBORDER);
        categoryContent.repaint();
        categoryContent.revalidate();
    }

    private void updateNotWithout() {
        // Namen und Laenge
        int id = 2;

        categoryContent.removeAll();
        if (cats != null) {
            cats.clear();
        }
        cats = new ArrayList<>();

        int size = 1;
        if (e != null && e.getCategories() != null)  {
            for (Category c : e.getCategories()) {
                NameAmTimePicker n = new NameAmTimePicker(id, this);
                n.setBorder(Konstanten.BLACKBORDER);
                n.name.setText(c.getName());
                int secs = c.getLength()%60;
                int mins = c.getLength()/60;
                n.minutes.setValue(mins);
                n.seconds.setValue(secs);
                cats.add(n);
                categoryContent.add(n);
            }
            size += e.getCategories().size();
        }

        addNewCatOrName = new JButton("New");
        categoryContent.add(addNewCatOrName);

        categoryContent.setPreferredSize(new Dimension(500, 50*size));
        categoryContent.setBorder(Konstanten.BLACKBORDER);
        categoryContent.repaint();
        categoryContent.revalidate();

    }

    public void setEntry(Entry e) {
        this.e = e;
        entryName.setText(e.getName());
        ratingSetter.setValue(e.getRating());
        amountRewatched.setValue(e.getReAmount());
        withCategories.setSelected(e.isWithCategories());
        sameLength.setSelected(e.isSameLength());

        resetTagButtons();
        updateCategories(e.isSameLength(), e.isWithCategories());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
    }
}
