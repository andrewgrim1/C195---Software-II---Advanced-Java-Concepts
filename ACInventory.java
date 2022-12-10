
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the ACInventory class.
 * The ACInventory class contains observable lists and logic checkers that are utilized throughout the application
 *
 */

public class ACInventory {

    private static int customerID = 1;
    private static int appointmentID = 1;
    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList(); //these were private
    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList(); //these were private
    public static ObservableList<Appointment> allCurrentWeekAppointments = FXCollections.observableArrayList(); //these were private
    public static ObservableList<Appointment> allCurrentMonthAppointments = FXCollections.observableArrayList(); //these were private
    public static int dataspace;
    public static ArrayList<String> customerIDsThatHaveAppointmentsList = new ArrayList<>(dataspace);
    public static ObservableList<Customer> getAllCustomers(){return allCustomers;}
    public static ObservableList<Appointment> getAllAppointments(){return allAppointments;}
    public static ObservableList<Appointment> getAllCurrentWeekAppointments(){return allCurrentWeekAppointments;}
    public static ObservableList<Appointment> getAllCurrentMonthAppointments(){return allCurrentMonthAppointments;}
    public static ObservableList<Appointment> allAppointmentsBySelectContact = FXCollections.observableArrayList(); //this was private
    public static ObservableList<Appointment> getAllAppointmentsBySelectContact(){return allAppointmentsBySelectContact;}
    public static void addAppointmentBySelectContact(Appointment newAppointment){allAppointmentsBySelectContact.add(newAppointment);}
    public static ObservableList<Appointment> allAppointmentsBySelectMonth = FXCollections.observableArrayList(); //this was private
    public static ObservableList<Appointment> getAllAppointmentsBySelectMonth(){return allAppointmentsBySelectMonth;}
    public static void addAppointmentBySelectMonth(Appointment newAppointment){allAppointmentsBySelectMonth.add(newAppointment);}
    public static ObservableList<Appointment> allAppointmentsBySelectCountry = FXCollections.observableArrayList(); //this was private
    public static ObservableList<Appointment> getAllAppointmentsBySelectCountry(){return allAppointmentsBySelectCountry;}
    public static void addAppointmentBySelectCountry(Appointment newAppointment){allAppointmentsBySelectCountry.add(newAppointment);}
    public static ObservableList<Appointment> allAppointments15MinuteChecker = FXCollections.observableArrayList(); //this was private
    public static ObservableList<Appointment> getAllAppointments15MinuteChecker(){return allAppointments15MinuteChecker;}
    public static void addAppointmentBy15MinuteChecker(Appointment newAppointment){allAppointments15MinuteChecker.add(newAppointment);}





    public static void addCustomer(Customer newCustomer){allCustomers.add(newCustomer);}
    public static void addAppointment(Appointment newAppointment){allAppointments.add(newAppointment);}
    public static void addCurrentWeekAppointment(Appointment newAppointment){allCurrentWeekAppointments.add(newAppointment);}
    public static void addCurrentMonthAppointment(Appointment newAppointment){allCurrentMonthAppointments.add(newAppointment);}

    /**
     * checkCustomerForDuplicateID loops through the list of customers to check for a duplicate ID
     * @return customerID - checkCustomerForDuplicateID returns customerID
     */
    public static int checkCustomerForDuplicateID() {
        for(int i = 0; i < allCustomers.size(); i++) {
            if(allCustomers.get(i).getCustomerID() == customerID) {
                customerID = customerID + 1;
            }
        }return customerID;
    }
    /**
     * checkAppointmentForDuplicateID loops through the list of appointments to check for a duplicate ID
     * @return appointmentID - checkAppointmentForDuplicateID returns appointmentID
     */
    public static int checkAppointmentForDuplicateID() {
        for(int i = 0; i < allAppointments.size(); i++) {
            if(allAppointments.get(i).getAppointmentID() == appointmentID) {
                appointmentID = appointmentID + 1;
            }
        }return appointmentID;
    }

    /**
     * generateNewCustomerID calls the checkCustomerForDuplicateID method
     * @return customerID - generateNewCustomerID returns customerID
     */
    public static int generateNewCustomerID() {
        checkCustomerForDuplicateID();
        return customerID;
    }

    /**
     * generateNewAppointmentID calls the checkAppointmentForDuplicateID method
     * @return appointmentID - generateNewAppointmentID returns appointmentID
     */
    public static int generateNewAppointmentID() {
        checkAppointmentForDuplicateID();
        return appointmentID;
    }


    /**
     * deleteCustomer deletes the selected Customer object from the list of customers
     * @param selectedCustomer - deleteCustomer takes selectedCustomer as an argument
     * @param <Customer> - deleteCustomer takes objects of type Customer
     * @return true or false - deleteCustomer returns boolean true or false
     */
    public static <Customer> boolean deleteCustomer(Customer selectedCustomer) {
        if (allCustomers.contains(selectedCustomer)) {
            allCustomers.remove(selectedCustomer);
            return true;
        } else {
            return false;
        }
    }

    /**
     * deleteAppointment deletes the selected Appointment object from the list of appointments
     * @param selectedAppointment - deleteAppointment takes selectedAppointment as an argument
     * @param <Appointment> - deleteAppointment takes objects of type Appointment
     * @return true or false - deleteAppointment returns boolean true or false
     */
    public static <Appointment> boolean deleteAppointment(Appointment selectedAppointment) {
        if (allAppointments.contains(selectedAppointment)) {
            allAppointments.remove(selectedAppointment);
            return true;
        } else {
            return false;
        }
    }

    /**
     * deleteAppointmentCurrentWeek deletes the selected Appointment object from the list of appointments
     * @param selectedAppointment - deleteAppointmentCurrentWeek takes selectedAppointment as an argument
     * @param <Appointment> - deleteAppointmentCurrentWeek takes objects of type Appointment
     * @return true or false - deleteAppointmentCurrentWeek returns boolean true or false
     */
    public static <Appointment> boolean deleteAppointmentCurrentWeek(Appointment selectedAppointment) {
        if(allCurrentWeekAppointments.contains(selectedAppointment)){
            allCurrentWeekAppointments.remove(selectedAppointment);
            return true;
        } else {
            return false;
        }
    }

    /**
     * deleteAppointmentCurrentMonth deletes the selected Appointment object from the list of appointments
     * @param selectedAppointment - deleteAppointmentCurrentMonth takes selectedAppointment as an argument
     * @param <Appointment> - deleteAppointmentCurrentMonth takes objects of type Appointment
     * @return true or false - deleteAppointmentCurrentMonth returns boolean true or false
     */
    public static <Appointment> boolean deleteAppointmentCurrentMonth(Appointment selectedAppointment) {
        if (allCurrentMonthAppointments.contains(selectedAppointment)){
            allCurrentMonthAppointments.remove(selectedAppointment);
            return true;
        } else {
            return false;
        }
    }



}
