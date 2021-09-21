package hr.vub.ui.login;

import hr.vub.DependencyGraph;

public class LoginMvpFactory {

    public static LoginMvp.Presenter getPresenter(DependencyGraph dependencyGraph, LoginMvp.View view) {
        return new LoginPresenter(view);
    }
}
