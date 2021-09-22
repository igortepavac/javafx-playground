package hr.vub.data.mappers;

import hr.vub.data.entities.FestivalEntity;
import hr.vub.data.models.Festival;

public class FestivalMapper implements Mapper<FestivalEntity, Festival> {

    @Override
    public FestivalEntity toEntity(Festival domain) {
        return new FestivalEntity(
            domain.getUuid(),
            domain.getDescription(),
            domain.getCapacity(),
            domain.getGenre()
        );
    }

    @Override
    public Festival toDomain(FestivalEntity entity) {
        return new Festival(
            entity.getUuid(),
            entity.getDescription(),
            entity.getCapacity(),
            entity.getGenre()
        );
    }
}
