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
                paint(p, repeat, line, color, iters, thickness, thickFactor, length, lengthFactor, angle,
                        sleepAfterShape, sleepAfterShapeMS, sleepAfterIter, sleepAfterIterMS);
                change = false;
            }
            //get new values from controlpanel
            newRepeat = controlFrame.getRepeat();
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
            if(repeat != newRepeat || line != newLine || color != newColor || iters != newIters ||
            thickness != newThickness || thickFactor != newThickFactor || length != newLength ||
            angle != newAngle || sleepAfterShape != newSAS || sleepAfterShapeMS != newSASMS ||
            sleepAfterIter != newSAI || sleepAfterIterMS != newSAIMS){
                repeat = newRepeat;
                line = newLine;
                color = newColor;
                iters = newIters;
                thickness = newThickness;
                thickFactor = newThickFactor;
                length = newLength;
                angle = newAngle;
                sleepAfterShape = newSAS;
                sleepAfterShapeMS = newSASMS;
                sleepAfterIter = newSAI;
                sleepAfterIterMS = newSAIMS;
                change = true;
            }
        }

        
    }

    public static void paint(JPanel panel, boolean repeat, boolean line, Color color, int iters,
                      int thickness, double thickFactor, int length, double lengthFactor,
                      double angle, boolean sleepAfterShape, int sleepAfterShapeMS,
                      boolean sleepAfterIter, int sleepAfterIterMS){

    }
}