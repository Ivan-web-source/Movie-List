package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MovieCollectionUI implements ActionListener {
    private JFrame frame;
    private JButton addButton;
    private JButton viewButton;
    private JButton unwatchedButton;
    private JButton saveButton;
    private JButton loadButton;
    private JPanel panel;

    public MovieCollectionUI() {
        frame = new JFrame();
        addButton = new JButton("Add Movie");
        viewButton = new JButton("View Movie List");
        unwatchedButton = new JButton("View Unwatched Movies");
        saveButton = new JButton("Save Movie List");
        loadButton = new JButton("Load Movie List");
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 50));
        panel.setLayout(new GridLayout(10, 10));

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(unwatchedButton);
        panel.add(saveButton);
        panel.add(loadButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Collection");
        frame.pack();
        frame.setVisible(true);
    }

    public void actionListener() {
        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        unwatchedButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
