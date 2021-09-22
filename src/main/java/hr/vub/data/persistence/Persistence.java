package hr.vub.data.persistence;

public interface Persistence<T> {

    void save(T item);

    T read();
}
