package hr.vub.data.models;

import java.util.UUID;

public class Festival extends Event {

    private static final double BASE_PRICE = 100.0;
    private static final double CAPACITY_DIVIDER = 100.0;
    private static final double CAPACITY_MULTIPLIER = 5;
    private static final double FESTIVAL_MULTIPLIER = 1.2;

    private final String genre;

    public Festival(UUID uuid, String description, int capacity, String genre) {
        super(uuid, description, capacity);
        this.genre = genre;
    }

    @Override
    public double calculateEstimatedPrice() {
        return BASE_PRICE + ((getCapacity() / CAPACITY_DIVIDER) * CAPACITY_MULTIPLIER) * FESTIVAL_MULTIPLIER;
    }

    public String getGenre() {
        return genre;
    }
}
