package homework24.controller.mvc;

import homework24.model.Cart;
import homework24.model.User;
import homework24.model.security.CustomUserDetails;
import homework24.service.CartService;
import homework24.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserControllerMvc {

    private final UserService userService;
    private final CartService cartService;

    private User user;
    private Cart cart;
    private PasswordEncoder encoder;

    public UserControllerMvc(UserService userService,
                             CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostConstruct
    public void init() {
        user = new User();
        cart = new Cart();
        encoder = new BCryptPasswordEncoder(4);
    }

    @GetMapping("/register")
    public String getUserByParameters(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");

        model.addAttribute("login", login);
        model.addAttribute("password", password);

        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, HttpServletRequest request, HttpSession session, CustomUserDetails details) {
        User newUser = addNewUser(request);

        details = new CustomUserDetails(newUser);
        String login = details.getUser().getLogin();
        String password = details.getUser().getPassword();

        model.addAttribute("login", login);
        model.addAttribute("password", password);

        session.setAttribute("login", login);
        session.setAttribute("password", password);

        updateData(session);

        if (request.getParameter("checkbox") != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/success")
    public ModelAndView success(HttpSession session, Authentication authentication) {
        String login = authentication.getName();

        user.setLogin(login);

        session.setAttribute("login", user.getLogin());

        final ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("login", login);

        return modelAndView;
    }

    @GetMapping("/login")
    public String start() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        updateData(session);

        return "redirect:/success/";
    }

    @GetMapping("/loginError")
    public String loginError() {
        return "loginError";
    }

    private User addNewUser(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        user.setLogin(login);

        List<User> usersList = userService.getAll();
        Long id = (long) usersList.size() + 1;

        user.setId(id);
        user.setPassword(encoder.encode(password));

        return user;
    }

    private void updateData(HttpSession session) {
        cart.deleteGoods();

        session.setAttribute("cart", cart);

        String chosenGoods = "Make your order\n";
        session.setAttribute("chosenGoods", chosenGoods);
    }
}
