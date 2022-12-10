package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the CountriesTable class.
 * TypeTable is used by the reports page to display the types of appointments and total number of types.
 */
public class TypeTable {

    public static ObservableList<TypeTable> typesList = FXCollections.observableArrayList();
    private String type;
    private int total;

    /**
     * the TypeTable object is created
     * @param type - TypeTable takes type as an argument
     * @param total - TypeTable takes total as an argument
     */
    public TypeTable(String type, int total){
        this.type = type;
        this.total = total;
    }

    /**
     * the getType method
     * @return type - getType returns type
     */
    public String getType() {return type;}

    /**
     * the setType method
     * @param type - setType takes type as an argument
     */
    public void setType(String type){this.type=type;}

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