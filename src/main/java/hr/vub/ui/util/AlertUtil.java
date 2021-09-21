package hr.vub.ui.util;

import javafx.scene.control.Alert;

public class AlertUtil {

    private static final String TITLE_ERROR = "Error";

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(TITLE_ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
