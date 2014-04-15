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
    private Color easyColor = new Color(251,184,41);
    private Color medColor = new Color(255,153,0);
    private Color hardColor = new Color(255, 102, 0);

    public int numberOfDisks;
    public Rod rod1, rod2, rod3;

    private final Dimension labelDim = new Dimension(200,30);
    // ========================================================================== ROD SUBCLASS
    public class Rod {
        public int diskArray[];
        public int ID;
        public int top;

        // constructor
        public Rod(int disksHeld, int num){
            this.diskArray = new int[disksHeld];
            this.ID = num;
            this.top = disksHeld - 1; // e.g., 5 disks, top index is 4
        }
    }
    //========================================================================== GAME CONSTRUCTOR

    public Game(int numberOfDisks){
        this.numberOfDisks = numberOfDisks;

        rod1 = new Rod(numberOfDisks,1);
        rod2 = new Rod(numberOfDisks,2);
        rod3 = new Rod(numberOfDisks,3);

        for(int i = 0; i < numberOfDisks; ++i){
            // initialize the game
            rod1.diskArray[i] = (numberOfDisks - i);
            rod2.diskArray[i] = 0;
            rod3.diskArray[i] = 0;
        }

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

        rod2Label = new JLabel("—2—");
        rod2Label.setHorizontalAlignment(SwingConstants.CENTER);
        rod2Label.setSize(labelDim);

        rod3Label = new JLabel("—3—");
        rod3Label.setHorizontalAlignment(SwingConstants.CENTER);
        rod3Label.setSize(labelDim);

        status = new JLabel("");

        this.add(rod1Label);
        rod1Label.setLocation(100,400);
        this.add(rod2Label);
        rod2Label.setLocation(300,400);
        this.add(rod3Label);
        rod3Label.setLocation(500,400);

        this.setFocusable(true);
        this.setVisible(true);

    }

    // ===================================================================== OTHER MEMBER FUNCTIONS
    public void print(){
        for(int i = (numberOfDisks - 1); i >= 0; i -= 1){
            System.out.println(rod1.diskArray[i] + " " + rod2.diskArray[i] + " " + rod3.diskArray[i]);
        }
    }

    public void move(Rod origin, Rod destination){
        if(origin.diskArray[origin.top] > destination.diskArray[destination.top]){
            // invalid move
        }
        else{
            destination.top++;  // move to the next blank spot in the array
            destination.diskArray[destination.top] = origin.diskArray[origin.top]; // set it equal to the disk in the top spot of origin
            origin.diskArray[origin.top] = 0;   // set the top spot of origin to 0
            origin.top--;           // move the top tracker to the next full spot
        }
    }


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
