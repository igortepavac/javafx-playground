package hr.vub.data.entities;

import java.util.UUID;

public class ConcertEntity {

    private final UUID uuid;
    private final String description;
    private final int capacity;
    private final UUID artistId;

    public ConcertEntity(UUID uuid, String description, int capacity, UUID artistId) {
        this.uuid = uuid;
        this.description = description;
        this.capacity = capacity;
        this.artistId = artistId;
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

    public UUID getArtist() {
        return artistId;
    }
}
