package console_view;

import ebookstore.controller.BooksController;
import ebookstore.controller.ClientsController;
import ebookstore.controller.OrdersController;
import ebookstore.controller.RequestsController;

public class MenuTest {

	public static void main(String[] args) {

		/*ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		BooksController booksController = ctx.getBean(BooksController.class);
		ClientsController clientsController = ctx.getBean(ClientsController.class);
		OrdersController ordersController = ctx.getBean(OrdersController.class);
		RequestsController requestsController = ctx.getBean(RequestsController.class);*/

		BooksController booksController = new BooksController();
		ClientsController clientsController = new ClientsController();
		OrdersController ordersController = new OrdersController();
		RequestsController requestsController = new RequestsController();

		// Создаем консольное меню:
		InitMenu initMenu = new InitMenu(booksController, clientsController, ordersController, requestsController);
		initMenu.mainMenu();
	}
}
