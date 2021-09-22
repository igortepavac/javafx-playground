package hr.vub.data.models;

import java.util.UUID;

public class Concert extends Event {

    private static final double BASE_PRICE = 70.0;
    private static final double CAPACITY_DIVIDER = 100.0;
    private static final double CAPACITY_MULTIPLIER = 5;

    private final Artist artist;

    public Concert(UUID uuid, String description, int capacity, Artist artist) {
        super(uuid, description, capacity);
        this.artist = artist;
    }

    @Override
    public double calculateEstimatedPrice() {
        return BASE_PRICE + ((getCapacity() / CAPACITY_DIVIDER) * CAPACITY_MULTIPLIER);
    }

    public Artist getArtist() {
        return artist;
    }
}
