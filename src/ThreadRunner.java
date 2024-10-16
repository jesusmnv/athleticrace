import javax.swing.JTextArea;

public class ThreadRunner extends Thread {
  private int runnerCount;
  private String runnerName;
  private int speed;
  private JTextArea resultsArea;
  private ProgressManager progressManager;

  public ThreadRunner(int runnerCount, String runnerName, int speed, JTextArea resultsArea,
      ProgressManager progressManager) {
    this.runnerCount = runnerCount;
    this.runnerName = runnerName;
    this.speed = speed;
    this.resultsArea = resultsArea;
    this.progressManager = progressManager;
  }

  @Override
  public void run() {
    int distance = 100 / 10;
    int sleepTime = (1000 / speed) * distance;
    try {
      for (int i = 0; i <= 100; i += 10) {
        progressManager.updateProgress(runnerCount - 1, i);
        System.out.println("Runner " + runnerName + ": " + i + "% completed.");
        Thread.sleep(sleepTime);
      }
      resultsArea.append(runnerCount + " - " + runnerName + " has finished the race!\n");
    } catch (InterruptedException e) {
      System.out.println(runnerName + " was interrupted.");
    }
  }
}
