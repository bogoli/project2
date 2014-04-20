import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * Created by Lia Bogoev using IntelliJ
 * 4/13/14
 * CS 2410
 */
public class Game extends JPanel implements ActionListener, KeyListener{

    private JLabel status;
    private JButton rod1Button, rod2Button, rod3Button;
    private Color easyColor = new Color(120,120,120);
    private Color medColor = new Color(100,100,100);
    private Color hardColor = new Color(75,75,75);

    private Color diskColor6  = new Color(100,200,50);
    private Color diskColor5  = new Color(130,200,50);
    private Color diskColor4  = new Color(160,200,50);
    private Color diskColor3  = new Color(190,200,50);
    private Color diskColor2  = new Color(220,200,50);
    private Color diskColor1  = new Color(250,200,50);
    private Color selected = new Color(250, 50, 70);

    public int numberOfDisks;
    public int storedTowerID;

    public LinkedList<DiskRect>[] diskRectList = new LinkedList[3];
    public LinkedList<Disk>[] towers = new LinkedList[3];

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
        public int xPos, yPos, width, size;
        public Color color;

        public DiskRect(int size) {
            this.size = size;
            xPos = 125  + (15 * (5-size));
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


    public void addRect(int size) {
        DiskRect diskRect = new DiskRect(size);
        diskRectList[0].add(diskRect);
    }


    public void paint(Graphics g) {
        //super.paint(g);

        // use double buffering
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) bufferedImage.getGraphics();

        super.paint(buffer);

        for (DiskRect b : diskRectList[0]){
            b.draw(buffer);
        }
        for (DiskRect b : diskRectList[1]){
            b.draw(buffer);
        }
        for (DiskRect b : diskRectList[2]){
            b.draw(buffer);
        }

        g.drawImage(bufferedImage, 0, 0, this);
    }


    //----------------------------------------------- GAME moveDisk function

    public void moveDisk(int orig, int dest){
        try{
        if(towers[dest].isEmpty()){
            if(towers[orig].peekLast().size < towers[dest].peekLast().size){
                System.out.println("Orig.size = " + towers[orig].peekLast().size);
                towers[dest].add(towers[orig].removeLast());
                moveDiskRect(orig, dest);
                repaint();
                System.out.println("Moved from " + orig + " to " + dest);

            }
            else{
                System.out.println("invalid move.");
            }
        }
        if(!towers[dest].isEmpty()){
            towers[dest].add(towers[orig].removeLast());
            moveDiskRect(orig, dest);
            repaint();
            System.out.println("Moved from " + orig + " to " + dest);
        }
        }catch(NullPointerException e){
            System.out.println("towers.orig empty?");

        }
    } // end moveDisk



    public void moveDiskRect(int orig, int dest){
        diskRectList[orig].getLast().xPos += 200 * (dest - orig);
        diskRectList[orig].getLast().yPos += 30 * ((diskRectList[orig].size()-1) - diskRectList[dest].size());
        diskRectList[dest].add(diskRectList[orig].removeLast());

        switch(diskRectList[dest].peekLast().size){
            case 1:
                diskRectList[dest].getLast().color = diskColor1;
                break;
            case 2:
                diskRectList[dest].getLast().color = diskColor2;
                break;
            case 3:
                diskRectList[dest].getLast().color = diskColor3;
                break;
            case 4:
                diskRectList[dest].getLast().color = diskColor4;
                break;
            case 5:
                diskRectList[dest].getLast().color = diskColor5;
                break;
            case 6:
                diskRectList[dest].getLast().color = diskColor6;
                break;
        }

    }

    public void colorDiskRect(int orig){
        diskRectList[orig].getLast().color = selected;
    }

    //========================================================================== GAME CONSTRUCTOR

    public Game(int numberOfDisks){
        addKeyListener(this);
        this.numberOfDisks = numberOfDisks;
        this.storedTowerID = -1;

        for (int i = 0; i < 3; ++i){
            diskRectList[i] = new LinkedList<DiskRect>();
            towers[i] = new LinkedList<Disk>();
        }

        for(int i=0; i < 5; ++i){
            towers[0].add(new Disk((i)));
            towers[1].add(new Disk(6));
            towers[2].add(new Disk(6));
        }


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
                try{
                switch(e.getKeyChar()){
                    case '1':
                        //colorDiskRect(0);
                        repaint();
                        storedTowerID = 0;
                        break;
                    case '2':
                        //colorDiskRect(1);
                        repaint();
                        storedTowerID = 1;
                        break;
                    case '3':
                        //colorDiskRect(2);
                        repaint();
                        storedTowerID = 2;
                        break;
                    }
                }catch(NoSuchElementException noSuch){
                    System.out.println("No Such Element Exception in colorDiskRect switch");
                    System.out.println(noSuch.getMessage());
                    storedTowerID = -1;
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
                        moveDisk(0, 2);
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