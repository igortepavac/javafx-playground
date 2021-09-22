package hr.vub.data.models;

import java.util.UUID;

public abstract class Event implements Identifiable {

    private final UUID uuid;
    private final String description;
    private final int capacity;

    public Event(UUID uuid, String description, int capacity) {
        this.uuid = uuid;
        this.description = description;
        this.capacity = capacity;
    }

    public abstract double calculateEstimatedPrice();

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }
}
