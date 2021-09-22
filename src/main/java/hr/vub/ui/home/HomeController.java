package hr.vub.ui.home;

import hr.vub.ui.Screen;
import hr.vub.util.LogUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController {

    private static final Logger logger = LogUtil.getLogger();

    private static final List<Screen> SCREENS = Arrays.asList(Screen.ARTISTS, Screen.EVENTS);

    @FXML
    TabPane tabPane;

    public void initialize() {
        initTabs();
    }

    private void initTabs() {
        for (Screen screen : SCREENS) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(screen.getRoute()));
                Parent root = fxmlLoader.load();
                Tab tab = new Tab();
                tab.setId(screen.getTitle());
                tab.setText(screen.getTitle());
                tab.setContent(root);
                tabPane.getTabs().add(tab);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to initialize tabs", e);
            }
        }
    }
}
