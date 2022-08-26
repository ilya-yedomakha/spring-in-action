package sia.tacocloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.repositories.OrderRepository;

@Controller
@RequestMapping("/orderslist")
public class ShowOrdersTest {
    private OrderRepository orderRepo;
    @Autowired
    public ShowOrdersTest(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
    @GetMapping
    public String showOrderList(Model model){

        model.addAttribute("orderList",orderRepo.findByDeliveryZip("123"));
        return "ordersQuerryList";
    }

}
