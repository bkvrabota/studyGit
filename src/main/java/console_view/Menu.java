package console_view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import console_view.utils.MenuUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
	private static BufferedReader input;
	private static Menu startMenu;
	private List<MenuItem> itemList;
	private MenuItem exitItem;
	private String title;
	private boolean isStartMenu;

	private static final Logger LOGGER = LogManager.getLogger();

	// Конструктор меню
	public Menu() {
		this.itemList = new ArrayList<>();
		
		if (Menu.startMenu == null) {
			Menu.input = new BufferedReader(new InputStreamReader(System.in));
			Menu.startMenu = this;
			this.isStartMenu = true;
			this.setTitle("Menu");
			this.exitItem = new MenuItem("Exit");
		}	else {
				this.setTitle("Submenu");
				this.exitItem = new MenuItem("Back");
			}
		this.exitItem.setExitItem(true);
	}



	// Добавить элемент к меню
	public void addItem(MenuItem item) { this.itemList.add(item); }

	// Возврат или выход
	public void execute() {
		MenuItem item = null;
		do {
			this.print();
			item = this.getUserInput(); 
			item.call();
		}
		while(!item.isExitItem());
	}

	private int getExitIndex() { return this.itemList.size() + 1; }

	private MenuItem getUserInput() {
		MenuItem item = null;
		String input = null;
		
		try { 
			input = Menu.input.readLine();
			int option = Integer.parseInt(input);
			
			if (option < 1 || option > this.getExitIndex())
				throw new NumberFormatException();
			
			if (option == this.getExitIndex()) {
				item = exitItem;

				if (this.isStartMenu)
					Menu.input.close();
			}
			else item = itemList.get(option - 1);
		}
		catch (NumberFormatException ex) {
			LOGGER.error("Ошибка: '" + input + "' не является пунктом меню!");

			item = new MenuItem(null);
			MenuUtils.pressEnterKey();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			return item;
		}
	}

	// Вывод меню на экран
	private void print() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		if (!this.title.equals("")) {
			sb.append(this.title + "\n");
		}
		for (int i = 0; i < this.itemList.size(); i++) {
			sb.append("\n" + (i + 1) + ". " + this.itemList.get(i).getLabel());
		}
		sb.append("\n" + getExitIndex() + ". " + exitItem.getLabel());
		sb.append("\n> ");
		System.out.print(sb.toString());
	}

	public void setTitle(String title) { this.title = title; }
	
	public String toString() {
		return "menu=[" + this.title + "]  items=" + this.itemList.toString();
	}
}