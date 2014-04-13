import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Lia Bogoev using IntelliJ
 * 4/7/14
 * CS 2410
 */

public class GUI extends JFrame implements ActionListener, KeyListener{
    private JMenuBar menuBar;
    private JMenu newGame, options, about;
    private JMenuItem four, five, six, reset, quit, towers, dev;

    private final Dimension dimension = new Dimension(800,600);

    public GUI(){
        Container pane = getContentPane();
        pane.setLayout(null);

        // Menu bar setup
        menuBar = new JMenuBar();
        menuBar.setSize(800, 20);

        newGame = new JMenu("New Game");
        options = new JMenu("Options");
        about = new JMenu("About");

        menuBar.add(newGame);
        menuBar.add(options);
        menuBar.add(about);

        reset = new JMenuItem("Reset Game");
        reset.addActionListener(this);

        quit = new JMenuItem("Quit Game");
        quit.addActionListener(this);

        towers = new JMenuItem("About Towers of Hanoi");
        towers.addActionListener(this);

        dev = new JMenuItem("About Development");
        dev.addActionListener(this);

        four = new JMenuItem("Easy");
        four.addActionListener(this);

        five = new JMenuItem("Medium");
        five.addActionListener(this);

        six = new JMenuItem("Hard");
        six.addActionListener(this);

        newGame.add(four);
        newGame.add(five);
        newGame.add(six);
        options.add(reset);
        options.add(quit);
        about.add(towers);
        about.add(dev);

        reset.addActionListener(this);
        about.addActionListener(this);

        pane.add(menuBar);

        this.setSize(dimension);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(quit)){
            int quitResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Game", JOptionPane.YES_OPTION);
            if(quitResult == 0){
                System.exit(0);
            }
            else{
                // do nothing (go back to game)
            }
        }

        else if(e.getSource() == dev){
            String devText = new String ("\tDeveloped by Lia Bogoev \n\n" +
                                        "\tUSU CS2410: GUI Design in Java \n" +
                                        "\tApril 2014\n");
            JOptionPane.showMessageDialog(null, devText, "About Developer", JOptionPane.PLAIN_MESSAGE);
        }

        else if(e.getSource() == towers){
            String towersText = new String ("There is a story about an Indian temple in Kashi Vishwanath which contains a large room \n" +
                                            "with three time-worn posts in it surrounded by 64 golden disks. Brahmin priests, acting out \n" +
                                            "the command of an ancient prophecy, have been moving these disks, in accordance with the \n" +
                                            "immutable rules of the Brahma, since that time. The puzzle is therefore also known as the \n" +
                                            "Tower of Brahma puzzle. According to the legend, when the last move of the puzzle will be \n" +
                                            "completed, the world will end." +
                                            "\n\nSource: http://en.wikipedia.org/wiki/Tower_of_Hanoi" );
            JOptionPane.showMessageDialog(null, towersText, "About Towers of Hanoi", JOptionPane.PLAIN_MESSAGE);
        }
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
