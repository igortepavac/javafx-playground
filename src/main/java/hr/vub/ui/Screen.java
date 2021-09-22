package hr.vub.ui;

public enum Screen {
    LOGIN("Login", "/hr/vub/login.fxml"),
    HOME("Home", "/hr/vub/home.fxml"),
    ARTISTS("Artists", "/hr/vub/artists.fxml"),
    ADD_ARTIST("Add Artist", "/hr/vub/edit_artist.fxml"),
    EDIT_ARTIST("Edit Artist", "/hr/vub/edit_artist.fxml"),
    EVENTS("Events", "/hr/vub/events.fxml"),
    ADD_EVENT("Add Event", "/hr/vub/edit_event.fxml"),
    EDIT_EVENT("Edit Event", "/hr/vub/edit_event.fxml");

    private final String title;
    private final String route;

    Screen(String title, String route) {
        this.title = title;
        this.route = route;
    }

    public String getTitle() {
        return title;
    }

    public String getRoute() {
        return route;
    }
}
