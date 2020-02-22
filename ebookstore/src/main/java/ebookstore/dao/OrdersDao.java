package ebookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ebookstore.dao.interfaces.AGenericDAO;
import ebookstore.dao.interfaces.IOrdersDao;
import ebookstore.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OrdersDao extends AGenericDAO<Order> implements IOrdersDao {

    @Autowired
    private EntityManager em;

    public OrdersDao() {
    }

    @Override
    public List<Order> getCompletedOrders() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> CriteriaQuery = cb.createQuery(Order.class);
        Root<Order> root = CriteriaQuery.from(Order.class);

        CriteriaQuery.select(root).where(cb.like(root.get("statusOrder"), "Completed"));
        return em.createQuery(CriteriaQuery).getResultList();
    }

    @Override
    public List<Order> showComplOrders(LocalDate start, LocalDate end) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> CriteriaQuery = cb.createQuery(Order.class);
        Root<Order> root = CriteriaQuery.from(Order.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.between(root.get("dateExecution"), start, end);
        predicates[1] = cb.like(root.get("statusOrder"), "Completed");
        CriteriaQuery.select(root).where(predicates);
        return em.createQuery(CriteriaQuery).getResultList();
    }

    @Override
    public List<Order> sortBy(String typeSort) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> CriteriaQuery = cb.createQuery(Order.class);
        Root<Order> root = CriteriaQuery.from(Order.class);

        switch (typeSort) {
            case "price":
                CriteriaQuery.orderBy(cb.asc(root.get("orderPrice")));
                return em.createQuery(CriteriaQuery).getResultList();
            case "date":
                CriteriaQuery.orderBy(cb.asc(root.get("dateExecution")));
                return em.createQuery(CriteriaQuery).getResultList();
            case "status":
                CriteriaQuery.orderBy(cb.asc(root.get("statusOrder")));
                return em.createQuery(CriteriaQuery).getResultList();
            default:
                break;
        }
        return null;
    }
}
