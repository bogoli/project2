import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

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

    public int numberOfDisks;
    public Stack<Disk>[] towers = new Stack[3];
    public int storedTowerID;

    private final Dimension labelDim = new Dimension(200,30);
    private final Dimension buttonDim = new Dimension(180,30);
    //========================================================================== DISK SUBCLASS

    public class Disk {
        public Color recColor = new Color(0, 0, 0);
        public int size;

        public Graphics rectangle;
        public int width, xpos, ypos;
        public int height;

        public Disk(int size){
            this.size = size;
            this.width = 10*size;
            this.height = 30;
            this.xpos = 100;
            this.ypos = 30*size + 100;
        }

        public void setPos(int x, int y){
            this.xpos = x;
            this.ypos = y;
        }

    }


    //========================================================================== GAME CONSTRUCTOR

    public void moveDisk(int orig, int dest){
        if(towers[orig].peek().size !=0 && (towers[orig].peek().size < towers[dest].peek().size)){
            towers[dest].push(towers[orig].pop());
            System.out.println("Moved from " + orig + " to " + dest);
        }
    }

    public Game(int numberOfDisks){
        addKeyListener(this);
        this.numberOfDisks = numberOfDisks;
        this.storedTowerID = -1;

        for (int i=0; i<3; ++i){
            towers[i] = new Stack<Disk>();
        }

        for(int i=0; i < 5; ++i){
            towers[0].push(new Disk((5-i)));
            // towers[0].peek().draw(towers[0].peek().rectangle);
            towers[1].push(new Disk(6));
            towers[2].push(new Disk(6));
        }

        repaint();

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

        rod1Label = new JLabel("——————");
        rod1Label.setHorizontalAlignment(SwingConstants.CENTER);
        rod1Label.setSize(labelDim);
        rod1Label.setLocation(100,400);

        rod2Label = new JLabel("——————");
        rod2Label.setHorizontalAlignment(SwingConstants.CENTER);
        rod2Label.setSize(labelDim);
        rod2Label.setLocation(300,400);

        rod3Label = new JLabel("——————");
        rod3Label.setHorizontalAlignment(SwingConstants.CENTER);
        rod3Label.setSize(labelDim);
        rod3Label.setLocation(500,400);

        status = new JLabel("");
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setSize(labelDim);
        status.setLocation(300,500);

        rod1Button = new JButton("1");
        rod1Button.addActionListener(this);
        rod1Button.setSize(buttonDim);
        rod1Button.setLocation(110,450);

        rod2Button = new JButton("2");
        rod2Button.addActionListener(this);
        rod2Button.setSize(buttonDim);
        rod2Button.setLocation(310,450);

        rod3Button = new JButton("3");
        rod3Button.addActionListener(this);
        rod3Button.setSize(buttonDim);
        rod3Button.setLocation(510,450);

        this.add(rod1Label);
        this.add(rod2Label);
        this.add(rod3Label);
        this.add(rod1Button);
        this.add(rod2Button);
        this.add(rod3Button);
        this.add(status);

        this.setSize(800,600);
        this.setFocusable(true);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(storedTowerID){
            case -1:
                // no tower selected
                switch(e.getKeyChar()){
                    case '1':
                        storedTowerID = 0;
                        break;
                    case '2':
                        storedTowerID = 1;
                        break;
                    case '3':
                        storedTowerID = 2;
                        break;
                }
                break;
            case 0:
                switch(e.getKeyChar()){
                    case '1':
                        status.setText("1 - 1");
                        storedTowerID = -1;
                        break;
                    case '2':
                        moveDisk(0, 1);
                        storedTowerID = -1;
                        break;
                    case '3':
                        moveDisk(0,2);
                        storedTowerID = -1;
                        break;
                }
                break;
            case 1:
                switch(e.getKeyChar()){
                    case '1':
                        moveDisk(1,0);
                        storedTowerID = -1;
                        break;
                    case '2':
                        status.setText("2-2 invalid");
                        storedTowerID = -1;
                        break;
                    case '3':
                        moveDisk(1,2);
                        storedTowerID = -1;
                        break;
                }
                break;
            case 2:
                switch(e.getKeyChar()){
                    case '1':
                        moveDisk(2,0);
                        storedTowerID = -1;
                        break;
                    case '2':
                        moveDisk(2,1);
                        storedTowerID = -1;
                        break;
                    case '3':
                        status.setText("3-3 invalid");
                        storedTowerID = -1;
                        break;
                }
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}