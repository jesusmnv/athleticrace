import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class AthleticRaceInterface extends JFrame {

  public AthleticRaceInterface() {
    super("Athletic Race");
    setLayout(new BorderLayout());
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create top panel with choice, timer, and score
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new GridLayout(2, 2));

    JLabel recordLabel = new JLabel("Register runner");
    JTextField recordText = new JTextField();
    JButton recordButton = new JButton("Save");

    recordLabel.setBounds(10, 10, 100, 30);
    recordText.setBounds(120, 10, 200, 60);
    recordButton.setBounds(10, 40, 100, 30);

    topPanel.add(recordLabel);
    topPanel.add(new JLabel());
    topPanel.add(recordText);
    topPanel.add(recordButton);

    add(topPanel, BorderLayout.NORTH);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(3, 1));

    JLabel runnersLabel = new JLabel("Runners registered");
    JTextArea runnersArea = new JTextArea();
    JLabel resultsLabel = new JLabel("Scoreboard");

    runnersLabel.setBounds(10, 100, 100, 30);
    runnersArea.setBounds(10, 130, 310, 300);
    resultsLabel.setBounds(10, 490, 100, 30);

    centerPanel.add(runnersLabel);
    centerPanel.add(runnersArea);
    centerPanel.add(resultsLabel);

    add(centerPanel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new GridLayout(1, 2));

    JPanel resultsPanel = new JPanel();
    resultsPanel.setLayout(new GridLayout(1, 1));
    resultsPanel.setBounds(10, 520, 310, 60);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(3, 1));
    buttonsPanel.setBounds(10, 450, 310, 60);

    JTextArea resultsArea = new JTextArea();
    JButton starButton = new JButton("Start");
    JButton stopButton = new JButton("Stop");
    JButton reseButton = new JButton("Reset");

    resultsArea.setBounds(10, 520, 310, 60);
    starButton.setBounds(10, 450, 100, 30);
    starButton.setBounds(10, 450, 100, 30);
    reseButton.setBounds(230, 450, 100, 30);
    stopButton.setBounds(120, 450, 100, 30);

    resultsPanel.add(resultsArea);
    buttonsPanel.add(starButton);
    buttonsPanel.add(stopButton);
    buttonsPanel.add(reseButton);

    bottomPanel.add(resultsPanel);
    bottomPanel.add(buttonsPanel);

    add(bottomPanel, BorderLayout.SOUTH);

    setSize(600, 400);
    setVisible(true);
  }

  public static void main(String[] args) {
    new AthleticRaceInterface();
  }

}
