package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class About extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(About.class));

    private static final String TEXT = "                                              \n  "
            + "\nElectricity Billing System is a software-based application "
            + "developed in Java programming language. The project aims at serving "
            + "the department of electricity by computerizing the billing system. "
            + "It mainly focuses on the calculation of Units consumed during the "
            + "specified time and the money to be paid to electricity offices. "
            + "This computerized system will make the overall billing system easy, "
            + "accessible, comfortable and effective for consumers.\n\n";


    public About () {

        //============================================================================================================//

        LOGGER.info("==: About:: Inside About Constructor Method:==");


        //============================================================================================================//

        /*----Create Frame----*/
        setBounds(700, 220, 500, 500);
        setLayout(null);

        //============================================================================================================//


        /*----Label For About Project:----*/
        JLabel lAboutProject = new JLabel("!TEXT PROJECT!");
        lAboutProject.setBounds(150, 10, 200, 80);
        lAboutProject.setForeground(Color.red);
        lAboutProject.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lAboutProject);


        //============================================================================================================//

        /*----TextArea For TEXT:----*/
        TextArea tABOUT = new TextArea(TEXT, 10, 40, Scrollbar.VERTICAL);
        tABOUT.setEditable(false);
        tABOUT.setBounds(20, 100, 450, 250);
        tABOUT.setFont(new Font("RALEWAY", Font.BOLD, 17));
        add(tABOUT);


        //============================================================================================================//

        /*----Create GetStarted Button and also add some properties:----*/
        ImageIcon getStartedIcon = new ImageIcon(ClassLoader.getSystemResource("icon/GetStarted.png"));
        Image getStartedImage = getStartedIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        JButton letGetStarted = new JButton(new ImageIcon(getStartedImage));
        letGetStarted.setBounds(145, 380, 200, 40);
        letGetStarted.addActionListener(this);
        add(letGetStarted);


    }

    public static void main (String[] args) {
        LOGGER.info("==: About:: Inside main Method:==");
        new About().setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        LOGGER.info("==: About:: Inside actionPerformed Method :==");
        // close the current frame:
        this.setVisible(false);
        // then open the Splash frame:
        new Splash().setVisible(true);
    }


}
