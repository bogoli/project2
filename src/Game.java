import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Lia Bogoev using IntelliJ
 * 4/13/14
 * CS 2410
 */
public class Game extends JPanel implements ActionListener, KeyListener{

    private JLabel rod1Label, rod2Label, rod3Label, status;
    private JButton rod1Button, rod2Button, rod3Button;
    private Color easyColor = new Color(251,184,41);
    private Color medColor = new Color(255,153,0);
    private Color hardColor = new Color(255, 102, 0);

    // public int storedRodID;
    public int numberOfDisks;
    // public Rod rod1, rod2, rod3;

    private final Dimension labelDim = new Dimension(200,30);
    /* ========================================================================== ROD SUBCLASS
    public class Rod {
        public int diskArray[];
        public int top;

        // constructor
        public Rod(int disksHeld){
            this.diskArray = new int[disksHeld];
            this.top = 0;
        }
    }
    */
    //========================================================================== GAME CONSTRUCTOR

    public Game(int numberOfDisks){
        addKeyListener(this);
        this.numberOfDisks = numberOfDisks;

        /*
        rod1 = new Rod(numberOfDisks);
        rod2 = new Rod(numberOfDisks);
        rod3 = new Rod(numberOfDisks);

        for(int i = 0; i < numberOfDisks; ++i){
            // initialize the game
            rod1.diskArray[i] = (numberOfDisks - i);
            rod1.top = numberOfDisks - 1;
            rod2.diskArray[i] = 10;
            rod3.diskArray[i] = 10;
        }
        */
        // ------------------------------------------------ gamePane - adding components
        this.setLayout(null);
        switch(numberOfDisks){
            case 4:
                this.setBackground(easyColor);
                break;
            case 5:
                this.setBackground(medColor);
                break;
            case 6:
                this.setBackground(hardColor);
                break;
            default:
                this.setBackground(medColor);
                break;
        }
        this.setOpaque(true);

        rod1Label = new JLabel("—1—");
        rod1Label.setHorizontalAlignment(SwingConstants.CENTER);
        rod1Label.setSize(labelDim);
        rod1Label.setLocation(100,400);

        rod2Label = new JLabel("—2—");
        rod2Label.setHorizontalAlignment(SwingConstants.CENTER);
        rod2Label.setSize(labelDim);
        rod2Label.setLocation(300,400);

        rod3Label = new JLabel("—3—");
        rod3Label.setHorizontalAlignment(SwingConstants.CENTER);
        rod3Label.setSize(labelDim);
        rod3Label.setLocation(500,400);

        status = new JLabel("");
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setSize(labelDim);
        status.setLocation(300,500);

        rod1Button = new JButton(" ");
        rod1Button.addActionListener(this);
        rod1Button.setSize(labelDim);
        rod1Button.setLocation(100,450);

        rod2Button = new JButton(" ");
        rod2Button.addActionListener(this);
        rod2Button.setSize(labelDim);
        rod2Button.setLocation(300,450);

        rod3Button = new JButton(" ");
        rod3Button.addActionListener(this);
        rod3Button.setSize(labelDim);
        rod3Button.setLocation(500,450);

        this.add(rod1Label);
        this.add(rod2Label);
        this.add(rod3Label);
        this.add(rod1Button);
        this.add(rod2Button);
        this.add(rod3Button);
        this.add(status);

        // storedRodID = 0;

        this.setSize(800,600);
        this.setFocusable(true);
        this.setVisible(true);
    }

    /* ===================================================================== OTHER MEMBER FUNCTIONS
    public void print(){
        for(int i = (numberOfDisks - 1); i >= 0; i -= 1){
            System.out.println(rod1.diskArray[i] + " " + rod2.diskArray[i] + " " + rod3.diskArray[i]);
        }
    }

    public void move(Rod origin, Rod destination){
        if(origin.diskArray[origin.top] > destination.diskArray[destination.top]){
            status.setText("Invalid move.");
        }
        else{
            destination.top++;  // move to the next blank spot in the array
            destination.diskArray[destination.top] = origin.diskArray[origin.top]; // set it equal to the disk in the top spot of origin
            origin.diskArray[origin.top] = 0;   // set the top spot of origin to 0
            origin.top--;           // move the top tracker to the next full spot
        }
    }
    */

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
/*
        switch(storedRodID){
            case 0:
                status.setText("Case: 0");
                // set the storedRodID, there's no chosen rod
                System.out.println(e.getKeyChar());
                switch(e.getKeyChar()){
                    case '1':
                        storedRodID = 1;
                        break;
                    case '2':
                        storedRodID = 2;
                        break;
                    case '3':
                        storedRodID = 3;
                        break;
                    default:
                        break;
                }
                break;

            case 1:
                status.setText("Case: 1");
                switch(e.getKeyChar()){
                    case '1':
                        status.setText("1 -> 1 : invalid");
                        break;
                    case '2':
                        move(rod1, rod2);
                        storedRodID = 0;
                        break;
                    case '3':
                        move(rod1, rod3);
                        storedRodID = 0;
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch(e.getKeyChar()){
                    case '1':
                        move(rod2, rod1);
                        storedRodID = 0;
                        break;
                    case '2':
                        status.setText("2 -> 2 : invalid");
                        break;
                    case '3':
                        move(rod2, rod3);
                        storedRodID = 0;
                        break;
                    default:
                        break;
                }
                break;

            case 3:
                switch(e.getKeyChar()){
                    case '1':
                        move(rod3, rod1);
                        storedRodID = 0;
                        break;
                    case '2':
                        move(rod3, rod2);
                        storedRodID = 0;
                        break;
                    case '3':
                        status.setText("3 -> 3 : invalid");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    */