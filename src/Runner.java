import java.util.Random;

public class Runner {
  private String name;
  private int speed;

  public Runner(String name) {
    this.name = name;
    this.speed = new Random().nextInt(1, 30);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }
}