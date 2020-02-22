package ebookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ebookstore.dao.interfaces.AGenericDAO;
import ebookstore.dao.interfaces.IBooksDao;
import ebookstore.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BooksDao extends AGenericDAO<Book> implements IBooksDao {

    @Autowired
    private EntityManager em;

    public BooksDao() {
    }

    @Override
    public List<Book> getOldBooks(LocalDate start, LocalDate end) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> CriteriaQuery = cb.createQuery(Book.class);
        Root<Book> root = CriteriaQuery.from(Book.class);

        CriteriaQuery.select(root).where(cb.between(root.get("datePublic"), start, end));
        return em.createQuery(CriteriaQuery).getResultList();
    }

    @Override
    public List<Book> sortBy(String typeSort) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> CriteriaQuery = cb.createQuery(Book.class);
        Root<Book> root = CriteriaQuery.from(Book.class);

        switch (typeSort) {
            case "author":
                CriteriaQuery.orderBy(cb.asc(root.get("author")));
                return em.createQuery(CriteriaQuery).getResultList();
            case "date":
                CriteriaQuery.orderBy(cb.asc(root.get("datePublic")));
                return em.createQuery(CriteriaQuery).getResultList();
            case "status":
                CriteriaQuery.orderBy(cb.asc(root.get("available")));
                return em.createQuery(CriteriaQuery).getResultList();
            default:
                break;
        }
        return null;
    }
}
