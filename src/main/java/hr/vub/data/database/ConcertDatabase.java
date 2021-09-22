package hr.vub.data.database;

import hr.vub.data.entities.ConcertEntity;
import hr.vub.data.mappers.ConcertMapper;
import hr.vub.data.models.Concert;
import hr.vub.data.persistence.ConcertPersistence;
import hr.vub.data.repository.ConcertRepository;

public class ConcertDatabase extends AbstractDatabase<ConcertEntity, Concert> implements ConcertRepository {

    public ConcertDatabase(ConcertPersistence persistence, ConcertMapper mapper) {
        super(persistence, mapper);
    }
}
