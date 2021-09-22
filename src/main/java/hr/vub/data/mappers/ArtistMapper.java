package hr.vub.data.mappers;

import hr.vub.data.entities.ArtistEntity;
import hr.vub.data.models.Artist;

public class ArtistMapper implements Mapper<ArtistEntity, Artist> {

    @Override
    public ArtistEntity toEntity(Artist domain) {
        return new ArtistEntity(domain.getUuid(), domain.getName(), domain.getGenre());
    }

    @Override
    public Artist toDomain(ArtistEntity entity) {
        return new Artist(entity.getUuid(), entity.getName(), entity.getGenre());
    }
}
