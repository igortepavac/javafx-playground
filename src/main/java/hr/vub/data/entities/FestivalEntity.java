package hr.vub.data.entities;

import java.util.UUID;

public class FestivalEntity {

    private final UUID uuid;
    private final String description;
    private final int capacity;
    private final String genre;

    public FestivalEntity(UUID uuid, String description, int capacity, String genre) {
        this.uuid = uuid;
        this.description = description;
        this.capacity = capacity;
        this.genre = genre;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getGenre() {
        return genre;
    }
}
