package de.luiskun.manager.ui.windows.entryEditing;

import javax.swing.*;
import java.awt.*;

public class NameAmTimePicker extends JPanel {

    public JSpinner minutes, seconds, amount;
    public JTextField name;
    public JButton removeButton;

    EntryInformation w;

    public NameAmTimePicker(int mode, EntryInformation w) {
        this.setPreferredSize(new Dimension(500, 25));
        this.setLayout(null);

        this.w = w;

        int x = 0;
        name = new JTextField();
        name.setBounds(x, 0, 300, 25);
        this.add(name);
        x += 300;

        switch (mode) {
            case 0:
                amount = new JSpinner();
                amount.setBounds(x, 0, 50, 25);
                this.add(amount);
                x += 50;

                minutes = new JSpinner();
                minutes.setBounds(x, 0, 50, 25);
                this.add(minutes);
                x += 50;

                seconds = new JSpinner();
                seconds.setBounds(x, 0, 50, 25);
                this.add(seconds);
                x += 50;
                break;
            case 1:
                amount = new JSpinner();
                amount.setBounds(x, 0, 150, 25);
                this.add(amount);
                x += 150;
                break;
            case 2:
                minutes = new JSpinner();
                minutes.setBounds(x, 0, 75, 25);
                this.add(minutes);
                x += 75;

                seconds = new JSpinner();
                seconds.setBounds(x, 0, 75, 25);
                this.add(seconds);
                x += 75;
                break;
            case 3:
                name.setBounds(0, 0, 440, 25);
                break;
        }

        removeButton = new JButton("rem");
        removeButton.setBounds(440, 0, 60, 25);
        removeButton.addActionListener(w);
        this.add(removeButton);

    }

    public String getName() {
        return name.getText();
    }

    public int getAm() {
        return (int)amount.getValue();
    }

    public int getTime () {
        return (int)minutes.getValue()*60 + (int)seconds.getValue();
    }

}
