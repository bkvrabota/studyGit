package console_view;

import ebookstore.controller.BooksController;
import ebookstore.controller.ClientsController;
import ebookstore.controller.OrdersController;
import ebookstore.controller.RequestsController;

public class InitMenu {

    private MenuController menuController;

    public InitMenu(BooksController booksController, ClientsController clientsController,
                    OrdersController ordersController, RequestsController requestsController) {
        this.menuController = new MenuController(booksController, clientsController,
                                                    ordersController, requestsController);
    }

    public void mainMenu() {

        Menu menu = new Menu();
        menu.setTitle("- Main menu -");
        menu.addItem(new MenuItem("Storage", this, "storageMenu"));
        menu.addItem(new MenuItem("Shop", this, "shopMenu"));
        menu.addItem(new MenuItem("Save change", this.menuController, "saveState"));
        menu.execute();
    }

    public void storageMenu() {
        Menu menu = new Menu();
        menu.setTitle("- Storage -");
        menu.addItem(new MenuItem("Books", this, "booksMenu"));
        menu.addItem(new MenuItem("Requests", this, "requestsMenu"));
        menu.execute();
    }

    public void shopMenu() {
        Menu menu = new Menu();
        menu.setTitle("- Shop -");
        menu.addItem(new MenuItem("Clients", this, "clientsMenu"));
        menu.addItem(new MenuItem("Orders", this, "ordersMenu"));
        menu.execute();
    }

    public void booksMenu() {
        Menu menu = new Menu();
        menu.setTitle("- Books -");
        menu.addItem(new MenuItem("Add book", this.menuController, "addBook"));
        menu.addItem(new MenuItem("Delete book", this.menuController, "deleteBook"));
        menu.addItem(new MenuItem("List of book", this.menuController, "checkBooks"));
        menu.addItem(new MenuItem("Change book status", this.menuController, "editAvailableBook"));
        menu.addItem(new MenuItem("Get book by ID", this.menuController, "checkBook"));
        menu.addItem(new MenuItem("Get book description", this.menuController, "checkDescription"));
        menu.addItem(new MenuItem("List of old books", this.menuController, "oldBooks"));
        menu.addItem(new MenuItem("Sort books", this.menuController, "sortBooksBy"));
        menu.execute();
    }

    public void requestsMenu() {
        Menu menu = new Menu();
        menu.setTitle("- Requests -");
        menu.addItem(new MenuItem("Add requests on book", this.menuController, "createRequestBook"));
        menu.addItem(new MenuItem("Delete request", this.menuController, "deleteRequestBook"));
        menu.addItem(new MenuItem("List of requests", this.menuController, "checkRequestBooks"));
        menu.addItem(new MenuItem("Change request status", this.menuController, "editStatusReq"));
        menu.addItem(new MenuItem("Find requests on book", this.menuController, "findRequestBook"));
        menu.execute();
    }

    public void clientsMenu() {
        Menu menu = new Menu();
        menu.setTitle("- Clients -");
        menu.addItem(new MenuItem("Add client", this.menuController, "addClient"));
        menu.addItem(new MenuItem("List of clients", this.menuController, "checkClients"));
        menu.execute();
    }

    public void ordersMenu() {
        Menu menu = new Menu();
        menu.setTitle("- Orders -");
        menu.addItem(new MenuItem("Add order", this.menuController, "createOrder"));
        menu.addItem(new MenuItem("Delete order", this.menuController, "deleteOrder"));
        menu.addItem(new MenuItem("List of orders", this.menuController, "checkOrders"));
        menu.addItem(new MenuItem("Change order status", this.menuController, "editStatusOrder"));
        menu.addItem(new MenuItem("Show order detail", this.menuController, "showOrderDetail"));
        menu.addItem(new MenuItem("Show order price", this.menuController, "checkOrderPrice"));
        menu.addItem(new MenuItem("List of completed orders", this.menuController, "completedOrders"));
        menu.addItem(new MenuItem("Show sum money for time", this.menuController, "showEarnedMoney"));
        menu.addItem(new MenuItem("Sort orders", this.menuController, "sortOrdersBy"));
        menu.execute();
    }
}
