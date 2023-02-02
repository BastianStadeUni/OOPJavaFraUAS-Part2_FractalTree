import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //Create Controlframe
        MyControlFrame controlFrame = new MyControlFrame("Control Panel");
        JFrame treeFrame = new JFrame("Fractal Tree");
        //Create Painting frame
        treeFrame.setSize(600, 600);
        JPanel p = new JPanel();
        treeFrame.add(p);
        treeFrame.setVisible(true);
        treeFrame.setLocationRelativeTo(null); //Center painting frame

        //get all variables
        boolean repeat = controlFrame.getRepeat();
        boolean line = controlFrame.getLine(), newLine;
        Color color = controlFrame.getColor(), newColor;
        int iters = controlFrame.getIters(), newIters;
        int thickness = controlFrame.getThickness(), newThickness;
        double thickFactor = controlFrame.getThickFactor(), newThickFactor;
        int length = controlFrame.getLength(), newLength;
        double lengthFactor = controlFrame.getLengthFactor(), newLengthFactor;
        double angle = controlFrame.getAngle(), newAngle;
        boolean sleepAfterShape = controlFrame.getSleepAfterShape(), newSAS;
        int sleepAfterShapeMS = controlFrame.getSleepAfterShapeMS(), newSASMS;
        boolean sleepAfterIter = controlFrame.getSleepAfterIter(), newSAI;
        int sleepAfterIterMS = controlFrame.getSleepAfterIterMS(), newSAIMS;
        //start drawing in endless loop
        boolean isChanged = true;
        while(true){
            if(isChanged || repeat){
                treeFrame.repaint();
                paint(p, line, color, iters, thickness, thickFactor, length, lengthFactor, angle,
                        sleepAfterShape, sleepAfterShapeMS, sleepAfterIter, sleepAfterIterMS);
                isChanged = false;
            }
            //get new values from controlpanel
            repeat = controlFrame.getRepeat();
            newLine = controlFrame.getLine();
            newColor = controlFrame.getColor();
            newIters = controlFrame.getIters();
            newThickness = controlFrame.getThickness();
            newThickFactor = controlFrame.getThickFactor();
            newLength = controlFrame.getLength();
            newLengthFactor = controlFrame.getLengthFactor();
            newAngle = controlFrame.getAngle();
            newSAS = controlFrame.getSleepAfterShape();
            newSASMS = controlFrame.getSleepAfterShapeMS();
            newSAI = controlFrame.getSleepAfterIter();
            newSAIMS = controlFrame.getSleepAfterIterMS();
            //compare old and new values if any have changed
            if(line != newLine || color != newColor || iters != newIters ||
            thickness != newThickness || thickFactor != newThickFactor || length != newLength ||
            lengthFactor != newLengthFactor || angle != newAngle || sleepAfterShape != newSAS || sleepAfterShapeMS != newSASMS ||
            sleepAfterIter != newSAI || sleepAfterIterMS != newSAIMS){
                line = newLine;
                color = newColor;
                iters = newIters;
                thickness = newThickness;
                thickFactor = newThickFactor;
                length = newLength;
                lengthFactor = newLengthFactor;
                angle = newAngle;
                sleepAfterShape = newSAS;
                sleepAfterShapeMS = newSASMS;
                sleepAfterIter = newSAI;
                sleepAfterIterMS = newSAIMS;
                isChanged = true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
    }

    public static void paint(JPanel panel, boolean line, Color color, int iters,
                      int thickness, double thickFactor, int length, double lengthFactor,
                      double angle, boolean sleepAfterShape, int sleepAfterShapeMS,
                      boolean sleepAfterIter, int sleepAfterIterMS){
        Graphics2D g2d = (Graphics2D)panel.getGraphics();
        //Mandatory pause to see the first line
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int width = panel.getWidth();
        int height = panel.getHeight();
        int delta = (int)angle / 2; //delta is half of he given angle,
        //we add and subtract it from our current angle to get the new values for both branches
        //sP = startPoint | x of sP is always in the middle of the panel and y of sP is always at the bottom of the Panel
        //eP = endPoint | x is also always in the middle, the same as sP, y is above sP by the size of the length
        Point sP = new Point(width / 2, height), eP = new Point(width / 2, height - length);
        Polygon rectangle = new Polygon();
        ArrayList<Point> currStartPoints = new ArrayList<Point>();
        ArrayList<Point> nextStartPoints = new ArrayList<Point>();
        ArrayList<Integer> currAngles = new ArrayList<Integer>();
        ArrayList<Integer> nextAngles = new ArrayList<Integer>();
        //first line
        if(line){
            g2d.drawLine(sP.getX(),sP.getY(), eP.getX(), eP.getY());
        }
        //or first rectangle
        else{
            g2d.setColor(color);
            //create polygon with 4 cornerns
            rectangle.npoints = 4;
            rectangle.xpoints = new int[4];
            rectangle.ypoints = new int[4];

            //getting the first corner of the polygon by turning a line from the start point by 90 degrees
            //in both direction with a length of half the thickness
            //bottomright corner
            rectangle.xpoints[0] = (int)(sP.getX() + thickness / 2 * Math.sin(Math.toRadians(90)));
            rectangle.ypoints[0] = (int)(sP.getY() - thickness / 2 * Math.cos(Math.toRadians(90)));

            //bottomleft corner, from startpoint with -90 degrees turned
            rectangle.xpoints[1] = (int)(sP.getX() + thickness / 2 * Math.sin(Math.toRadians(-90)));
            rectangle.ypoints[1] = (int)(sP.getY() - thickness / 2 * Math.cos(Math.toRadians(-90)));
            //topright corner, from endpoint turned with 90 degrees

            rectangle.xpoints[3] = (int)(eP.getX() + thickness / 2 * Math.sin(Math.toRadians(90)));
            rectangle.ypoints[3] = (int)(eP.getY() - thickness / 2 * Math.cos(Math.toRadians(90)));

            //topleft point, from endpoint turned with -90 degrees
            rectangle.xpoints[2] = (int)(eP.getX() + thickness / 2 * Math.sin(Math.toRadians(-90)));
            rectangle.ypoints[2] = (int)(eP.getY() - thickness / 2 * Math.cos(Math.toRadians(-90)));

            g2d.fillPolygon(rectangle);
        }
        if(sleepAfterIter){
            try {
                Thread.sleep(sleepAfterIterMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currStartPoints.add(new Point(eP.getX(), eP.getY()));
        currAngles.add(0);
        for(int iterations = 0; iterations < iters; iterations++){
            length *= lengthFactor;
            thickness *= thickFactor;
            for(int i = 0; i <= currStartPoints.size() - 1; i++){
                sP = currStartPoints.get(i);
                for(int j = 0; j < 2; j++){
                    if(line) {
                        //calculate the x coordinate of the end of the line by calculating
                        //starting point x + length * sin of the radiant of our new angle
                        //our new angle is the old angle once + and once - half of the given angle
                        eP.setX((int) (sP.getX() + length * Math.sin(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j)))));
                        eP.setY((int) (sP.getY() - length * Math.cos(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j)))));
                        g2d.drawLine(sP.getX(), sP.getY(), eP.getX(), eP.getY());
                    }
                    else {
                        //get X and Y of the endpoint from the line to determine the endpoints from the polygon
                        eP.setX((int) (sP.getX() + length * Math.sin(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j)))));
                        eP.setY((int) (sP.getY() - length * Math.cos(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j)))));

                        //bottomright corner (we increase thickness in the bottom corners
                        //so the branch is a trapezoid and not a rectangle)
                        rectangle.xpoints[0] = (int)(sP.getX() + thickness / (2 * thickFactor) * Math.sin(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j) + 90)));
                        rectangle.ypoints[0] = (int)(sP.getY() - thickness / (2 * thickFactor) * Math.cos(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j) + 90)));
                        //bottomleft corner, from startpoint with -90 degrees turned
                        rectangle.xpoints[1] = (int)(sP.getX() + thickness / (2 * thickFactor) * Math.sin(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j) - 90)));
                        rectangle.ypoints[1] = (int)(sP.getY() - thickness / (2 * thickFactor) * Math.cos(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j) - 90)));
                        //topright corner, from endpoint turned with 90 degrees
                        rectangle.xpoints[3] = (int)(eP.getX() + thickness / 2 * Math.sin(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j) + 90)));
                        rectangle.ypoints[3] = (int)(eP.getY() - thickness / 2 * Math.cos(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j) + 90)));
                        //topleft point, from endpoint turned with -90 degrees
                        rectangle.xpoints[2] = (int)(eP.getX() + thickness / 2 * Math.sin(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j) - 90)));
                        rectangle.ypoints[2] = (int)(eP.getY() - thickness / 2 * Math.cos(Math.toRadians(currAngles.get(i) + delta * Math.pow(-1, j) - 90)));
                        g2d.fillPolygon(rectangle);
                        g2d.draw(rectangle);
                    }
                    //adding new endpoints to the list for the next iteration
                    nextStartPoints.add(new Point(eP.getX(), eP.getY()));
                    nextAngles.add((int) (currAngles.get(i) + delta * Math.pow(-1, j)));

                    if(sleepAfterShape){
                        try {
                            Thread.sleep(sleepAfterShapeMS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            currStartPoints = nextStartPoints;
            currAngles = nextAngles;
            nextStartPoints = new ArrayList<Point>();
            nextAngles = new ArrayList<Integer>();
            if(sleepAfterIter){
                try {
                    Thread.sleep(sleepAfterIterMS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
