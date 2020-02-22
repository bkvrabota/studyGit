package console_view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

public class MenuItem {
	private Object object;
	private String title;
	private String target;
	private boolean isExitItem;

	private static final Logger LOGGER = LogManager.getLogger();

	public MenuItem(String title) { this(title, null, null); }

	 /*Конструктор для создания элемента меню, где title - заголовок меню,
	 object - родительский объект вызываемого метода, target - имя вызываемого метода.*/
	public MenuItem(String title, Object object, String target) {
		this.title = title;
		this.object = object;
		this.target = target;
	}
	
	public String getLabel() { return title; }
	
	// Вызов элемента меню из родительского меню
	void call() {
		if (target == null) return;
		
		try {
			Method method = object.getClass().getMethod(target);
			method.invoke(object);
		}
		catch (Exception ex) {
			LOGGER.error("Ошибка при вызове элемента/Error in calling an item.");
			ex.printStackTrace();
		}
	}
	
	// Сообщает, выбран ли пункт выхода
	boolean isExitItem() { return isExitItem; }

	void setExitItem(boolean isExitItem) { this.isExitItem = isExitItem; }
	
	public String toString() { return getLabel(); }
}