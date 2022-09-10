package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.domain.Order;

import javax.validation.Valid;
import org.springframework.validation.Errors;
import sia.tacocloud.domain.User;
import sia.tacocloud.repositories.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderProps props;


    private OrderRepository orderRepo;

//    private OrderProps = props;
    @Autowired
    public OrderController(OrderRepository orderRepo, OrderProps props) {
        this.orderRepo = orderRepo;
        this.props = props;
//        this.props = props;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute Order order){
//        model.addAttribute("order",new Order());
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }
        return "orderForm";
    }

    @GetMapping("/orderedByOrders")
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {

        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders",
                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();
//        log.info("Order submitted: " + order);
        return "redirect:/";
    }

}
