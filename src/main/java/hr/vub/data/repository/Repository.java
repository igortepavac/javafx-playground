package hr.vub.data.repository;

import java.util.List;
import java.util.UUID;

public interface Repository<EntityType> {

    EntityType get(UUID id);

    void add(EntityType entity);

    boolean update(EntityType entity);

    void delete(EntityType entity);

    List<EntityType> getAll();
}