package hr.vub.data.database;

import hr.vub.data.entities.ArtistEntity;
import hr.vub.data.mappers.ArtistMapper;
import hr.vub.data.models.Artist;
import hr.vub.data.persistence.ArtistPersistence;
import hr.vub.data.repository.ArtistRepository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ArtistDatabase extends AbstractDatabase<ArtistEntity, Artist> implements ArtistRepository {

    public ArtistDatabase(ArtistPersistence persistence, ArtistMapper mapper) {
        super(persistence, mapper);
    }
}
