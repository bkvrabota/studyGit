package ebookstore.dao.interfaces;

import java.util.List;

public interface IGenericDAO<Entity> {

    void add(Entity entity);

    void update(Entity entity);

    void deleteById(Class clazz, int id);

    List<Entity> getAll(Class clazz);

    Entity findById(Class clazz, int id);

    List<Entity> sortBy(String typeSort);
}
