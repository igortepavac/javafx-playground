package hr.vub.data.database;

import hr.vub.data.models.Event;
import hr.vub.data.repository.EventRepository;

import java.util.List;
import java.util.UUID;

public class EventDatabase implements EventRepository {

    @Override
    public Event get(UUID id) {
        return null;
    }

    @Override
    public void add(Event entity) {

    }

    @Override
    public boolean update(Event entity) {
        return false;
    }

    @Override
    public void delete(Event entity) {

    }

    @Override
    public List<Event> getAll() {
        return null;
    }
}
