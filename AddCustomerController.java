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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;
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
 * This class is the AddCustomerController class which implements the Initializable interface.
 * This class takes customer input from the user and saves it to the database
 *
 */
public class AddCustomerController implements Initializable {

    @FXML
    public TextField customerID_txt_AddCustomer;
    @FXML
    public TextField customerName_txt_AddCustomer;
    @FXML
    public TextField customerAddress_txt_AddCustomer;
    @FXML
    public TextField customerPhoneNumber_txt_AddCustomer;
    @FXML
    public TextField customerPostalCode_txt_AddCustomer;
    @FXML
    public ComboBox customerCountry_cb_AddCustomer;
    @FXML
    public ComboBox customerProvince_cb_AddCustomer;

    @FXML
    private Button save_addCustomer;

    @FXML
    private Button cancel_addCustomer;

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

    public String[] divisionIDArray = {"ph", alabama, arizona, arkansas, california, colorado, connecticut, delaware, districtcolumbia,
            florida, georgia, idaho, illinois, indiana, iowa, kansas, kentucky, louisiana, maine, maryland, massachusetts, michigan, minnesota, mississippi, missouri, montana, nebraska,
            nevada, newhampshire, newjersey, newmexico, newyork, northcarolina, northdakota, ohio, oklahoma, oregon, pennsylvania, rhodeisland, southcarolina, southdakota, tennessee, texas, utah, vermont,
            virginia, washington, westvirginia, wisconsin, wyoming, "ph", "ph", hawaii, "ph", alaska, "ph", "ph", "ph", "ph", "ph", northwestterritories, alberta, britishcolumbia, manitoba, newbrunswick, novascotia, princeedwardisland,
            ontario, quebec, saskatchewan, nunavut, yukon, newfoundlandandlabrador, "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph", "ph",
            "ph", "ph", "ph", "ph", "ph", england, wales, scotland, northernireland};

    /**
     * the open_AppointmentsMain method opens the AppointmentsMain page
     * @param event - open_AppointmentsMain takes the event ActionEvent
     * @throws IOException - open_AppointmentsMain throws IOEXception
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
     * the cancel_addCustomer method confirms that the user wants to cancel adding the data
     * then the method opens the AppointmentsMain page
     * @param event - cancel_addCustomer takes the event ActionEvent
     * @throws IOException - cancel_addCustomer throws IOException
     */
    @FXML
    public void cancel_addCustomer(ActionEvent event) throws IOException {
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
     * the save_addCustomer method saves the user input to the database if all fields contain data
     * lists pertaining to customer data are filled
     * @param event - save_addCustomer takes event ActionEvent
     * @throws IOException - save_addCustomer throws IOException
     * @throws ClassNotFoundException - save_addCustomer throws ClassNotFoundException
     * @throws SQLException - save_addCustomer throws SQLException
     * @throws Exception - save_addCustomer throws Exception
     */
    @FXML
    public void save_addCustomer(ActionEvent event) throws IOException {
        try {
            int customerID = 1;
            String customerName = customerName_txt_AddCustomer.getText();
            String customerAddress = customerAddress_txt_AddCustomer.getText();
            String customerPhoneNumber = customerPhoneNumber_txt_AddCustomer.getText();
            String customerPostalCode = customerPostalCode_txt_AddCustomer.getText();
            String customerCountry = customerCountry_cb_AddCustomer.getSelectionModel().getSelectedItem().toString();
            String customerProvince = customerProvince_cb_AddCustomer.getSelectionModel().getSelectedItem().toString();


            //loop through the list of ids to check if the id is a duplicate
            for (int i = 0; i < AppointmentsMainController.custIDsArrayList.size(); i++){
                if (AppointmentsMainController.custIDsArrayList.contains(customerID)){
                    customerID++;
                }
            }
            AppointmentsMainController.custIDsArrayList.add(customerID);

            Customer newCustomer = new Customer (customerID, customerName, customerAddress, customerPhoneNumber, customerCountry, customerProvince, customerPostalCode);

            //newCustomer.setCustomerID(ACInventory.generateNewCustomerID());
            ACInventory.addCustomer(newCustomer);

            int divisionID = 0;
            for (int i = 0; i < divisionIDArray.length; i++){
                if (divisionIDArray[i].equals(customerProvince)){
                    divisionID = i;
                }
            }

            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(jdbcUrl, userName, password);
                Statement insertSt = connection.createStatement();
                int result = insertSt.executeUpdate("INSERT INTO customers(Customer_ID,Customer_Name,Address,Postal_Code,Phone,Division_ID) values('" + customerID + "','" + customerName + "','" + customerAddress + "','" + customerPostalCode + "','" + customerPhoneNumber + "','" + divisionID + "')");
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





            open_AppointmentsMain(event);
        } catch (Exception e) {
            Alerts.alertCases(6);
        }
    }

    /**
     * the closeDatabaseConnection method closes the connection to the database
     * @throws SQLException - closeDatbaseConnection throws SQLException
     */
    public static void closeDatabaseConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * the selectionChange method activates when a user selects an item in the country combobox
     * if a user selects a certain country, only certain results are available in the province combobox
     * @param event - selectionChange takes event ActionEvent
     * @throws IOException - selectionChange throws IOException
     * @throws Exception - selectionChange throws Exception
     */
    @FXML
    public void selectionChange(ActionEvent event) throws IOException {
        try {
            if (customerCountry_cb_AddCustomer.getSelectionModel().getSelectedItem().toString() == "Canada"){
                ObservableList<String> statesObsList = FXCollections.observableArrayList(northwestterritories, alberta, britishcolumbia, manitoba, newbrunswick, novascotia, princeedwardisland,
                        ontario, quebec, saskatchewan, nunavut, yukon, newfoundlandandlabrador);
                customerProvince_cb_AddCustomer.setItems(statesObsList);
            }
            if (customerCountry_cb_AddCustomer.getSelectionModel().getSelectedItem().toString() == "UK"){
                ObservableList<String> statesObsList = FXCollections.observableArrayList(england, wales, scotland, northernireland);
                customerProvince_cb_AddCustomer.setItems(statesObsList);
            }
            if (customerCountry_cb_AddCustomer.getSelectionModel().getSelectedItem().toString() == "USA"){
                ObservableList<String> statesObsList = FXCollections.observableArrayList(alabama, arizona, arkansas, california, colorado, connecticut, delaware, districtcolumbia,
                        florida, georgia, idaho, illinois, indiana, iowa, kansas, kentucky, louisiana, maine, maryland, massachusetts, michigan, minnesota, mississippi, missouri, montana, nebraska,
                        nevada, newhampshire, newjersey, newmexico, newyork, northcarolina, northdakota, ohio, oklahoma, oregon, pennsylvania, rhodeisland, southcarolina, southdakota, tennessee, texas, utah, vermont,
                        virginia, washington, westvirginia, wisconsin, wyoming, hawaii, alaska);
                customerProvince_cb_AddCustomer.setItems(statesObsList);
            }
        } catch (Exception e) {
            //do nothing
        }
    }


    /**
     * the initialize method initializes variables and sets the available items in the country combobox
     * @param url - initialize takes url as argument
     * @param resourceBundle - initialize takes resourceBundle as argument
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int usa_int = 1;
        int uk_int = 2;
        int canada_int = 3;
        String usa_string = "USA";
        String uk_string = "UK";
        String canada_string = "Canada";
        ObservableList<String> countriesObsList = FXCollections.observableArrayList(usa_string, uk_string, canada_string);
        customerCountry_cb_AddCustomer.setItems(countriesObsList);
    }
}
