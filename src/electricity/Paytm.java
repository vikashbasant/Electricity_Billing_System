package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class Paytm extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Paytm.class));

    String meter;

    JButton backButton;

    Paytm (String meter) {

        //============================================================================================================//

        LOGGER.info("==: Paytm:: Inside Paytm Constructor :==");

        //============================================================================================================//

        /*----set the meter value:----*/
        this.meter = meter;

        //============================================================================================================//


        JEditorPane jEditorPane = new JEditorPane();
        jEditorPane.setEditable(false);

        try {

            jEditorPane.setPage("https://paytm.com/electricity-bill-payment");

        } catch (Exception e) {

            jEditorPane.setContentType("text/html");
            jEditorPane.setText("<html>Could not load</html>");

        }

        // Create scrollPane:
        JScrollPane scrollPane = new JScrollPane(jEditorPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(800, 600));
        setSize(800, 800);
        setLocation(250, 120);
        setVisible(true);

        //============================================================================================================//


        /*----Create Back Button and also set the properties:----*/
        ImageIcon backIcon = new ImageIcon(ClassLoader.getSystemResource("icon/back3.png"));
        Image backImage = backIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        backButton = new JButton("Back", new ImageIcon(backImage));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(500, 20, 120, 25);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.addActionListener(this);
        jEditorPane.add(backButton);

    }

    public static void main (String[] args) {

        LOGGER.info("==: Paytm:: Inside main Method:==");
        new Paytm("").setVisible(true);

    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: Paytm:: Inside actionPerformed Method:==");

        // current window is close:
        this.setVisible(false);

        // Then simply open PayBill Page:
        new PayBill(meter).setVisible(true);

    }
}
