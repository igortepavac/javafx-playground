package hr.vub.ui;

import hr.vub.util.LogUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ScreenController {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private final Logger logger = LogUtil.getLogger(ScreenController.class.getName());

    public void navigateToScreen(Node node, Screen screen) {
        navigateToScreen((Stage) node.getScene().getWindow(), screen);
    }

    public void navigateToScreen(Stage stage, Screen screen) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(screen.getRoute()));
            stage.setScene(new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT));
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to navigate", e);
        }
    }
}
