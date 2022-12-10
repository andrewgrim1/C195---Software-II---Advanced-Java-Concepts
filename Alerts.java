package sample;

import javafx.scene.control.Alert;

public class Alerts {


    /**
     * @author - Andrew Grim
     * @version - 1.0.0
     * @since - 11/30/2022
     *
     * C195 Software II : Scheduling Application
     *
     * This program allows a user to schedule, add, update, delete, and view appointments and customers.
     *
     * This class is the Alerts class.
     * The Alerts class contains a switch case function which contains alerts that are called from all over the application
     *
     * @param alertNumber - alertCases takes alertNumber as an argument
     */
    public static void alertCases(int alertNumber) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch(alertNumber) {
            case 1:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("You must select an Appointment to Update.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("You must select an Appointment to Delete.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("You must select a Customer to Update.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("You must select a Customer to Delete.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("All fields must be filled before an Appointment can be saved.");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("All fields must be filled before a Customer can be saved.");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("The login or password you've entered is incorrect. Please try again.");
                alert.showAndWait();
                break;
            case 8:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("This customer's appointments must be deleted first.");
                alert.showAndWait();
                break;
            case 9:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("This customer already has an appointment during this time.");
                alert.showAndWait();
                break;
            case 10:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("Appointments cannot be scheduled outside of business hours (8:00a.m. to 10:0pm EST).");
                alert.showAndWait();
                break;
            case 11:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("Appointments cannot start and end at the same time.");
                alert.showAndWait();
                break;
            case 12:
                alert.setTitle("Error!");
                alert.setHeaderText("Error:");
                alert.setContentText("Looks like you didn't make any changes. If you don't want to make changes to this appointment, please click Cancel.");
                alert.showAndWait();
                break;
            case 13:
                alert.setTitle("Erreur!");
                alert.setHeaderText("Erreur:");
                alert.setContentText("L'identifiant ou le mot de passe que vous avez entré est incorrect. Veuillez réessayer.");
                alert.showAndWait();
                break;
        }
    }
}
