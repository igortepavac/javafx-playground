package hr.vub.ui.login;

import hr.vub.util.StringUtil;

public class LoginPresenter implements LoginMvp.Presenter {

    private final LoginMvp.View view;

    public LoginPresenter(LoginMvp.View view) {
        this.view = view;
    }

    @Override
    public void onLoginClicked(String username, String password) {
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            // Ignore
            return;
        }
        if (username.equals("admin") && password.equals("admin")) {
            view.navigateToHome();
        } else {
            view.showInvalidCredentialsMessage();
        }
    }
}
