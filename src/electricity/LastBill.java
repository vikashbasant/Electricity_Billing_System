package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class LastBill extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LastBill.class));


    JLabel l1;

    JTextArea t1, t2;

    JButton b1;

    JPanel p1;

    LastBill () {

        LOGGER.info("==: LastBill:: Inside LastBill Constructor Method:==");

        // set the size and layout:
        setSize(500, 900);
        setLayout(new BorderLayout());

        // Create panel:
        p1 = new JPanel();

        // Create Label "Generate Bill"
        l1 = new JLabel("Generate Bill");

        // Create TextArea:
        t2 = new JTextArea();

        // Create TextArea:
        t1 = new JTextArea(50, 15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif", Font.ITALIC, 18));

        // Create Button "Generate Bill":
        b1 = new JButton("Generate Bill");

        // adding label and panel:
        p1.add(l1);
        p1.add(t2);
        add(p1, "North");

        add(jsp, "Center");
        add(b1, "South");

        // adding action listener to "Generate Bill" Button:
        b1.addActionListener(this);

        // set location:
        setLocation(350, 40);
    }

    public static void main (String[] args) {
        LOGGER.info("==: LastBill:: Inside main Method:==");
        new LastBill().setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: LastBill:: Inside actionPerformed Method:==");

        try {

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            // Fetch all the records from customer table where meter = ?:
            ResultSet rs = s.executeQuery("select * from customer where meter=" + t2.getSelectedText());

            if (rs.next()) {

                // Fetch the all information and add into billTable table:
                t1.append("\n    Customer Name:" + rs.getString("name"));
                t1.append("\n    Meter Number:  " + rs.getString("meter"));
                t1.append("\n    Address:            " + rs.getString("address"));
                t1.append("\n    State:                 " + rs.getString("state"));
                t1.append("\n    City:                   " + rs.getString("city"));
                t1.append("\n    Email:                " + rs.getString("email"));
                t1.append("\n    Phone Number  " + rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");

            }

            // also append the last Bill:
            t1.append("Details of the Last Bills\n\n\n");

            // Fetch the record from bill table where meter = ?:
            rs = s.executeQuery("select * from bill where meter=" + t2.getSelectedText());

            while (rs.next()) {

                // Fetch the all information just simply add to table:
                t1.append("       " + rs.getString("month") + "           " + rs.getString("amount") + "\n");

            }

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----LastBill:: Getting Exception actionPerformed----" + e);


        }
    }
}

