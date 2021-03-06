import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.util.LinkedList;


/**
 * Created by Lia Bogoev using IntelliJ
 * 4/13/14
 * CS 2410
 */
public class Game extends JPanel implements ActionListener{

    private static JButton tower1B, tower2B, tower3B;
    // private JLabel status;

    // colors corresponding to disks
    private Color diskColor6  = new Color(100,200,50);
    private Color diskColor5  = new Color(130,200,50);
    private Color diskColor4  = new Color(160,200,50);
    private Color diskColor3  = new Color(190,200,50);
    private Color diskColor2  = new Color(220,200,50);
    private Color diskColor1  = new Color(250,200,50);
    private Color selected = new Color(250, 50, 70);

    public int numberOfDisks;   // determines the difficulty of the game (4,5,6 are the only options)
    public int storedTowerID;   // determines whether it's the first move (-1) or which tower the disk comes from (0,1,2)

    public LinkedList<DiskRect>[] diskRectList = new LinkedList[3];     // Linked list holding the graphics objects
    public LinkedList<Disk>[] towers = new LinkedList[3];               // Linked list holding simple Disk objects (which just have a size)

    //============================================================================== Disk SUBCLASS
    // Simple class representing the disks (logically)
    public class Disk {
        public int size;

        // Constructor
        public Disk(int size){
            this.size = size;
        }
    }
    //========================================================================== DiskRect SUBCLASS
    // class of graphics objects representing the disks (visually)
    public class DiskRect{
        public int xPos, yPos, width, size;
        public Color color;

        // Constructor
        public DiskRect(int size) {
            this.size = size;

            // positions each disk centered on top the the previous one
            xPos = 125  + (15 * (5-size));
            yPos = 200 + (30 * (size));
            width = 30 * size;

            // set color based on what the size is:
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
            }
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(xPos, yPos, width, 30);
        }

    }
    //=============================================================================== GRAPHICS METHODS

    // method called in the GUI class, which adds the disks to this panel
    public void addRect(int size) {
        DiskRect diskRect = new DiskRect(size);
        diskRectList[0].add(diskRect);
    }

    // paint method called by the panel (never called by me)
    public void paint(Graphics g) {
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

    // move function for the diskRect (only graphically, no checking if it is a legal move (see move())
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

    // changes the color of the block currently selected.
    public void colorDiskSelected(int orig){
        // make sure that both Linked lists (logical and graphical) aren't empty
        if(!towers[orig].isEmpty() && !diskRectList[orig].isEmpty()){
            diskRectList[orig].getLast().color = selected;
        }
        else{
            System.out.println("Error: tried to select empty tower. (inside colorDiskSelected)");
        }
    }

    // change the color of the disk back to its original color after it's moved.
    public void colorDiskBack(){
        for(int i = 0; i < 3; ++i){

            // make sure that both Linked lists (logical and graphical) aren't empty
            if(!towers[i].isEmpty() && !diskRectList[i].isEmpty()){
                switch(diskRectList[i].peekLast().size){
                    case 1:
                        diskRectList[i].getLast().color = diskColor1;
                        break;
                    case 2:
                        diskRectList[i].getLast().color = diskColor2;
                        break;
                    case 3:
                        diskRectList[i].getLast().color = diskColor3;
                        break;
                    case 4:
                        diskRectList[i].getLast().color = diskColor4;
                        break;
                    case 5:
                        diskRectList[i].getLast().color = diskColor5;
                        break;
                    case 6:
                        diskRectList[i].getLast().color = diskColor6;
                        break;
                }// end switch
            }
        }
    }

    //------------------------------------------------------------------------ GAME moveDisk function

    // move function for the disk --> All logical checks are here
    public void moveDisk(int orig, int dest){
        // if the first tower isn't empty
        if(!towers[orig].isEmpty()){

            // if the destination is empty, just do the move
            if(towers[dest].isEmpty()){
                towers[dest].add(towers[orig].removeLast());
                moveDiskRect(orig, dest);
                repaint();
                checkWin();
            }

            // if the first disk is smaller than the second disk
            else if(towers[orig].peekLast().size < towers[dest].peekLast().size){
                towers[dest].add(towers[orig].removeLast());
                moveDiskRect(orig, dest);
                repaint();
                checkWin();
            }

            // this is an invalid move for some reason or another
            else{
                colorDiskBack();
                repaint();
            }
        }

    } // end moveDisk

    //------------------------------------------------------------------------ GAME checkWin function

    // check for win
    public void checkWin(){
        if(!towers[2].isEmpty()){
            if(towers[2].size() == numberOfDisks){
                JOptionPane.showMessageDialog(null, "You won!", "Winner!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    //========================================================================== GAME CONSTRUCTOR

    public Game(int numberOfDisks){

        // Local variables
        Color easyColor = new Color(120,120,120);
        Color medColor = new Color(100,100,100);
        Color hardColor = new Color(75,75,75);

        final Dimension buttonDim = new Dimension(180, 40);

        // addKeyListener(this);

        this.numberOfDisks = numberOfDisks;
        this.storedTowerID = -1;

        for (int i = 0; i < 3; ++i){
            diskRectList[i] = new LinkedList<DiskRect>();
            towers[i] = new LinkedList<Disk>();
        }

        for(int i=0; i < numberOfDisks; ++i){
            towers[0].add(new Disk((numberOfDisks-i)));
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

        /*  // Not used in this version, but left for future
        status = new JLabel("");
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setSize(labelDim);
        status.setLocation(300,500);
        */

        // Buttons also function as labels
        tower1B = new JButton("1");
        tower1B.addActionListener(this);
        tower1B.setSize(buttonDim);
        tower1B.setLocation(110,450);

        tower2B = new JButton("2");
        tower2B.addActionListener(this);
        tower2B.setSize(buttonDim);
        tower2B.setLocation(310,450);

        tower3B = new JButton("3");
        tower3B.addActionListener(this);
        tower3B.setSize(buttonDim);
        tower3B.setLocation(510,450);

        this.add(tower1B);
        this.add(tower2B);
        this.add(tower3B);
        // this.add(status);

        this.setSize(800,600);
        this.setFocusable(true);
        this.setVisible(true);
    }


    //============================================================================ ActionListener

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(storedTowerID){
            case -1:
                if(e.getSource() == tower1B){
                    colorDiskSelected(0);
                    repaint();
                    storedTowerID = 0;
                }
                else if(e.getSource() == tower2B){
                    colorDiskSelected(1);
                    repaint();
                    storedTowerID = 1;
                }
                else if(e.getSource() == tower3B){
                    colorDiskSelected(2);
                    repaint();
                    storedTowerID = 2;
                }
                break;

            case 0:
                if(e.getSource() == tower1B){
                    colorDiskBack();
                    repaint();
                    storedTowerID = -1;
                }
                if(e.getSource() == tower2B){
                    moveDisk(0, 1);
                    storedTowerID = -1;
                }
                else if(e.getSource() == tower3B){
                    moveDisk(0, 2);
                    storedTowerID = -1;
                }
                break;

            case 1:
                if(e.getSource() == tower1B){
                    moveDisk(1,0);
                    storedTowerID = -1;
                }
                if(e.getSource() == tower2B){
                    colorDiskBack();
                    repaint();
                    storedTowerID = -1;
                }
                if(e.getSource() == tower3B){
                    moveDisk(1,2);
                    storedTowerID = -1;
                }
                break;

            case 2:
                if(e.getSource() == tower1B){
                    moveDisk(2,0);
                    storedTowerID = -1;
                }

                if(e.getSource() == tower2B){
                    moveDisk(2,1);
                    storedTowerID = -1;
                }
                if(e.getSource() == tower3B){
                    colorDiskBack();
                    repaint();
                    storedTowerID = -1;
                }
            break;
        }

    }


    //=============================================================================== KeyListener
/*
    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        this.requestFocus();
        switch(storedTowerID){
            case -1:
                switch(e.getKeyChar()){
                    case '1':
                        colorDiskSelected(0);
                        repaint();
                        storedTowerID = 0;
                        break;
                    case '2':
                        colorDiskSelected(1);
                        repaint();
                        storedTowerID = 1;
                        break;
                    case '3':
                        colorDiskSelected(2);
                        repaint();
                        storedTowerID = 2;
                        break;
                }
                break;
            case 0:
                switch(e.getKeyChar()){
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
                }
                break;
        }
    }
*/
}

