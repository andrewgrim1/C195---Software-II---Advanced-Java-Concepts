package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;
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
 * This class is the AppointmentsMainController class which implements the Initializable interface.
 * This class initializes the data from the database and places it into tableviews for the user.
 * This class allows creates the functionalities for deleting customers and appointments and viewing appointments by month or week.
 * This class contains methods for redirecting the user to the Reports Page, and the Add or Update Appointments and Customers Pages.
 */
public class AppointmentsMainController implements Initializable {

    @FXML
    private Button add_appointment_AppointmentsMain;

    @FXML
    private Button modify_appointment_AppointmentsMain;

    @FXML
    private Button delete_appointment_AppointmentsMain;

    @FXML
    private Button add_customer_AppointmentsMain;

    @FXML
    private Button modify_customer_AppointmentsMain;

    @FXML
    private Button delete_customer_AppointmentsMain;

    @FXML
    private Button reports_AppointmentsMain;

    @FXML
    private Button logout_AppointmentsMain;

    @FXML
    private RadioButton currentWeekRB;

    @FXML
    private RadioButton currentMonthRB;

    @FXML
    private RadioButton allAppointmentsRB;

    @FXML
    private TableView<Appointment> appointments_tblview_AppointmentsMain;
    @FXML
    private TableColumn<TableRow, Integer> appointmentID;
    @FXML
    private TableColumn<TableRow, String> appointmentTitle;
    @FXML
    private TableColumn<TableRow, String> appointmentType;
    @FXML
    private TableColumn<TableRow, String> appointmentDescription;
    @FXML
    private TableColumn<TableRow, String> appointmentLocation;
    @FXML
    private TableColumn<TableRow, String> appointmentStartDateTime;
    @FXML
    private TableColumn<TableRow, String> appointmentEndDateTime;
    @FXML
    private TableColumn<TableRow, String> appointmentContact;
    @FXML
    private TableColumn<TableRow, Integer> appointmentCustomerID;
    @FXML
    private TableColumn<TableRow, Integer> appointmentUserID;


    @FXML
    private TableView<Customer> customers_tblview_AppointmentsMain;
    @FXML
    private TableColumn<TableRow, Integer> customerID;
    @FXML
    private TableColumn<TableRow, String> customerName;
    @FXML
    private TableColumn<TableRow, String> customerAddress;
    @FXML
    private TableColumn<TableRow, String> customerPhoneNumber;
    @FXML
    private TableColumn<TableRow, String> customerCountry;
    @FXML
    private TableColumn<TableRow, String> customerProvince;
    @FXML
    private TableColumn<TableRow, String> customerPostalCode;
    @FXML
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Appointment> currentWeekAppointments = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Appointment> currentMonthAppointments = FXCollections.observableArrayList();

    public static int dataspace = 1;
    public static ArrayList<Integer> apptIDsArrayList = new ArrayList<>(dataspace);
    public static int dataspace_cw = 1;
    public static ArrayList<Integer> apptIDsArrayListCW = new ArrayList<>(dataspace);
    public static int dataspace_cm = 1;
    public static ArrayList<Integer> apptIDsArrayListCM = new ArrayList<>(dataspace);
    public static int dataspace2 = 1;
    public static ArrayList<Integer> custIDsArrayList = new ArrayList<>(dataspace2);

    public static Appointment targetAppointment; //***************  Andrew -- this variable is private in the program you wrote for software I.
    public static Customer targetCustomer;

    public static Appointment getTargetAppointment() {
        return targetAppointment;
    }

    public static Customer getTargetCustomer() {
        return targetCustomer;
    }

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    private static Connection connection = null;  // Connection Interface

    public LocalDate today = LocalDate.now();
    public WeekFields weekFields = WeekFields.of(Locale.getDefault());


    public String alabama = "Alabama";
    public String arizona = "Arizona";
    public String arkansas = "Arkansas";
    public String california = "California";
    public String colorado = "Colorado";
    public String connecticut = "Connecticut";
    public String delaware = "Delaware";
    public String districtcolumbia = "The District of Columbia";
    public String florida = "Florida";
    public String georgia = "Georgia";
    public String idaho = "Idaho";
    public String illinois = "Illinois";
    public String indiana = "Indiana";
    public String iowa = "Iowa";
    public String kansas = "Kansas";
    public String kentucky = "Kentucky";
    public String louisiana = "Louisiana";
    public String maine = "Maine";
    public String maryland = "Maryland";
    public String massachusetts = "Massachusetts";
    public String michigan = "Michigan";
    public String minnesota = "Minnesota";
    public String mississippi = "Mississippi";
    public String missouri = "Missouri";
    public String montana = "Montana";
    public String nebraska = "Nebraska";
    public String nevada = "Nevada";
    public String newhampshire = "New Hampshire";
    public String newjersey = "New Jersey";
    public String newmexico = "New Mexico";
    public String newyork = "New York";
    public String northcarolina = "North Carolina";
    public String northdakota = "North Dakota";
    public String ohio = "Ohio";
    public String oklahoma = "Oklahoma";
    public String oregon = "Oregon";
    public String pennsylvania = "Pennsylvania";
    public String rhodeisland = "Rhode Island";
    public String southcarolina = "South Carolina";
    public String southdakota = "South Dakota";
    public String tennessee = "Tennessee";
    public String texas = "Texas";
    public String utah = "Utah";
    public String vermont = "Vermont";
    public String virginia = "Virginia";
    public String washington = "Washington";
    public String westvirginia = "West Virginia";
    public String wisconsin = "Wisconsin";
    public String wyoming = "Wyoming";
    public String hawaii = "Hawaii";
    public String alaska = "Alaska";
    public String northwestterritories = "The Northwest Territories";
    public String alberta = "Alberta";
    public String britishcolumbia = "British Columbia";
    public String manitoba = "Manitoba";
    public String newbrunswick = "New Brunswick";
    public String novascotia = "Nova Scotia";
    public String princeedwardisland = "Prince Edward Island";
    public String ontario = "Ontario";
    public String quebec = "Quebec";
    public String saskatchewan = "Saskatchewan";
    public String nunavut = "Nunavut";
    public String yukon = "Yukon";
    public String newfoundlandandlabrador = "Newfoundland and Labrador";
    public String england = "England";
    public String wales = "Wales";
    public String scotland = "Scotland";
    public String northernireland = "Northern Ireland";

    public String[] divisonIDArray = {"ph", alabama, arizona, arkansas, california, colorado, connecticut, delaware, districtcolumbia,
            florida, georgia, idaho, illinois, indiana, iowa, kansas, kentucky, louisiana, maine, maryland, massachusetts, michigan, minnesota, mississippi, missouri, montana, nebraska,
            nevada, newhampshire, newjersey, newmexico, newyork, northcarolina, northdakota, ohio, oklahoma, oregon, pennsylvania, rhodeisland, southcarolina, southdakota, tennessee, texas, utah, vermont,
            virginia, washington, westvirginia, wisconsin, wyoming, "ph", "ph", hawaii, "ph", alaska, "ph", "ph", "ph", "ph", "ph", northwestterritories, alberta, britishcolumbia, manitoba, newbrunswick, novascotia, princeedwardisland,
            ontario, quebec, saskatchewan, nunavut, yukon, newfoundlandandlabrador, "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph",
            "ph", "ph", "ph", "ph", "ph", england, wales, scotland, northernireland};

    /**
     * the selectAllAppointmentsRB method displays all the appoinments in the TableView when clicked
     *
     * @param event - selectAllAppointmentsRB takes the event ActionEvent
     * @throws IOException - selectAllAppointmentsRB throws IOException
     */
    @FXML
    public void selectAllAppointmentsRB(ActionEvent event) throws IOException {
        //When RB is selected, DESELECT all other RadioButtons
        //Display all appointments
        if (allAppointmentsRB.isSelected()) {
            currentMonthRB.setSelected(false);
            currentWeekRB.setSelected(false);
            appointments_tblview_AppointmentsMain.setItems((ObservableList<Appointment>) ACInventory.getAllAppointments());
            appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            appointmentType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
            appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
            appointmentStartDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDateFormat"));
            appointmentEndDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEndDateFormat"));
            appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
            appointmentUserID.setCellValueFactory(new PropertyValueFactory<>("appointmentUserID"));
            appointmentContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        }

    }


    /**
     * The selectCurrentWeekRB method displays all appointments in the tableview that are scheduled for the current week
     *
     * @param event - selectCurrentWeekRB takes the event ActionEvent
     * @throws IOException - selectCurrentWeekRB throws IOException
     */
    @FXML
    public void selectCurrentWeekRB(ActionEvent event) throws IOException {
        if (currentWeekRB.isSelected()) {
            currentMonthRB.setSelected(false);
            allAppointmentsRB.setSelected(false);
            appointments_tblview_AppointmentsMain.setItems((ObservableList<Appointment>) ACInventory.getAllCurrentWeekAppointments());
            appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            appointmentType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
            appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
            appointmentStartDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDateFormat"));
            appointmentEndDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEndDateFormat"));
            appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
            appointmentUserID.setCellValueFactory(new PropertyValueFactory<>("appointmentUserID"));
            appointmentContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        }
    }

    /**
     * The selectCurrentMonthRB method displays all Appointments in the tableview that are scheduled for the current month
     *
     * @param event - selectCurrentMonthRB takes the event ActionEvent
     * @throws IOException - selectCurrentMonthRB throws IOException
     */
    @FXML
    public void selectCurrentMonthRB(ActionEvent event) throws IOException {
        if (currentMonthRB.isSelected()) {
            allAppointmentsRB.setSelected(false);
            currentWeekRB.setSelected(false);
            appointments_tblview_AppointmentsMain.setItems((ObservableList<Appointment>) ACInventory.getAllCurrentMonthAppointments());
            appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
            appointmentType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
            appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
            appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
            appointmentStartDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDateFormat"));
            appointmentEndDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEndDateFormat"));
            appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
            appointmentUserID.setCellValueFactory(new PropertyValueFactory<>("appointmentUserID"));
            appointmentContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        }
    }

    /**
     * the logout_AppointmentsMain method opens the login page
     *
     * @param event - logout_AppointmentsMain takes the event ActionEvent
     * @throws IOException - logout_AppointmentsMain throws IOException
     */
    @FXML
    public void logout_AppointmentsMain(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * the reports_AppointmentsMain method opens the reports page
     *
     * @param event - reports_AppointmentsMain takes the event ActionEvent
     * @throws IOException - reports_AppointmentsMain throws IOException
     */
    @FXML
    public void reports_AppointmentsMain(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Reports.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * the add_appointment_AppointmentsMain method opens the add appointment page
     *
     * @param event - add_appointment_AppointmentsMain takes the event ActionEvent
     * @throws IOException - add_appointment_AppointmentsMain throws IOException
     */
    @FXML
    public void add_appointment_AppointmentsMain(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddAppointment.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * the modify_appointment_AppointmentsMain method opens the modify appointment page
     *
     * @param event - modify_appointment_AppointmentsMain takes the event ActionEvent
     * @throws IOException - modify_appointment_AppointmentsMain throws IOException
     */
    @FXML
    public void modify_appointment_AppointmentsMain(ActionEvent event) throws IOException {
        targetAppointment = appointments_tblview_AppointmentsMain.getSelectionModel().getSelectedItem();
        if (targetAppointment == null) {
            Alerts.alertCases(1);
        } else {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyAppointment.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * the add_customer_AppointmentsMain opens the add customer page
     *
     * @param event - add_customer_AppointmentsMain takes the event ActionEvent
     * @throws IOException - add_customer_AppointmentsMain throws IOException
     */
    @FXML
    public void add_customer_AppointmentsMain(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddCustomer.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * the modify_customer_AppointmentsMain method opens the modify customer page
     *
     * @param event - modfify_customer_AppointmentsMain takes the event ActionEvent
     * @throws IOException - modify_customer_AppointmentsMain throws IOException
     */
    @FXML
    public void modify_customer_AppointmentsMain(ActionEvent event) throws IOException {
        targetCustomer = customers_tblview_AppointmentsMain.getSelectionModel().getSelectedItem();
        if (targetCustomer == null) {
            Alerts.alertCases(3);
        } else {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyCustomer.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }


    /**
     * the deleteCustomer method:
     * - confirms that a customer object is selected in the tableview
     * - checks if the customer object has an appointment
     * deletes the customer object and removes it from database and lists
     *
     * @throws NullPointerException   - deleteCustomer throws a null pointer exception
     * @throws SQLException           - deleteCustomer throws a SQLException
     * @throws ClassNotFoundException - deleteCustomer throws a ClassNotFoundException
     */
    @FXML
    public void deleteCustomer() {
        Customer customer = customers_tblview_AppointmentsMain.getSelectionModel().getSelectedItem();
        String tempCustIDAsString = Integer.toString(customer.getCustomerID());
        int tempCustID = customer.getCustomerID();

        for(int a=0; a<ACInventory.allAppointments.size(); a++){
            appointments_tblview_AppointmentsMain.getSelectionModel().select(a);
            Appointment dummyAppt = appointments_tblview_AppointmentsMain.getSelectionModel().getSelectedItem();
            if(dummyAppt.getAppointmentCustomerID().equals(tempCustIDAsString)){
                try {
                    ACInventory.deleteAppointment(dummyAppt);
                    ACInventory.deleteAppointmentCurrentWeek(dummyAppt);
                    ACInventory.deleteAppointmentCurrentMonth(dummyAppt);
                    Class.forName(driver);
                    connection = DriverManager.getConnection(jdbcUrl, userName, password);
                    Statement st = connection.createStatement();
                    int targetApptID = dummyAppt.getAppointmentID();
                    String query1 = "DELETE FROM appointments WHERE Appointment_ID = "+targetApptID+"";
                    st.executeUpdate(query1);
                    closeDatabaseConnection();
                    Alert deleteConfirmer = new Alert(Alert.AlertType.INFORMATION);
                    deleteConfirmer.setTitle("Deletion Successful");
                    deleteConfirmer.setContentText("The appointment with ID: " + dummyAppt.getAppointmentID() + " and Type: " + dummyAppt.getAppointmentType() + " was successfully deleted.");
                    Optional<ButtonType> response = deleteConfirmer.showAndWait();
                    //everything checks out. delete the appointment's customer id from the ACInventory list
                    ACInventory.customerIDsThatHaveAppointmentsList.remove(tempCustIDAsString);
                } catch(ClassNotFoundException e) {
                    System.out.println("Error:" + e.getMessage());
                } catch(SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                }
            }
        }
        try {
                try {
                    Class.forName(driver);
                    connection = DriverManager.getConnection(jdbcUrl, userName, password);
                    Statement st = connection.createStatement();
                    int targetCustID = customer.getCustomerID();
                    String query1 = "DELETE FROM customers WHERE Customer_ID = "+targetCustID+"";
                    st.executeUpdate(query1);
                    closeDatabaseConnection();
                    Alert deleteConfirmer = new Alert(Alert.AlertType.INFORMATION);
                    deleteConfirmer.setTitle("Deletion Successful");
                    deleteConfirmer.setContentText("The customer with ID: " + customer.getCustomerID() + " was successfully deleted.");
                    Optional<ButtonType> response = deleteConfirmer.showAndWait();
                    //everything went well. remove the current customer id from all the lists
                    ACInventory.customerIDsThatHaveAppointmentsList.remove(tempCustIDAsString);
                } catch(ClassNotFoundException e) {
                    System.out.println("Error:" + e.getMessage());
                } catch(SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                }
                ACInventory.deleteCustomer(customer);
            } catch (NullPointerException e) {
                Alerts.alertCases(4);
            }
    }







    /**
     * the deleteAppointment method:
     *  - checks that an Appointment object is selected in the tableview
     *  - deletes the appointment object from the database and lists
     * @throws NullPointerException - deleteAppointment throws null pointer exception
     * @throws SQLException - deleteAppointment throws a SQLException
     * @throws ClassNotFoundException - deleteAppointment throws a ClassNotFoundException
     */
    @FXML
    public void deleteAppointment() {
            try {
                Appointment appointment = appointments_tblview_AppointmentsMain.getSelectionModel().getSelectedItem();
                String tempApptCustIDAsString = appointment.getAppointmentCustomerID();
                int tempApptCustID = Integer.parseInt(tempApptCustIDAsString);
                int tempApptID = appointment.getAppointmentID();
                ACInventory.deleteAppointment(appointment);
                ACInventory.deleteAppointmentCurrentWeek(appointment);
                ACInventory.deleteAppointmentCurrentMonth(appointment);
                try {
                    Class.forName(driver);
                    connection = DriverManager.getConnection(jdbcUrl, userName, password);
                    Statement st = connection.createStatement();
                    int targetApptID = appointment.getAppointmentID();
                    String query1 = "DELETE FROM appointments WHERE Appointment_ID = "+targetApptID+"";
                    st.executeUpdate(query1);
                    closeDatabaseConnection();
                    Alert deleteConfirmer = new Alert(Alert.AlertType.INFORMATION);
                    deleteConfirmer.setTitle("Deletion Successful");
                    deleteConfirmer.setContentText("The appointment with ID: " + appointment.getAppointmentID() + " and Type: " + appointment.getAppointmentType() + " was successfully deleted.");
                    Optional<ButtonType> response = deleteConfirmer.showAndWait();
                    //everything checks out. delete the appointment's customer id from the ACInventory list
                    ACInventory.customerIDsThatHaveAppointmentsList.remove(tempApptCustIDAsString);
                } catch(ClassNotFoundException e) {
                    System.out.println("Error:" + e.getMessage());
                } catch(SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                }
            } catch (NullPointerException e) {
                Alerts.alertCases(2);
            }
    }

    /**
     * the closeDatabaseConnection method closes the Database connection
     * @throws SQLException - closeDatabaseConnection throws a SQLException
     */
    public static void closeDatabaseConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The initialize method connects to the database and obtains data pertaining to customers and appointments
     * This data is then placed into Observable Lists located in ACInventory
     * The list data is then used to populate the tableviews
     * Some of these lists are specialized and allow for:
     *  - the user to view appointments by month or week
     *  - the appointment and customer IDs to be recorded for adding purposes
     *  - the customer IDs with scheduled appointments to be recorded
     *
     * @param url - initialize takes URL as an argument
     * @param resourceBundle - initialize takes resourceBundle as an argument
     * @throws ClassNotFoundException - initialize throws ClassNotFoundException
     * @throws SQLException - initialize throws SQLException
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> contactNamesList = new ArrayList<String>();
        List<String> contactIDsList = new ArrayList<String>();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            String query_contacts = "SELECT * FROM contacts;";
            Statement st_contacts = connection.createStatement();
            ResultSet contacts_rs = st_contacts.executeQuery(query_contacts);
            int contacts_rs_rowCount = 0;
            while (contacts_rs.next()) {
                String Contact_ID = contacts_rs.getString("Contact_ID");
                String Contact_Name = contacts_rs.getString("Contact_Name");
                contactNamesList.add(Contact_Name);
                contactIDsList.add(Contact_ID);
                contacts_rs_rowCount++;
            }
            st_contacts.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        } catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        closeDatabaseConnection();
        //DataPopulation from Database Attempt -- successfully grabs the contact name and id data
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            String query_appointments = "SELECT * FROM appointments;";
            Statement st_appointments = connection.createStatement();
            ResultSet appointments_rs = st_appointments.executeQuery(query_appointments);
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
                Month appointmentMonth = Month.from(appointmentStartDateTimeUTC);
                String appointmentCustomerID = appointments_rs.getString("Customer_ID");
                String appointmentUserID = appointments_rs.getString("User_ID");
                String appointmentContactIDasString = appointments_rs.getString("Contact_ID");
                String appointmentContact = "base value";
                String[] contactNamesArray = new String[contactNamesList.size()];
                String[] contactIDsArray = new String[contactIDsList.size()];
                contactNamesList.toArray(contactNamesArray);
                contactIDsList.toArray(contactIDsArray);
                for (int i = 0; i < contactNamesArray.length; i++) {
                    if (contactIDsArray[i].equals(appointmentContactIDasString)){
                        appointmentContact = contactNamesArray[i];
                    }
                }
                //this is the code that matters, the code that actually creates the appointment object
                Appointment newAppointment = new Appointment (appointmentID, appointmentTitle, appointmentType, appointmentDescription,
                        appointmentLocation, appointmentStartDateTime, appointmentEndDateTime, appointmentStartDateTimeUTC, appointmentStartDateTimeSystem,
                        appointmentStartDateTimeEST, appointmentEndDateTimeUTC, appointmentEndDateTimeSystem, appointmentEndDateTimeEST, appointmentStartDateFormat, appointmentEndDateFormat,
                        appointmentMonth, appointmentCustomerID, appointmentUserID, appointmentContact);

                String tempCustID = newAppointment.getAppointmentCustomerID();
                int tempapptid = newAppointment.getAppointmentID();
                if (apptIDsArrayList.isEmpty()){
                    //the apptIDSArrayList is empty so do this:
                    apptIDsArrayList.add(tempapptid); //add the current appointment id to the list of ids
                    //the current appt ID was added, because there are no other appts in the list. GOOD! Lets add this appointments customer id as well
                    ACInventory.customerIDsThatHaveAppointmentsList.add(tempCustID);
                    //appointment code
                    ACInventory.addAppointment(newAppointment); //add this appointment to the official list
                } else {
                    //the list of IDs has IDs on it
                    if (apptIDsArrayList.contains(tempapptid)) {
                        //the list of IDs DOES contain the current appointment's ID **THIS WILL NEVER HAPPEN**
                    } else {
                        //the list of IDs DOES NOT contain the current appointment's ID
                        apptIDsArrayList.add(tempapptid);
                        ACInventory.addAppointment(newAppointment);
                        ACInventory.customerIDsThatHaveAppointmentsList.add(tempCustID);
                    }
                }
                //the following code will add appointments that satsify params from the database to the observable lists in ACInventory for filtering of the Tableview
                int todayWeek = today.get(weekFields.weekOfWeekBasedYear());
                int appointmentWeek = appointmentStartDateTimeUTC.get(weekFields.weekOfWeekBasedYear());
                if (todayWeek == appointmentWeek){
                    if (apptIDsArrayListCW.isEmpty()){
                        apptIDsArrayListCW.add(tempapptid);
                        ACInventory.addCurrentWeekAppointment(newAppointment);
                    } else {
                        if(apptIDsArrayListCW.contains(tempapptid)){
                            //Don't add the current ID it's already in there
                        } else {
                                apptIDsArrayListCW.add(tempapptid);
                                ACInventory.addCurrentWeekAppointment(newAppointment);
                            }
                        }
                    }
                Month todayMonth = today.getMonth();
                Month tempAppointmentMonth = appointmentStartDateTimeUTC.getMonth();
                if (todayMonth == tempAppointmentMonth){
                    if (apptIDsArrayListCM.isEmpty()){
                        apptIDsArrayListCM.add(tempapptid);
                        ACInventory.addCurrentMonthAppointment(newAppointment);
                    } else {
                        if(apptIDsArrayListCM.contains(tempapptid)){
                            //Don't add the current ID it's already in there
                        } else {
                            apptIDsArrayListCM.add(tempapptid);
                            ACInventory.addCurrentMonthAppointment(newAppointment);
                        }
                    }
                }
                appointments_rs_rowCount++;
            }
            st_appointments.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        } catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        closeDatabaseConnection();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            String query_customers = "SELECT * FROM customers;";
            Statement st_customers = connection.createStatement();
            ResultSet customers_rs = st_customers.executeQuery(query_customers);
            int customers_rs_rowcount = 0;
            while (customers_rs.next()) {
                int customerID = customers_rs.getInt("Customer_ID");
                String customerName = customers_rs.getString("Customer_Name");
                String customerAddress = customers_rs.getString("Address");
                String customerPhoneNumber = customers_rs.getString("Phone");
                String customerCountry = customers_rs.getString("Division_ID");
                String customerProvince = customers_rs.getString("Division_ID");
                String customerPostalCode = customers_rs.getString("Postal_Code");
                String tempCustomerCountryDivisionIDasString = customerCountry;
                int tempCustomerCountryDivisionIDasInt = Integer.parseInt(tempCustomerCountryDivisionIDasString);
                customerProvince = divisonIDArray[tempCustomerCountryDivisionIDasInt];
                if (tempCustomerCountryDivisionIDasInt <= 54 ){
                    //USA
                    customerCountry = "USA";
                } else if (tempCustomerCountryDivisionIDasInt <= 72){
                    //Canada
                    customerCountry = "Canada";
                } else {
                    //UK
                    customerCountry = "UK";
                }
                //this is the code that matters, the code that actually creates the customer object
                Customer newCustomer = new Customer (customerID, customerName, customerAddress, customerPhoneNumber, customerCountry,
                        customerProvince, customerPostalCode);

                int tempcustid = newCustomer.getCustomerID();
                if (custIDsArrayList.isEmpty()){
                    //then just add the stuff
                    custIDsArrayList.add(tempcustid);
                    //appointment code
                    ACInventory.addCustomer(newCustomer);
                } else {
                    //check first
                    if (custIDsArrayList.contains(tempcustid)) {
                        //do nothing
                    } else {
                        custIDsArrayList.add(tempcustid);
                        ACInventory.addCustomer(newCustomer);
                    }
                }
                customers_rs_rowcount++;
            }
            st_customers.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());

        } catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        customers_tblview_AppointmentsMain.setItems((ObservableList<Customer>) ACInventory.getAllCustomers());
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        customerCountry.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));
        customerProvince.setCellValueFactory(new PropertyValueFactory<>("customerProvince"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));

        appointments_tblview_AppointmentsMain.setItems((ObservableList<Appointment>) ACInventory.getAllAppointments());
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentStartDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDateFormat"));
        appointmentEndDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEndDateFormat"));
        appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
        appointmentUserID.setCellValueFactory(new PropertyValueFactory<>("appointmentUserID"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
    }
}