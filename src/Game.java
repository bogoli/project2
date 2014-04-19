import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

/**
 * Created by Lia Bogoev using IntelliJ
 * 4/13/14
 * CS 2410
 */
public class Game extends JPanel implements ActionListener, KeyListener{

    private JLabel rod1Label, rod2Label, rod3Label, status;
    private JButton rod1Button, rod2Button, rod3Button;
    private Color easyColor = new Color(240,240,240);
    private Color medColor = new Color(204,204,204);
    private Color hardColor = new Color(192,192,192);

    private Color diskColor1 = new Color(160,220,0);
    private Color diskColor2  = new Color(175,220,0);
    private Color diskColor3  = new Color(190,220,0);
    private Color diskColor4  = new Color(205,220,0);
    private Color diskColor5  = new Color(220,220,0);
    private Color diskColor6  = new Color(235,220,0);

    public int numberOfDisks;
    public Stack<Disk>[] towers = new Stack[3];
    public int storedTowerID;

    public List<DiskRect> diskRectList;

    private final Dimension labelDim = new Dimension(200,30);
    private final Dimension buttonDim = new Dimension(180,40);
    //============================================================================== Disk SUBCLASS

    public class Disk {
        public int size;

        public Disk(int size){
            this.size = size;
        }
    }

    //========================================================================== DiskRect SUBCLASS

    public class DiskRect{
        public int xPos, yPos, width;
        public Color color;

        public DiskRect(int size) {
            xPos = 125 + (15 * (5-size));
            yPos = 200 + (30 * (size));
            width = 30 * size;
            switch(size){
                case 1:
                    color = diskColor1;
                    break;
                case 2:
                    color = diskColor2;
                    break;
                case 3:
                    color = diskColor3;
                    break;
                case 4:
                    color = diskColor4;
                    break;
                case 5:
                    color = diskColor5;
                    break;
                case 6:
                    color = diskColor6;
                    break;
                default:
                    color = Color.BLACK;
            }
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(xPos, yPos, width, 30);
        }

    }
    //=============================================================================== GRAPHICS METHODS

    private DiskRect testRect = null;


    public void addRect(int size) {

        DiskRect diskRect= new DiskRect(size);
        diskRectList.add(diskRect);
    }


    public void paint(Graphics g) {
        //super.paint(g);

        // use double buffering
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) bufferedImage.getGraphics();

        super.paint(buffer);

        for (DiskRect b : diskRectList)
            b.draw(buffer);

        g.drawImage(bufferedImage, 0, 0, this);
    }


    //----------------------------------------------- GAME moveDisk function

    public void moveDisk(int orig, int dest){
        if(towers[orig].peek().size !=0 && (towers[orig].peek().size < towers[dest].peek().size)){
            towers[dest].push(towers[orig].pop());
            System.out.println("Moved from " + orig + " to " + dest);
        }
    }

    //========================================================================== GAME CONSTRUCTOR

    public Game(int numberOfDisks){
        addKeyListener(this);
        this.numberOfDisks = numberOfDisks;
        this.storedTowerID = -1;

        for (int i=0; i<3; ++i){
            towers[i] = new Stack<Disk>();
        }

        for(int i=0; i < 5; ++i){
            towers[0].push(new Disk((5-i)));
            towers[1].push(new Disk(6));
            towers[2].push(new Disk(6));
        }

        diskRectList = new ArrayList<DiskRect>();


        // -------------------------------------------- gamePane - adding components
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

    /*
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
    */

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

        /*
        this.add(rod1Label);
        this.add(rod2Label);
        this.add(rod3Label);
        */
        this.add(rod1Button);
        this.add(rod2Button);
        this.add(rod3Button);
        this.add(status);

        this.setSize(800,600);
        this.setFocusable(true);
        this.setVisible(true);
    }


    //============================================================================ ActionListener

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    //=============================================================================== KeyListener

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