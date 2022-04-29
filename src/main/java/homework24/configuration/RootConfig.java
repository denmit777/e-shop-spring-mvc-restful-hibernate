package homework24.configuration;

import homework24.dao.GoodDAO;
import homework24.dao.OrderDAO;
import homework24.dao.UserDAO;
import homework24.dao.impl.GoodDAOImpl;
import homework24.dao.impl.OrderDAOImpl;
import homework24.dao.impl.UserDAOImpl;
import homework24.service.CartService;
import homework24.service.GoodService;
import homework24.service.OrderService;
import homework24.service.UserService;
import homework24.service.impl.*;

import homework24.service.impl.security.UserDetailsServiceImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@ComponentScan(value = {"homework24.service", "homework24.dao"})
public class RootConfig {

    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public GoodDAO goodDAO() {
        return new GoodDAOImpl(sessionFactory);
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAOImpl(sessionFactory);
    }

    @Bean
    public OrderDAO orderDAO() {
        return new OrderDAOImpl(sessionFactory);
    }

    @Bean
    public GoodService goodService() {
        return new GoodServiceImpl(goodDAO());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(orderDAO());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userDAO());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userDAO());
    }

    @Bean
    public CartService cartService() {
        return new CartServiceImpl();
    }
}
