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
 * CountriesTable is used by the reports page to display the countries and total number of customer from each country
 */
public class CountriesTable {

    public static ObservableList<CountriesTable> countriesList = FXCollections.observableArrayList();
    private String country;
    private int total;

    /**
     * the CountriesTable object is created
     * @param country - CountriesTable takes country as an argument
     * @param total - CountriesTable takes total as an argument
     */
    public CountriesTable(String country, int total){
        this.country = country;
        this.total = total;
    }

    /**
     * getCountry method
     * @return - getCountry returns country
     */
    public String getCountry() {return country;}

    /**
     * setCountry method
     * @param country - setCountry takes country as argument
     */
    public void setCountry(String country){this.country=country;}

    /**
     * getTotal method
     * @return - getTotal returns total
     */
    public int getTotal() {return total;}

    /**
     * setTotal method
     * @param total - setTotal takes total as argument
     */
    public void setTotal(int total){this.total=total;}
}
