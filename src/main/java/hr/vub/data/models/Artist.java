package hr.vub.data.models;

import java.util.UUID;

public class Artist implements Identifiable {

    private final UUID uuid;
    private final String name;
    private final String genre;

    public Artist(UUID uuid, String name, String genre) {
        this.uuid = uuid;
        this.name = name;
        this.genre = genre;
    }

    @Override
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
