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

    public MovieCollectionUI() {
        JFrame frame = new JFrame();
        JButton addButton = new JButton("Add Movie");
        JButton viewButton = new JButton("View Movie List");
        JButton unwatchedButton = new JButton("View Unwatched Movies");
        JButton saveButton = new JButton("Save Movie List");
        JButton loadButton = new JButton("Load Movie List");
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 50));
        panel.setLayout(new GridLayout(10, 10));
        panel.add(addButton);
        addButton.addActionListener(this);
        panel.add(viewButton);
        viewButton.addActionListener(this);
        panel.add(unwatchedButton);
        unwatchedButton.addActionListener(this);
        panel.add(saveButton);
        saveButton.addActionListener(this);
        panel.add(loadButton);
        loadButton.addActionListener(this);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Collection");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
