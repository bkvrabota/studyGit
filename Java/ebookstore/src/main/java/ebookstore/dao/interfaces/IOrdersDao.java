package ebookstore.dao.interfaces;

import ebookstore.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrdersDao extends IGenericDAO<Order> {

    List<Order> getCompletedOrders();

    List<Order> showComplOrders(LocalDate start, LocalDate end);
}
