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
                updateList();
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("selected "+ e.toString());
                System.out.println(e.);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void updateList() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        List<Note> notes = conn.getAllNotes();
        for(Note n: notes){
            model.addElement(n.getText());
        }
        list1.setModel(model);
    }
}
