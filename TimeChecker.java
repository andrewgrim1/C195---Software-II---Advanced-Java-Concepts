package sample;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the TimeChecker class.
 * TimeChecker provides functionality utilized by AddAppointment and ModifyAppointment
 * This functionality enables appointments to be logically checked:
 *  - Ensuring that appointments are between 8:00 am and 10:00 pm EST
 *  - Ensuring that appointments are input correctly logically
 */
public class TimeChecker {

    //Determine that a given LocalTime is between the hours of 8AM EST and 10PM EST
   public static LocalTime eight = LocalTime.of(7, 59, 00, 00000);
   public static LocalTime ten = LocalTime.of(22, 01, 00, 00000);

    /**
     * zonedDateTimeBuilder creates a ZonedDateTime based on a LocalDate, a LocalTime, and a ZoneID
     * @param x - zonedDateTimeBuilder takes x as an argument
     * @param y - zonedDateTimeBuilder takes y as an argument
     * @param z - zonedDateTimeBuilder takes z as an argument
     * @return key - zonedDateTimeBuilder returns key
     */
   public static ZonedDateTime zonedDateTimeBuilder(LocalDate x, LocalTime y, ZoneId z){
       ZonedDateTime key = ZonedDateTime.of(x, y, z);
       return key;
   }

    /**
     * startIsBeforeEnd checks whether a ZonedDateTime is before another ZonedDateTime
     * @param x - startIsBeforeEnd takes x as an argument
     * @param y - startIsBeforeEnd takes y as an argument
     * @return determiner - startIsBeforeEnd returns determiner
     */
    public static boolean startIsBeforeEnd(ZonedDateTime x, ZonedDateTime y) {
        //determine that the given localtime (x) is before the end (y)
        boolean determiner = x.isBefore(y);
        return determiner;
    }
    /**
     * startIsAfter8 checks whether a ZonedDateTime is after another ZonedDateTime
     * @param x - startIsAfter8 takes x as an argument
     * @param y - startIsAfter8 takes y as an argument
     * @return determiner - startIsAfter8 returns determiner
     */
    public static boolean startIsAfter8(ZonedDateTime x, ZonedDateTime y) {
        //determine that the given localtime (start) is after eight
        boolean determiner = x.isAfter(y);
        return determiner;
    }
    /**
     * startIsBefore10 checks whether a ZonedDateTime is before another ZonedDateTime
     * @param x - startIsBefore10 takes x as an argument
     * @param y - startIsBefore10 takes y as an argument
     * @return determiner - startIsBefore10 returns determiner
     */
    public static boolean startIsBefore10(ZonedDateTime x, ZonedDateTime y) {
        //determine that the given localtime (start) is before ten
        boolean determiner = x.isBefore(y);
        return determiner;
    }
    /**
     * endIsAfter8 checks whether a ZonedDateTime is after another ZonedDateTime
     * @param x - endIsAfter8 takes x as an argument
     * @param y - endIsAfter8 takes y as an argument
     * @return determiner - endIsAfter8 returns determiner
     */
    public static boolean endIsAfter8(ZonedDateTime x, ZonedDateTime y) {
        //determine that the given localtime (end) is after eight
        boolean determiner = x.isAfter(y);
        return determiner;
    }
    /**
     * endIsBefore10 checks whether a ZonedDateTime is before another ZonedDateTime
     * @param x - endIsBefore10 takes x as an argument
     * @param y - endIsBefore10 takes y as an argument
     * @return determiner - endIsBefore10 returns determiner
     */
    public static boolean endIsBefore10(ZonedDateTime x, ZonedDateTime y) {
        //determine that the given localtime (end) is before ten
        boolean determiner = x.isBefore(y);
        return determiner;
    }

    //Determine that appointment times in EST do not overlap with existing appointment times in EST
    //A lambda function checks each existing appointment

    /**
     * tstIsAftercst determines whether or not a ZonedDateTime is after another ZonedDateTime
     * @param x - tstIsAftercst takes x as an argument
     * @param y - tstIsAftercst takes y as an argument
     * @return determiner - tstIsAftercst returns determiner
     */
    public static boolean tstIsAftercst(ZonedDateTime x, ZonedDateTime y) {
        //determine that the given localtime (end) is before ten
        boolean determiner = x.isAfter(y);
        return determiner;
    }
    /**
     * tstIsBeforecst determines whether or not a ZonedDateTime is before another ZonedDateTime
     * @param x - tstIsBeforecst takes x as an argument
     * @param y - tstIsBeforecst takes y as an argument
     * @return determiner - tstIsBeforecst returns determiner
     */
    public static boolean tstIsBeforecst(ZonedDateTime x, ZonedDateTime y) {
        //determine that the given localtime (end) is before ten
        boolean determiner = x.isBefore(y);
        return determiner;
    }
    /**
     * tstIsAftercet determines whether or not a ZonedDateTime is after another ZonedDateTime
     * @param x - tstIsAftercet takes x as an argument
     * @param y - tstIsAftercet takes y as an argument
     * @return determiner - tstIsAftercet returns determiner
     */
    public static boolean tstIsAftercet(ZonedDateTime x, ZonedDateTime y) {
        boolean determiner = x.isAfter(y);
        return determiner;
    }
    /**
     * tstIsBeforecet determines whether or not a ZonedDateTime is before another ZonedDateTime
     * @param x - tstIsBeforecet takes x as an argument
     * @param y - tstIsBeforecet takes y as an argument
     * @return determiner - tstIsBeforecet returns determiner
     */
    public static boolean tstIsBeforecet(ZonedDateTime x, ZonedDateTime y) {
        boolean determiner = x.isBefore(y);
        return determiner;
    }
    /**
     * tetIsAftercst determines whether or not a ZonedDateTime is after another ZonedDateTime
     * @param x - tetIsAftercst takes x as an argument
     * @param y - tetIsAftercst takes y as an argument
     * @return determiner - tetIsAftercst returns determiner
     */
    public static boolean tetIsAftercst(ZonedDateTime x, ZonedDateTime y) {
        boolean determiner = x.isAfter(y);
        return determiner;
    }
    /**
     * tetIsBeforecst determines whether or not a ZonedDateTime is before another ZonedDateTime
     * @param x - tetIsBeforecst takes x as an argument
     * @param y - tetIsBeforecst takes y as an argument
     * @return determiner - tetIsBeforecst returns determiner
     */
    public static boolean tetIsBeforecst(ZonedDateTime x, ZonedDateTime y) {
        boolean determiner = x.isBefore(y);
        return determiner;
    }
    /**
     * tetIsAftercet determines whether or not a ZonedDateTime is after another ZonedDateTime
     * @param x - tetIsAftercet takes x as an argument
     * @param y - tetIsAftercet takes y as an argument
     * @return determiner - tetIsAftercet returns determiner
     */
    public static boolean tetIsAftercet(ZonedDateTime x, ZonedDateTime y) {
        boolean determiner = x.isAfter(y);
        return determiner;
    }
    /**
     * tetIsBeforecet determines whether or not a ZonedDateTime is before another ZonedDateTime
     * @param x - tetIsBeforecet takes x as an argument
     * @param y - tetIsBeforecet takes y as an argument
     * @return determiner - tetIsBeforecet returns determiner
     */
    public static boolean tetIsBeforecet(ZonedDateTime x, ZonedDateTime y) {
        boolean determiner = x.isBefore(y);
        return determiner;
    }
    /**
     * tstEqualscst determines whether or not a ZonedDateTime is equivalent to another ZonedDateTime
     * @param x - tstEqualscst takes x as an argument
     * @param y - tstEqualscst takes y as an argument
     * @return determiner - tstEqualscst returns determiner
     */
    public static boolean tstEqualscst(ZonedDateTime x, ZonedDateTime y) {
        boolean determiner = x.equals(y);
        return determiner;
    }
    /**
     * tetEqualscet determines whether or not a ZonedDateTime is equivalent to another ZonedDateTime
     * @param x - tetEqualscet takes x as an argument
     * @param y - tetEqualscet takes y as an argument
     * @return determiner - tetEqualscet returns determiner
     */
    public static boolean tetEqualscet(ZonedDateTime x, ZonedDateTime y) {
        boolean determiner = x.equals(y);
        return determiner;
    }
}