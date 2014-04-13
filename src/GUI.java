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
    private JMenuItem four, five, six, reset, rules, quit, towers, dev;
    private JLabel introLabel;
    private JPanel introP, menuP;

    private final Dimension dimension = new Dimension(800,600);
    private static final String TITLE = "Towers of Hanoi";
    Container mainPane = this.getContentPane();

    Font menuFont = new Font("Helvetica", Font.PLAIN, 14);
    Font mainFont = new Font("Helvetica", Font.PLAIN, 24);

    public GUI(){
        // set UI component fonts and colors
        UIManager.put("MenuBar.font", menuFont);
        UIManager.put("Menu.font", menuFont);
        UIManager.put("MenuItem.font", menuFont);
        UIManager.put("Label.font", mainFont);
        UIManager.put("OptionPane.font", menuFont);

        mainPane.setLayout(new BorderLayout());

        // Menu bar setup
        menuBar = new JMenuBar();
        menuBar.setSize(800, 30);

        newGame = new JMenu("Start");
        options = new JMenu("Options");
        about = new JMenu("About");

        // Add Menus to MenuBar
        menuBar.add(newGame);
        menuBar.add(options);
        menuBar.add(about);

        reset = new JMenuItem("Reset Game");
        reset.addActionListener(this);

        rules = new JMenuItem("View Game Rules");
        rules.addActionListener(this);

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

        // Add MenuItems to Menus
        newGame.add(four);
        newGame.add(five);
        newGame.add(six);
        options.add(reset);
        options.add(rules);
        options.add(quit);
        about.add(towers);
        about.add(dev);

        // add the menu bar to the menu panel
        menuP = new JPanel(new BorderLayout());
        menuP.add(menuBar, BorderLayout.NORTH);

        introLabel = new JLabel("Welcome to Tower of Hanoi!");
        introLabel.setForeground(Color.black);
        introLabel.setSize(50,50);
        introLabel.setHorizontalAlignment(SwingConstants.CENTER);

        introP = new JPanel(new BorderLayout());
        introP.add(introLabel, BorderLayout.CENTER);
        introP.setBackground(Color.white);
        introP.setOpaque(true);



        // Add everything to contentPane
        mainPane.add(menuP, BorderLayout.NORTH);
        mainPane.add(introP, BorderLayout.CENTER);

        this.setSize(dimension);
        this.setTitle(TITLE);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    public void actionPerformed(ActionEvent e) {

        // new game ==================================================================
        if(e.getSource().equals(four)){
            System.out.println("Easy - four");
        }
        else if(e.getSource().equals(five)){
            System.out.println("med - five");
        }
        else if(e.getSource().equals(six)){
            System.out.println("hard - six");

        }

        // options ===================================================================

        else if(e.getSource().equals(reset)){
            System.out.println("reset");
        }

        else if(e.getSource().equals(rules)){
            String ruleText =  "The objective of the puzzle is to move the entire stack to another rod.\n\n" +
                                "— Only one disk can be moved at a time.\n" +
                                "— Each move consists of taking the upper disk from one of the stacks\n" +
                                "    and placing it on top of another stack i.e. a disk can only be \n" +
                                "    moved if it is the uppermost disk on a stack.\n" +
                                "— No disk may be placed on top of a smaller disk.";
            JOptionPane.showMessageDialog(null, ruleText, "Rules", JOptionPane.PLAIN_MESSAGE);
        }

        else if(e.getSource().equals(quit)){
            int quitResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Game", JOptionPane.YES_NO_OPTION);
            if(quitResult == 0){
                System.exit(0);
            }

        }

        // about =====================================================================
        else if(e.getSource() == dev){
            String devText =  "\tDeveloped by Lia Bogoev \n\n" +
                                        "\tUSU CS2410: GUI Design in Java \n" +
                                        "\tApril 2014\n";
            JOptionPane.showMessageDialog(null, devText, "About Developer", JOptionPane.PLAIN_MESSAGE);
        }

        else if(e.getSource() == towers){
            String towersText = "There is a story about an Indian temple in Kashi Vishwanath which contains a large room \n" +
                                "with three time-worn posts in it surrounded by 64 golden disks. Brahmin priests, acting out \n" +
                                "the command of an ancient prophecy, have been moving these disks, in accordance with the \n" +
                                "immutable rules of the Brahma, since that time. The puzzle is therefore also known as the \n" +
                                "Tower of Brahma puzzle. According to the legend, when the last move of the puzzle will be \n" +
                                "completed, the world will end." +
                                "\n\nSource: http://en.wikipedia.org/wiki/Tower_of_Hanoi";
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
