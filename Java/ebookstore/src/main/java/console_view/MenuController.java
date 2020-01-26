package console_view;

import ebookstore.controller.BooksController;
import ebookstore.controller.ClientsController;
import ebookstore.controller.OrdersController;
import ebookstore.controller.RequestsController;
import ebookstore.model.Book;
import ebookstore.model.Client;
import ebookstore.model.Order;
import ebookstore.model.Request;
import console_view.utils.*;

import java.io.IOException;
import java.time.LocalDate;

public class MenuController {

    private BooksController booksController;
    private ClientsController clientsController;
    private OrdersController ordersController;
    private RequestsController requestsController;
    private BookUtils bookUtils = new BookUtils();
    private RequestUtils requestUtils = new RequestUtils();
    private ClientUtils clientUtils = new ClientUtils();
    private OrderUtils orderUtils = new OrderUtils();

    public MenuController(BooksController booksController, ClientsController clientsController,
                          OrdersController ordersController, RequestsController requestsController) {
        this.booksController = booksController;
        this.clientsController = clientsController;
        this.ordersController = ordersController;
        this.requestsController = requestsController;
    }

    //Книги:
    public void addBook() throws IOException {
        booksController.addBook(new Book( 0,
                bookUtils.inputGenre(), bookUtils.inputPublisher(), bookUtils.inputAuthor(),
                bookUtils.inputTitle(), bookUtils.inputDescription(), bookUtils.inputPrice(), bookUtils.inputDate(), 1));
    }

    public void deleteBook() throws IOException {
        booksController.deleteBook(bookUtils.inputId(booksController.checkBooks()));
    }

    public void checkBooks() {
        booksController.checkBooks().forEach(book -> System.out.println(book.toString()));
        MenuUtils.pressEnterKey();
    }

    public void editAvailableBook() throws IOException {
        booksController.editAvailableBook(bookUtils.inputId(booksController.checkBooks()), bookUtils.inputAvailable());
    }

    public void checkBook() throws IOException {
        Book book = booksController.checkBook(bookUtils.inputId(booksController.checkBooks()));
        System.out.println(book.toString());
        MenuUtils.pressEnterKey();
    }

    public void checkDescription() throws IOException {
        System.out.println(booksController.checkDescription(bookUtils.inputId(booksController.checkBooks())));
        MenuUtils.pressEnterKey();
    }

    public void oldBooks() {
        booksController.oldBooks().forEach(book -> System.out.println(book.toString()));
        MenuUtils.pressEnterKey();
    }

    public void sortBooksBy() throws IOException {
        booksController.sortBooksBy(bookUtils.inputSort()).forEach(book -> System.out.println(book.toString()));
        MenuUtils.pressEnterKey();
    }

    // Запросы:
    public void createRequestBook() throws IOException {
        requestsController.createRequestBook(new Request(0,
                clientsController.checkClients().get(clientUtils.inputId(clientsController.checkClients()) - 1),
                booksController.checkBook(bookUtils.inputId(booksController.checkBooks()) - 1),
                requestUtils.inputDate(), requestUtils.inputStatus()));
    }

    public void deleteRequestBook() throws IOException {
        requestsController.deleteRequestBook(requestUtils.inputIdRequest(requestsController.checkRequestBooks()));
    }

    public void checkRequestBooks() {
        requestsController.checkRequestBooks().forEach(request -> System.out.println(request.toString()));
        MenuUtils.pressEnterKey();
    }

    public void editStatusReq() throws IOException {
        requestsController.editStatusReq(requestUtils.inputIdRequest(requestsController.checkRequestBooks()), requestUtils.inputStatus());
    }

    public void findRequestBook() throws IOException {
        Integer idReq = requestsController.findRequestBook(booksController.checkBook(bookUtils.inputId(booksController.checkBooks())));
        if (idReq != null) {
            System.out.println("Запрашиваемая книга присутствует в запросе/The requested book is present in request №" + idReq);
        } else System.out.println("Запрашиваемая книга отсутствует в запросах/The requested book is not in the requests.");
        MenuUtils.pressEnterKey();
    }

    // Клиенты
    public void checkClients() {
        clientsController.checkClients().forEach(client -> System.out.println(client.toString()));
        MenuUtils.pressEnterKey();
    }

    public void addClient() throws IOException {
        clientsController.addClient(new Client(0, clientUtils.inputCard(),
                clientUtils.inputPhone(), clientUtils.inputFName(), clientUtils.inputLName()));
    }

    // Заказы
    public void createOrder() throws IOException {
        ordersController.createOrder(new Order(0,
                clientsController.checkClients().get(clientUtils.inputId(clientsController.checkClients()) - 1), orderUtils.inputStatus(),
                bookUtils.addBuyBooks(booksController.checkBooks()), orderUtils.inputDate()));
    }

    public void editStatusOrder() throws IOException {
        ordersController.editStatusOrder(orderUtils.inputIdOrder(ordersController.checkOrders()), orderUtils.inputStatus());
    }

    public void deleteOrder() throws IOException {
        ordersController.deleteOrder(orderUtils.inputIdOrder(ordersController.checkOrders()));
    }

    public void checkOrders() {
        ordersController.checkOrders().forEach(order -> System.out.println(order.toString()));
        MenuUtils.pressEnterKey();
    }

    public void showOrderDetail() throws IOException {
        Order order = ordersController.showOrderDetail(orderUtils.inputIdOrder(ordersController.checkOrders()));
        System.out.println(order.toString());
        MenuUtils.pressEnterKey();
    }

    public void checkOrderPrice() throws IOException {
        System.out.println(ordersController.checkOrderPrice(orderUtils.inputIdOrder(ordersController.checkOrders())));
        MenuUtils.pressEnterKey();
    }

    public void completedOrders() {
        ordersController.completedOrders().forEach(order -> System.out.println(order.toString()));
        MenuUtils.pressEnterKey();
    }

    public void showEarnedMoney() throws IOException {
        System.out.println("Начало требуемого периода/The beginning of the period: ");
        LocalDate start = orderUtils.inputDate();
        System.out.println("Конец требуемого периода/The end of the required period: ");
        LocalDate end = orderUtils.inputDate();
        System.out.println(start + " - " + end + ": " + ordersController.showEarnedMoney(start, end));
        MenuUtils.pressEnterKey();
    }

    public void sortOrdersBy() throws IOException {
        ordersController.sortOrdersBy(orderUtils.inputSort()).forEach(order -> System.out.println(order.toString()));
        MenuUtils.pressEnterKey();
    }
}
