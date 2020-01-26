package ebookstore.service.interfaces;

import ebookstore.model.Client;
import ebookstore.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface IShopService {

    // Получить список клиентов:
    List<Client> checkClients();

    // Добавление нового клиента в базу:
    void addClient(Client client);

    // Создание нового заказа:
    void createOrder(Order order);

    // Изменение статуса заказа:
    void editStatusOrder(int idOrder, String newStatus);

    // Удаление заказа:
    void deleteOrder(int idOrder);

    // Просмотр всех заказов в магазине:
    List<Order> checkOrders();

    // Получить конкретный заказ:
    Order showOrderDetail(int idOrder);

    // Сумма заказа:
    double checkOrderPrice(int idOrder);

    // Список выполненных заказов:
    List<Order> completedOrders();

    // Сумма полученных средств (за выполненые заказы) за установленный период времени:
    double showEarnedMoney(LocalDate start, LocalDate end);

    // Сортировки:
    List<Order> sortBy(String typeSort);
}
