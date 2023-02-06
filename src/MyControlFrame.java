import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyControlFrame extends JFrame implements ActionListener, ItemListener {
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
    private JCheckBox slpaftershapeCheck;
    private JTextField shapeSleepText;
    private JCheckBox slpafterIterCheck;
    private JTextField iterSleepText;
    private boolean repeating = false;
    private boolean line = true;
    private Color branchColor = Color.black;
    private int iters = 10;
    private int thickness = 50;
    private double thickFactor = 0.6;
    private int length = 100;
    private double lengthFactor = 0.8;
    private double angle = 60.0;
    private boolean sleepAfterShape = false;
    private int sleepAfterShapeMS = 20;
    private boolean sleepAfterIter = true;
    private int sleepAfterIterMS = 500;

    public MyControlFrame(String s){
        this.setTitle(s);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set Layout for the Control Frame, (x by 2 grid)
        GridLayout controlFrameLayout = new GridLayout(0, 2);
        compsToGrid = new JPanel();
        compsToGrid.setLayout(controlFrameLayout); //compsToGrid is a *list* of components that will be put into a x by 2 grid
        //Radio Button for Repeat:
        newLabel("Repeat");
        //created buttons + their listeners
        radioYes = new JRadioButton("Yes");
        radioYes.addActionListener(this);
        radioNo = new JRadioButton("No");
        radioNo.addActionListener(this);
        //group buttons together
        ButtonGroup repeatGroup = new ButtonGroup();
        repeatGroup.add(radioYes);
        repeatGroup.add(radioNo);
        //create gridlayout to place them next to each other
        JPanel repeatPanel = new JPanel(new GridLayout(1, 0));
        repeatPanel.add(radioYes);
        repeatPanel.add(radioNo);
        radioNo.setSelected(true);
        //add grid to panel
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
        //create button + listener
        colorButton = new JButton("Choose color");
        colorButton.addActionListener(this);
        //center position of button
        colorButton.setHorizontalTextPosition(AbstractButton.CENTER); //center text
        colorButton.setVerticalTextPosition(AbstractButton.CENTER);
        //add to end grid
        compsToGrid.add(colorButton);

        //Textbox for iterations
        newLabel("Iterations");
        iterText = new JTextField(5); //create textfield
        iterText.setText("10"); //set default message inside textfield
        iterText.addActionListener(this);//add listener
        compsToGrid.add(iterText);//add to end grid

        //Textbox for Base Thickness (Only required for rectangels)
        newLabel("Thickness (base)");
        thickBaseText = new JTextField(5);
        thickBaseText.setText("50");
        thickBaseText.addActionListener(this);
        compsToGrid.add(thickBaseText);

        //Textbox for Thickness factor
        newLabel("Thickness factor");
        thickfactorText = new JTextField(5);
        thickfactorText.setText("0.6");
        thickfactorText.addActionListener(this);
        compsToGrid.add(thickfactorText);

        //Textbox for Length
        newLabel("Length (base)");
        lengthText = new JTextField(5);
        lengthText.setText("100");
        lengthText.addActionListener(this);
        compsToGrid.add(lengthText);

        //Textbox for Length factor
        newLabel("Length factor");
        lengthFactorText = new JTextField(5);
        lengthFactorText.setText("0.8");
        lengthFactorText.addActionListener(this);
        compsToGrid.add(lengthFactorText);

        //Textbox for angle
        newLabel("Split angle °");
        angleText = new JTextField(5);
        angleText.setText("60.0");
        angleText.addActionListener(this);
        compsToGrid.add(angleText);

        //Checkbox to check if the Tree should sleep after every shape
        slpaftershapeCheck = new JCheckBox("Sleep after each shape"); //create checkbox
        slpaftershapeCheck.addItemListener(this);//add listener
        compsToGrid.add(slpaftershapeCheck);//add to end grid
        newLabel(""); //Filler for grid

        //Textbox for ms to sleep after every shape if checkbox is checked
        newLabel("Sleep Time (ms)");
        compsToGrid.getComponent(20).setEnabled(false);
        shapeSleepText = new JTextField(5);
        shapeSleepText.setText("20");
        shapeSleepText.addActionListener(this);
        shapeSleepText.setEnabled(false);
        compsToGrid.add(shapeSleepText);

        //Checkbox if programm should sleep after each iteration or not
        slpafterIterCheck = new JCheckBox("Sleep after each iteration");
        slpafterIterCheck.setSelected(true);
        slpafterIterCheck.addItemListener(this);
        compsToGrid.add(slpafterIterCheck);
        newLabel(""); //Filler for grid

        //Textbox for how long the programm should sleep after every iteration if checkbox is checked
        newLabel("Sleep Time (ms)");
        iterSleepText = new JTextField(5);
        iterSleepText.setText("500");
        iterSleepText.addActionListener(this);
        compsToGrid.add(iterSleepText);


        //bring the Grid onto the JFrame
        //add(compsToGrid, BorderLayout.NORTH);
        this.add(compsToGrid);
        compsToGrid.setOpaque(true);
        this.setContentPane(compsToGrid);
        this.pack();
        this.setVisible(true);
    }

    public void newLabel(String s){
        compsToGrid.add(new JLabel(s));
    }

    public void actionPerformed(ActionEvent e){
        //This function triggers when a action listener has been triggered.
        //It checks the source of the trigger and makes changes accordingly
        if(e.getSource() == radioYes){
            this.repeating = true;
        }
        else if(e.getSource() == radioNo){
            this.repeating = false;
        }
        else if(e.getSource() == radioLine){
            this.line = true;
        }
        else if(e.getSource() == radioRect){
            this.line = false;
        }
        else if(e.getSource() == colorButton){
            JColorChooser colorChooser = new JColorChooser();
            branchColor = JColorChooser.showDialog(null, "Color selection", Color.black);
        }
        else if(e.getSource() == iterText){
            try {
                this.iters = Integer.parseInt(iterText.getText());
            }catch (NumberFormatException ex){
                System.out.println("Integer required");
            }
        }
        else if(e.getSource() == thickBaseText){
            try {
                this.thickness = Integer.parseInt(thickBaseText.getText());
            }catch (NumberFormatException ex){
                System.out.println("Integer required");
            }
        }
        else if(e.getSource() == thickfactorText){
            try {
                this.thickFactor = Double.parseDouble(thickfactorText.getText());
            }catch (NumberFormatException ex){
                System.out.println("Double required");
            }
        }
        else if(e.getSource() == lengthText){
            try {
                this.length = Integer.parseInt(lengthText.getText());
            }catch (NumberFormatException ex){
                System.out.println("Integer required");
            }
        }
        else if(e.getSource() == lengthFactorText){
            try {
                this.lengthFactor = Double.parseDouble(lengthFactorText.getText());
            }catch (NumberFormatException ex){
                System.out.println("Double required");
            }
        }
        else if(e.getSource() == angleText){
            try {
                this.angle = Double.parseDouble(angleText.getText());
            }catch (NumberFormatException ex){
                System.out.println("Double required");
            }
        }
        else if(e.getSource() == shapeSleepText){
            try {
                this.sleepAfterShapeMS = Integer.parseInt(shapeSleepText.getText());
            }catch (NumberFormatException ex){
                System.out.println("Integer required");
            }
        }
        else if(e.getSource() == iterSleepText){
            try {
                this.sleepAfterIterMS = Integer.parseInt(iterSleepText.getText());
            }catch (NumberFormatException ex){
                System.out.println("Integer required");
            }
        }
    }

    public void itemStateChanged(ItemEvent e){
        //This function triggers when a checkbox has been checked or unchecked.
        //It checks for the source and its new status and makes changes accordingly
        if(e.getSource() == slpaftershapeCheck){
            if(e.getStateChange() == ItemEvent.DESELECTED){
                this.sleepAfterShape = false;
                shapeSleepText.setEnabled(false);
                //Get component from grid at 20th position
                compsToGrid.getComponent(20).setEnabled(false);
            }
            else{
                this.sleepAfterShape = true;
                shapeSleepText.setEnabled(true);
                compsToGrid.getComponent(20).setEnabled(true);
            }
        }
        if(e.getSource() == slpafterIterCheck){
            if(e.getStateChange() == ItemEvent.DESELECTED){
                this.sleepAfterIter = false;
                iterSleepText.setEnabled(false);
                //Get component from grid at the 24th position
                compsToGrid.getComponent(24).setEnabled(false);
            }
            else{
                this.sleepAfterIter = true;
                iterSleepText.setEnabled(true);
                compsToGrid.getComponent(24).setEnabled(true);
            }
        }
    }

    //Simple getter methods, so the main function can retrieve these parameters
    public boolean getRepeat(){
        return this.repeating;
    }
    public boolean getLine(){
        return this.line;
    }
    public Color getColor(){
        return this.branchColor;
    }

    public double getLengthFactor(){
        return this.lengthFactor;
    }
    public double getAngle(){
        return this.angle;
    }
    public boolean getSleepAfterShape(){
        return this.sleepAfterShape;
    }
    public int getSleepAfterShapeMS(){
        return this.sleepAfterShapeMS;
    }
    public boolean getSleepAfterIter(){
        return this.sleepAfterIter;
    }
    public int getSleepAfterIterMS(){
        return this.sleepAfterIterMS;
    }
    public int getIters(){
        return this.iters;
    }
    public int getThickness(){
        return this.thickness;
    }
    public double getThickFactor(){
        return this.thickFactor;
    }
    public int getLength(){
        return this.length;
    }

}
