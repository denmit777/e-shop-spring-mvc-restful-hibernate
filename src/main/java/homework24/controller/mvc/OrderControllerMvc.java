package homework24.controller.mvc;

import homework24.model.Cart;
import homework24.model.Order;
import homework24.model.User;
import homework24.service.CartService;
import homework24.service.OrderService;
import homework24.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class OrderControllerMvc {

    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;

    private User user;
    private Cart cart;
    private Order order;

    public OrderControllerMvc(UserService userService,
                              OrderService orderService,
                              CartService cartService) {
        this.userService = userService;
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @PostConstruct
    public void init() {
        user = new User();
        cart = new Cart();
        order = new Order();
    }

    @GetMapping("/order")
    public String getOrder(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");

        cart = (Cart) session.getAttribute("cart");
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        String orderPrint = cartService.print(cart).replace("You have already chosen:\n", "");

        Order newOrder = getNewOrder(session, totalPrice);
        orderService.save(newOrder);

        model.addAttribute("login", login);
        model.addAttribute("order", orderPrint);
        model.addAttribute("totalPrice", newOrder.getTotalPrice());

        return "order";
    }

    @PostMapping("/order")
    public String logout() {
        return "redirect:/login/";
    }

    private Order getNewOrder(HttpSession session, BigDecimal totalPrice) {
        Long id = (Long) session.getAttribute("id");
        user.setId(id);

        List<Order> orderList = orderService.getAll();

        Long orderId = (long) orderList.size() + 1;

        order.setId(orderId);
        order.setUserId(user.getId());
        order.setTotalPrice(totalPrice);

        return order;
    }
}
