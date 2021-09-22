package hr.vub;

import hr.vub.ui.Screen;
import hr.vub.util.JavaFxScheduler;
import hr.vub.util.LogUtil;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainApp extends Application {

    private static final String PRIMARY_STAGE_TITLE = "EventXyz";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(PRIMARY_STAGE_TITLE);
        DependencyGraph.getInstance().getDummyDataGenerator().generateDataIfNeeded();
        DependencyGraph.getInstance().getScreenController().navigateToScreen(primaryStage, Screen.LOGIN);

        primaryStage.setOnCloseRequest(event -> {
            LogUtil.clear();
        });
    }
}
