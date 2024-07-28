package ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MovieCollectionUI implements ActionListener {
    private JFrame frame;
    private JLabel introLabel;
    private JButton addButton;
    private JButton viewButton;
    private JButton unwatchedButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JPanel panel;

    /*
     * EFFECTS: handles user input on each action
     *          button 1 allows user to add a movie
     *          button 2 allows user to view movie list
     *          button 3 allows user to view unwatched movie list
     *          button 4 allows user to save movie list
     *          button 5 allows user to load movie list
     *          button 6 allows user to exit the application
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MovieCollectionUI() {
        frame = new JFrame();
        introLabel = new JLabel("Welcome to your Movie Collection");
        addButton = new JButton("Add Movie");
        viewButton = new JButton("View Movie List");
        unwatchedButton = new JButton("View Unwatched Movies");
        saveButton = new JButton("Save Movie List");
        loadButton = new JButton("Load Movie List");
        quitButton = new JButton("Exit Application");
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 50));
        panel.setLayout(new GridLayout(10, 10));

        panel.add(introLabel);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(unwatchedButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(quitButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Collection");
        frame.pack();
        frame.setVisible(true);
    }

    public void actionListener() {
        addButton.addActionListener(this);
        addButton.setActionCommand("AddMovie");
        viewButton.addActionListener(this);
        addButton.setActionCommand("ViewList");
        unwatchedButton.addActionListener(this);
        addButton.setActionCommand("ViewUnwatchedList");
        saveButton.addActionListener(this);
        addButton.setActionCommand("SaveList");
        loadButton.addActionListener(this);
        addButton.setActionCommand("LoadList");
        quitButton.addActionListener(this);
        addButton.setActionCommand("Exit");
    }

    @Override
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "AddMovie":
                frame.dispose();
                addMovie();
                break;
            case "ViewList":
                frame.dispose();
                viewList();
                break;
            case "ViewUnwatchedList":
                frame.dispose();
                viewUnwatchedList();
                break;
            case "SaveList":
                saveList();
                JOptionPane.showMessageDialog(null, "Data successfully saved!",
                        "Save Data", JOptionPane.WARNING_MESSAGE);
                break;
            case "LoadList":
                loadList();
                JOptionPane.showMessageDialog(null, "Data successfully loaded!",
                        "Load Data", JOptionPane.WARNING_MESSAGE);
                break;
            case "Exit":
                frame.dispose();
                System.exit(0);
        }
    }

    private void loadList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadList'");
    }

    private void saveList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveList'");
    }

    private void viewUnwatchedList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewUnwatchedList'");
    }

    private void viewList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewList'");
    }

    private void addMovie() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMovie'");
    }
}
