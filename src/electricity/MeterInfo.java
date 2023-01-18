package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class MeterInfo extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(MeterInfo.class));

    JLabel lMeterNo, lMeterLocation, lMeterType, lBillType, lPhaseCode, lDays, lNote, l8, lDaysValue, lNoteValue, lMeterValue;

    Choice cMeterLocation, cMeterType, cPhaseCode, cBillType;

    JButton submitButton, cancelButton;

    MeterInfo (String meter) {

        //============================================================================================================//

        LOGGER.info("==: MeterInfo:: Inside MeterInfo Constructor :==");

        //============================================================================================================//

        /*----create frame----*/
        // set the location and size:
        setLocation(600, 200);
        setSize(700, 500);

        //============================================================================================================//

        /*----Create a panel:----*/
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));

        //============================================================================================================//

        /*----Title of the panel:----*/
        JLabel title = new JLabel("Meter Information");
        title.setBounds(160, 10, 250, 26);
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        panel.add(title);

        //============================================================================================================//

        /*----Label Meter Number:-----*/
        lMeterNo = new JLabel("Meter Number");
        lMeterNo.setBounds(100, 80, 100, 20);
        lMeterNo.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Label Meter Number value:----*/
        lMeterValue = new JLabel(meter);
        lMeterValue.setBounds(240, 80, 200, 20);
        lMeterValue.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----adding label lMeterNo, lMeterValue into panel:----*/
        panel.add(lMeterNo);
        panel.add(lMeterValue);

        //============================================================================================================//

        /*----Label Meter Location:----*/
        lMeterLocation = new JLabel("Meter Location");
        lMeterLocation.setBounds(100, 120, 100, 20);
        lMeterLocation.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Choice Meter Location:----*/
        cMeterLocation = new Choice();
        cMeterLocation.add("Outside");
        cMeterLocation.add("Inside");
        cMeterLocation.setBounds(240, 120, 200, 20);
        cMeterLocation.setFont(new Font("Tahoma", Font.BOLD, 12));
        cMeterLocation.setForeground(new Color(0, 102, 102));

        /*----adding label and choice to panel:----*/
        panel.add(lMeterLocation);
        panel.add(cMeterLocation);

        //============================================================================================================//

        /*----Label Meter Type:----*/
        lMeterType = new JLabel("Meter Type");
        lMeterType.setBounds(100, 160, 100, 20);
        lMeterType.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Choice Meter Type:----*/
        cMeterType = new Choice();
        cMeterType.add("Electric Meter");
        cMeterType.add("Solar Meter");
        cMeterType.add("Smart Meter");
        cMeterType.setForeground(new Color(0, 102, 102));
        cMeterType.setBounds(240, 160, 200, 20);
        cMeterType.setFont(new Font("Tahoma", Font.BOLD, 12));


        /*----adding both label and choice to panel:----*/
        panel.add(lMeterType);
        panel.add(cMeterType);

        //============================================================================================================//

        /*----Label Phase Code:----*/
        lPhaseCode = new JLabel("Phase Code");
        lPhaseCode.setBounds(100, 200, 100, 20);
        lPhaseCode.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Choice Phase Code:-----*/
        cPhaseCode = new Choice();
        cPhaseCode.add("011");
        cPhaseCode.add("022");
        cPhaseCode.add("033");
        cPhaseCode.add("044");
        cPhaseCode.add("055");
        cPhaseCode.add("066");
        cPhaseCode.add("077");
        cPhaseCode.add("088");
        cPhaseCode.add("099");
        cPhaseCode.setBounds(240, 200, 200, 20);
        cPhaseCode.setFont(new Font("Tahoma", Font.BOLD, 12));
        cPhaseCode.setForeground(new Color(0, 102, 102));

        /*----adding both label and choice to panel:----*/
        panel.add(lPhaseCode);
        panel.add(cPhaseCode);


        //============================================================================================================//


        /*----Label Bill Type:----*/
        lBillType = new JLabel("Bill Type");
        lBillType.setBounds(100, 240, 100, 20);
        lBillType.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Choice Bill Type:----*/
        cBillType = new Choice();
        cBillType.add("Normal");
        cBillType.add("Industrial");
        cBillType.setBounds(240, 240, 200, 20);
        cBillType.setFont(new Font("Tahoma", Font.BOLD, 12));
        cBillType.setForeground(new Color(0, 102, 102));

        /*----adding both label and choice to panel:----*/
        panel.add(lBillType);
        panel.add(cBillType);


        //============================================================================================================//

        /*----Label Days:----*/
        lDays = new JLabel("Days");
        lDays.setBounds(100, 280, 100, 20);
        lDays.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Label Days value default set to "30 days"----*/
        lDaysValue = new JLabel("30 Days");
        lDaysValue.setBounds(240, 280, 200, 20);
        lDaysValue.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----adding both label to panel:----*/
        panel.add(lDays);
        panel.add(lDaysValue);

        //============================================================================================================//

        /*----Label Note:----*/
        lNote = new JLabel("Note");
        lNote.setBounds(100, 320, 100, 20);
        lNote.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Label Note value by default set to be "By Default Bill is calculated for 30 days only":----*/
        lNoteValue = new JLabel("By Default Bill is calculated for 30 days only");
        lNoteValue.setBounds(240, 320, 300, 20);
        lNoteValue.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----adding both label to panel:----*/
        panel.add(lNote);
        panel.add(lNoteValue);

        //============================================================================================================//


        /*----Creating Submit and set background and foreground color to it:----*/
        ImageIcon submitIcon = new ImageIcon(ClassLoader.getSystemResource("icon/save.png"));
        Image submitImage = submitIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        submitButton = new JButton("Submit", new ImageIcon(submitImage));
        submitButton.setBounds(120, 390, 100, 25);
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 12));

        submitButton.setBackground(Color.WHITE);
        submitButton.setForeground(Color.BLACK);


        ImageIcon cancelIcon = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image cancelImage = cancelIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancelButton = new JButton("Cancel", new ImageIcon(cancelImage));
        cancelButton.setBounds(250, 390, 100, 25);
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setForeground(Color.BLACK);

        /*----adding submit and cancel button to panel:----*/
        panel.add(submitButton);
        panel.add(cancelButton);

        /*----adding action listener to both submit anc cancel button:----*/
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);


        //============================================================================================================//

        setLayout(new BorderLayout());

        /*----set panel to center:----*/
        add(panel, "Center");

        //============================================================================================================//

        /*-----Label Image Icon on west side:----*/
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);
        add(l8, "West");
        /*----for changing the color of the whole Frame----*/
        getContentPane().setBackground(Color.WHITE);


    }

    public static void main (String[] args) {
        LOGGER.info("==: MeterInfo:: Inside Main Method :==");
        new MeterInfo("").setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: MeterInfo:: Inside actionPerformed Method:==");

        // Connection With database:
        Connection c = ConnectionProvider.getConnection();


        // Fetch meterNumber from lMeterValue:
        String meterNumber = lMeterValue.getText();
        LOGGER.info("meterNumber: " + meterNumber);

        // Fetch meterLocation from cMeterLocation:
        String meterLocation = cMeterLocation.getSelectedItem();
        LOGGER.info("meterLocation: " + meterLocation);

        // Fetch meterType from cMeterType:
        String meterType = cMeterType.getSelectedItem();
        LOGGER.info("meterType: " + meterType);

        // Fetch phaseCode from cPhaseCode:
        String phaseCode = cPhaseCode.getSelectedItem();
        LOGGER.info("phaseCode: " + phaseCode);

        // Fetch billType from cBillType:
        String billType = cBillType.getSelectedItem();
        LOGGER.info("billType" + billType);

        // Fixed value of days = 30"
        String days = "30";
        LOGGER.info("days: " + days);

        // If click on submit button:
        if (ae.getSource() == submitButton) {

            try {

                String query1 = "insert into meter_info values('" + meterNumber + "','" + meterLocation + "','" + meterType + "','" + phaseCode + "','" + billType + "','" + days + "')";
                LOGGER.info("query1: " + query1);


                Statement s = c.createStatement();

                // update the information into meter_info table:
                s.executeUpdate(query1);

                // after updating the information into meter_info table then simply
                // one pop-up message is shows "Meter Info Added Successfully":
                JOptionPane.showMessageDialog(null, "Meter Info Added Successfully");

                // Now simply current frame is close:
                this.setVisible(false);

            } catch (Exception ex) {

                ex.printStackTrace();
                StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----MeterInfo:: Getting Exception actionPerformed Submit Button----" + errors);

            }

            // If click on Button Cancel: Then simply delete the all information of customer table of specific
            // meterNumber:
        } else if (ae.getSource() == cancelButton) {

            String query2 = "delete from customer where meter = ?";
            LOGGER.info("query2: " + query2);

            try {

                PreparedStatement prest = c.prepareStatement(query2);
                prest.setString(1, meterNumber);

                int del = prest.executeUpdate();
                LOGGER.info("Number of Record are Deleted: " + del);

                // after updating the information into customer table then simply
                // one pop-up message is shows "Customer Info Deleted Successfully":
                JOptionPane.showMessageDialog(null, "Customer Info Deleted Successfully");

                // Now simply close current frame:
                this.setVisible(false);

                // Then simply open NewCustomer frame again:
                new NewCustomer().setVisible(true);


            } catch (SQLException e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----MeterInfo:: Getting Exception actionPerformed Cancel Button----" + errors);

            }


            // Simply close the current frame:
            this.setVisible(false);

        }
    }
}
