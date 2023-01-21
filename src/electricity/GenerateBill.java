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
import java.util.Calendar;
import java.util.logging.Logger;

public class GenerateBill extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(GenerateBill.class));

    JLabel lGenerateBill, lMeter;

    JTextArea tScreenText;

    JButton generateBillButton;

    Choice cMonth;

    JPanel panel;

    String meter;

    GenerateBill (String meter) {

        LOGGER.info("==: GenerateBill:: Inside GenerateBill Constructor :==");

        //============================================================================================================//

        /*----Set the meter value----*/
        this.meter = meter;

        //============================================================================================================//

        /*----Create Frame----*/
        setSize(500, 900);
        setLocation(750, 100);
        setLayout(new BorderLayout());

        //============================================================================================================//

        /*----Create Panel----*/
        panel = new JPanel();

        //============================================================================================================//

        /*----Label for GenerateBill----*/
        lGenerateBill = new JLabel("Generate Bill");
        lGenerateBill.setFont(new Font("Tahoma", Font.BOLD, 12));
        // add lGenerateBill into panel:
        panel.add(lGenerateBill);

        //============================================================================================================//

        /*----Label for meter----*/
        lMeter = new JLabel(meter);
        lMeter.setFont(new Font("Tahoma", Font.BOLD, 12));
        lMeter.setForeground(new Color(0, 102, 102));
        // add lMeter into panel:
        panel.add(lMeter);

        //============================================================================================================//

        /*----Choice For Month----*/
        cMonth = new Choice();
        cMonth.setFont(new Font("Tahoma", Font.BOLD, 12));
        cMonth.setForeground(new Color(0, 102, 102));
        cMonth.add("January");
        cMonth.add("February");
        cMonth.add("March");
        cMonth.add("April");
        cMonth.add("May");
        cMonth.add("June");
        cMonth.add("July");
        cMonth.add("August");
        cMonth.add("September");
        cMonth.add("October");
        cMonth.add("November");
        cMonth.add("December");
        // add cMonth into panel:
        panel.add(cMonth);

        //============================================================================================================//

        /*----Text show in the screen----*/
        tScreenText = new JTextArea(50, 15);
        tScreenText.setText("\n\n\t------- Click on the -------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month\n\n");
        JScrollPane jsp = new JScrollPane(tScreenText);
        tScreenText.setFont(new Font("Senserif", Font.ITALIC, 18));
        add(jsp, "Center");


        //============================================================================================================//

        /*----Generate Bill Button----*/
        ImageIcon generateIcon = new ImageIcon(ClassLoader.getSystemResource("icon/generate.png"));
        Image generateImage = generateIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        generateBillButton = new JButton("Generate Bill", new ImageIcon(generateImage));
        generateBillButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        // add generateBillButton into frame:
        add(generateBillButton, "South");

        // add action listener on generate bill button:
        generateBillButton.addActionListener(this);


        //============================================================================================================//

        // add panel to frame:
        add(panel, "North");

    }

    public static void main (String[] args) {

        LOGGER.info("==: GenerateBill:: Inside main Method:==");

        new GenerateBill("").setVisible(true);

    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==:GenerateBill:: Inside actionPerformed method:==");

        // If we click on generateBill Button:
        try {

            // Get Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            // Fetch the current year:
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);

            // Fetch the select month:
            String month = cMonth.getSelectedItem();

            // On the bill slip heading:
            tScreenText.setText("\n\n\tReliance Power Limited\n" + "  " + " ELECTRICITY BILL GENERATED FOR THE " +
                    "MONTH\n\tOF " + month + "," + year + "\n\n");

            // Fetch all the customer details:
            String customerQuery = "select * from customer where meter=" + meter;
            ResultSet rs = s.executeQuery(customerQuery);

            if (rs.next()) {

                tScreenText.append("\n----------------------------------------------------------------------------");
                tScreenText.append("\n");
                tScreenText.append("\n");
                tScreenText.append("==============Customer Details==============");
                tScreenText.append("\n    Customer Name: \t" + rs.getString("name"));
                tScreenText.append("\n    Meter Number:   \t" + rs.getString("meter"));
                tScreenText.append("\n    Address:             \t" + rs.getString("address"));
                tScreenText.append("\n    State:                  \t" + rs.getString("state"));
                tScreenText.append("\n    City:                    \t" + rs.getString("city"));
                tScreenText.append("\n    Email:                 \t" + rs.getString("email"));
                tScreenText.append("\n    Phone Number:   \t" + rs.getString("phone"));
                tScreenText.append("\n");
                tScreenText.append("\n----------------------------------------------------------------------------");
                tScreenText.append("\n");

            }

            // Fetch all information of meter:
            String meterInfoQuery = "select * from meter_info where meter_number = " + meter;
            rs = s.executeQuery(meterInfoQuery);

            if (rs.next()) {

                tScreenText.append("\n=================Meter Info=================");
                tScreenText.append("\n    Meter Location:\t" + rs.getString("meter_location"));
                tScreenText.append("\n    Meter Type:      \t" + rs.getString("meter_type"));
                tScreenText.append("\n    Phase Code:    \t" + rs.getString("phase_code"));
                tScreenText.append("\n    Bill Type:         \t" + rs.getString("bill_type"));
                tScreenText.append("\n    Days:               \t" + rs.getString("days"));
                tScreenText.append("\n");
                tScreenText.append("\n----------------------------------------------------------------------------");
                tScreenText.append("\n");

            }

            // Fetch all information of tax:
            String taxQuery = "select * from tax";
            rs = s.executeQuery(taxQuery);

            if (rs.next()) {

                tScreenText.append("\n==================Tax Info==================");
                tScreenText.append("\n Cost per Unit:                     Rs " + rs.getString("cost_per_unit"));
                tScreenText.append("\n Meter Rent:                        Rs " + rs.getString("meter_rent"));
                tScreenText.append("\n Service Charge:                 Rs " + rs.getString("service_charge"));
                tScreenText.append("\n Service Tax:                      Rs " + rs.getString("service_tax"));
                tScreenText.append("\n Swachh Bharat Cess:       Rs " + rs.getString("swachh_bharat_cess"));
                tScreenText.append("\n Fixed Tax:                        Rs " + rs.getString("fixed_tax"));
                tScreenText.append("\n");
                tScreenText.append("\n----------------------------------------------------------------------------");
                tScreenText.append("\n");

            }

            // Fetch all information of bill:
            String billQuery =
                    "select * from bill where meter=" + meter + " AND month = '" + cMonth.getSelectedItem() + "'";
            rs = s.executeQuery(billQuery);

            if (rs.next()) {

                tScreenText.append("\n==================Bill Info==================");
                tScreenText.append("\n    Current Month :\t" + rs.getString("month"));
                tScreenText.append("\n    Units Consumed:\t" + rs.getString("units"));
                tScreenText.append("\n    Total Charges :\t" + rs.getString("total_bill"));
                tScreenText.append("\n----------------------------------------------------------");
                tScreenText.append("\n    TOTAL PAYABLE :\t" + rs.getString("total_bill"));
                tScreenText.append("\n----------------------------------------------------------");


            }

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----GenerateBill:: Inside Generate Bill action listener method----" + errors);

        }
    }
}

