package electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class BillDetails extends JFrame {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BillDetails.class));

    JTable tBillDetailsTable;

    String[] x = {"Meter Number", "Month", "Units", "Total Bill", "Status"};

    String[][] y = new String[40][8];

    BillDetails (String meter) {

        //============================================================================================================//

        super("Bill Details");

        //============================================================================================================//

        LOGGER.info("==: BillDetails:: Inside BillDetails Constructor:==");

        //============================================================================================================//

        /*----Create Frame:----*/
        setSize(700, 650);
        setLocation(600, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        //============================================================================================================//

        /*----create a table:----*/
        tBillDetailsTable = new JTable(y, x);

        /*----Fetch all the information from bill table for specific meter no put into table----*/

        try {

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            // Fetch the all records from bill table where meter = ?:
            String billQuery = "select * from bill where meter = '" + meter + "'";
            LOGGER.info("billQuery: " + billQuery);

            ResultSet rs = s.executeQuery(billQuery);

            // set All the records into model:
            tBillDetailsTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----BillDetails:: Getting Exception BillDetails Constructor----" + errors);

        }

        //============================================================================================================//

        /*----set the scroll pane:----*/
        JScrollPane sp = new JScrollPane(tBillDetailsTable);
        sp.setBounds(0, 0, 700, 650);
        // scroll pane into frame:
        add(sp);

    }

    public static void main (String[] args) {

        LOGGER.info("==: BillDetails:: Inside main Method:==");
        new BillDetails("").setVisible(true);

    }

}
