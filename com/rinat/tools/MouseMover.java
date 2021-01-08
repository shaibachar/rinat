import java.util.Random;
import java.awt.*; 

public class MM {
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
                a = MouseInfo.getPointerInfo();
                loc = a.getLocation();
                robot.mouseMove((int)loc.getX()+6,(int) loc.getY()+6);
                a = MouseInfo.getPointerInfo();
                loc = a.getLocation();
                Thread.sleep(1000);
                robot.mouseMove((int)loc.getX() - 6, (int)loc.getY() - 6);
            }
            Thread.sleep(TIME);
            lastLoc = loc;
        }
    }
}
