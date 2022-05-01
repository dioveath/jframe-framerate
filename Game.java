import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game extends JFrame {

  JButton threadStopper;

  public Game(){
    threadStopper = new JButton("StopThreadFor 5 seconds");
    threadStopper.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent actionEvent){
        try {
          Thread.sleep(1000);
        } catch(InterruptedException ie){
          ie.printStackTrace();
        }
      }
    });
    setSize(800,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    add(new MyScreen());
    add(threadStopper, BorderLayout.NORTH);
  }

  public static void main(String[] args) {
    new Game();
  }

  public class MyScreen extends JPanel {
    long numberOfFrames = 0;

    double deltaTime;
    long currentTime;
    long previousTime;

    int cubex;
    int cubeSpeed = 60;

    public MyScreen(){
      previousTime = System.currentTimeMillis();
      previousTimeForFrame = System.currentTimeMillis();
    }

    public void paintComponent(Graphics g){
      currentTime = System.currentTimeMillis();
      deltaTime =  (double)(currentTime - previousTime)/1000;
      System.out.println("deltaTime: " + deltaTime);
      updateGame();
      updateDisplay(g);
      previousTime = currentTime;
      repaint();
    }

    double totalMoveAmount;

    public void updateGame(){
      //Little bit of
      //is there method to call fillrect in float or alike for float
      double moveAmountPerFrame =  (cubeSpeed * deltaTime);
      totalMoveAmount += moveAmountPerFrame;
      if(totalMoveAmount > 1){
        cubex += 1;
        totalMoveAmount = 0;
      }
      if(cubex > 800){
        cubex = 0;
      }
    }

    //members for determining fps framePerSecond
    long currentTimeForFrame;
    long previousTimeForFrame;
    double framePerSecond;

    public void updateDisplay(Graphics g){
      numberOfFrames++;
      currentTimeForFrame = System.currentTimeMillis(); //get current time in miliseconds
      if(currentTimeForFrame - previousTimeForFrame > 1000){ //this means that 60 seconds has passed as 1 second is 1000 miliseconds
        // and then looking at the numberOfFrames we get number of frames that has been in a 1 second
        framePerSecond = numberOfFrames;
        previousTimeForFrame = currentTimeForFrame;
        numberOfFrames = 0;
      }


      g.setColor(Color.white);
      g.fillRect(0, 0, 800,600);
      g.setColor(Color.red);

      g.fillRect(cubex, 0, 100, 100);

      //draw line to test if it is really taking our speed
      for(int i = 0; i < 10; i++){
        g.drawLine(i * 60, 0, i*60, 100);
      }

      g.drawString("Number of Frames: " + numberOfFrames, 200, 200);
      g.drawString("Frame Per Second: " + framePerSecond, 200, 240);
      g.drawString("deltaTime: " + String.format("%2f", deltaTime) , 200, 220);
    }

  }
}
