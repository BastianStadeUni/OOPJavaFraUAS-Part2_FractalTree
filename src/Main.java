import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //Create Controlframe
        MyControlFrame controlFrame = new MyControlFrame("Control Panel Fractal Tree");
        JFrame treeFrame = new JFrame("Fractal Tree");
        //Create Painting frame
        treeFrame.setSize(600, 600);
        JPanel p = new JPanel();
        treeFrame.add(p);
        treeFrame.setVisible(true);
        treeFrame.setLocationRelativeTo(null); //Center painting frame

        //get all values        new variables are to check if changes happend
        boolean repeat = controlFrame.getRepeat(), newRepeat;
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
        boolean change = true;
        while(true){
            if(change || repeat){
                treeFrame.repaint();
                paint(p, line, color, iters, thickness, thickFactor, length, lengthFactor, angle,
                        sleepAfterShape, sleepAfterShapeMS, sleepAfterIter, sleepAfterIterMS);
                change = false;
                System.out.println("Drawn");
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
                change = true;
                System.out.println("Changed");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        
    }

    public static void paint(JPanel panel, boolean line, Color color, int iters,
                      int thickness, double thickFactor, int length, double lengthFactor,
                      double angle, boolean sleepAfterShape, int sleepAfterShapeMS,
                      boolean sleepAfterIter, int sleepAfterIterMS){
        Graphics2D g2d =(Graphics2D)panel.getGraphics();
        //Mandatory pause to see the first line
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int width = panel.getWidth();
        int height = panel.getHeight();
        int endX = width / 2, endY = height - length, delta = (int)angle / 2;
        //sP = startPoint | x of sP is always in the middle of the panel and y of sP is always at the bottom of the Panel
        //polyC1-C4 are the 4 corners of the rectangle that is being used to draw the three out of rectangles
        Point polyC1, polyC2, polyC3, polyC4, sP = new Point(width / 2, height);
        ArrayList<Point> currStartPoints = new ArrayList<Point>();
        ArrayList<Point> nextStartPoints = new ArrayList<Point>();
        ArrayList<Integer> currAngles = new ArrayList<Integer>();
        ArrayList<Integer> nextAngles = new ArrayList<Integer>();
        //first line
        if(line){
            g2d.drawLine(sP.getX(),sP.getY(), endX, endY);
        }
        else{
            //TODO
        }
        if(sleepAfterIter){
            try {
                Thread.sleep(sleepAfterIterMS);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        currStartPoints.add(new Point(endX, endY));
        currAngles.add(0);
        for(int iterations = 1; iterations < iters; iterations++){
            length *= lengthFactor;
            thickness *= thickFactor;
            System.out.println(currStartPoints.size());
            for(int i = 0; i <= currStartPoints.size() - 1; i++){
                sP = currStartPoints.get(i);
                for(int j = 0; j < 2; j++){
                    if(line) {
                        endX = (int) (sP.getX() + length * Math.sin(Math.PI * (currAngles.get(i) + delta * Math.pow(-1, j)) / 180));
                        endY = (int) (sP.getY() - length * Math.cos(Math.PI * (currAngles.get(i) + delta * Math.pow(-1, j)) / 180));
                        g2d.drawLine(sP.getX(), sP.getY(), endX, endY);
                        nextStartPoints.add(new Point(endX, endY));
                        nextAngles.add((int) (currAngles.get(i) + delta * Math.pow(-1, j)));
                    }
                    else {
                        //get X and Y of the endpoint from the line to determine the endpoints from the polygon
                        endX = (int) (sP.getX() + length * Math.sin(Math.PI * (currAngles.get(i) + delta * Math.pow(-1, j)) / 180));
                        endY = (int) (sP.getY() - length * Math.cos(Math.PI * (currAngles.get(i) + delta * Math.pow(-1, j)) / 180));
                        //TODO
                    }
                    if(sleepAfterShape){
                        try {
                            Thread.sleep(sleepAfterShapeMS);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


    }
}