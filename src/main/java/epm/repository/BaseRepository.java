package epm.repository;

public interface BaseRepository<T> {

    Iterable<T> findAll();

    T findOne(T t);

    T save(T t);

    void delete(T t);

    T update(T t);

}
