package quiz.service;

import java.util.List;

public interface CrudService<T> {

    T create(T t);

    void update(T t, Long id);

    T get(Long id);

    void delete(Long id);

    List<T> getAll();
}
