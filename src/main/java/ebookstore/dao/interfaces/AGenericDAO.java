package ebookstore.dao.interfaces;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AGenericDAO<Entity> implements IGenericDAO<Entity> {

    @Autowired
    private EntityManager em;

    @Autowired
    private Logger LOGGER;

    @Override
    public void add(Entity entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            LOGGER.error("EXCEPTION -- > " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Entity entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            LOGGER.error("EXCEPTION -- > " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Class clazz, int id) {
        try {
            em.getTransaction().begin();
            Object object = em.find(clazz, id);
            em.remove(object);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            LOGGER.error("EXCEPTION -- > " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Entity> getAll(Class clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Entity> CriteriaQuery = cb.createQuery(clazz);
        Root<Entity> Root = CriteriaQuery.from(clazz);
        CriteriaQuery.select(Root);
        return em.createQuery(CriteriaQuery).getResultList();
    }

    @Override
    public Entity findById(Class clazz, int id) {
        Entity entity = (Entity) em.find(clazz, id);
        return entity;
    }

    @Override
    public List<Entity> sortBy(String typeSort) {
        return null;
    }
}
