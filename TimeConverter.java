package sample;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the TimeConverter class.
 * TimeConverter allows the program to convert quickly between time zones
 * This functionality is helpful to the classes that display, add, or update appointments
 */
public class TimeConverter {

    public static ZoneId systemZone = ZoneId.systemDefault();
    public static ZoneId zoneNY = ZoneId.of("America/New_York");
    public static ZoneId zoneUTC = ZoneId.of("UTC");

    //CONVERT A GIVEN ZONEDATETIME TO A RESPECTABLE STRING VALUE

    /**
     * respectableZDT formats a ZonedDateTime input and returns a string
     * @param selectedZDT - respectableZDT takes selectedZDT as an argument
     * @return respectableString - respectableZDT returns respectableString
     */
    public static String respectableZDT(ZonedDateTime selectedZDT){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
        String respectableString = selectedZDT.format(formatter);
        return respectableString;
    }
    //CONVERT A GIVEN ZONEDATETIME TO A RESPECTABLE STRING VALUE WITH SECONDS
    /**
     * respectableZDTwithSeconds formats a ZonedDateTime input and returns a string with seconds included
     * @param selectedZDT - respectableZDTwithSeconds takes selectedZDT as an argument
     * @return respectableString - respectableZDTwithSeconds returns respectableString
     */
    public static String respectableZDTwithSeconds(ZonedDateTime selectedZDT){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss:SS a z");
        String respectableString = selectedZDT.format(formatter);
        return respectableString;
    }


    //CONVERT UTC (DATABASE) TO EST

    /**
     * convertUTCtoEST converts a ZonedDateTime to another ZonedDateTime
     * @param selectedUTC - convertUTCtoEST takes selectedUTC as an argument
     * @return EST - convertUTCtoEST returns EST
     */
    public static ZonedDateTime convertUTCtoEST(ZonedDateTime selectedUTC){
        ZonedDateTime EST = selectedUTC.withZoneSameInstant(zoneNY);
        return EST;
    }
    //CONVERT UTC (DATABASE) TO systemZone
    /**
     * convertUTCtoSystem converts a ZonedDateTime to another ZonedDateTime
     * @param selectedUTC - convertUTCtoSystem takes selectedUTC as an argument
     * @return SYSTEM - convertUTCtoSystem returns SYSTEM
     */
    public static ZonedDateTime convertUTCtoSystem(ZonedDateTime selectedUTC){
        ZonedDateTime SYSTEM = selectedUTC.withZoneSameInstant(systemZone);
        return SYSTEM;
    }
    //CONVERT EST to UTC (DATABASE)
    /**
     * convertESTtoUTC converts a ZonedDateTime to another ZonedDateTime
     * @param selectedEST - convertESTtoUTC takes selectedUTC as an argument
     * @return UTC - convertESTtoUTC returns UTC
     */
    public static ZonedDateTime convertESTtoUTC(ZonedDateTime selectedEST){
        ZonedDateTime UTC = selectedEST.withZoneSameInstant(zoneUTC);
        return UTC;
    }
    //CONVERT EST to systemZone
    /**
     * convertESTtoSystem converts a ZonedDateTime to another ZonedDateTime
     * @param selectedEST - convertESTtoSystem takes selectedUTC as an argument
     * @return SYSTEM - convertESTtoSystem returns SYSTEM
     */
    public static ZonedDateTime convertESTtoSystem(ZonedDateTime selectedEST){
        ZonedDateTime SYSTEM = selectedEST.withZoneSameInstant(systemZone);
        return SYSTEM;
    }
    //CONVERT systemZone to UTC (DATABASE)
    /**
     * convertSystemtoUTC converts a ZonedDateTime to another ZonedDateTime
     * @param selectedSystem - convertSystemtoUTC takes selectedUTC as an argument
     * @return UTC - convertSystemtoUTC returns UTC
     */
    public static ZonedDateTime convertSystemtoUTC(ZonedDateTime selectedSystem){
        ZonedDateTime UTC = selectedSystem.withZoneSameInstant(zoneUTC);
        return UTC;
    }
    //CONVERT systemZone to EST
    /**
     * convertSystemtoEST converts a ZonedDateTime to another ZonedDateTime
     * @param selectedSystem - convertSystemtoEST takes selectedUTC as an argument
     * @return EST - convertSystemtoEST returns EST
     */
    public static ZonedDateTime convertSystemtoEST(ZonedDateTime selectedSystem){
        ZonedDateTime EST = selectedSystem.withZoneSameInstant(zoneNY);
        return EST;
    }







    //Convert LocalDateTime to UTC(Database)

    /**
     * localDateTimeToUTC converts a LocalDateTime to a ZonedDateTime
     * @param selectedLocalDateTime - localDateTimeToUTC takes selectedLocalDateTime as an argument
     * @return utcZoned - localDateTimeToUTC returns utcZoned
     */
    public static ZonedDateTime localDateTimeToUTC(LocalDateTime selectedLocalDateTime){
        ZonedDateTime localDateTimeZoned = selectedLocalDateTime.atZone(systemZone);
        ZonedDateTime utcZoned = localDateTimeZoned.withZoneSameInstant(ZoneId.of("UTC"));
        return utcZoned;
    }
    //Convert UTC(Database) to LocalDateTime
    /**
     * utcToLocalDateTime converts a ZonedDateTime to a LocalDateTime
     * @param selectedUTC - utcToLocalDateTime takes selectedUTC as an argument
     * @return toLocalDateTime - utcToLocalDateTime returns toLocalDateTime
     */
    public static LocalDateTime utcToLocalDateTime(ZonedDateTime selectedUTC){
        LocalDateTime toLocalDateTime = selectedUTC.toLocalDateTime();
        return toLocalDateTime;
    }
    //Convert LocalDateTime to LocalTime(ZoneID)
    /**
     * localDateTimeToLocalTimeZone converts a LocalDateTime to a ZonedDateTime
     * @param currentLocalDateTime - localDateTimeToLocalTimeZone takes currentLocalDateTime as an argument
     * @return toZonedDateTime - localDateTimeToLocalTimeZone returns toZonedDateTime
     */
    public static ZonedDateTime localDateTimeToLocalTimeZone(LocalDateTime currentLocalDateTime){
        ZonedDateTime toZonedDateTime = ZonedDateTime.of(currentLocalDateTime, systemZone);
        return toZonedDateTime;
    }
    //Convert LocalTime(ZoneID) to LocalDateTime
    /**
     * localTimeZoneToLocalDateTime converts a ZonedDateTime to a LocalDateTime
     * @param currentZonedTime - localTimeZoneToLocalDateTime takes currentZonedTime as an argument
     * @return toLocalDateTime - localTimeZoneToLocalDateTime returns toLocalDateTime
     */
    public static LocalDateTime localTimeZoneToLocalDateTime(ZonedDateTime currentZonedTime){
        LocalDateTime toLocalDateTime = LocalDateTime.ofInstant(currentZonedTime.toInstant(),systemZone);
        return toLocalDateTime;
    }
    //Convert LocalDateTime to EST
    /**
     * localDateTimeToEST converts a LocalDateTime to a ZonedDateTime
     * @param currentLocalDateTime - localDateTimeToEST takes currentLocalDateTime as an argument
     * @return toZonedDateTime - localDateTimeToEST returns toZonedDateTime
     */
    public static ZonedDateTime localDateTimeToEST(LocalDateTime currentLocalDateTime){
        ZonedDateTime toZonedDateTime = ZonedDateTime.of(currentLocalDateTime,ZoneId.of("America/New_York"));
        return toZonedDateTime;
    }
    //Convert EST to LocalDateTime
    /**
     * ESTToLocalDateTime converts a ZonedDateTime to a LocalDateTime
     * @param currentEST - ESTToLocalDateTime takes currentEST as an argument
     * @return toLocalDateTime - ESTToLocalDateTime returns toLocalDateTime
     */
    public static LocalDateTime ESTToLocalDateTime(ZonedDateTime currentEST){
        LocalDateTime toLocalDateTime = LocalDateTime.ofInstant(currentEST.toInstant(),ZoneId.of("America/New_York"));
        return toLocalDateTime;
    }
}