package sample;
/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the Customer class.
 * The Customer class creates the Customer object, and includes the getters and setters for that object
 */
public class Customer {

    private int customerID;

    private String customerName;

    private String customerAddress;

    private String customerPhoneNumber;

    private String customerPostalCode;

    private String customerCountry;

    private String customerProvince;


    /**
     * the Customer method creates the Customer object
     * @param customerID - Customer takes customerID as an argument
     * @param customerName - Customer takes customerName as an argument
     * @param customerAddress - Customer takes customerAddress as an argument
     * @param customerPhoneNumber - Customer takes customerPhoneNumber as an argument
     * @param customerCountry - Customer takes customerCountry as an argument
     * @param customerProvince - Customer takes customerProvince as an argument
     * @param customerPostalCode - Customer takes customerPostalCode as an argument
     */
    public Customer(int customerID, String customerName, String customerAddress, String customerPhoneNumber, String customerCountry, String customerProvince, String customerPostalCode) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerCountry = customerCountry;
        this.customerProvince = customerProvince;
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * the getCustomerID method
     * @return customerID - getCustomerID returns customerID
     */
    public int getCustomerID() {return customerID;}

    /**
     * the setCustomerID method
     * @param customerID - setCustomerID takes customerID as an argument
     */
    public void setCustomerID(int customerID){this.customerID=customerID;}

    /**
     * the getCustomerName method
     * @return customerName - getCustomerName returns customerName
     */
    public String getCustomerName() {return customerName;}

    /**
     * the setCustomerName method
     * @param customerName - setCustomerName takes customerName as an argument
     */
    public void setCustomerName(String customerName){this.customerName=customerName;}

    /**
     * the getCustomerAddress method
     * @return customerAddress - getCustomerAddress returns customerAddress
     */
    public String getCustomerAddress() {return customerAddress;}

    /**
     * the setCustomerAddress method
     * @param customerAddress - setCustomerAddress takes customerAddress as argument
     */
    public void setCustomerAddress(String customerAddress){this.customerAddress=customerAddress;}

    /**
     * the getCustomerPhoneNumber method
     * @return customerPhoneNumber - getCustomerPhoneNumber returns customerPhoneNumber
     */
    public String getCustomerPhoneNumber() {return customerPhoneNumber;}

    /**
     * the setCustomerPhoneNumber method
     * @param customerPhoneNumber - setCustomerPhoneNumer takes customerPhoneNumber as argument
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber){this.customerPhoneNumber=customerPhoneNumber;}

    /**
     * the getCustomerCountry method
     * @return customerCountry - getCustomerCountry returns customerCountry
     */
    public String getCustomerCountry() {return customerCountry;}

    /**
     * the setCustomerCountry method
     * @param customerCountry - setCustomerCountry takes customerCountry as an argument
     */
    public void setCustomerCountry(String customerCountry){this.customerCountry=customerCountry;}

    /**
     * the getCustomerProvince method
     * @return customerProvince - getCustomerProvince returns customerProvince
     */
    public String getCustomerProvince() {return customerProvince;}

    /**
     * the setCustomerProvince method
     * @param customerProvince - setCustomerProvince takes customerProvince as argument
     */
    public void setCustomerProvince(String customerProvince){this.customerProvince=customerProvince;}

    /**
     * the getCustomerPostalCode method
     * @return customerPostalCode - getCustomerPostalCode returns customerPostalCode
     */
    public String getCustomerPostalCode() {return customerPostalCode;}

    /**
     * the setCustomerPostalCode method
     * @param customerPostalCode - setCustomerPostalCode takes customerPostalCode as argument
     */
    public void setCustomerPostalCode(String customerPostalCode){this.customerPostalCode=customerPostalCode;}


}
