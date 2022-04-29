package homework24.controller.mvc;

import homework24.model.Cart;
import homework24.model.Good;
import homework24.model.User;
import homework24.service.CartService;
import homework24.service.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class GoodControllerMvc {

    private static final String REGEX_ONLY_LETTERS = "[^A-Za-z]";
    private static final String REGEX_ONLY_FIGURES = "[A-Za-z]";
    private static final String REGEX_LETTERS_FIGURES_POINT = "[^A-Za-z0-9.]";

    private final GoodService goodService;
    private final CartService cartService;

    private User user;
    private Cart cart;

    public GoodControllerMvc(GoodService goodService,
                          CartService cartService) {
        this.goodService = goodService;
        this.cartService = cartService;
    }

    @PostConstruct
    public void init() {
        user = new User();
        cart = new Cart();
    }

    @GetMapping("/good")
    public String getAllGoods(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");
        session.setAttribute("login", login);

        List<Good> goods = goodService.getAll();
        String options = getStringOfOptionsForDroppingMenuFromGoodList(goods);
        String chosenGoods = (String) session.getAttribute("chosenGoods");
        String choice = getChoice(chosenGoods);

        model.addAttribute("login", login);
        model.addAttribute("options", options);
        model.addAttribute("choice", choice);

        return "good";
    }

    @PostMapping("/createCart")
    public String createCart(HttpServletRequest request) {
        HttpSession session = request.getSession();

        createCart(session);

        BigDecimal totalPrice = cartService.getTotalPrice(cart);

        session.setAttribute("cart", cart);
        session.setAttribute("totalPrice", totalPrice);

        String command = request.getParameter("submit");

        if ("Add Good".equals(command)) {
            String option = getStringOfNameAndPriceFromOptionMenu(request.getParameter("goodName"));

            addGoodToCart(option);

            String chosenGoods = cartService.print(cart);
            session.setAttribute("chosenGoods", chosenGoods);

            return "redirect:/good/";
        }
        if ("Log out".equals(command)) {
            return "redirect:/login/";
        }
        return "redirect:/order/";
    }

    private String getStringOfOptionsForDroppingMenuFromGoodList(List<Good> goods) {
        StringBuilder sb = new StringBuilder();

        for (Good good : goods) {
            sb.append("<option>" + good.getName() + " (" + good.getPrice() + ") </option>\n");
        }
        return sb.toString();
    }

    private String getChoice(String chosenGoods) {
        if (chosenGoods != null) {
            return chosenGoods;
        } else {
            return "Make your order\n";
        }
    }

    private void createCart(HttpSession session) {
        if (session.getAttribute("cart") != null) {
            cart = (Cart) session.getAttribute("cart");
        }
    }

    private String getStringOfNameAndPriceFromOptionMenu(String s) {
        return s.replaceAll(REGEX_LETTERS_FIGURES_POINT, "");
    }

    private void addGoodToCart(String option) {
        String name = option.replaceAll(REGEX_ONLY_LETTERS, "");
        String price = option.replaceAll(REGEX_ONLY_FIGURES, "");

        cart.addGood(new Good(name, BigDecimal.valueOf(Double.valueOf(price))));
    }
}
