package hr.vub.data.database;

import hr.vub.data.entities.FestivalEntity;
import hr.vub.data.mappers.Mapper;
import hr.vub.data.models.Festival;
import hr.vub.data.persistence.Persistence;
import hr.vub.data.repository.FestivalRepository;

import java.util.List;

public class FestivalDatabase extends AbstractDatabase<FestivalEntity, Festival> implements FestivalRepository {

    public FestivalDatabase(Persistence<List<FestivalEntity>> persistence, Mapper<FestivalEntity, Festival> mapper) {
        super(persistence, mapper);
    }
}
