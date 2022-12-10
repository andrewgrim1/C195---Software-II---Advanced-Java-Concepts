package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.Month;

/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the MonthsTable class.
 * MonthsTable is used by the reports page to display the months and total number of appointments for each month
 */
public class MonthsTable {

    public static ObservableList<MonthsTable> monthsList = FXCollections.observableArrayList();
    private Month month;
    private int total;

    /**
     * the MonthsTable object is created
     * @param month - MonthsTable takes month as an argument
     * @param total - MonthsTable takes total as an argument
     */
    public MonthsTable(Month month, int total){
        this.month = month;
        this.total = total;
    }

    /**
     * the getMonth method
     * @return month - getMonth returns month
     */
    public Month getMonth() {return month;}

    /**
     * the setMonth method
     * @param month - setMonth takes month as an argument
     */
    public void setMonth(Month month){this.month=month;}

    /**
     * the getTotal method
     * @return total - getTotal returns total
     */
    public int getTotal() {return total;}

    /**
     * the setTotal method
     * @param total - setTotal takes total as an argument
     */
    public void setTotal(int total){this.total=total;}
}