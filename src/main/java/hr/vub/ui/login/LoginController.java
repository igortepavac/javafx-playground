package hr.vub.ui.login;

import hr.vub.DependencyGraph;
import hr.vub.ui.Screen;
import hr.vub.ui.ScreenController;
import hr.vub.ui.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController implements LoginMvp.View {

    private ScreenController screenController;
    private LoginMvp.Presenter presenter;

    @FXML
    AnchorPane root;

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    Button btnLogin;

    public void initialize() {
        screenController = DependencyGraph.getInstance().getScreenController();
        presenter = LoginMvpFactory.getPresenter(DependencyGraph.getInstance(), this);
    }

    @FXML
    void onLoginClicked(ActionEvent event) {
        presenter.onLoginClicked(username.getText(), password.getText());
    }

    @Override
    public void showInvalidCredentialsMessage() {
        AlertUtil.showError("Invalid credentials!");
    }

    @Override
    public void navigateToHome() {
        screenController.navigateToScreen(root, Screen.HOME);
    }
}
