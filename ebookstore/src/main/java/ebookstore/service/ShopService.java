package ebookstore.service;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ebookstore.dao.interfaces.IClientsDao;
import ebookstore.dao.interfaces.IOrdersDao;
import ebookstore.model.Client;
import ebookstore.model.Order;
import ebookstore.service.interfaces.IShopService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShopService implements IShopService {

    @Autowired
    private IOrdersDao ordersDao;
    @Autowired
    private IClientsDao clientsDao;

    @Autowired
    private Logger LOGGER;

    public ShopService() {
    }

    // Получить список клиентов:
    @Override
    public List<Client> checkClients() {
        return clientsDao.getAll(Client.class);
    }

    // Добавление нового клиента в базу:
    @Override
    public void addClient(Client client) {
        clientsDao.add(client);
    }

    // Создание нового заказа:
    @Override
    public void createOrder(Order order) {
        ordersDao.add(order);
    }

    // Изменение статуса заказа:
    @Override
    public void editStatusOrder(int idOrder, String newStatus) {
        try {
            Order order = ordersDao.findById(Order.class, idOrder);
            order.setStatusOrder(newStatus);
            ordersDao.update(order);
        } catch (Exception ex) {
            LOGGER.info("Заказ с таким ID отсутствует/There is no order with such ID.\n" + ex);
        }
    }

    // Удаление заказа:
    @Override
    public void deleteOrder(int idOrder) {
        ordersDao.deleteById(Order.class, idOrder);
    }

    // Просмотр всех заказов в магазине:
    @Override
    public List<Order> checkOrders() {
        return ordersDao.getAll(Order.class);
    }

    // Получить конкретный заказ:
    @Override
    public Order showOrderDetail(int idOrder) {
        return ordersDao.findById(Order.class, idOrder);
    }

    // Сумма заказа:
    @Override
    public double checkOrderPrice(int idOrder) {
        return ordersDao.findById(Order.class, idOrder).getOrderPrice();
    }

    // Список выполненных заказов:
    @Override
    public List<Order> completedOrders() {
        try {
            return ordersDao.getCompletedOrders();
        } catch (Exception ex) {
            LOGGER.error("Выполненные заказы отсутствуют/There are no orders completed.\n" + ex);
            return null;
        }
    }

    // Сумма полученных средств (за выполненые заказы) за установленный период времени:
    @Override
    public double showEarnedMoney(LocalDate start, LocalDate end) {
        double earnedMoney = 0;
        for (Order order : ordersDao.showComplOrders(start, end)) {
            earnedMoney += order.getOrderPrice();
        }
        return earnedMoney;
    }

    // Сортировки:
    @Override
    public List<Order> sortBy(String typeSort) {
        try {
            return ordersDao.sortBy(typeSort);
        } catch (Exception ex) {
            LOGGER.error("ОШИБКА! Введите один из предложенных вариантов сортировки/Error! Enter one of the proposed sorting options.\n" + ex);
            return null;
        }
    }
}
