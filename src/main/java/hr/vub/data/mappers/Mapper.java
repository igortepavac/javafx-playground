package hr.vub.data.mappers;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<EntityType, DomainType> {

    EntityType toEntity(DomainType domain);

    DomainType toDomain(EntityType entity);

    default List<EntityType> toEntity(List<DomainType> domainTypes) {
        return domainTypes.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
