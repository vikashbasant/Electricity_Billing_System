package electricity;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class Signup extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Login.class));


    JPanel panel;
    JTextField userNameTextField, nameTextField, passwordPField, meterNumberTextField;
    Choice createAccountChoice;
    JButton createButton, backButton;

    public Signup () {


        LOGGER.info("==: Signup:: Inside Signup Constructor:==");

        //============================================================================================================//

        // Creating frame with setBounds() function whereas first 2 parameter are location of frame is open and
        // last 2 parameter are width and height of the frame:
        setBounds(600, 250, 700, 400);
        getContentPane().setBackground(Color.WHITE);

        //============================================================================================================//

        // JPanel is nothing frame inside frame:
        // Create JPanel For Create-Account:
        panel = new JPanel();
        // set properties of Create-Account for JPanel:
        panel.setBounds(30, 30, 650, 300);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setForeground(new Color(0, 102, 102));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 3), "Create-Account",
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14), new Color(51, 25, 0)));
        // add JPanel to frame:
        add(panel);


        //============================================================================================================//

        /*----- JLabel For Username-----*/
        JLabel userNameLabel = new JLabel("Username");
        // set properties for Username JLabel:
        userNameLabel.setForeground(Color.DARK_GRAY);
        userNameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        userNameLabel.setBounds(100, 50, 100, 20);
        // added Username JLabel into Panel panel:
        panel.add(userNameLabel);

        /*------Text Field For Username-----*/
        userNameTextField = new JTextField();
        // set properties for Text Field for Username
        userNameTextField.setBounds(260, 50, 150, 20);
        userNameTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
        // added Username Text Field into Panel panel:
        panel.add(userNameTextField);

        //============================================================================================================//

        /*----JLabel For Name-----*/
        JLabel nameLabel = new JLabel("Name");
        // set properties for Name JLabel:
        nameLabel.setForeground(Color.DARK_GRAY);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameLabel.setBounds(100, 90, 100, 20);
        // added Name JLabel into Panel panel:
        panel.add(nameLabel);

        /*----Text Field For Name----*/
        nameTextField = new JTextField();
        nameTextField.setBounds(260, 90, 150, 20);
        nameTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
        // added Name Text Field into Panel panel:
        panel.add(nameTextField);

        //============================================================================================================//

        /*----JLabel For Password----*/
        JLabel passwordLabel = new JLabel("Password");
        // set properties for Password JLabel:
        passwordLabel.setForeground(Color.DARK_GRAY);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordLabel.setBounds(100, 130, 100, 20);
        // added Password JLabel into Panel panel:
        panel.add(passwordLabel);

        /*----JPasswordField For Password----*/
        passwordPField = new JPasswordField(15);
        passwordPField.setBounds(260, 130, 150, 20);
        passwordPField.setFont(new Font("Tahoma", Font.BOLD, 14));
        // added Password JPasswordField into Panel panel:
        panel.add(passwordPField);

        //============================================================================================================//

        /*----JLabel For Create Account As----*/
        JLabel createAccountLabel = new JLabel("Create Account As");
        // set properties for Create Account As JLabel:
        createAccountLabel.setForeground(Color.DARK_GRAY);
        createAccountLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        createAccountLabel.setBounds(100, 170, 140, 20);
        // added Create Account As JLabel into Panel panel:
        panel.add(createAccountLabel);

        /*-----Choice For Create Account As----*/
        createAccountChoice = new Choice();
        createAccountChoice.add("Admin"); // added Admin to choice:
        createAccountChoice.add("Customer"); // added Customer to choice:
        createAccountChoice.setBounds(260, 170, 150, 20);
        createAccountChoice.setFont(new Font("Tahoma", Font.BOLD, 14));
        // added Create Account As Choice into Panel:
        panel.add(createAccountChoice);


        //============================================================================================================//


        /*----JLabel For Meter Number----*/
        JLabel meterNumberLabel = new JLabel("Meter Number");
        // set properties for Meter Number As JLabel:
        meterNumberLabel.setForeground(Color.DARK_GRAY);
        meterNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        meterNumberLabel.setBounds(100, 210, 100, 20);
        meterNumberLabel.setVisible(false);
        // added Meter Number JLabel into Panel panel:
        panel.add(meterNumberLabel);

        /*----Text Field For Meter Number----*/
        meterNumberTextField = new JTextField();
        // set properties for Meter Number Than Text Field:
        meterNumberTextField.setBounds(260, 210, 150, 20);
        meterNumberTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
        meterNumberTextField.setVisible(false);
        // added Meter Number Text Field into Panel panel:
        panel.add(meterNumberTextField);

        //============================================================================================================//

        //For Specific meter no fetch the name from login table then set into name text field after focus shifted
        // from meter no
        meterNumberTextField.addFocusListener(new FocusListener() {

            /**
             * Invoked when a component gains the keyboard focus.
             *
             * @param e
             */
            @Override
            public void focusGained (FocusEvent e) {

            }

            /**
             * Invoked when a component loses the keyboard focus.
             *
             * @param e
             */
            @Override
            public void focusLost (FocusEvent e) {
                try {
                    /*----Connection With Database----*/
                    Connection c = ConnectionProvider.getConnection();
                    Statement s = c.createStatement();

                    String loginQuery =
                            "select * from login where meter_no = '" + meterNumberTextField.getText() + "'";
                    ResultSet rs = s.executeQuery(loginQuery);

                    while (rs.next()) {
                        nameTextField.setText(rs.getString("name"));
                    }


                } catch (Exception ex) {

                    ex.printStackTrace();
                    StringWriter errors = new StringWriter();
                    ex.printStackTrace(new PrintWriter(errors));
                    LOGGER.info("----Signup:: Get Exception focusLost Method----" + errors);
                }
            }
        });


        //============================================================================================================//

        createAccountChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged (ItemEvent ae) {
                // If selectedItem is Customer: then Meter Number Field is Visible:
                String user = createAccountChoice.getSelectedItem();
                if (user.equals("Customer")) {
                    meterNumberLabel.setVisible(true);
                    meterNumberTextField.setVisible(true);

                    // If user is customer then customer name fetch from login table so that nameTextField non-editable:
                    nameTextField.setEnabled(false);
                    // If selectedItem is Admin: then Meter Number Field is hidden:
                } else {
                    meterNumberLabel.setVisible(false);
                    meterNumberTextField.setVisible(false);

                    // If user is admin then we need to give name value so that nameTextField editable:
                    nameTextField.setEnabled(true);
                }
            }
        });


        //============================================================================================================//



        /*----Create Create Button----*/
        ImageIcon iIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image createImage = iIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        createButton = new JButton("Create", new ImageIcon(createImage));
        // set properties of Create Button:
        createButton.setBackground(Color.WHITE);
        createButton.setForeground(Color.BLACK);
        createButton.setBounds(140, 290, 120, 30);
        createButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        // addActionListener when click on this button then they trigger the action listener:
        createButton.addActionListener(this);
        // added Create Button to Panel panel:
        panel.add(createButton);

        /*----Create Back Button----*/
        ImageIcon backIcon = new ImageIcon(ClassLoader.getSystemResource("icon/back3.png"));
        Image backImage = backIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);

        backButton = new JButton("Back", new ImageIcon(backImage));
        // set properties of Back Button:
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(300, 290, 120, 30);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        // addActionListener when click on this button then they trigger the action listener:
        backButton.addActionListener(this);
        // added Back Button to Panel panel:
        panel.add(backButton);


        //============================================================================================================//

        // ImageIcon For Signup:
        ImageIcon signupIcon = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        // Scale the Image:
        Image signupImage = signupIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon convertSignupIcon = new ImageIcon(signupImage);
        JLabel signupImg = new JLabel(convertSignupIcon);
        signupImg.setBounds(450, 30, 250, 250);
        // added signupImage into Panel:
        panel.add(signupImg);


    }

    public static void main (String[] args) {
        LOGGER.info("==: Signup:: Inside main method:==");
        new Signup().setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: Signup:: Inside actionPerformed method:==");
        // If click on Create Button:
        if (ae.getSource() == createButton) {

            // Fetch userName from userNameTextField:
            String username = userNameTextField.getText();
            LOGGER.info("userName: " + username);

            // Fetch name from nameTextField:
            String name = nameTextField.getText();
            LOGGER.info("name: " + name);

            // Fetch password from passwordPField:
            String password = passwordPField.getText();
            LOGGER.info("password: " + password);

            // Fetch user from createAccountChoice:
            String user = createAccountChoice.getSelectedItem();
            LOGGER.info("user: " + user);

            // Fetch meter from meterNumberTextField:
            String meter = meterNumberTextField.getText();
            LOGGER.info("meter: " + meter);

            try {

                // Get connection with database:
                Connection c = ConnectionProvider.getConnection();

                String query = null;

                // If user.equals("Admin")
                if (user.equals("Admin")) {

                    // If user is Admin, then all the information inserted into login table:
                    query = "insert into login values('" + meter + "', '" + username + "', '" + name + "', '" + password + "', '" + user + "')";

                } else {

                    // If user is customer, then all the information updated into login table on the condition of
                    // meter number:
                    query = "update login set username = '" + username + "', password = '" + password + "', user = '" + user + "' where meter_no = '" + meterNumberTextField.getText() + "'";

                }

                LOGGER.info("query: " + query);

                Statement s = c.createStatement();
                s.executeUpdate(query);

                // After successfully execute the query, then simply one pop-up message i.e "Account Created
                // Successfully"
                JOptionPane.showMessageDialog(null, "Account Created Successfully");

                // then current frame is closed:
                this.setVisible(false);

                // login frame is open:
                new Login().setVisible(true);

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----Signup:: Get Exception Create Button action listener----" + errors);
            }
            // If Click on Back Button:
        } else if (ae.getSource() == backButton) {

            // then simply closed the current frame:
            this.setVisible(false);

            // then again open the login frame:
            new Login().setVisible(true);
        }
    }
}
