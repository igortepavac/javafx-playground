package hr.vub.util;

import hr.vub.data.models.Artist;
import hr.vub.data.models.Concert;
import hr.vub.data.models.Festival;
import hr.vub.data.models.UserPreferences;
import hr.vub.data.persistence.UserPreferencesPersistence;
import hr.vub.data.repository.ArtistRepository;
import hr.vub.data.repository.ConcertRepository;
import hr.vub.data.repository.FestivalRepository;
import javafx.util.Pair;

import java.util.*;
import java.util.logging.Logger;

public class DummyDataGenerator {

    private static final Logger logger = LogUtil.getLogger();

    private static final int ARTISTS_NUM = 30;
    private static final int CONCERTS_NUM = 30;
    private static final int FESTIVALS_NUM = 30;

    private static final List<Pair<String, String>> POSSIBLE_ARTISTS = Arrays.asList(
        new Pair("David Bowie", "Art rock"),
        new Pair("Roisin Murphy", "Art pop"),
        new Pair("Eddie Murphy", "Pop"),
        new Pair("Michael Jackson", "Pop"),
        new Pair("Ekatarina Velika", "New wave"),
        new Pair("z++", "Electro pop"),
        new Pair("Valentino Boskovic", "Cosmic pop"),
        new Pair("Mr. Finger", "Electronic"),
        new Pair("T. Rex", "Electronic")
    );

    private final UserPreferencesPersistence userPreferencesPersistence;
    private final ArtistRepository artistRepository;
    private final ConcertRepository concertRepository;
    private final FestivalRepository festivalRepository;

    public DummyDataGenerator(UserPreferencesPersistence userPreferencesPersistence,
                              ArtistRepository artistRepository,
                              ConcertRepository concertRepository,
                              FestivalRepository festivalRepository) {
        this.userPreferencesPersistence = userPreferencesPersistence;
        this.artistRepository = artistRepository;
        this.concertRepository = concertRepository;
        this.festivalRepository = festivalRepository;
    }

    public void generateDataIfNeeded() {
        UserPreferences userPreferences = userPreferencesPersistence.read();
        if (!userPreferences.didGenerateInitialData()) {
            logger.info("There is no data in the database. Generating now.");
            generateData();
            userPreferencesPersistence.save(new UserPreferences(true));
            logger.info("Data in the database generated.");
        } else {
            logger.info("Data in the database already generated.");
        }
    }

    private void generateData() {
        List<Artist> artistList = generateArtistData();
        generateConcertData(artistList);
        generateFestivalData();
    }

    private List<Artist> generateArtistData() {
        List<Artist> artistList = new ArrayList<>();
        for (int i = 0; i < ARTISTS_NUM; i++) {
            Pair<String, String> artist = POSSIBLE_ARTISTS.get(i % POSSIBLE_ARTISTS.size());
            artistList.add(new Artist(UUID.randomUUID(), artist.getKey(), artist.getValue()));
        }
        artistRepository.addAll(artistList);
        return artistList;
    }

    private void generateConcertData(List<Artist> artistList) {
        List<Concert> concertList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < CONCERTS_NUM; i++) {
            concertList.add(
                new Concert(
                    UUID.randomUUID(),
                    "Description " + i,
                    random.nextInt(10000) + 100,
                    artistList.get(i % artistList.size())
                )
            );
        }
        concertRepository.addAll(concertList);
    }

    private void generateFestivalData() {
        List<Festival> festivalList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < FESTIVALS_NUM; i++) {
            festivalList.add(
                new Festival(
                    UUID.randomUUID(),
                    "Description " + i,
                    random.nextInt(10000) + 100,
                    "Genre " + i
                )
            );
        }
        festivalRepository.addAll(festivalList);
    }
}
