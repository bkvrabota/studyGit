package ebookstore.dao;

import org.springframework.stereotype.Repository;
import ebookstore.dao.interfaces.AGenericDAO;
import ebookstore.dao.interfaces.IClientsDao;
import ebookstore.model.Client;

@Repository
public class ClientsDao extends AGenericDAO<Client> implements IClientsDao {

    public ClientsDao() {
    }
}
