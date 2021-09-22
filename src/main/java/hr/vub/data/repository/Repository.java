package hr.vub.data.repository;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<EntityType> {

    Observable<List<EntityType>> getAllObservable();

    EntityType get(UUID id);

    List<EntityType> getAll();

    void add(EntityType entities);

    void addAll(List<EntityType> entity);

    void update(EntityType entity);

    void delete(EntityType entity);
}