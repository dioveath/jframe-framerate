public class Cube {

  public int x;
  public int y;
  public int width;
  public int height;

  public int speed;

  public Cube(){

    x = (int) Math.random() * 800;
    y = (int) Math.random() * 600;
    width = (int)Math.random() * 60 + 40;
    height = (int)Math.random() * 60 + 40;
    speed = (int) Math.random() * 10 + 5;
  }

}
