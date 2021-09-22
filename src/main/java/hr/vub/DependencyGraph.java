package hr.vub;

import com.squareup.moshi.Moshi;
import hr.vub.data.FileDatabase;
import hr.vub.data.adapters.UuidJsonAdapter;
import hr.vub.data.database.ArtistDatabase;
import hr.vub.data.database.ConcertDatabase;
import hr.vub.data.database.FestivalDatabase;
import hr.vub.data.mappers.ArtistMapper;
import hr.vub.data.mappers.ConcertMapper;
import hr.vub.data.mappers.FestivalMapper;
import hr.vub.data.persistence.ArtistPersistence;
import hr.vub.data.persistence.ConcertPersistence;
import hr.vub.data.persistence.FestivalPersistence;
import hr.vub.data.persistence.UserPreferencesPersistence;
import hr.vub.data.repository.ArtistRepository;
import hr.vub.data.repository.ConcertRepository;
import hr.vub.data.repository.FestivalRepository;
import hr.vub.ui.ScreenController;
import hr.vub.util.AppConstants;
import hr.vub.util.DummyDataGenerator;

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

    private final Moshi moshi = new Moshi.Builder()
        .add(new UuidJsonAdapter())
        .build();

    private final ArtistRepository artistRepository = new ArtistDatabase(
        new ArtistPersistence(moshi, new FileDatabase(AppConstants.DB_ARTISTS_FILE_NAME)),
        new ArtistMapper()
    );

    private final ConcertRepository concertRepository = new ConcertDatabase(
        new ConcertPersistence(moshi, new FileDatabase(AppConstants.DB_CONCERTS_FILE_NAME)),
        new ConcertMapper(artistRepository)
    );

    private final FestivalRepository festivalRepository = new FestivalDatabase(
        new FestivalPersistence(moshi, new FileDatabase(AppConstants.DB_FESTIVALS_FILE_NAME)),
        new FestivalMapper()
    );

    private final UserPreferencesPersistence userPreferencesPersistence = new UserPreferencesPersistence(
        moshi,
        new FileDatabase(AppConstants.USER_PREFERENCES_FILE_NAME)
    );

    private final DummyDataGenerator dummyDataGenerator = new DummyDataGenerator(
        userPreferencesPersistence,
        artistRepository,
        concertRepository, festivalRepository);

    public ScreenController getScreenController() {
        return screenController;
    }

    public Moshi getMoshi() {
        return moshi;
    }

    public ArtistRepository getArtistRepository() {
        return artistRepository;
    }

    public ConcertRepository getConcertRepository() {
        return concertRepository;
    }

    public FestivalRepository getFestivalRepository() {
        return festivalRepository;
    }

    public DummyDataGenerator getDummyDataGenerator() {
        return dummyDataGenerator;
    }
}