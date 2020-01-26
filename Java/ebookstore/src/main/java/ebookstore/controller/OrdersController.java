package ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ebookstore.model.Order;
import ebookstore.service.interfaces.IShopService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private IShopService shopService;

    @GetMapping
    public List<Order> checkOrders() {
        return shopService.checkOrders();
    }

    @GetMapping("/{id}")
    public Order showOrderDetail(@PathVariable("id") int idOrder) {
        return shopService.showOrderDetail(idOrder);
    }

    @GetMapping("/orderPrice")
    public double checkOrderPrice(@RequestParam("idOrder") int idOrder) {
        return shopService.checkOrderPrice(idOrder);
    }

    @GetMapping("/completed")
    public List<Order> completedOrders() {
        return shopService.completedOrders();
    }

    @GetMapping("/money")
    public double showEarnedMoney(@RequestBody LocalDate start, LocalDate end) {
        return shopService.showEarnedMoney(start, end);
    }

    @GetMapping("/sortedOrders")
    public List<Order> sortOrdersBy(@RequestParam("typeSort") String typeSort) {
        return shopService.sortBy(typeSort);
    }

    @PostMapping
    public void createOrder(@RequestBody Order order) {
        shopService.createOrder(order);
    }

    @PutMapping("/{id}")
    public void editStatusOrder(@PathVariable("id") int idOrder, @RequestBody String newStatus) {
        shopService.editStatusOrder(idOrder, newStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") int idOrder) {
        shopService.deleteOrder(idOrder);
    }
}
