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


    JLabel lGenerateBill, lMeter;

    JTextArea tLastBillTextArea;

    JButton generateButton;

    JPanel panel;

    String meter;

    LastBill (String meter) {


        //============================================================================================================//

        LOGGER.info("==: LastBill:: Inside LastBill Constructor Method:==");

        //============================================================================================================//

        /*----set the meter value----*/
        this.meter = meter;

        //============================================================================================================//

        /*----Create Frame----*/
        setSize(500, 900);
        // set location:
        setLocation(350, 40);
        setLayout(new BorderLayout());

        //============================================================================================================//

        /*----Create panel:----*/
        panel = new JPanel();
        add(panel, "North");

        //============================================================================================================//

        /*----Create Label "Generate Bill"----*/
        lGenerateBill = new JLabel("Generate Bill");
        lGenerateBill.setFont(new Font("Senserif", Font.BOLD, 13));
        // add lGenerateBill into panel:
        panel.add(lGenerateBill);

        //============================================================================================================//

        /*----Create Label Meter----*/
        lMeter = new JLabel(meter);
        lMeter.setFont(new Font("Senserif", Font.BOLD, 13));
        lMeter.setForeground(new Color(0, 102, 102));
        // add lMeter into panel:
        panel.add(lMeter);

        //============================================================================================================//


        /*----Create TextArea:----*/
        tLastBillTextArea = new JTextArea(50, 15);
        JScrollPane jsp = new JScrollPane(tLastBillTextArea);
        tLastBillTextArea.setFont(new Font("Senserif", Font.ITALIC, 18));
        // add jsp into panel:
        add(jsp, "Center");


        //============================================================================================================//


        /*----Generate Bill Button----*/
        ImageIcon generateIcon = new ImageIcon(ClassLoader.getSystemResource("icon/generate.png"));
        Image generateImage = generateIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        generateButton = new JButton("Generate Bill", new ImageIcon(generateImage));
        generateButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        // add generateBillButton into frame:
        add(generateButton, "South");
        // add action listener on generate bill button:
        generateButton.addActionListener(this);

    }

    public static void main (String[] args) {
        LOGGER.info("==: LastBill:: Inside main Method:==");
        new LastBill("").setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: LastBill:: Inside actionPerformed Method:==");

        try {

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            String customerQuery = "select * from customer where meter=" + meter;

            // Fetch all the records from customer table where meter = ?:
            ResultSet rs = s.executeQuery(customerQuery);

            if (rs.next()) {

                // Fetch the all information and add into billTable table:
                tLastBillTextArea.append("\n----------------------------------------------------------------------------");
                tLastBillTextArea.append("\n");
                tLastBillTextArea.append("\n");
                tLastBillTextArea.append("===============Customer Details==============");
                tLastBillTextArea.append("\n    Customer Name:\t" + rs.getString("name"));
                tLastBillTextArea.append("\n    Meter Number:  \t" + rs.getString("meter"));
                tLastBillTextArea.append("\n    Address:            \t" + rs.getString("address"));
                tLastBillTextArea.append("\n    State:                 \t" + rs.getString("state"));
                tLastBillTextArea.append("\n    City:                   \t" + rs.getString("city"));
                tLastBillTextArea.append("\n    Email:                \t" + rs.getString("email"));
                tLastBillTextArea.append("\n    Phone Number:  \t" + rs.getString("phone"));
                tLastBillTextArea.append("\n----------------------------------------------------------------------------");
                tLastBillTextArea.append("\n");
                tLastBillTextArea.append("\n");

            }

            /*----also append the last Bill:----*/
            tLastBillTextArea.append("=============Details of the Last Bills============\n\n\n");


            String billQuery = "select * from bill where meter=" + meter;

            // Fetch the record from bill table where meter = ?:
            rs = s.executeQuery(billQuery);

            while (rs.next()) {

                // Fetch the all information just simply add to table:
                tLastBillTextArea.append("       " + rs.getString("month") + "           " + rs.getString("total_bill") + "\n");

            }

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----LastBill:: Getting Exception actionPerformed----" + e);


        }
    }
}