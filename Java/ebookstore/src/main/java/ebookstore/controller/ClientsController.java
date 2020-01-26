package ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ebookstore.model.Client;
import ebookstore.service.interfaces.IShopService;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientsController {

    @Autowired
    private IShopService shopService;

    @GetMapping
    public List<Client> checkClients() {
        return shopService.checkClients();
    }

    @PostMapping
    public void addClient(@RequestBody Client client) {
        shopService.addClient(client);
    }
}
