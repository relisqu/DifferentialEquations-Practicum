
package GUIExtentions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author kopko
 */
public class AlertRaiser {
    public static void showWarningAlert(String message,String title){
        Alert alert = new Alert(AlertType.WARNING,message);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
