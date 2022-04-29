package homework24.dao.impl;

import homework24.dao.OrderDAO;
import homework24.model.Order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private static final String QUERY_SELECT_FROM_ORDER = "from Order";
    private static final String QUERY_SELECT_FROM_ORDER_BY_USER_ID = "from Order o where o.userId = :userId";

    private final SessionFactory sessionFactory;

    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Order order) {
        getCurrentSession().save(order);
    }

    @Override
    public Order getById(Long id) {
        final Order order = getCurrentSession().get(Order.class, id);

        return order;
    }

    @Override
    public Order getByUserId(Long userId) {
        final Order order = (Order) getCurrentSession().createQuery(QUERY_SELECT_FROM_ORDER_BY_USER_ID)
                .setParameter("userId", userId).uniqueResult();

        return order;
    }

    @Override
    public List<Order> getAll() {
        final List<Order> orders = getCurrentSession().createQuery(QUERY_SELECT_FROM_ORDER).list();

        return orders;
    }

    @Override
    public void deleteById(Long id) {
        final Order order = getById(id);

        getCurrentSession().delete(order);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
