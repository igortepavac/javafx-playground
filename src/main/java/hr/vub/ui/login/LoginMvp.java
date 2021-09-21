package hr.vub.ui.login;

public interface LoginMvp {

    interface View {

        void showInvalidCredentialsMessage();
        void navigateToHome();
    }

    interface Presenter {

        void onLoginClicked(String username, String password);
    }
}
