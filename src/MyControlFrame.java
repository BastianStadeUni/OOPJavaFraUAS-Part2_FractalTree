import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class MyControlFrame extends JPanel implements ActionListener {
    private JPanel compsToGrid;
    private JRadioButton radioYes;
    private JRadioButton radioNo;
    private JRadioButton radioLine;
    private JRadioButton radioRect;
    private JButton colorButton;
    private JTextField iterText;
    private JTextField thickBaseText;
    private JTextField thickfactorText;
    private JTextField lengthText;
    private JTextField lengthFactorText;
    private JTextField angleText;
    private boolean repeating;
    private boolean line;
    private Color branchColor;
    private int iters;
    private int thickness;
    private double thickFactor;
    private int length;
    private double lengthFactor;
    private float angle;

    public MyControlFrame(){
        //Set Layout for the Control Frame, (x by 2 grid)
        GridLayout controlFrameLayout = new GridLayout(0, 2);
        compsToGrid = new JPanel();
        compsToGrid.setLayout(controlFrameLayout); //compsToGrid is a *list* of components that will be put into a x by 2 grid
        //Radio Button for Repeat:
        //JLabel repeatLabel = new JLabel("Repeat");
        //compsToGrid.add(repeatLabel);
        newLabel("Repeat");

        radioYes = new JRadioButton("Yes");
        radioYes.addActionListener(this);
        radioNo = new JRadioButton("No");
        radioNo.addActionListener(this);

        ButtonGroup repeatGroup = new ButtonGroup();
        repeatGroup.add(radioYes);
        repeatGroup.add(radioNo);

        JPanel repeatPanel = new JPanel(new GridLayout(1, 0));
        repeatPanel.add(radioYes);
        repeatPanel.add(radioNo);
        radioNo.setSelected(true);

        compsToGrid.add(repeatPanel);
        //Radio Button for Shape
        newLabel("Shape");

        radioLine = new JRadioButton("Line");
        radioLine.addActionListener(this);
        radioRect = new JRadioButton("Rect");
        radioRect.addActionListener(this);

        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(radioLine);
        shapeGroup.add(radioRect);

        JPanel shapePanel = new JPanel(new GridLayout(1, 0));
        shapePanel.add(radioLine);
        shapePanel.add(radioRect);
        radioLine.setSelected(true);
        compsToGrid.add(shapePanel);

        //Button for Color changes
        newLabel("Color");
        colorButton = new JButton("Choose color");
        colorButton.addActionListener(this);
        colorButton.setHorizontalTextPosition(AbstractButton.CENTER); //center text
        colorButton.setVerticalTextPosition(AbstractButton.CENTER);
        compsToGrid.add(colorButton);

        //Textbox for iterations
        newLabel("Iterations");
        iterText = new JTextField(5);
        iterText.addActionListener(this);
        compsToGrid.add(iterText);

        //Textbox for Base Thickness (Only required for rectangels)
        newLabel("Thickness (base)");
        thickBaseText = new JTextField(5);
        thickBaseText.addActionListener(this);
        compsToGrid.add(thickBaseText);

        //Textbox for Thickness factor
        newLabel("Thickness factor");
        thickfactorText = new JTextField(5);
        thickfactorText.addActionListener(this);
        compsToGrid.add(thickfactorText);

        //Textbox for Length
        newLabel("Length (base)");
        lengthText = new JTextField(5);
        lengthText.addActionListener(this);
        compsToGrid.add(lengthText);

        //Textbox for Length factor
        newLabel("Length factor");
        lengthFactorText = new JTextField(5);
        lengthFactorText.addActionListener(this);
        compsToGrid.add(lengthFactorText);

        //Textbox for angle
        newLabel("Split angle °");
        angleText = new JTextField(5);
        angleText.addActionListener(this);
        compsToGrid.add(angleText);

        //bring the Grid onto the JFrame
        add(compsToGrid, BorderLayout.NORTH);
    }

    public void newLabel(String s){
        compsToGrid.add(new JLabel(s));
    }

    public void actionPerformed(ActionEvent e){
        //checks if button has been pressed to select a new color
        if(e.getSource() == radioYes){
            this.repeating = true;
            System.out.println(this.repeating);
        }
        else if(e.getSource() == radioNo){
            this.repeating = false;
            System.out.println(this.repeating);
        }
        else if(e.getSource() == radioLine){
            this.line = true;
            System.out.println(this.line);
        }
        else if(e.getSource() == radioRect){
            this.line = false;
            System.out.println(this.line);
        }
        else if(e.getSource() == colorButton){
            JColorChooser colorChooser = new JColorChooser();
            branchColor = JColorChooser.showDialog(null, "Color selection", Color.black);
            System.out.println(this.branchColor);
        }
        else if(e.getSource() == iterText){
            try {
                this.iters = Integer.parseInt(iterText.getText());
                System.out.println(this.iters);
            }catch (NumberFormatException ex){
                System.out.println("Integer required");
            }
        }
        else if(e.getSource() == thickBaseText){
            try {
                this.thickness = Integer.parseInt(thickBaseText.getText());
                System.out.println(this.thickness);
            }catch (NumberFormatException ex){
                System.out.println("Integer required");
            }
        }
        else if(e.getSource() == thickfactorText){
            try {
                this.thickFactor = Double.parseDouble(thickfactorText.getText());
                System.out.println(this.thickFactor);
            }catch (NumberFormatException ex){
                System.out.println("Double required");
            }
        }
        else if(e.getSource() == lengthText){
            try {
                this.length = Integer.parseInt(lengthText.getText());
                System.out.println(this.length);
            }catch (NumberFormatException ex){
                System.out.println("Integer required");
            }
        }
        else if(e.getSource() == lengthFactorText){
            try {
                this.lengthFactor = Double.parseDouble(lengthFactorText.getText());
                System.out.println(this.lengthFactor);
            }catch (NumberFormatException ex){
                System.out.println("Double required");
            }
        }
        else if(e.getSource() == angleText){
            try {
                this.angle = Float.parseFloat(angleText.getText());
                System.out.println(this.angle);
            }catch (NumberFormatException ex){
                System.out.println("Float required");
            }
        }
    }

    public static void createAndShowGUI(){
        JFrame controlPanel = new JFrame("Control Panel Fractal Tree");
        controlPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent radioRepeat = new MyControlFrame();
        radioRepeat.setOpaque(true);
        controlPanel.setContentPane(radioRepeat);

        controlPanel.pack();
        controlPanel.setVisible(true);
    }
}
