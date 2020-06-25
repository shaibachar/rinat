import java.util.Random;
import java.awt.*; 

public class MouseMover {
    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_Y = 40;
    public static final int MAX_X = 40;

    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        PointerInfo a = MouseInfo.getPointerInfo();
        java.awt.Point loc = a.getLocation();
        java.awt.Point lastLoc = loc;

        Random random = new Random();
        while (true) {
            a = MouseInfo.getPointerInfo();
            loc = a.getLocation();
            
            if (lastLoc.getX()==loc.getX() && lastLoc.getY()==loc.getY()){
                robot.mouseMove((int)loc.getX()+random.nextInt(MAX_X),(int)loc.getY()+ random.nextInt(MAX_Y));
            }
            Thread.sleep(FIVE_SECONDS);
            lastLoc = loc;
        }
    }
}
