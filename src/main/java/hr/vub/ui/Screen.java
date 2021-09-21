package hr.vub.ui;

public enum Screen {
    LOGIN("/hr/vub/login.fxml"),
    HOME("/hr/vub/home.fxml");

    private final String route;

    Screen(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
