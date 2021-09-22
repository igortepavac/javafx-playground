package hr.vub.data.database;

import hr.vub.data.mappers.Mapper;
import hr.vub.data.models.Identifiable;
import hr.vub.data.persistence.Persistence;
import hr.vub.data.repository.Repository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractDatabase<EntityType, DomainType extends Identifiable> implements Repository<DomainType> {

    private final Persistence<List<EntityType>> persistence;
    private final Mapper<EntityType, DomainType> mapper;

    private List<DomainType> memoryList;

    private final BehaviorSubject<List<DomainType>> behaviorSubject = BehaviorSubject.create();

    protected AbstractDatabase(Persistence<List<EntityType>> persistence, Mapper<EntityType, DomainType> mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
        this.memoryList = persistence.read()
            .stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
        behaviorSubject.onNext(memoryList);
    }

    private void updateObservable() {
        behaviorSubject.onNext(memoryList);
    }

    private void persistChanges() {
        persistence.save(mapper.toEntity(memoryList));
    }

    @Override
    public Observable<List<DomainType>> getAllObservable() {
        return behaviorSubject;
    }

    @Override
    public DomainType get(UUID id) {
        if (id == null) {
            return null;
        }
        return memoryList.stream()
            .filter(item -> item.getUuid().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<DomainType> getAll() {
        return memoryList;
    }

    @Override
    public void add(DomainType item) {
        memoryList.add(item);
        updateObservable();
        persistChanges();
    }

    @Override
    public void addAll(List<DomainType> items) {
        memoryList.addAll(items);
        updateObservable();
        persistChanges();
    }

    @Override
    public void update(DomainType updatedItem) {
        memoryList = memoryList.stream()
            .map(item -> {
                if (item.getUuid().equals(updatedItem.getUuid())){
                    return updatedItem;
                } else {
                    return item;
                }
            })
            .collect(Collectors.toList());
        updateObservable();
        persistChanges();
    }

    @Override
    public void delete(DomainType item) {
        memoryList.remove(item);
        updateObservable();
        persistChanges();
    }
}
