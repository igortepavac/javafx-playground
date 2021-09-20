package hr.vub.ui;

public enum Screen {
    LOGIN("main.fxml");

    private final String route;


    Screen(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
