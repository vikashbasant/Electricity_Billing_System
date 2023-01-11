package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class Paytm extends JFrame implements ActionListener{

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Paytm.class));

    String meter;

    JButton b1;

    Paytm(String meter){

        LOGGER.info("==: Paytm:: Inside Paytm Constructor :==");

        // set the meter value:
        this.meter = meter;


        JEditorPane j = new JEditorPane();
        j.setEditable(false);   

        // Create Back Button and also set the properties:
        b1 = new JButton("Back");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(500, 20, 120, 25);
        b1.addActionListener(this);
        j.add(b1);

        try {

            j.setPage("https://paytm.com/electricity-bill-payment");

        }catch (Exception e) {

            j.setContentType("text/html");
            j.setText("<html>Could not load</html>");

        }

        // Create scrollPane:
        JScrollPane scrollPane = new JScrollPane(j);     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(800,600));
        setSize(800,800);
        setLocation(250,120);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent ae){

        LOGGER.info("==: Paytm:: Inside actionPerformed Method:==");

        // current window is close:
        this.setVisible(false);

        // Then simply open PayBill Page:
        new PayBill(meter).setVisible(true);

    }
    public static void main(String[] args){

        LOGGER.info("==: Paytm:: Inside main Method:==");
        new Paytm("").setVisible(true);

    }
}
