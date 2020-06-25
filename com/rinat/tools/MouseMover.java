import java.util.Random;
import java.awt.*; 

public class MouseMover {
    public static final int TIME = 10000;
    public static final int MAX_Y = 40;
    public static final int MAX_X = 40;
    public static final int MIN_X = 500;
    public static final int MIN_Y = 500;

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
                int x = random.nextInt(MAX_X);
                int y = random.nextInt(MAX_Y);
                robot.mouseMove(MIN_X + x,MIN_Y + y);
                Thread.sleep(TIME/2);
                robot.mouseMove(MIN_X - x,MIN_Y - y);
            }
            Thread.sleep(TIME);
            lastLoc = loc;
        }
    }
}
