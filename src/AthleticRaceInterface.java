import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AthleticRaceInterface extends JFrame {

  private static final int MAX_RUNNERS = 5;
  private Runner[] runners = new Runner[MAX_RUNNERS];
  private int runnerCount = 0;
  private JTextArea runnersArea;
  private JTextArea resultsArea;
  private JTextField recordText;
  private JButton startButton;
  private JButton exitButton;
  private JButton resetButton;
  private JPanel progressPanel;
  private ProgressManager progressManager;

  public AthleticRaceInterface() {
    super("Athletic Race");
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel topPanel = new JPanel();
    topPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    topPanel.setLayout(new GridLayout(2, 2, 10, 10));

    JLabel recordLabel = new JLabel("Register runner");
    recordText = new JTextField();
    JButton recordButton = new JButton("Save");

    recordLabel.setBounds(20, 20, 200, 30);
    recordText.setBounds(20, 70, 200, 30);
    recordButton.setBounds(260, 70, 50, 30);

    topPanel.add(recordLabel);
    topPanel.add(new JLabel());
    topPanel.add(recordText);
    topPanel.add(recordButton);

    add(topPanel, BorderLayout.NORTH);

    JPanel centerPanel = new JPanel();
    centerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    centerPanel.setLayout(new GridLayout(3, 1, 10, 10));

    JLabel runnersLabel = new JLabel("Runners registered");
    runnersArea = new JTextArea();
    runnersArea.setEditable(false);
    JLabel resultsLabel = new JLabel("Scoreboard");

    runnersLabel.setBounds(10, 100, 100, 30);
    runnersArea.setBounds(10, 130, 310, 300);
    resultsLabel.setBounds(10, 490, 100, 30);

    centerPanel.add(runnersLabel);
    centerPanel.add(runnersArea);
    centerPanel.add(resultsLabel);

    add(centerPanel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    bottomPanel.setLayout(new GridLayout(1, 2, 10, 10));

    JPanel resultsPanel = new JPanel();
    resultsPanel.setLayout(new GridLayout(1, 1));
    resultsPanel.setBounds(10, 520, 310, 60);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(3, 1));
    buttonsPanel.setBounds(10, 450, 310, 60);

    resultsArea = new JTextArea();
    startButton = new JButton("Start");
    exitButton = new JButton("Exit");
    resetButton = new JButton("Reset");

    resultsArea.setBounds(10, 520, 310, 60);
    startButton.setBounds(10, 450, 100, 30);
    startButton.setBounds(10, 450, 100, 30);
    resetButton.setBounds(230, 450, 100, 30);
    exitButton.setBounds(120, 450, 100, 30);

    resultsPanel.add(resultsArea);
    buttonsPanel.add(startButton);
    buttonsPanel.add(exitButton);
    buttonsPanel.add(resetButton);

    bottomPanel.add(resultsPanel);
    bottomPanel.add(buttonsPanel);

    add(bottomPanel, BorderLayout.SOUTH);

    progressPanel = new JPanel();
    progressPanel.setBounds(330, 10, 200, 570);
    add(progressPanel, BorderLayout.EAST);
    progressManager = new ProgressManager(progressPanel, MAX_RUNNERS);

    recordButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String runnerName = recordText.getText().trim();
        if (runnerName.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Runner name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }

        if (runnerCount >= MAX_RUNNERS) {
          JOptionPane.showMessageDialog(null, "You cannot register more than 5 runners!", "Error",
              JOptionPane.ERROR_MESSAGE);
          return;
        }

        Runner newRunner = new Runner(runnerName);
        runners[runnerCount] = newRunner;
        runnerCount++;

        runnersArea.append(runnerCount + " - " + newRunner.getName() + " - Speed: " + newRunner.getSpeed() + "\n");

        recordText.setText("");

        JOptionPane.showMessageDialog(null, "Runner registered successfully!", "Success",
            JOptionPane.INFORMATION_MESSAGE);

        if (runnerCount == MAX_RUNNERS) {
          JOptionPane.showMessageDialog(null, "The runners are ready for race!!!", "Ready",
              JOptionPane.INFORMATION_MESSAGE);
        }

      }
    });

    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (runnerCount < MAX_RUNNERS) {
          JOptionPane.showMessageDialog(null, "Please register 5 runners before starting the race!", "Error",
              JOptionPane.ERROR_MESSAGE);
          return;
        }

        progressManager.createProgressBars(runners);

        resultsArea.setText("");

        for (int i = 0; i < runners.length; i++) {
          Runner runner = runners[i];
          if (runner != null) {
            ThreadRunner threadRunner = new ThreadRunner(i + 1, runner.getName(), runner.getSpeed(), resultsArea,
                progressManager);
            threadRunner.start();
          }
        }
      }
    });

    // Reset Button ActionListener
    resetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Clear all fields and reset variables
        runnerCount = 0;
        runnersArea.setText("");
        resultsArea.setText("");
        recordText.setText("");
        progressPanel.removeAll();
        progressPanel.revalidate();
        progressPanel.repaint();
        runners = new Runner[MAX_RUNNERS]; // Clear runners array
        JOptionPane.showMessageDialog(null, "Race has been reset!", "Reset", JOptionPane.INFORMATION_MESSAGE);
      }
    });

    // Stop Button ActionListener
    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0); // Exit the program
      }
    });

    setSize(400, 500);
    setVisible(true);
  }

  public static void main(String[] args) {
    new AthleticRaceInterface();
  }

}
