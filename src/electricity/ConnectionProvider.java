package org.electricity;


import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionProvider {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ConnectionProvider.class));

    private static Connection con;

    private static Properties readPropertiesFile(String fileName) {

        FileInputStream fis = null;
        Properties prop = null;

        try {

            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);

        } catch(Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("==: Getting Exception For Properties :==" + errors);

        } finally {

            assert fis != null;

            try {

                fis.close();

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("==: Getting Exception For File Close :==" + errors);

            }
        }
        return prop;
    }



    public static Connection getConnection(){

        try {

            LOGGER.info("==: Inside getConnection Method :==");

            // Getting application.properties values:
            Properties prop = readPropertiesFile("src/main/resources/application.properties");

            // load driver:
            Class.forName(prop.getProperty("database.className"));

            // create connection:
            Connection con = DriverManager.getConnection(prop.getProperty("database.url"), prop.getProperty(
                    "database.username"), prop.getProperty("database.password"));

            LOGGER.info("==: Connection Made Successfully :==" + con);

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("==: Getting Exception For getConnection Method :==" + errors);

        }

        return con;
    }

    public static void main(String[] args) {
        Connection connection = ConnectionProvider.getConnection();
    }
}
