package com.swing;

import com.database.ConnectionProvider;
import com.models.Note;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class App {
    private JButton button1;
    private JTextField textField1;
    private JList list1;
    private JPanel mainPanel;
    private ConnectionProvider conn = new ConnectionProvider();

    public App() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (conn.setNote(textField1.getText()))
                    updateList();
                textField1.setText("");
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                deleteFromList();
            }
        });
    }

    public static void main(String[] args) {
        App ui = new App();
        JFrame frame = new JFrame("Ulohy");
        frame.setContentPane(ui.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ui.init();
    }

    private void init() {
        updateList();
    }

    private void updateList() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        List<Note> notes = conn.getAllNotes();
        for (Note n : notes) {
            model.addElement(n.getText());
        }
        list1.setModel(model);
    }

    private void deleteFromList() {
        DefaultListModel<String> model = (DefaultListModel) list1.getModel();
        int selectedIndex = list1.getSelectedIndex();
        if (selectedIndex != -1) {
            conn.deleteNote(model.elementAt(selectedIndex));
            model.remove(selectedIndex);
        }
    }
}
