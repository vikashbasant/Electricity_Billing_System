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

public class Login extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Login.class));

    JLabel userNameLabel, passwordLabel, l3, loginAsLabel;
    JTextField userNameTextField;
    JPasswordField passwordPField;
    JButton loginButton, signupButton, cancelButton;
    JPanel p1, p2, p3, p4;
    Choice loginAsChoice;

    Login () {

        //============================================================================================================//
        /*------Header--------*/
        // Super is first statement inside the constructor:
        super("Login Page"); // super keyword used for heading of the page:

        //============================================================================================================//

        LOGGER.info("==: Login:: Inside Login Constructor :==");

        //============================================================================================================//

        setLayout(null); // swing de-fault layout is set to be false, Because we are creating own layout:

        // Here getContentPane() gives the whole frame access: after that we need to set the color the frame.
        // Color Class present in awt package:
        getContentPane().setBackground(Color.WHITE);

        //============================================================================================================//

        /*------------------JLabel Class: used for write any thing on the frame----------------------*/

        /*-------JLabel For Username-------*/
        userNameLabel = new JLabel("Username"); // For creating Username JLabel:
        // setBounds function is working, when setLayout is null:
        userNameLabel.setBounds(300, 20, 100, 20);
        userNameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        // Username JLabel added into frame:
        add(userNameLabel);

        /*-------Text Field For Username-------*/
        userNameTextField = new JTextField(15); // For creating an input field:
        // set the properties of input field:
        userNameTextField.setBounds(400, 20, 150, 20);
        userNameTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
        // Username Text Field added into frame:
        add(userNameTextField);

        //============================================================================================================//


        /*-------JLabel For Password-------*/
        passwordLabel = new JLabel("Password"); // For creating Password JLabel:
        // set the properties of Password JLabel:
        passwordLabel.setBounds(300, 60, 100, 20);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        // password JLabel added into frame:
        add(passwordLabel);

        /*------Password Field For Password------*/
        passwordPField = new JPasswordField(15); // For creating a Password Field:
        // set the properties of password field:
        passwordPField.setBounds(400, 60, 150, 20);
        passwordPField.setFont(new Font("Tahoma", Font.BOLD, 14));
        // password field added into frame:
        add(passwordPField);


        //============================================================================================================//

        /*------JLabel For Logging in as-----*/
        loginAsLabel = new JLabel("Logging As"); // For creating Logging in as JLabel:
        // set the properties of Logging is as JLabel:
        loginAsLabel.setBounds(300, 100, 100, 20);
        loginAsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        // Logging in as JLabel added into frame:
        add(loginAsLabel);

        /*-----Drop-Down For Logging in as-----*/
        /* For Drop-Down we need used:
                1. Choice Class which is present in awt package:
                2. JComboBox Class which is also present in swing package:
            Both Class used for Drop-Down menu:
         */
        loginAsChoice = new Choice(); // Choice Class is used for Drop-Down:
        loginAsChoice.add("Admin"); // Admin added into drop-down:
        loginAsChoice.add("Customer"); // Customer added into drop-down:
        // set the properties of Drop-Down:
        loginAsChoice.setBounds(400, 100, 150, 20);
        loginAsChoice.setFont(new Font("Tahoma", Font.BOLD, 14));
        loginAsChoice.setForeground(new Color(0, 102, 102));
        // Logging in as Drop-Down added into frame:
        add(loginAsChoice);


        //============================================================================================================//



        /*-----Login Button-----*/
        // By the use of ImageIcon Class we can set the image over frame:
        ImageIcon loginIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        // now scale the loginIcon ImageIcon:
        Image loginImage = loginIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        // now create an Login Button with ImageIcon loginImage;
        loginButton = new JButton("Login", new ImageIcon(loginImage));
        // set the properties of Login Button:
        loginButton.setBounds(330, 160, 100, 20);
        // Login Button added in frame:
        add(loginButton);


        /*-----Cancel Button-----*/
        ImageIcon cancelIcon = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image cancelImage = cancelIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancelButton = new JButton("Cancel", new ImageIcon(cancelImage));
        cancelButton.setBounds(450, 160, 100, 20);
        // Cancel Button added in frame:
        add(cancelButton);


        /*------Signup Button------*/
        ImageIcon signupIcon = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image signupImage = signupIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signupButton = new JButton("Signup", new ImageIcon(signupImage));
        signupButton.setBounds(380, 200, 130, 20);
        // Signup Button added into frame:
        add(signupButton);


        //============================================================================================================//


        // adding the action listener on the all the 3 Button like Login, Signup, Cancel:
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        signupButton.addActionListener(this);


        //============================================================================================================//

        /*-----Image JLabel------*/
        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i3 = ic3.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        l3 = new JLabel(icc3);
        l3.setBounds(0, 0, 250, 250);
        // added the Login Image to frame:
        add(l3);


        //============================================================================================================//

        // Create a Frame of size and location on which location frame is open and also set the Layout of the frame:
        setLayout(new BorderLayout());
        setSize(640, 300);
        setLocation(600, 300);
        setVisible(true);

    }

    public static void main (String[] args) {

        LOGGER.info("==:Login:: Inside main method:==");

        new Login().setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: Login::Inside actionPerformed Method :==");

        /*----If Click on Login Button----*/
        if (ae.getSource() == loginButton) {

            try {

                /*-----Get the Connection With Database-----*/
                Connection c = ConnectionProvider.getConnection();

                // Fetch the username from userNameTextField from textarea:
                String userName = userNameTextField.getText();
                LOGGER.info("userName: " + userName);

                // Fetch the password from passwordPField from password area:
                String password = passwordPField.getText();
                LOGGER.info("password: " + password);

                // Fetch the selected item from choice loginAsChoice:
                String user = loginAsChoice.getSelectedItem();
                LOGGER.info("user: " + user);

                // Query For login:
                String query = "select * from login where username = '" + userName + "' and password = '" + password +
                        "'" + " and user = '" + user + "'";

                LOGGER.info("query: " + query);

                Statement s = c.createStatement();

                // Fetch the resultSet from login table where username = ? password = ? user = ?
                ResultSet rs = s.executeQuery(query);

                // If data records is present then go inside if condition:
                if (rs.next()) {

                    // Fetch the meter_no from resultSet:
                    String meter = rs.getString("meter_no");

                    // Call the Project page with parameter meter_no, user i.e (admin, customer):
                    new Project(meter, user).setVisible(true);
                    // Now close the window:
                    this.setVisible(false);


                    // If data records is not present then go inside else condition:
                } else {

                    // If the resultSet is null then one pop-up message show i.e Invalid login:
                    JOptionPane.showMessageDialog(null, "Invalid Login");

                    // In case of Invalid Login userName and password field set to be "empty":
                    // After showing message i.e Invalid login: set the username = "" and password="" i.e(empty):
                    userNameTextField.setText("");
                    passwordPField.setText("");

                }

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----Login:: Getting Exception Inside button login actionListener----" + e.getMessage());

            }
            /*----If Click on Cancel Button----*/
        } else if (ae.getSource() == cancelButton) {

            // Then window is closed:
            this.setVisible(false);

            /*----If Click on Signup Button----*/
        } else if (ae.getSource() == signupButton) {

            // First closed the login window
            this.setVisible(false);

            // Then call the Signup page and window is open:
            new Signup().setVisible(true);

        }

    }
}
