package hr.vub;

import hr.vub.data.database.ArtistDatabase;
import hr.vub.data.database.EventDatabase;
import hr.vub.data.repository.ArtistRepository;
import hr.vub.data.repository.EventRepository;
import hr.vub.ui.ScreenController;

public class DependencyGraph {

    private static DependencyGraph INSTANCE = null;

    public static DependencyGraph getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DependencyGraph();
        }
        return INSTANCE;
    }

    private DependencyGraph() {
        // Singleton class constructor is private.
    }

    private final ScreenController screenController = new ScreenController();

    private final ArtistRepository artistRepository = new ArtistDatabase();
    private final EventRepository eventRepository = new EventDatabase();

    public ScreenController getScreenController() {
        return screenController;
    }

    public ArtistRepository getArtistRepository() {
        return artistRepository;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }
}