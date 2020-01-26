package ebookstore.dao;

import org.springframework.stereotype.Repository;
import ebookstore.dao.interfaces.AGenericDAO;
import ebookstore.dao.interfaces.IRequestsDao;
import ebookstore.model.Request;

@Repository
public class RequestsDao extends AGenericDAO<Request> implements IRequestsDao {

    public RequestsDao() {
    }
}
