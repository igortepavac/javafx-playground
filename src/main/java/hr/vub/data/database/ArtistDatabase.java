package hr.vub.data.database;

import hr.vub.data.models.Artist;
import hr.vub.data.repository.ArtistRepository;

import java.util.List;
import java.util.UUID;

public class ArtistDatabase implements ArtistRepository {

    @Override
    public Artist get(UUID id) {
        return null;
    }

    @Override
    public void add(Artist entity) {

    }

    @Override
    public boolean update(Artist entity) {
        return false;
    }

    @Override
    public void delete(Artist entity) {

    }

    @Override
    public List<Artist> getAll() {
        return null;
    }
}
