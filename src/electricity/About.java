package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class About extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(About.class));

    JButton b1;
    JLabel l1;
    Font f, f1, f2;
    TextArea t1;
    String s;


    public About() {

        LOGGER.info("==: About:: Inside About Constructor Method:==");

        // set layout:
        setLayout(null);

        // Create Exist Button and also add some properties:
        JButton b1 = new JButton("Exit");
        add(b1);
        b1.setBounds(180, 430, 120, 20);
        b1.addActionListener(this);

        // Create font:
        Font f = new Font("RALEWAY", Font.BOLD, 180);
        setFont(f);

        // Create String:
        s = "                                    About Projects          \n  "
                + "\nElectricity Billing System is a software-based application "
                + "developed in Java programming language. The project aims at serving "
                + "the department of electricity by computerizing the billing system. "
                + "It mainly focuses on the calculation of Units consumed during the "
                + "specified time and the money to be paid to electricity offices. "
                + "This computerized system will make the overall billing system easy, "
                + "accessible, comfortable and effective for consumers.\n\n"
        ;


        // Create TextArea:
        TextArea t1 = new TextArea(s, 10, 40, Scrollbar.VERTICAL);
        t1.setEditable(false);
        t1.setBounds(20,100,450, 300);

        add(t1);

        // Create Font:
        Font f1 = new Font("RALEWAY", Font.BOLD, 16);
        t1.setFont(f1);

        // Create contentpane:
        Container contentPane = this.getContentPane();
        t1 = new TextArea();

        // Create Label "About Project":
        JLabel l1 = new JLabel("About Project");
        add(l1);
        l1.setBounds(170, 10, 180, 80);
        l1.setForeground(Color.red);

        Font f2 = new Font("RALEWAY", Font.BOLD, 20);
        l1.setFont(f2);

        setBounds(700, 220, 500, 550);

        setLayout(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LOGGER.info("==: About:: Inside actionPerformed Method :==");
        dispose();
    }


    public static void main(String[] args) {
        LOGGER.info("==: About:: Inside main Method:==");
        new About().setVisible(true);
    }


}
