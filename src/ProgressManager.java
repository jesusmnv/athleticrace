import javax.swing.*;
import java.awt.*;

public class ProgressManager {
  private JPanel progressPanel;
  private JProgressBar[] progressBars;
  private JLabel[] runnerLabels;

  public ProgressManager(JPanel panel, int maxRunners) {
    this.progressPanel = panel;
    this.progressBars = new JProgressBar[maxRunners];
    this.runnerLabels = new JLabel[maxRunners];

    progressPanel.setLayout(new GridLayout(maxRunners, 1));
  }

  public void createProgressBars(Runner[] runners) {
    progressPanel.removeAll();

    for (int i = 0; i < runners.length; i++) {
      if (runners[i] != null) {
        JPanel progressContainer = new JPanel(new BorderLayout());
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBars[i] = progressBar;

        JLabel runnerLabel = new JLabel(String.valueOf(i + 1));
        runnerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        runnerLabel.setForeground(Color.BLACK);
        runnerLabels[i] = runnerLabel;

        progressContainer.add(progressBar, BorderLayout.CENTER);
        progressContainer.add(runnerLabel, BorderLayout.WEST);
        progressPanel.add(progressContainer);
      }
    }

    progressPanel.revalidate();
    progressPanel.repaint();
  }

  public void updateProgress(int index, int value) {
    if (index >= 0 && index < progressBars.length) {
      progressBars[index].setValue(value);
    }
  }
}
