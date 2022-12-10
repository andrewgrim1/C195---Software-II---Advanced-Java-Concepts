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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
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
 * This class is the ModifyAppointmentController class which implements the Initializable interface.
 * This class takes selected appointment data and allows the user to edit and save it to the database if it passes logic checks
 *
 */
public class ModifyAppointmentController implements Initializable {

    @FXML
    public TextField appointmentID_txt_ModifyAppointment;
    @FXML
    public TextField appointmentTitle_txt_ModifyAppointment;
    @FXML
    public TextField appointmentType_txt_ModifyAppointment;
    @FXML
    public TextField appointmentDescription_txt_ModifyAppointment;
    @FXML
    public TextField appointmentLocation_txt_ModifyAppointment;
    @FXML
    public DatePicker appointmentStartDate_dp_ModifyAppointment; //start date
    @FXML
    public ComboBox appointmentStartTime_cb_ModifyAppointment; //start time combobox
    @FXML
    public DatePicker appointmentEndDate_dp_ModifyAppointment; //end date
    @FXML
    public ComboBox appointmentEndTime_cb_ModifyAppointment; //end time combobox
    @FXML
    public ComboBox appointmentCustomerID_cb_ModifyAppointment; //customer id combobox
    @FXML
    public ComboBox appointmentUserID_cb_ModifyAppointment; //userid combobox
    @FXML
    public ComboBox appointmentContact_cb_ModifyAppointment; //contact combobox
    @FXML
    public Label systemLister;
    @FXML
    private Button save_modifyAppointment;
    @FXML
    private Button cancel_modifyAppointment;

    private Appointment apptToModify;

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

    boolean errorWasFound = false;
    ZoneId zoneNY = ZoneId.of("America/New_York");
    ZoneId systemZone = ZoneId.systemDefault();

    public LocalDate today = LocalDate.now();
    public WeekFields weekFields = WeekFields.of(Locale.getDefault());

    public int tempFormerApptCustID;
    public int tempNewApptCustID;
    public String tempFormerApptCustIDAsString;
    public String tempNewApptCustIDAsString;

    /**
     * the cancel_modifyAppointment method opens the AppointmentsMain page
     * @param event - cancel_modifyAppointment takes event ActionEvent
     * @throws IOException - cancel_modifyAppointment throws IOException
     */
    @FXML
    public void cancel_modifyAppointment(ActionEvent event) throws IOException {
        Alert alert_Exit = new Alert(Alert.AlertType.CONFIRMATION);
        alert_Exit.setTitle("Cancel?");
        alert_Exit.setContentText("Continue with Cancellation? Data won't be saved...");
        Optional<ButtonType> response = alert_Exit.showAndWait();
        if (response.isPresent() && response.get() == ButtonType.OK) {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsMain.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * the save_modifyAppointment method checks the user input fields for changes
     * if no changes were made to the data, the user is asked to cancel the modification
     * if changes are detected, the data is checked for logic errors:
     *  - appointments cannot overlap for the same customer
     *  - appointments must be made between 8:00 am and 10:00 pm EST
     *  If the data passes the logic check, it is saved to the database, and the appropriate lists are updated
     *
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void save_modifyAppointment(ActionEvent event) throws IOException {
        errorWasFound = false;
        try {
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
            //START ORIGINAL DATA VARIABLES
            int originApptID = apptToModify.getAppointmentID();
            String originApptIDAsString = Integer.toString(originApptID);
            String originApptTitle = apptToModify.getAppointmentTitle();
            String originApptType = apptToModify.getAppointmentType();
            String originApptDescription = apptToModify.getAppointmentDescription();
            String originApptLocation = apptToModify.getAppointmentLocation();
            LocalDate originApptStartDateSystem = apptToModify.getAppointmentStartDateTimeSystem().toLocalDate();
            LocalDate originApptEndDateSystem = apptToModify.getAppointmentEndDateTimeSystem().toLocalDate();
            LocalTime originApptStartTimeSystem = apptToModify.getAppointmentStartDateTimeSystem().toLocalTime();
            LocalTime originApptEndTimeSystem = apptToModify.getAppointmentEndDateTimeSystem().toLocalTime();
            String originApptCustID = apptToModify.getAppointmentCustomerID();
            String originApptUserID = apptToModify.getAppointmentUserID();
            String originApptContact = apptToModify.getAppointmentContact();
            //END ORIGINAL DATA VARIABLES
            String appointmentIDAsString = appointmentID_txt_ModifyAppointment.getText();
            int appointmentID = Integer.parseInt(appointmentIDAsString);
            String appointmentTitle = appointmentTitle_txt_ModifyAppointment.getText();
            String appointmentType = appointmentType_txt_ModifyAppointment.getText();
            String appointmentDescription = appointmentDescription_txt_ModifyAppointment.getText();
            String appointmentLocation = appointmentLocation_txt_ModifyAppointment.getText();
            LocalDate appointmentStartDate = appointmentStartDate_dp_ModifyAppointment.getValue();
            String appointmentStartTime = appointmentStartTime_cb_ModifyAppointment.getSelectionModel().getSelectedItem().toString();
            LocalTime appointmentStartTimeLT = LocalTime.parse(appointmentStartTime);
            LocalDate appointmentEndDate = appointmentEndDate_dp_ModifyAppointment.getValue();
            String appointmentEndTime = appointmentEndTime_cb_ModifyAppointment.getSelectionModel().getSelectedItem().toString();
            LocalTime appointmentEndTimeLT = LocalTime.parse(appointmentEndTime);
            //combine Start Time + Date
            LocalDateTime appointmentStartDateTime = LocalDateTime.of(appointmentStartDate, appointmentStartTimeLT);
            ZonedDateTime appointmentStartDateTimeCompilerSystem = ZonedDateTime.of(appointmentStartDate, appointmentStartTimeLT, systemZone);
            //combine End Time + Date
            LocalDateTime appointmentEndDateTime = LocalDateTime.of(appointmentEndDate, appointmentEndTimeLT);
            ZonedDateTime appointmentEndDateTimeCompilerSystem = ZonedDateTime.of(appointmentEndDate, appointmentEndTimeLT, systemZone);
            ZonedDateTime appointmentStartDateTimeUTC = TimeConverter.convertSystemtoUTC(appointmentStartDateTimeCompilerSystem);
            ZonedDateTime appointmentStartDateTimeSystem = appointmentStartDateTimeCompilerSystem;
            ZonedDateTime appointmentStartDateTimeEST = TimeConverter.convertSystemtoEST(appointmentStartDateTimeCompilerSystem);
            ZonedDateTime appointmentEndDateTimeUTC = TimeConverter.convertSystemtoUTC(appointmentEndDateTimeCompilerSystem);
            ZonedDateTime appointmentEndDateTimeSystem = appointmentEndDateTimeCompilerSystem;
            ZonedDateTime appointmentEndDateTimeEST = TimeConverter.convertSystemtoEST(appointmentEndDateTimeCompilerSystem);
            String appointmentCustomerID = appointmentCustomerID_cb_ModifyAppointment.getSelectionModel().getSelectedItem().toString();
            String appointmentUserID = appointmentUserID_cb_ModifyAppointment.getSelectionModel().getSelectedItem().toString();
            String appointmentContact = appointmentContact_cb_ModifyAppointment.getSelectionModel().getSelectedItem().toString();
            String appointmentStartDateFormat = TimeConverter.respectableZDT(appointmentStartDateTimeSystem);
            String appointmentEndDateFormat = TimeConverter.respectableZDT(appointmentEndDateTimeSystem);
            Month appointmentMonth = Month.from(appointmentStartDateTimeUTC);
            String appointmentContactIDasString = appointmentContact;
            String[] contactNamesArray = new String[contactNamesList.size()];
            String[] contactIDsArray = new String[contactIDsList.size()];
            contactNamesList.toArray(contactNamesArray);
            contactIDsList.toArray(contactIDsArray);
            for (int i = 0; i < contactNamesArray.length; i++) {
                if (contactNamesArray[i].equals(appointmentContactIDasString)){
                    appointmentContactIDasString = contactIDsArray[i];
                }
            }
            int appointmentContactID = Integer.parseInt(appointmentContactIDasString);
            //HERE WE WILL USE THE ORIGINAL DATA VALUES
            //IF THE ORIGINAL VALUES ARE THE SAME AS THE NEW ONES, WE CAN BYPASS ALL OF THE CHECKS AND BALANCES
            //THE DATA IS THE SAME SO JUST CANCEL IT ALREADY
            boolean apptid = originApptIDAsString.equals(appointmentIDAsString);
            boolean appttitle = originApptTitle.equals(appointmentTitle);
            boolean appttype = originApptType.equals(appointmentType);
            boolean apptdesc = originApptDescription.equals(appointmentDescription);
            boolean apptloc = originApptLocation.equals(appointmentLocation);
            boolean apptStartDate = originApptStartDateSystem.equals(appointmentStartDate);
            boolean apptEndDate = originApptEndDateSystem.equals(appointmentEndDate);
            boolean apptStartTime = originApptStartTimeSystem.equals(appointmentStartTimeLT);
            boolean apptEndTime = originApptEndTimeSystem.equals(appointmentEndTimeLT);
            boolean apptCustID = originApptCustID.equals(appointmentCustomerID);
            boolean apptUserID = originApptUserID.equals(appointmentUserID);
            boolean apptContact = originApptContact.equals(appointmentContact);


            if (apptid && appttitle && appttype && apptdesc && apptloc && apptStartDate && apptEndDate && apptStartTime && apptEndTime && apptCustID && apptUserID && apptContact){
                Alerts.alertCases(12);
                return;
            }
            //HERE WE STOP USING THE ORIGINAL DATA VALUES
            //final stop before the appointment is created. Here we will make sure the time is between 8:00 am and 10:00 pm EST
            ZoneId zoneNY = ZoneId.of("America/New_York");
            //LocalTime EightAM = LocalTime.of(7, 59, 00, 00000);
            //LocalTime TenPM = LocalTime.of(22, 01, 00, 00000);
            //ZonedDateTime theoreticalStartDateTimeSystem = TimeConverter.convertUTCtoSystem(appointmentStartDateTimeUTC);
            //ZonedDateTime theoreticalEndDateTimeSystem = TimeConverter.convertUTCtoSystem(appointmentEndDateTimeUTC);
            ZonedDateTime theoreticalStartDateTimeEST = TimeConverter.convertUTCtoEST(appointmentStartDateTimeUTC);
            ZonedDateTime theoreticalEndDateTimeEST = TimeConverter.convertUTCtoEST(appointmentEndDateTimeUTC);
            LocalDate theoreticalStartDate = LocalDate.from(theoreticalStartDateTimeEST);
            LocalDate theoreticalEndDate = LocalDate.from(theoreticalEndDateTimeEST);
            if (theoreticalStartDate.isEqual(theoreticalEndDate)){
                //do nothing
            } else {
                Alerts.alertCases(10);
                return;
            }
            Instant StartInstant = theoreticalStartDateTimeEST.toInstant();
            Instant EndInstant = theoreticalEndDateTimeEST.toInstant();
            LocalTime theoreticalStartTime = LocalTime.ofInstant(StartInstant, zoneNY);
            LocalTime theoreticalEndTime = LocalTime.ofInstant(EndInstant, zoneNY);
            //FIRST check if TST == TET and if true call alert 11
            if (theoreticalStartTime.equals(theoreticalEndTime)){
                //ITS TRUE
                //call alert 11
                Alerts.alertCases(11);
                return;
            }
            LocalTime eightAM = LocalTime.of(7, 59, 00, 00000);
            LocalTime tenPM = LocalTime.of(22, 01, 00, 00000);
            ZonedDateTime theoreticalZDTStartEST = TimeChecker.zonedDateTimeBuilder(theoreticalStartDate, theoreticalStartTime, zoneNY);
            ZonedDateTime theoreticalZDTEndEST = TimeChecker.zonedDateTimeBuilder(theoreticalEndDate, theoreticalEndTime, zoneNY);
            ZonedDateTime eight = TimeChecker.zonedDateTimeBuilder(theoreticalStartDate, eightAM, zoneNY);
            ZonedDateTime ten = TimeChecker.zonedDateTimeBuilder(theoreticalStartDate, tenPM, zoneNY);
            boolean startBeforeEnd = TimeChecker.startIsBeforeEnd(theoreticalZDTStartEST, theoreticalZDTEndEST);
            boolean startAfter8 = TimeChecker.startIsAfter8(theoreticalZDTStartEST, eight);
            boolean startBefore10 = TimeChecker.startIsBefore10(theoreticalZDTStartEST, ten);
            boolean endAfter8 = TimeChecker.endIsAfter8(theoreticalZDTEndEST, eight);
            boolean endBefore10 = TimeChecker.endIsBefore10(theoreticalZDTEndEST, ten);
            if (startBeforeEnd && startAfter8 && startBefore10 && endAfter8 && endBefore10) {
            } else {
                Alerts.alertCases(10);
                return;
            }
            //Here we will make sure that the appointment does not cause an overlap for a customer
            //use an if statement to check if the customer has an appointment on that same date, and then check the time
            String theoreticalApptIDchecker = Integer.toString(appointmentID);

            ACInventory.allAppointments.forEach(Appointment -> {
                String tempCustIDapptchecker = Appointment.getAppointmentCustomerID();
                String tempApptIDchecker = Integer.toString(Appointment.getAppointmentID());
                boolean custidbool = tempCustIDapptchecker.equals(appointmentCustomerID);
                boolean apptidbool = tempApptIDchecker.equals(theoreticalApptIDchecker);
                int counter = 1;
                if (custidbool && !apptidbool) {
                    ZonedDateTime currentStartDateTimeSystem = TimeConverter.convertUTCtoSystem(Appointment.getAppointmentStartDateTimeUTC());
                    ZonedDateTime currentStartDateTimeEST = TimeConverter.convertUTCtoEST(Appointment.getAppointmentStartDateTimeUTC());
                    Instant currentStartInstant = currentStartDateTimeEST.toInstant();
                    LocalTime currentStartTime = LocalTime.ofInstant(currentStartInstant, ZoneId.systemDefault());
                    LocalDate currentDate = LocalDate.from(Appointment.getAppointmentStartDateTimeUTC());
                    LocalDate theoreticalDate = LocalDate.from(appointmentStartDateTimeUTC);
                    ZonedDateTime currentEndDateTimeSystem = TimeConverter.convertUTCtoSystem(Appointment.getAppointmentEndDateTimeUTC());
                    ZonedDateTime currentEndDateTimeEST = TimeConverter.convertUTCtoEST(Appointment.getAppointmentEndDateTimeUTC());
                    Instant currentEndInstant = currentEndDateTimeEST.toInstant();
                    LocalTime currentEndTime = LocalTime.ofInstant(currentEndInstant, ZoneId.systemDefault());
                    boolean tstIsBeforecst = TimeChecker.tstIsBeforecst(theoreticalStartDateTimeEST, currentStartDateTimeEST);
                    boolean tstIsAftercet = TimeChecker.tstIsAftercet(theoreticalStartDateTimeEST, currentEndDateTimeEST);
                    boolean tetIsBeforecst = TimeChecker.tetIsBeforecst(theoreticalEndDateTimeEST, currentStartDateTimeEST);
                    boolean tetIsAftercet = TimeChecker.tetIsAftercet(theoreticalEndDateTimeEST, currentEndDateTimeEST);
                    boolean tstEqualscst = TimeChecker.tstEqualscst(theoreticalStartDateTimeEST, currentStartDateTimeEST);
                    boolean tetEqualscet = TimeChecker.tetEqualscet(theoreticalEndDateTimeEST, currentEndDateTimeEST);
                    //ONLY GO THROUGH THE FOLLOWING CODE IF THE THEORETICAL DATE == CURRENT DATE
                    String currentDateString = currentDate.toString();
                    String theoreticalDateString = theoreticalDate.toString();
                    if (currentDate.isEqual(theoreticalDate)) {
                        if (tstIsBeforecst) {
                            if (tetIsBeforecst) {
                                //were good
                            } else {
                                //problem
                                errorWasFound = true;
                            }
                        }
                        if (tetIsAftercet) {
                            if (tstIsAftercet) {
                                //were good
                            } else {
                                errorWasFound = true;
                            }
                        }
                    }
                    if(tstEqualscst && tetEqualscet){
                        errorWasFound = true;
                    }
                }
            });
            if(errorWasFound) {
                Alerts.alertCases(9);
                return;
            }
            Appointment newAppointment = new Appointment (appointmentID, appointmentTitle, appointmentType, appointmentDescription,
                    appointmentLocation, appointmentStartDateTime, appointmentEndDateTime, appointmentStartDateTimeUTC, appointmentStartDateTimeSystem,
                    appointmentStartDateTimeEST, appointmentEndDateTimeUTC, appointmentEndDateTimeSystem, appointmentEndDateTimeEST, appointmentStartDateFormat, appointmentEndDateFormat, appointmentMonth,
                    appointmentCustomerID, appointmentUserID, appointmentContact);
            ACInventory.addAppointment(newAppointment);
            ACInventory.deleteAppointment(apptToModify);
            //the following code will insert the new added appointment into the database
            //convert UTC to timestamp for database insertion
            Timestamp StartDateTimeTS = Timestamp.valueOf(appointmentStartDateTimeUTC.toLocalDateTime());
            Timestamp EndDateTimeTS = Timestamp.valueOf(appointmentEndDateTimeUTC.toLocalDateTime());
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(jdbcUrl, userName, password);
                Statement updateSt = connection.createStatement();
                int result = updateSt.executeUpdate("UPDATE appointments SET Title = '" + appointmentTitle + "',Type = '" + appointmentType + "',Description = '" + appointmentDescription + "',Location = '" + appointmentLocation + "',Start = '" + StartDateTimeTS + "',End = '" + EndDateTimeTS + "',Customer_ID = '" + appointmentCustomerID + "',User_ID = '" + appointmentUserID + "',Contact_ID = '" + appointmentContactID + "' WHERE Appointment_ID = '" + appointmentID + "'");
                if (result > 0) {
                    //do nothing
                } else {
                    //do nothing
                }
                closeDatabaseConnection();
            } catch(ClassNotFoundException e) {
                System.out.println("Error:" + e.getMessage());
            } catch(SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
            //the following code will add customerID to the customerIDsThatHaveAppointments List
            String tempCustID = newAppointment.getAppointmentCustomerID();
            if (tempCustID.equals(tempFormerApptCustIDAsString)){
                //The Customer ID has not changed. Do not remove or add to the list
            } else {
                //The Customer ID has changed! Remove the Former ID from the list, and Add the New ID
                ACInventory.customerIDsThatHaveAppointmentsList.remove(tempFormerApptCustIDAsString);
                ACInventory.customerIDsThatHaveAppointmentsList.add(tempCustID);
            }
            //the following code will add appointments that satsify params from the database to the observable lists in ACInventory for filtering of the Tableview
            int todayWeek = today.get(weekFields.weekOfWeekBasedYear());
            int appointmentWeek = apptToModify.getAppointmentStartDateTimeUTC().get(weekFields.weekOfWeekBasedYear());
            if (todayWeek == appointmentWeek){
                ACInventory.addCurrentWeekAppointment(newAppointment);
                ACInventory.deleteAppointment(apptToModify);
                ACInventory.deleteAppointmentCurrentWeek(apptToModify);
            }
            Month todayMonth = today.getMonth();
            Month tempAppointmentMonth = apptToModify.getAppointmentStartDateTimeUTC().getMonth();
            if (todayMonth == tempAppointmentMonth){
                ACInventory.addCurrentMonthAppointment(newAppointment);
                ACInventory.deleteAppointment(apptToModify);
                ACInventory.deleteAppointmentCurrentMonth(apptToModify);
            }
            open_AppointmentsMain(event);
        } catch (Exception e){
            Alerts.alertCases(5);
        }
    }

    /**
     * the closeDatabaseConnection method closes the connection to the database
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
     * the open_AppointmentsMain method opens the AppointmentsMain page
     * @param event - open_AppointmentsMain takes event ActionEvent
     * @throws IOException - open_AppointmentsMain throws IOException
     */
    private void open_AppointmentsMain(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsMain.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * the initialize method connects to the database and gets data pertaining to UserID, CustomerID, and Contacts
     * This data is then used to populate several comboboxes
     * Two observable lists are also generated to populate the Start and End Time comboboxes
     * initialize also takes the selected appointments data and populates it in the user input fields
     * @param url - initialize takes url as an argument
     * @param resourceBundle - initialize takes resourceBundle as an argument
     * @throws ClassNotFoundException - initialize throws ClassNotFoundException
     * @throws SQLException - initialize throws SQLException
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        systemLister.setText("Times are listed in " + systemZone + " time.");


        LocalTime twelve = LocalTime.parse("00:00:00");
        LocalTime twelvefifteen = LocalTime.parse("00:15:00");
        LocalTime twelvethirty = LocalTime.parse("00:30:00");
        LocalTime twelvefortyfive = LocalTime.parse("00:45:00");
        LocalTime one = LocalTime.parse("01:00:00");
        LocalTime onefifteen = LocalTime.parse("01:15:00");
        LocalTime onethirty = LocalTime.parse("01:30:00");
        LocalTime onefortyfive = LocalTime.parse("01:45:00");
        LocalTime two = LocalTime.parse("02:00:00");
        LocalTime twofifteen = LocalTime.parse("02:15:00");
        LocalTime twothirty = LocalTime.parse("02:30:00");
        LocalTime twofortyfive = LocalTime.parse("02:45:00");
        LocalTime three = LocalTime.parse("03:00:00");
        LocalTime threefifteen = LocalTime.parse("03:15:00");
        LocalTime threethirty = LocalTime.parse("03:30:00");
        LocalTime threefortyfive = LocalTime.parse("03:45:00");
        LocalTime four = LocalTime.parse("04:00:00");
        LocalTime fourfifteen = LocalTime.parse("04:15:00");
        LocalTime fourthirty = LocalTime.parse("04:30:00");
        LocalTime fourfortyfive = LocalTime.parse("04:45:00");
        LocalTime five = LocalTime.parse("05:00:00");
        LocalTime fivefifteen = LocalTime.parse("05:15:00");
        LocalTime fivethirty = LocalTime.parse("05:30:00");
        LocalTime fivefortyfive = LocalTime.parse("05:45:00");
        LocalTime six = LocalTime.parse("06:00:00");
        LocalTime sixfifteen = LocalTime.parse("06:15:00");
        LocalTime sixthirty = LocalTime.parse("06:30:00");
        LocalTime sixfortyfive = LocalTime.parse("06:45:00");
        LocalTime seven = LocalTime.parse("07:00:00");
        LocalTime sevenfifteen = LocalTime.parse("07:15:00");
        LocalTime seventhirty = LocalTime.parse("07:30:00");
        LocalTime sevenfortyfive = LocalTime.parse("07:45:00");
        LocalTime eight = LocalTime.parse("08:00:00");
        LocalTime eightfifteen = LocalTime.parse("08:15:00");
        LocalTime eightthirty = LocalTime.parse("08:30:00");
        LocalTime eightfortyfive = LocalTime.parse("08:45:00");
        LocalTime nine = LocalTime.parse("09:00:00");
        LocalTime ninefifteen = LocalTime.parse("09:15:00");
        LocalTime ninethirty = LocalTime.parse("09:30:00");
        LocalTime ninefortyfive = LocalTime.parse("09:45:00");
        LocalTime ten = LocalTime.parse("10:00:00");
        LocalTime tenfifteen = LocalTime.parse("10:15:00");
        LocalTime tenthirty = LocalTime.parse("10:30:00");
        LocalTime tenfortyfive = LocalTime.parse("10:45:00");
        LocalTime eleven = LocalTime.parse("11:00:00");
        LocalTime elevenfifteen = LocalTime.parse("11:15:00");
        LocalTime eleventhirty = LocalTime.parse("11:30:00");
        LocalTime elevenfortyfive = LocalTime.parse("11:45:00");
        LocalTime twelvepm = LocalTime.parse("12:00:00");
        LocalTime twelvefifteenpm = LocalTime.parse("12:15:00");
        LocalTime twelvethirtypm = LocalTime.parse("12:30:00");
        LocalTime twelvefortyfivepm = LocalTime.parse("12:45:00");
        LocalTime onepm = LocalTime.parse("13:00:00");
        LocalTime onefifteenpm = LocalTime.parse("13:15:00");
        LocalTime onethirtypm = LocalTime.parse("13:30:00");
        LocalTime onefortyfivepm = LocalTime.parse("13:45:00");
        LocalTime twopm = LocalTime.parse("14:00:00");
        LocalTime twofifteenpm = LocalTime.parse("14:15:00");
        LocalTime twothirtypm = LocalTime.parse("14:30:00");
        LocalTime twofortyfivepm = LocalTime.parse("14:45:00");
        LocalTime threepm = LocalTime.parse("15:00:00");
        LocalTime threefifteenpm = LocalTime.parse("15:15:00");
        LocalTime threethirtypm = LocalTime.parse("15:30:00");
        LocalTime threefortyfivepm = LocalTime.parse("15:45:00");
        LocalTime fourpm = LocalTime.parse("16:00:00");
        LocalTime fourfifteenpm = LocalTime.parse("16:15:00");
        LocalTime fourthirtypm = LocalTime.parse("16:30:00");
        LocalTime fourfortyfivepm = LocalTime.parse("16:45:00");
        LocalTime fivepm = LocalTime.parse("17:00:00");
        LocalTime fivefifteenpm = LocalTime.parse("17:15:00");
        LocalTime fivethirtypm = LocalTime.parse("17:30:00");
        LocalTime fivefortyfivepm = LocalTime.parse("17:45:00");
        LocalTime sixpm = LocalTime.parse("18:00:00");
        LocalTime sixfifteenpm = LocalTime.parse("18:15:00");
        LocalTime sixthirtypm = LocalTime.parse("18:30:00");
        LocalTime sixfortyfivepm = LocalTime.parse("18:45:00");
        LocalTime sevenpm = LocalTime.parse("19:00:00");
        LocalTime sevenfifteenpm = LocalTime.parse("19:15:00");
        LocalTime seventhirtypm = LocalTime.parse("19:30:00");
        LocalTime sevenfortyfivepm = LocalTime.parse("19:45:00");
        LocalTime eightpm = LocalTime.parse("20:00:00");
        LocalTime eightfifteenpm = LocalTime.parse("20:15:00");
        LocalTime eightthirtypm = LocalTime.parse("20:30:00");
        LocalTime eightfortyfivepm = LocalTime.parse("20:45:00");
        LocalTime ninepm = LocalTime.parse("21:00:00");
        LocalTime ninefifteenpm = LocalTime.parse("21:15:00");
        LocalTime ninethirtypm = LocalTime.parse("21:30:00");
        LocalTime ninefortyfivepm = LocalTime.parse("21:45:00");
        LocalTime tenpm = LocalTime.parse("22:00:00");
        LocalTime tenfifteenpm = LocalTime.parse("22:15:00");
        LocalTime tenthirtypm = LocalTime.parse("22:30:00");
        LocalTime tenfortyfivepm = LocalTime.parse("22:45:00");
        LocalTime elevenpm = LocalTime.parse("23:00:00");
        LocalTime elevenfifteenpm = LocalTime.parse("23:15:00");
        LocalTime eleventhirtypm = LocalTime.parse("23:30:00");
        LocalTime elevenfortyfivepm = LocalTime.parse("23:45:00");

        apptToModify = AppointmentsMainController.getTargetAppointment();
        LocalTime appointmentStartTime = apptToModify.getAppointmentStartDateTimeSystem().toLocalTime();
        LocalTime appointmentEndTime = apptToModify.getAppointmentEndDateTimeSystem().toLocalTime();

        ObservableList<LocalTime> appointmentStartTimeObsList = FXCollections.observableArrayList(twelve, twelvefifteen, twelvethirty, twelvefortyfive, one, onefifteen, onethirty, onefortyfive, two,
                twofifteen, twothirty, twofortyfive, three, threefifteen, threethirty, threefortyfive, four, fourfifteen, fourthirty, fourfortyfive, five, fivefifteen, fivethirty, fivefortyfive, six,
                sixfifteen, sixthirty, sixfortyfive, seven, sevenfifteen, seventhirty, sevenfortyfive, eight, eightfifteen, eightthirty, eightfortyfive, nine, ninefifteen, ninethirty, ninefortyfive, ten,
                tenfifteen, tenthirty, tenfortyfive, eleven, elevenfifteen, eleventhirty, elevenfortyfive, twelvepm, twelvefifteenpm, twelvethirtypm, twelvefortyfivepm, onepm, onefifteenpm, onethirtypm,
                onefortyfivepm, twopm, twofifteenpm, twothirtypm, twofortyfivepm, threepm, threefifteenpm, threethirtypm, threefortyfivepm, fourpm, fourfifteenpm, fourthirtypm, fourfortyfivepm, fivepm,
                fivefifteenpm, fivethirtypm, fivefortyfivepm, sixpm, sixfifteenpm, sixthirtypm, sixfortyfivepm, sevenpm, sevenfifteenpm, seventhirtypm, sevenfortyfivepm, eightpm, eightfifteenpm, eightthirtypm,
                eightfortyfivepm, ninepm, ninefifteenpm, ninethirtypm, ninefortyfivepm, tenpm, tenfifteenpm, tenthirtypm, tenfortyfivepm, elevenpm, elevenfifteenpm, eleventhirtypm, elevenfortyfivepm);
        appointmentStartTime_cb_ModifyAppointment.setItems(appointmentStartTimeObsList);

        ObservableList<LocalTime> appointmentEndTimeObsList = FXCollections.observableArrayList(twelve, twelvefifteen, twelvethirty, twelvefortyfive, one, onefifteen, onethirty, onefortyfive, two,
                twofifteen, twothirty, twofortyfive, three, threefifteen, threethirty, threefortyfive, four, fourfifteen, fourthirty, fourfortyfive, five, fivefifteen, fivethirty, fivefortyfive, six,
                sixfifteen, sixthirty, sixfortyfive, seven, sevenfifteen, seventhirty, sevenfortyfive, eight, eightfifteen, eightthirty, eightfortyfive, nine, ninefifteen, ninethirty, ninefortyfive, ten,
                tenfifteen, tenthirty, tenfortyfive, eleven, elevenfifteen, eleventhirty, elevenfortyfive, twelvepm, twelvefifteenpm, twelvethirtypm, twelvefortyfivepm, onepm, onefifteenpm, onethirtypm,
                onefortyfivepm, twopm, twofifteenpm, twothirtypm, twofortyfivepm, threepm, threefifteenpm, threethirtypm, threefortyfivepm, fourpm, fourfifteenpm, fourthirtypm, fourfortyfivepm, fivepm,
                fivefifteenpm, fivethirtypm, fivefortyfivepm, sixpm, sixfifteenpm, sixthirtypm, sixfortyfivepm, sevenpm, sevenfifteenpm, seventhirtypm, sevenfortyfivepm, eightpm, eightfifteenpm, eightthirtypm,
                eightfortyfivepm, ninepm, ninefifteenpm, ninethirtypm, ninefortyfivepm, tenpm, tenfifteenpm, tenthirtypm, tenfortyfivepm, elevenpm, elevenfifteenpm, eleventhirtypm, elevenfortyfivepm);

        appointmentEndTime_cb_ModifyAppointment.setItems(appointmentEndTimeObsList);

        //DataPopulation from Database Attempt -- to grab the userID data
        ObservableList<String> userID_ObsList = FXCollections.observableArrayList();
        appointmentUserID_cb_ModifyAppointment.setItems(userID_ObsList);

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            String query_users = "SELECT * FROM users;";
            Statement st_users = connection.createStatement();
            ResultSet users_rs = st_users.executeQuery(query_users);
            int customers_rs_rowCount = 0;
            while (users_rs.next()) {
                String userID = users_rs.getString("User_ID");
                userID_ObsList.add(userID);
                customers_rs_rowCount++;
            }
            st_users.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        } catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        closeDatabaseConnection();
        appointmentUserID_cb_ModifyAppointment.setItems(userID_ObsList);
        //DataPopulation from Database Attempt -- successfully grabs the userID data

        //DataPopulation from Database Attempt -- to grab the customerID data
        ObservableList<String> customerID_ObsList = FXCollections.observableArrayList();
        appointmentCustomerID_cb_ModifyAppointment.setItems(customerID_ObsList);

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            String query_customers = "SELECT * FROM customers;";
            Statement st_customers = connection.createStatement();
            ResultSet customers_rs = st_customers.executeQuery(query_customers);
            int customers_rs_rowCount = 0;
            while (customers_rs.next()) {
                String customerID = customers_rs.getString("Customer_ID");
                customerID_ObsList.add(customerID);
                customers_rs_rowCount++;
            }
            st_customers.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        } catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        closeDatabaseConnection();
        appointmentCustomerID_cb_ModifyAppointment.setItems(customerID_ObsList);
        //DataPopulation from Database Attempt -- successfully grabs the customerID data

        //DataPopulation from Database Attempt -- to grab the contact name data
        ObservableList<String> contactsObsList = FXCollections.observableArrayList();
        appointmentContact_cb_ModifyAppointment.setItems(contactsObsList);

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            String query_contacts = "SELECT * FROM contacts;";
            Statement st_contacts = connection.createStatement();
            ResultSet contacts_rs = st_contacts.executeQuery(query_contacts);
            int contacts_rs_rowCount = 0;
            while (contacts_rs.next()) {
                int Contact_ID = contacts_rs.getInt("Contact_ID");
                String Contact_Name = contacts_rs.getString("Contact_Name");
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
        appointmentContact_cb_ModifyAppointment.setItems(contactsObsList);
        //DataPopulation from Database Attempt -- successfully grabs the contact name data

        String appointmentStartTimeAsString = appointmentStartTime.toString();
        String appointmentEndTimeAsString = appointmentEndTime.toString();


        appointmentID_txt_ModifyAppointment.setText(String.valueOf(apptToModify.getAppointmentID()));
        appointmentTitle_txt_ModifyAppointment.setText(apptToModify.getAppointmentTitle());
        appointmentType_txt_ModifyAppointment.setText(apptToModify.getAppointmentType());
        appointmentDescription_txt_ModifyAppointment.setText(apptToModify.getAppointmentDescription());
        appointmentLocation_txt_ModifyAppointment.setText(apptToModify.getAppointmentLocation());
        appointmentStartDate_dp_ModifyAppointment.setValue(apptToModify.getAppointmentStartDateTimeEST().toLocalDate());
        appointmentEndDate_dp_ModifyAppointment.setValue(apptToModify.getAppointmentEndDateTimeEST().toLocalDate());
        appointmentStartTime_cb_ModifyAppointment.setValue(appointmentStartTimeAsString);
        appointmentEndTime_cb_ModifyAppointment.setValue(appointmentEndTimeAsString);
        appointmentCustomerID_cb_ModifyAppointment.setValue(apptToModify.getAppointmentCustomerID());
        appointmentUserID_cb_ModifyAppointment.setValue(apptToModify.getAppointmentUserID());
        appointmentContact_cb_ModifyAppointment.setValue(apptToModify.getAppointmentContact());
        tempFormerApptCustIDAsString = apptToModify.getAppointmentCustomerID();
    }
}
