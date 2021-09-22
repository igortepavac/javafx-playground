package hr.vub.data.entities;

import java.util.UUID;

public class ArtistEntity {

    private final UUID uuid;
    private final String name;
    private final String genre;

    public ArtistEntity(UUID uuid, String name, String genre) {
        this.uuid = uuid;
        this.name = name;
        this.genre = genre;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
}
