package hr.vub.data.mappers;

import hr.vub.data.entities.ConcertEntity;
import hr.vub.data.models.Artist;
import hr.vub.data.models.Concert;
import hr.vub.data.repository.ArtistRepository;

import java.util.UUID;

public class ConcertMapper implements Mapper<ConcertEntity, Concert> {

    private final ArtistRepository artistRepository;

    public ConcertMapper(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public ConcertEntity toEntity(Concert domain) {
        return new ConcertEntity(
            domain.getUuid(),
            domain.getDescription(),
            domain.getCapacity(),
            domain.getArtist() != null ? domain.getArtist().getUuid() : null
        );
    }

    @Override
    public Concert toDomain(ConcertEntity entity) {
        UUID artistUuid = entity.getArtist();
        Artist artist = artistRepository.get(artistUuid);
        return new Concert(
            entity.getUuid(),
            entity.getDescription(),
            entity.getCapacity(),
            artist
        );
    }
}
