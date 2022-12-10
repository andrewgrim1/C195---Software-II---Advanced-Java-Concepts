package sample;

import java.time.*;

/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the Appointment class.
 * The Appointment class creates the Appointment object, and includes the getters and setters for that object
 */
public class Appointment {

    private int appointmentID;

    private String appointmentTitle;

    private String appointmentType;

    private String appointmentDescription;

    private String appointmentLocation;

    private LocalDateTime appointmentStartDateTime;

    private LocalDateTime appointmentEndDateTime;

    private ZonedDateTime appointmentStartDateTimeUTC;

    private ZonedDateTime appointmentStartDateTimeSystem;

    private ZonedDateTime appointmentStartDateTimeEST;

    private ZonedDateTime appointmentEndDateTimeUTC;

    private ZonedDateTime appointmentEndDateTimeSystem;

    private ZonedDateTime appointmentEndDateTimeEST;

    private String appointmentStartDateFormat;

    private String appointmentEndDateFormat;

    private Month appointmentMonth;

    private String appointmentCustomerID;

    private String appointmentUserID;

    private String appointmentContact;

    /**
     * the Appointment method creates the Appointment object
     * @param appointmentID - Appointment takes appointmentID as an argument
     * @param appointmentTitle - Appointment takes appointmentTitle as an argument
     * @param appointmentType - Appointment takes appointmentType as an argument
     * @param appointmentDescription - Appointment takes appointmentDescription as an argument
     * @param appointmentLocation - Appointment takes appointmentLocation as an argument
     * @param appointmentStartDateTime - Appointment takes appointmentStartDateTime as an argument
     * @param appointmentEndDateTime - Appointment takes appointmentEndDateTime as an argument
     * @param appointmentStartDateTimeUTC - Appointment takes appointmentStartDateTimeUTC as an argument
     * @param appointmentStartDateTimeSystem - Appointment takes appointmentStartDateTimeSystem as an argument
     * @param appointmentStartDateTimeEST - Appointment takes appointmentStartDateTimeEST as an argument
     * @param appointmentEndDateTimeUTC - Appointment takes appointmentEndDateTimeUTC as an argument
     * @param appointmentEndDateTimeSystem - Appointment takes appointmentEndDateTimeSystem as an argument
     * @param appointmentEndDateTimeEST - Appointment takes appointmentEndDateTimeEST as an argument
     * @param appointmentStartDateFormat - Appointment takes appointmentStartDateFormat as an argument
     * @param appointmentEndDateFormat - Appointment takes appointmentEndDateFormat as an argument
     * @param appointmentMonth - Appointment takes appointmentMonth as an argument
     * @param appointmentCustomerID - Appointment takes appointmentCustomerID as an argument
     * @param appointmentUserID - Appointment takes appointmentUserID as an argument
     * @param appointmentContact - Appointment takes appointmentContact as an argument
     */
    public Appointment(int appointmentID, String appointmentTitle, String appointmentType, String appointmentDescription, String appointmentLocation,
                       LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
                       ZonedDateTime appointmentStartDateTimeUTC, ZonedDateTime appointmentStartDateTimeSystem,
                       ZonedDateTime appointmentStartDateTimeEST, ZonedDateTime appointmentEndDateTimeUTC, ZonedDateTime appointmentEndDateTimeSystem,
                       ZonedDateTime appointmentEndDateTimeEST, String appointmentStartDateFormat, String appointmentEndDateFormat, Month appointmentMonth, String appointmentCustomerID, String appointmentUserID, String appointmentContact){
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentType = appointmentType;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentStartDateTime = appointmentStartDateTime;
        this.appointmentEndDateTime = appointmentEndDateTime;
        this.appointmentStartDateTimeUTC = appointmentStartDateTimeUTC;
        this.appointmentStartDateTimeSystem = appointmentStartDateTimeSystem;
        this.appointmentStartDateTimeEST = appointmentStartDateTimeEST;
        this.appointmentEndDateTimeUTC = appointmentEndDateTimeUTC;
        this.appointmentEndDateTimeSystem = appointmentEndDateTimeSystem;
        this.appointmentEndDateTimeEST = appointmentEndDateTimeEST;
        this.appointmentStartDateFormat = appointmentStartDateFormat;
        this.appointmentEndDateFormat = appointmentEndDateFormat;
        this.appointmentMonth = appointmentMonth;
        this.appointmentContact = appointmentContact;
        this.appointmentCustomerID = appointmentCustomerID;
        this.appointmentUserID = appointmentUserID;
    }

    /**
     * the getAppointmentID method
     * @return appointmentID - getAppointmentID returns appointmentID
     */
    public int getAppointmentID() {return appointmentID;}

    /**
     * the setAppointmentID method
     * @param appointmentID - setAppointmentID takes appointmentID as an argument
     */
    public void setAppointmentID(int appointmentID){this.appointmentID=appointmentID;}

    /**
     * the getAppointmentTitle method
     * @return appointmentTitle - getAppointmentTitle returns appointmentTitle
     */
    public String getAppointmentTitle() {return appointmentTitle;}

    /**
     * the setAppointmentTitle method
     * @param appointmentTitle - setAppointmentTitle takes appointmentTitle as an argument
     */
    public void setAppointmentTitle(String appointmentTitle){this.appointmentTitle=appointmentTitle;}

    /**
     * the getAppointmentType method
     * @return appointmentType - getAppointmentType returns appointmentType
     */
    public String getAppointmentType() {return appointmentType;}

    /**
     * the setAppointmentType method
     * @param appointmentType - setAppointmentType takes appointmentType as an argument
     */
    public void setAppointmentType(String appointmentType){this.appointmentType=appointmentType;}

    /**
     * the getAppointmentDescription method
     * @return appointmentDescription - getAppointmentDescription returns appointmentDescription
     */
    public String getAppointmentDescription() {return appointmentDescription;}

    /**
     * the setAppointmentDescription method
     * @param appointmentDescription - setAppointmentDescription takes appointmentDescription as an argument
     */
    public void setAppointmentDescription(String appointmentDescription){this.appointmentDescription=appointmentDescription;}

    /**
     * the getAppointmentLocation method
     * @return appointmentLocation - getAppointmentLocation returns appointmentLocation
     */
    public String getAppointmentLocation() {return appointmentLocation;}

    /**
     * the setAppointmentLocation method
     * @param appointmentLocation - setAppointmentLocation takes appointmentLocation as an argument
     */
    public void setAppointmentLocation(String appointmentLocation){this.appointmentLocation=appointmentLocation;}

    /**
     * the getAppointmentStartDateTime method
     * @return appointmentStartDateTime - getAppointmentStartDateTime returns appointmentStartDateTime
     */
    public LocalDateTime getAppointmentStartDateTime() {return appointmentStartDateTime;}

    /**
     * the setAppointmentStartDateTime method
     * @param appointmentStartDateTime - setAppointmentStartDateTime takes appointmentStartDateTime as an argument
     */
    public void setAppointmentStartDateTime(LocalDateTime appointmentStartDateTime){this.appointmentStartDateTime=appointmentStartDateTime;}

    /**
     * the getAppointmentEndDateTime method
     * @return appointmentEndDateTime - getAppointmentEndDateTime returns appointmentEndDateTime
     */
    public LocalDateTime getAppointmentEndDateTime() {return appointmentEndDateTime;}

    /**
     * the setAppointmentEndDateTime method
     * @param appointmentEndDateTime - setAppointmentEndDateTime takes appointmentEndDateTime as an argument
     */
    public void setAppointmentEndDateTime(LocalDateTime appointmentEndDateTime){this.appointmentEndDateTime=appointmentEndDateTime;}

    /**
     * the getAppointmentStartDateTimeUTC method
     * @return appointmentStartDateTimeUTC - getAppointmentStartDateTimeUTC returns appointmentStartDateTimeUTC
     */
    public ZonedDateTime getAppointmentStartDateTimeUTC() {return appointmentStartDateTimeUTC;}

    /**
     * the setAppointmentStartDateTimeUTC method
     * @param appointmentStartDateTimeUTC - setAppointmentStartDateTimeUTC takes appointmentStartDateTimeUTC as an argument
     */
    public void setAppointmentStartDateTimeUTC(ZonedDateTime appointmentStartDateTimeUTC){this.appointmentStartDateTimeUTC=appointmentStartDateTimeUTC;}

    /**
     * the getAppointmentStartDateTimeSystem method
     * @return appointmentStartDateTimeSystem - getAppointmentStartDateTimeSystem returns appointmentStartDateTimeSystem
     */
    public ZonedDateTime getAppointmentStartDateTimeSystem() {return appointmentStartDateTimeSystem;}

    /**
     * the setAppointmentStartDateTimeSystem method
     * @param appointmentStartDateTimeSystem - setAppointmentStartDateTimeSystem takes appointmentStartDateTimeSystem as an argument
     */
    public void setAppointmentStartDateTimeSystem(ZonedDateTime appointmentStartDateTimeSystem){this.appointmentStartDateTimeSystem=appointmentStartDateTimeSystem;}

    /**
     * the getAppointmentStartDateTimeEST method
     * @return appointmentStartDateTimeEST - getAppointmentStartDateTimeEST returns appointmentStartDateTimeEST
     */
    public ZonedDateTime getAppointmentStartDateTimeEST() {return appointmentStartDateTimeEST;}

    /**
     * the setAppointmentStartDateTimeEST method
     * @param appointmentStartDateTimeEST - setAppointmentStartDateTimeEST takes appointmentStartDateTimeEST as an argument
     */
    public void setAppointmentStartDateTimeEST(ZonedDateTime appointmentStartDateTimeEST){this.appointmentStartDateTimeEST=appointmentStartDateTimeEST;}

    /**
     * the getAppointmentEndDateTimeUTC method
     * @return appointmentEndDateTimeUTC - getAppointmentEndDateTimeUTC returns appointmentEndDateTimeUTC
     */
    public ZonedDateTime getAppointmentEndDateTimeUTC() {return appointmentEndDateTimeUTC;}

    /**
     * the setAppointmentEndDateTimeUTC method
     * @param appointmentEndDateTimeUTC - setAppointmentEndDateTimeUTC takes appointmentEndDateTimeUTC as an argument
     */
    public void setAppointmentEndDateTimeUTC(ZonedDateTime appointmentEndDateTimeUTC){this.appointmentEndDateTimeUTC=appointmentEndDateTimeUTC;}

    /**
     * the getAppointmentEndDateTimeSystem method
     * @return appointmentEndDateTimeSystem - getAppointmentEndDateTimeSystem returns appointmentEndDateTimeSystem
     */
    public ZonedDateTime getAppointmentEndDateTimeSystem() {return appointmentEndDateTimeSystem;}

    /**
     * the setAppointmentEndDateTimeSystem method
     * @param appointmentEndDateTimeSystem - setAppointmentEndDateTimeSystem takes appointmentEndDateTimeSystem as an argument
     */
    public void setAppointmentEndDateTimeSystem(ZonedDateTime appointmentEndDateTimeSystem){this.appointmentEndDateTimeSystem=appointmentEndDateTimeSystem;}

    /**
     * the getAppointmentEndDateTimeEST method
     * @return appointmentEndDateTimeEST - getAppointmentEndDateTimeEST returns appointmentEndDateTimeEST
     */
    public ZonedDateTime getAppointmentEndDateTimeEST() {return appointmentEndDateTimeEST;}

    /**
     * the setAppointmentEndDateTimeEST method
     * @param appointmentEndDateTimeEST - setAppointmentEndDateTimeEST takes appointmentEndDateTimeEST as an argument
     */
    public void setAppointmentEndDateTimeEST(ZonedDateTime appointmentEndDateTimeEST){this.appointmentEndDateTimeEST=appointmentEndDateTimeEST;}

    /**
     * the getAppointmentStartDateFormat method
     * @return appointmentStartDateFormat - getAppointmentStartDateFormat returns appointmentStartDateFormat
     */
    public String getAppointmentStartDateFormat() {return appointmentStartDateFormat;}

    /**
     * the setAppointmentStartDateFormat method
     * @param appointmentStartDateFormat - setAppointmentStartDateFormat takes appointmentStartDateFormat as an argument
     */
    public void setAppointmentStartDateFormat(String appointmentStartDateFormat){this.appointmentStartDateFormat=appointmentStartDateFormat;}

    /**
     * the getAppointmentEndDateFormat method
     * @return appointmentEndDateFormat - getAppointmentEndDateFormat returns appointmentEndDateFormat
     */
    public String getAppointmentEndDateFormat() {return appointmentEndDateFormat;}

    /**
     * the setAppointmentEndDateFormat method
     * @param appointmentEndDateFormat - setAppointmentEndDateFormat takes appointmentEndDateFormat as an argument
     */
    public void setAppointmentEndDateFormat(String appointmentEndDateFormat){this.appointmentEndDateFormat=appointmentEndDateFormat;}

    /**
     * the getAppointmentMonth method
     * @return appointmentMonth - getAppointmentMonth returns appointmentMonth
     */
    public Month getAppointmentMonth() {return appointmentMonth;}

    /**
     * the setAppointmentMonth method
     * @param appointmentMonth - setAppointmentMonth takes appointmentMonth as an argument
     */
    public void setAppointmentMonth(Month appointmentMonth){this.appointmentMonth=appointmentMonth;}

    /**
     * the getAppointmentContact method
     * @return appointmentContact - getAppointmentContact returns appointmentContact
     */
    public String getAppointmentContact() {return appointmentContact;}

    /**
     * the setAppointmentContact method
     * @param appointmentContact - setAppointmentContact takes appointmentContact as an argument
     */
    public void setAppointmentContact(String appointmentContact){this.appointmentContact=appointmentContact;}

    /**
     * the getAppointmentCustomerID method
     * @return appointmentCustomerID - getAppointmentCustomerID returns appointmentCustomerID
     */
    public String getAppointmentCustomerID() {return appointmentCustomerID;}

    /**
     * the setAppointmentCustomerID method
     * @param appointmentCustomerID - setAppointmentCustomerID takes appointmentCustomerID as an argument
     */
    public void setAppointmentCustomerID(String appointmentCustomerID){this.appointmentCustomerID=appointmentCustomerID;}

    /**
     * the getAppointmentUserID method
     * @return appointmentUserID - getAppointmentUserID returns appointmentUserID
     */
    public String getAppointmentUserID() {return appointmentUserID;}

    /**
     * the setAppointmentUserID method
     * @param appointmentUserID - setAppointmentUserID takes appointmentUserID as an argument
     */
    public void setAppointmentUserID(String appointmentUserID){this.appointmentUserID=appointmentUserID;}

}
