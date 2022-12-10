package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.*;
import java.util.*;

/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the LoginController class.
 * THIS CLASS CONTAINS BOTH LAMBDA FUNCTIONS
 * LoginController checks for the users default language, and adjusts accordingly between English or French
 * LoginController checks for the users default ZoneID and displays the ZoneID in a label
 * LoginController allows the user to login or close the program
 * When the user attempts to login, the login attempt information is recorded in the login_activity.txt file
 * the users username and password are checked against the database, and if the database contains a match, the user is allowed to login
 * LoginController obtains all of the appointment data from the database and notifies the user as to whether or not an appointment is upcoming
 */

public class LoginController {
    @FXML
    private TextField login_username;

    @FXML
    private PasswordField login_password;

    @FXML
    private Button login_Login;

    @FXML
    private Button login_exit;

    @FXML
    private Label login_location;

    @FXML
    private Label locationLogin;

    @FXML
    private Label usernameLogin;

    @FXML
    private Label passwordLogin;

    @FXML
    private Label titleLogin;

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    private static Connection connection = null;  // Connection Interface
    public static int usernameNum = 0;
    public static int passwordNum = 0;

    public boolean thor = false;
    public boolean apptIn15 = false;
    public boolean langEnglishCheck = true;


    /**
     * the closeLoginConnection method closes the connection to the database
     * @throws SQLException - closeLoginConnection throws SQLException
     */
    public static void closeLoginConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * the login_Login method attempts to login using the user input username and password
     * if the username and password match the database username and password then the attempt is successful
     * any attempt is appended to the login_activity.txt file
     * the list of appointments from the database is checked for appointments within the next 15 minutes
     * the result of the appointments search is displayed to the user
     * after a successful login, the Appointments Main page is opened
     * after an unsuccessful login, the user is notified that their username and password were incorrect
     *
     * LAMBDA FUNCTION #1:
     * The login_Login method contains a lambda function
     * This lambda function loops through all the appointments in an observable list to determine if there is an appointment within the next 15 minutes
     *
     * LAMBDA FUNCTION #2:
     * Calls a function from the the LoginText interface that appends the current ZoneDateTime to the Zone String
     * This will ensure that the Login form displays the current time as well as the system zone location
     *
     *
     *
     * @param event - login_Login takes event ActionEvent
     * @throws IOException - login_Login throws IOException
     * @throws ClassNotFoundException - login_Login throws ClassNotFoundException
     * @throws SQLException - login_Login throws SQLException
     */
    @FXML
    public void login_Login(ActionEvent event) throws IOException {
        String pg_User_Name = login_username.getText();
        String pg_Password = login_password.getText();
        List<String> useridsList = new ArrayList<String>();
        List<String> usernamesList = new ArrayList<String>();
        List<String> passwordsList = new ArrayList<String>();
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(jdbcUrl, userName, password);
                String query = "SELECT * FROM users;";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String User_ID = rs.getString("User_ID");
                    String User_Name = rs.getString("User_Name");
                    String Password = rs.getString("Password");
                    if(!useridsList.contains(User_ID)){
                        useridsList.add(User_ID);
                    }
                    if(!usernamesList.contains(User_Name)){
                        usernamesList.add(User_Name);
                    }
                    if(!passwordsList.contains(Password)){
                        passwordsList.add(Password);
                    }
                }
                st.close();
            } catch(ClassNotFoundException e) {
                System.out.println("Error:" + e.getMessage());
            } catch(SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
            closeLoginConnection();
        String[] useridsArray = new String[useridsList.size()];
        String[] usernamesArray = new String[usernamesList.size()];
        String[] passwordsArray = new String[passwordsList.size()];
        useridsList.toArray(useridsArray);
        usernamesList.toArray(usernamesArray);
        passwordsList.toArray(passwordsArray);
        for(int i = 0; i < useridsArray.length; i++){
            boolean usercode = pg_User_Name.equals(usernamesArray[i]);
            boolean passcode = pg_Password.equals(passwordsArray[i]);
            if(usercode && passcode){
                thor = true;
                /*
                 * Here we will include the code that appends to the textfile with login attempt and result data
                 */
                try {
                    ZonedDateTime attemptDate = ZonedDateTime.now();
                    String attemptResult = "successful";
                    FileWriter file = new FileWriter("login_activity.txt." , true);
                    PrintWriter pw = new PrintWriter(file);
                    pw.print("Login-Attempt! Date: " + TimeConverter.respectableZDTwithSeconds(attemptDate) + ". Attempt was " +
                            attemptResult + " by the user with ID: " + useridsArray[i] +  " and username: " + usernamesArray[i] + ".\n");
                    pw.close();
                }
                catch (IOException e) {
                    System.out.println("Error:" + e.getMessage());
                }
                /*
                 * End Filewriter data
                 */
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsMain.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                //HERE YOU WILL PLACE THE CODE FOR DETERMINING IF THERE IS AN APPOINTMENT WITHIN FIFTEEN MINUTES OF THE LOGIN
                try {
                    Class.forName(driver);
                    connection = DriverManager.getConnection(jdbcUrl, userName, password);
                    String query = "SELECT * FROM appointments;";
                    Statement st = connection.createStatement();
                    ResultSet appointments_rs = st.executeQuery(query);
                    int appointments_rs_rowCount = 0;
                    while (appointments_rs.next()) {
                        int appointmentID = appointments_rs.getInt("Appointment_ID");
                        String appointmentTitle = appointments_rs.getString("Title");
                        String appointmentDescription = appointments_rs.getString("Description");
                        String appointmentLocation = appointments_rs.getString("Location");
                        String appointmentType = appointments_rs.getString("Type");
                        LocalDateTime appointmentStartDateTime = appointments_rs.getObject(6, LocalDateTime.class);
                        LocalDateTime appointmentEndDateTime = appointments_rs.getObject(7, LocalDateTime.class);
                        ZonedDateTime appointmentStartDateTimeUTC = appointments_rs.getObject(6, ZonedDateTime.class);
                        ZonedDateTime appointmentEndDateTimeUTC = appointments_rs.getObject(7, ZonedDateTime.class);
                        ZonedDateTime appointmentStartDateTimeSystem = TimeConverter.convertUTCtoSystem(appointmentStartDateTimeUTC);
                        ZonedDateTime appointmentEndDateTimeSystem = TimeConverter.convertUTCtoSystem(appointmentEndDateTimeUTC);
                        ZonedDateTime appointmentStartDateTimeEST = TimeConverter.convertUTCtoEST(appointmentStartDateTimeUTC);
                        ZonedDateTime appointmentEndDateTimeEST = TimeConverter.convertUTCtoEST(appointmentEndDateTimeUTC);
                        String appointmentStartDateFormat = TimeConverter.respectableZDT(appointmentStartDateTimeSystem);
                        String appointmentEndDateFormat = TimeConverter.respectableZDT(appointmentEndDateTimeSystem);
                        Month appointmentMonth = Month.from(appointmentStartDateTime);
                        String appointmentCustomerID = appointments_rs.getString("Customer_ID");
                        String appointmentUserID = appointments_rs.getString("User_ID");
                        String appointmentContactIDasString = appointments_rs.getString("Contact_ID");
                        String appointmentContact = "base value";
                        Appointment newAppointment = new Appointment(appointmentID, appointmentTitle, appointmentType, appointmentDescription,
                                appointmentLocation, appointmentStartDateTime, appointmentEndDateTime, appointmentStartDateTimeUTC, appointmentStartDateTimeSystem,
                                appointmentStartDateTimeEST, appointmentEndDateTimeUTC, appointmentEndDateTimeSystem, appointmentEndDateTimeEST, appointmentStartDateFormat, appointmentEndDateFormat,
                                appointmentMonth, appointmentCustomerID, appointmentUserID, appointmentContact);
                        ACInventory.addAppointmentBy15MinuteChecker(newAppointment);
                    }
                    st.close();
                } catch(ClassNotFoundException e) {
                    System.out.println("Error:" + e.getMessage());
                } catch(SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                }
                closeLoginConnection();
                //NOW WE HAVE AN OBSERVABLE LIST OF APPOINTMENTS THAT WE CAN CHECK FOR 15 MINUTE PROXIMITY

                //LAMBDA FUNCTION #1
                //This lambda function loops through all the appointments in an observable list to determine if there is an appointment within the next 15 minutes
                ACInventory.allAppointments15MinuteChecker.forEach(Appointment -> {
                    LocalTime startTimeLT = Appointment.getAppointmentStartDateTimeSystem().toLocalTime();
                    LocalTime currentTimeLT = LocalTime.now();
                    Boolean startIsAfterCurrent = startTimeLT.isAfter(currentTimeLT);
                    long elapsedMinutes = Duration.between(currentTimeLT, startTimeLT).toMinutes();
                    LocalDate startDateLD = Appointment.getAppointmentStartDateTimeSystem().toLocalDate();
                    LocalDate currentDateLD = LocalDate.now();
                    String respectable = TimeConverter.respectableZDT(Appointment.getAppointmentStartDateTimeSystem());
                    if (startDateLD.isEqual(currentDateLD)){
                        //THE APPOINTMENT IS SCHEDULED FOR TODAY
                        if(startIsAfterCurrent){
                            //THE APPOINTMENT IS IN THE FUTURE TODAY
                            System.out.println(elapsedMinutes);
                            if (0 <= elapsedMinutes && elapsedMinutes <= 15){
                                //THE APPOINTMENT IS IN THE NEXT 15 MINUTES
                                //set boolean to true to prevent a false alert
                                apptIn15 = true;
                                Alert alert15YES = new Alert(Alert.AlertType.INFORMATION);
                                alert15YES.setTitle("Upcoming Appointments");
                                alert15YES.setContentText("The appointment with ID: " + Appointment.getAppointmentID() + " is scheduled for " + respectable + ", which is in the next fifteen minutes.");
                                Optional<ButtonType> response = alert15YES.showAndWait();
                            } else {
                                //THE APPOINTMENT IS NOT IN THE NEXT 15 MINUTES
                            }
                        } else {
                            //THE APPOINTMENT IS NOT IN THE FUTURE TODAY
                        }
                    } else {
                        //THE APPOINTMENT IS NOT SCHEDULED FOR TODAY
                    }
                });
                //END LAMBDA FUNCTION #1
                if (!apptIn15){
                    //There was no appointment within 15 minutes
                    Alert alert15NO = new Alert(Alert.AlertType.INFORMATION);
                    alert15NO.setTitle("Upcoming Appointments");
                    alert15NO.setContentText("There are no upcoming appointments in the next fifteen minutes.");
                    Optional<ButtonType> response = alert15NO.showAndWait();
                }
            }
        }
        if(!thor){
            /**
             * Here we will include the code that appends to the textfile with login attempt and result data
             */
            try {
                ZonedDateTime attemptDate = ZonedDateTime.now();
                String attemptResult = "unsuccessful";
                FileWriter file = new FileWriter("login_activity.txt." , true);
                PrintWriter pw = new PrintWriter(file);
                pw.print("Login-Attempt! Date: " + TimeConverter.respectableZDTwithSeconds(attemptDate) + ". Attempt was "
                        + attemptResult + " by the user with username: " + pg_User_Name + ".\n");
                pw.close();
            }
            catch (IOException e) {
                System.out.println("Error:" + e.getMessage());
            }
            /**
             * End Filewriter data
             */
            if(!langEnglishCheck){
                Alerts.alertCases(13);
            } else {
                Alerts.alertCases(7);
            }
        }
    }

    /**
     * @param event - exit_Login takes event ActionEvent
     * @throws IOException - exit_Login throws IOException
     * The exit_Login method exits the program
     */
    @FXML
    public void exit_Login(ActionEvent event) throws IOException {
        System.exit(0);
    }

    /**
     * the initialize method initializes variables and determines the user language locale and default location
     */
    @FXML
    public void initialize() {
        //locale for language translation HERE
        Locale locale = Locale.getDefault();
        String lang = locale.getDisplayLanguage();
        String country = locale.getDisplayCountry();
        String langlocal = locale.getLanguage();
        String countrylocal = locale.getCountry();
        String english = "en";
        String french = "fr";
        boolean systemEnglish = langlocal.equals(english);
        boolean systemFrench = langlocal.equals(french);
        if(systemEnglish){
            //system language is English
            //set variable to TRUE (Default)
            langEnglishCheck = true;
        }
        if(systemFrench){
            //system language is French
            //set variable to FALSE
            langEnglishCheck = false;
        }
       if(!langEnglishCheck){
           //change it to French
           titleLogin.setText("Connexion");
           usernameLogin.setText("Nom d'utilisateur:");
           passwordLogin.setText("Mot de passe:");
           locationLogin.setText("Emplacement:");
           login_Login.setText("Connexion");
           login_exit.setText("Sortir");
       } else {
           //keep it as English
           //do nothing
       }

       ZoneId zone = ZoneId.systemDefault();
       String zoneString = zone.toString();
       ZonedDateTime now = ZonedDateTime.now();
       /*
       LAMBDA FUNCTION #2 -- Calls a function from the the LoginText interface that appends the current ZoneDateTime to the Zone String
        - This will ensure that the Login form displays the current time as well as the system zone location
        */
       LoginText LT = (z) -> z + ", " + TimeConverter.respectableZDT(now);

       String formatted = formatter(zoneString, LT);

        //String variable = zone.toString();
        login_location.setText(formatted);
        ACInventory.allAppointments15MinuteChecker.clear();
    }


    /**
     * The formatter method takes a string and a function from the LoginText interface and runs the change function
     * @param x - parameter x is a string value
     * @param y - parameter y is a LoginText value
     * @return - formatter returns the value r
     */
    public String formatter(String x, LoginText y){
        String r = y.change(x);
        return r;
    }
}