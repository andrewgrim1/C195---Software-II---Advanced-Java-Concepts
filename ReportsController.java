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
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the ReportsController class that implements the Initializable interface.
 * ReportsController displays data in tableviews that can be filtered by the user.
 * ReportsController also allows the user to navigate back to the Appointments Main page or logout of the program
 */

public class ReportsController implements Initializable {
    @FXML
    private Button back_Reports;
    @FXML
    private Button logout_Reports;
    @FXML
    private TableView<Appointment> scheduleTblView_Reports;
    @FXML
    private TableColumn<TableRow, Integer> scheduleID;
    @FXML
    private TableColumn<TableRow, String> scheduleTitle;
    @FXML
    private TableColumn<TableRow, String> scheduleType;
    @FXML
    private TableColumn<TableRow, String> scheduleDescription;
    @FXML
    private TableColumn<TableRow, String> scheduleStartDateTime;
    @FXML
    private TableColumn<TableRow, String> scheduleEndDateTime;
    @FXML
    private TableColumn<TableRow, Integer> scheduleCustomerID;
    @FXML
    private TableView<MonthsTable> MonthTblView_Reports;
    @FXML
    private TableColumn<MonthsTable, Month> monthMonth;
    @FXML
    private TableColumn<MonthsTable, Integer> monthTotal;
    @FXML
    private TableView<TypeTable> TypeTblView_Reports;
    @FXML
    private TableColumn<TypeTable, String> typeType;
    @FXML
    private TableColumn<TypeTable, Integer> typeTotal;
    @FXML
    private TableView<CountriesTable> CountryTblView_Reports;
    @FXML
    private TableColumn<CountriesTable, String> customCountry;
    @FXML
    private TableColumn<CountriesTable, Integer> customTotalCustomers;
    @FXML
    public ComboBox contactNamesCB;
    @FXML
    public ComboBox countriesCB;
    @FXML
    public ComboBox monthsCB;
    @FXML
    public ComboBox typesCB;

    List<String> contactNamesList = new ArrayList<String>();
    List<String> contactIDsList = new ArrayList<String>();

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

    public String[] months = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
    public String[] countries = {"USA", "UK", "Canada"};
    int counterVar = 0;
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
     * the logout_Reports method logs the user out of the program and opens the login page
     * @param event - logout_Reports takes event ActionEvent
     * @throws IOException - logout_Reports throws IOException
     */
    @FXML
    public void logout_Reports(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * the back_Reports method takes the user back to the appointments main page
     * @param event - back_Reports takes event ActionEvent
     * @throws IOException - back_Reports throws IOException
     */
    @FXML
    public void back_Reports(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsMain.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * closeDatabaseConnection closes the connection to the database
     * @throws SQLException - closeDatabaseConnection throws SQLException
     */
    public static void closeDatabaseConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * the selectionChangeTypes method filters the tableview that displays type
     * the tableview now will display only the type that is selected by the user
     * @param event - selectionChangeTypes takes event ActionEvent
     * @throws IOException - selectionChangeTypes throws IOException
     * @throws ClassNotFoundException - selectionChangeTypes throws ClassNotFoundException
     * @throws SQLException - selectionChangeTypes throws SQLException
     * @throws Exception - selectionChangeCountries throws Exception
     */
    @FXML
    public void selectionChangeTypes(ActionEvent event) throws IOException {
        try {
            //CLEAR THE MONTHS OBS LIST
            TypeTable.typesList.clear();
            counterVar = 0;
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
                    ZonedDateTime appointmentStartDateTimeUTC = TimeConverter.localDateTimeToUTC(appointmentStartDateTime);
                    ZonedDateTime appointmentStartDateTimeSystem = TimeConverter.localDateTimeToLocalTimeZone(appointmentStartDateTime);
                    ZonedDateTime appointmentStartDateTimeEST = TimeConverter.localDateTimeToEST(appointmentStartDateTime);
                    ZonedDateTime appointmentEndDateTimeUTC = TimeConverter.localDateTimeToUTC(appointmentEndDateTime);
                    ZonedDateTime appointmentEndDateTimeSystem = TimeConverter.localDateTimeToLocalTimeZone(appointmentEndDateTime);
                    ZonedDateTime appointmentEndDateTimeEST = TimeConverter.localDateTimeToEST(appointmentEndDateTime);
                    String appointmentStartDateFormat = TimeConverter.respectableZDT(appointmentStartDateTimeSystem);
                    String appointmentEndDateFormat = TimeConverter.respectableZDT(appointmentEndDateTimeSystem);
                    Month appointmentMonth = Month.from(appointmentStartDateTime);
                    String appointmentCustomerID = appointments_rs.getString("Customer_ID");
                    String appointmentUserID = appointments_rs.getString("User_ID");
                    String appointmentContactIDasString = appointments_rs.getString("Contact_ID");
                    String appointmentContact = "base value";
                    //this is the code that matters, the code that actually creates the appointment object
                    Appointment newAppointment = new Appointment (appointmentID, appointmentTitle, appointmentType, appointmentDescription,
                            appointmentLocation, appointmentStartDateTime, appointmentEndDateTime, appointmentStartDateTimeUTC, appointmentStartDateTimeSystem,
                            appointmentStartDateTimeEST, appointmentEndDateTimeUTC, appointmentEndDateTimeSystem, appointmentEndDateTimeEST, appointmentStartDateFormat, appointmentEndDateFormat, appointmentMonth,
                            appointmentCustomerID, appointmentUserID, appointmentContact);

                    String tempType = appointmentType;
                    if(tempType.equals(typesCB.getSelectionModel().getSelectedItem().toString())){
                        TypeTable.typesList.clear();
                        counterVar++;
                        int tempTotal = counterVar;
                        TypeTable.typesList.add(new TypeTable(tempType, tempTotal));
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
            TypeTblView_Reports.setItems(TypeTable.typesList);
            typeType.setCellValueFactory(new PropertyValueFactory<TypeTable, String>("type"));
            typeTotal.setCellValueFactory(new PropertyValueFactory<TypeTable, Integer>("total"));
        } catch (Exception e) {
            //do nothing
        }
    }

    /**
     * the selectionChangeCountries method filters the tableview that displays country and total customers from country
     * the tableview now will display only the country and customers that is selected by the user
     * @param event - selectionChangeCountries takes event ActionEvent
     * @throws IOException - selectionChangeCountries throws IOException
     * @throws ClassNotFoundException - selectionChangeCountries throws ClassNotFoundException
     * @throws SQLException - selectionChangeCountries throws SQLException
     * @throws Exception - selectionChangeCountries throws Exception
     */
    @FXML
    public void selectionChangeCountries(ActionEvent event) throws IOException {
        try {
            //CLEAR THE Countries OBS LIST
            CountriesTable.countriesList.clear();
            counterVar = 0;
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(jdbcUrl, userName, password);
                String query_appointments = "SELECT * FROM customers;";
                Statement st_appointments = connection.createStatement();
                ResultSet customers_rs = st_appointments.executeQuery(query_appointments);
                int appointments_rs_rowCount = 0;
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
                    if(customerCountry.equals(countriesCB.getSelectionModel().getSelectedItem().toString())){
                        CountriesTable.countriesList.clear();
                        counterVar++;
                        String tempCountry = customerCountry;
                        int tempTotal = counterVar;
                        CountriesTable.countriesList.add(new CountriesTable(tempCountry, tempTotal));
                    }
                }
            } catch(ClassNotFoundException e) {
                System.out.println("Error:" + e.getMessage());
            } catch(SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
            closeDatabaseConnection();
            CountryTblView_Reports.setItems(CountriesTable.countriesList);
            customCountry.setCellValueFactory(new PropertyValueFactory<CountriesTable, String>("country"));
            customTotalCustomers.setCellValueFactory(new PropertyValueFactory<CountriesTable, Integer>("total"));
        } catch (Exception e) {
            //do nothing
        }
    }

    /**
     * the selectionChangeMonth method filters the tableview that displays months and total appointments per month
     * the tableview now will display only the month and appointments that is selected by the user
     * @param event - selectionChangeMonth takes event ActionEvent
     * @throws IOException - selectionChangeMonth throws IOException
     * @throws ClassNotFoundException - selectionChangeMonth throws ClassNotFoundException
     * @throws SQLException - selectionChangeMonth throws SQLException
     * @throws Exception - selectionChangeMonth throws Exception
     */
    @FXML
    public void selectionChangeMonth(ActionEvent event) throws IOException {
        try {
            //CLEAR THE MONTHS OBS LIST
            MonthsTable.monthsList.clear();
            counterVar = 0;
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
                    ZonedDateTime appointmentStartDateTimeUTC = TimeConverter.localDateTimeToUTC(appointmentStartDateTime);
                    ZonedDateTime appointmentStartDateTimeSystem = TimeConverter.localDateTimeToLocalTimeZone(appointmentStartDateTime);
                    ZonedDateTime appointmentStartDateTimeEST = TimeConverter.localDateTimeToEST(appointmentStartDateTime);
                    ZonedDateTime appointmentEndDateTimeUTC = TimeConverter.localDateTimeToUTC(appointmentEndDateTime);
                    ZonedDateTime appointmentEndDateTimeSystem = TimeConverter.localDateTimeToLocalTimeZone(appointmentEndDateTime);
                    ZonedDateTime appointmentEndDateTimeEST = TimeConverter.localDateTimeToEST(appointmentEndDateTime);
                    String appointmentStartDateFormat = TimeConverter.respectableZDT(appointmentStartDateTimeSystem);
                    String appointmentEndDateFormat = TimeConverter.respectableZDT(appointmentEndDateTimeSystem);
                    Month appointmentMonth = Month.from(appointmentStartDateTime);
                    String appointmentCustomerID = appointments_rs.getString("Customer_ID");
                    String appointmentUserID = appointments_rs.getString("User_ID");
                    String appointmentContactIDasString = appointments_rs.getString("Contact_ID");
                    String appointmentContact = "base value";
                    //this is the code that matters, the code that actually creates the appointment object
                    Appointment newAppointment = new Appointment (appointmentID, appointmentTitle, appointmentType, appointmentDescription,
                            appointmentLocation, appointmentStartDateTime, appointmentEndDateTime, appointmentStartDateTimeUTC, appointmentStartDateTimeSystem,
                            appointmentStartDateTimeEST, appointmentEndDateTimeUTC, appointmentEndDateTimeSystem, appointmentEndDateTimeEST, appointmentStartDateFormat, appointmentEndDateFormat, appointmentMonth,
                            appointmentCustomerID, appointmentUserID, appointmentContact);
                    Month apptMonth = Month.from(appointmentStartDateTime);
                    String apptMonthAsString = apptMonth.toString();
                    if(apptMonthAsString.equals(monthsCB.getSelectionModel().getSelectedItem().toString())){
                        MonthsTable.monthsList.clear();
                        counterVar++;
                        Month tempMonth = apptMonth;
                        int tempTotal = counterVar;
                        MonthsTable.monthsList.add(new MonthsTable(tempMonth, tempTotal));
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
            MonthTblView_Reports.setItems(MonthsTable.monthsList);
            monthMonth.setCellValueFactory(new PropertyValueFactory<MonthsTable, Month>("month"));
            monthTotal.setCellValueFactory(new PropertyValueFactory<MonthsTable, Integer>("total"));
        } catch (Exception e) {
            //do nothing
        }
    }

    /**
     * the selectionChangeContact method filters the tableview that displays contacts and total appointments per contact
     * the tableview now will display only the contact and appointments that is selected by the user
     * @param event - selectionChangeContact takes event ActionEvent
     * @throws IOException - selectionChangeContact throws IOException
     * @throws ClassNotFoundException - selectionChangeContact throws ClassNotFoundException
     * @throws SQLException - selectionChangeContact throws SQLException
     * @throws Exception - selectionChangeContact throws Exception
     */
    @FXML
    public void selectionChangeContact(ActionEvent event) throws IOException {
        try {
            //CLEAR THE CONTACT OBS LIST
            ACInventory.allAppointmentsBySelectContact.clear();
            String[] contactNamesArray = new String[contactNamesList.size()];
            contactNamesList.toArray(contactNamesArray);
            String[] contactIDsArray = new String[contactIDsList.size()];
            contactIDsList.toArray(contactIDsArray);
            String tempContactName;
            String tempContactIDAsString;
            int tempContactID;
            for (int i=0; i<contactNamesArray.length; i++){
                if(contactNamesArray[i].equals(contactNamesCB.getSelectionModel().getSelectedItem().toString())){
                    //the current array value matches the selected item in the COMBOBOX
                    //Display all appointments with this contact name
                    tempContactName = contactNamesCB.getSelectionModel().getSelectedItem().toString();
                    tempContactIDAsString = contactIDsArray[i];
                    tempContactID = Integer.parseInt(tempContactIDAsString);
                    try {
                        Class.forName(driver);
                        connection = DriverManager.getConnection(jdbcUrl, userName, password);
                        String query_appointments = "SELECT * FROM appointments WHERE Contact_ID = "+tempContactID+";";
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
                            ZonedDateTime appointmentStartDateTimeUTC = TimeConverter.localDateTimeToUTC(appointmentStartDateTime);
                            ZonedDateTime appointmentStartDateTimeSystem = TimeConverter.localDateTimeToLocalTimeZone(appointmentStartDateTime);
                            ZonedDateTime appointmentStartDateTimeEST = TimeConverter.localDateTimeToEST(appointmentStartDateTime);
                            ZonedDateTime appointmentEndDateTimeUTC = TimeConverter.localDateTimeToUTC(appointmentEndDateTime);
                            ZonedDateTime appointmentEndDateTimeSystem = TimeConverter.localDateTimeToLocalTimeZone(appointmentEndDateTime);
                            ZonedDateTime appointmentEndDateTimeEST = TimeConverter.localDateTimeToEST(appointmentEndDateTime);
                            String appointmentStartDateFormat = TimeConverter.respectableZDT(appointmentStartDateTimeSystem);
                            String appointmentEndDateFormat = TimeConverter.respectableZDT(appointmentEndDateTimeSystem);
                            Month appointmentMonth = Month.from(appointmentStartDateTime);
                            String appointmentCustomerID = appointments_rs.getString("Customer_ID");
                            String appointmentUserID = appointments_rs.getString("User_ID");
                            String appointmentContactIDasString = appointments_rs.getString("Contact_ID");
                            String appointmentContact = "base value";
                            //this is the code that matters, the code that actually creates the appointment object
                            Appointment newAppointment = new Appointment (appointmentID, appointmentTitle, appointmentType, appointmentDescription,
                                    appointmentLocation, appointmentStartDateTime, appointmentEndDateTime, appointmentStartDateTimeUTC, appointmentStartDateTimeSystem,
                                    appointmentStartDateTimeEST, appointmentEndDateTimeUTC, appointmentEndDateTimeSystem, appointmentEndDateTimeEST, appointmentStartDateFormat, appointmentEndDateFormat,
                                    appointmentMonth, appointmentCustomerID, appointmentUserID, appointmentContact);
                            ACInventory.allAppointmentsBySelectContact.add(newAppointment);
                            appointments_rs_rowCount++;
                        }
                        st_appointments.close();
                    } catch(ClassNotFoundException e) {
                        System.out.println("Error:" + e.getMessage());
                    } catch(SQLException e) {
                        System.out.println("Error:" + e.getMessage());
                    }
                    closeDatabaseConnection();
                    //HERE we will make sure the tableview displays the proper data
                    //the schedule tableview displays all appointments for the COMBOBOX Selected Contact
                    scheduleTblView_Reports.setItems((ObservableList<Appointment>) ACInventory.getAllAppointmentsBySelectContact());
                    scheduleID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                    scheduleTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
                    scheduleType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
                    scheduleDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
                    scheduleStartDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDateFormat"));
                    scheduleEndDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEndDateFormat"));
                    scheduleCustomerID.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
                }
            }
        } catch (Exception e) {
            //do nothing
        }
    }

    /**
     * The initialize method populates the default tableview with list of appointments
     * the data needed to fill the comboboxes is obtained from the database
     * @param url - initialize takes url as an argument
     * @param resourceBundle - initialize takes resourceBundle as an argument
     * @throws ClassNotFoundException - initialize throws ClassNotFoundException
     * @throws SQLException - initialize throws SQLException
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> monthsObsList = FXCollections.observableArrayList();
        for (int i=0; i<months.length; i++){
            monthsObsList.add(months[i]);
        }
        monthsCB.setItems(monthsObsList);
        ObservableList<String> countriesObsList = FXCollections.observableArrayList();
        for (int i=0; i<countries.length; i++){
            countriesObsList.add(countries[i]);
        }
        countriesCB.setItems(countriesObsList);
        //DataPopulation from Database Attempt -- to grab the type data
        ObservableList<String> typesObsList = FXCollections.observableArrayList();
        typesCB.setItems(typesObsList);
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            String query_contacts = "SELECT * FROM appointments;";
            Statement st_contacts = connection.createStatement();
            ResultSet contacts_rs = st_contacts.executeQuery(query_contacts);
            int contacts_rs_rowCount = 0;
            while (contacts_rs.next()) {
                String type = contacts_rs.getString("Type");
                if(!typesObsList.contains(type)){
                    typesObsList.add(type);
                }
                contacts_rs_rowCount++;
            }
            st_contacts.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        } catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        closeDatabaseConnection();
        typesCB.setItems(typesObsList);
        //DataPopulation from Database Attempt -- successfully grabs the type data
        //DataPopulation from Database Attempt -- to grab the contact name data
        ObservableList<String> contactsObsList = FXCollections.observableArrayList();
        contactNamesCB.setItems(contactsObsList);
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
                contactsObsList.add(Contact_Name);
                contacts_rs_rowCount++;
            }
            st_contacts.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        } catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        closeDatabaseConnection();
        contactNamesCB.setItems(contactsObsList);
        //DataPopulation from Database Attempt -- successfully grabs the contact name data

        /*
         * We've obtained all of the contacts from the database to populate our COMBOBOX
         * Now we need to figure out which Contact is selected in the COMBOBOX so we can display
         * the correct information
         *
         * FIRST: Set the Default ComboBox to display ALL the appointments in the Scheduler
         * Then we can narrow it down to a specific contact using a selection change event
         */

        //the schedule tableview displays all appointments
        scheduleTblView_Reports.setItems((ObservableList<Appointment>) ACInventory.getAllAppointments());
        scheduleID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        scheduleTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        scheduleType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        scheduleDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        scheduleStartDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentStartDateFormat"));
        scheduleEndDateTime.setCellValueFactory(new PropertyValueFactory<>("appointmentEndDateFormat"));
        scheduleCustomerID.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerID"));
    }
}
