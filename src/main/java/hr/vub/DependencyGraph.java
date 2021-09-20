package hr.vub;

import hr.vub.data.database.ArtistDatabase;
import hr.vub.data.database.EventDatabase;
import hr.vub.data.repository.ArtistRepository;
import hr.vub.data.repository.EventRepository;

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

    private final ArtistRepository artistRepository = new ArtistDatabase();
    private final EventRepository eventRepository = new EventDatabase();

    public ArtistRepository getArtistRepository() {
        return artistRepository;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }
}