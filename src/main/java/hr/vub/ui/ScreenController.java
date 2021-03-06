package hr.vub.ui;

import hr.vub.util.LogUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenController {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final int DEFAULT_WIDTH_NEW_STAGE = 600;
    private static final int DEFAULT_HEIGHT_NEW_STAGE = 500;

    private final Logger logger = LogUtil.getLogger();

    public void navigateToScreen(Node node, Screen screen) {
        navigateToScreen((Stage) node.getScene().getWindow(), screen, null);
    }

    public void navigateToScreen(Stage stage, Screen screen) {
        navigateToScreen(stage, screen, null);
    }

    public void navigateToScreen(Node node, Screen screen, Function<FXMLLoader, Void> config) {
        navigateToScreen((Stage) node.getScene().getWindow(), screen, config);
    }

    public void navigateToScreen(Stage stage, Screen screen, Function<FXMLLoader, Void> config) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(screen.getRoute()));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT));
            if (config != null) {
                config.apply(fxmlLoader);
            }
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to navigate", e);
        }
    }

    public void navigateToStage(Screen screen) {
        navigateToStage(screen, null);
    }

    public void navigateToStage(Screen screen, Function<FXMLLoader, Void> config) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(screen.getRoute()));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(screen.getTitle());
            stage.setScene(new Scene(root, DEFAULT_WIDTH_NEW_STAGE, DEFAULT_HEIGHT_NEW_STAGE));
            if (config != null) {
                config.apply(fxmlLoader);
            }
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to navigate", e);
        }
    }
}
