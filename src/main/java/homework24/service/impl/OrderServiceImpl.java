package homework24.service.impl;

import homework24.dao.OrderDAO;
import homework24.model.Order;
import homework24.service.OrderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Order save(Order order) {
        orderDAO.save(order);
        return order;
    }

    @Override
    public Order getById(Long id) {
        return orderDAO.getById(id);
    }

    @Override
    public Order getByUserId(Long userId) {
        return orderDAO.getByUserId(userId);
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public Order update(Long id, Order order) {
        order.setId(id);

        orderDAO.save(order);

        return order;
    }

    @Override
    public void deleteById(Long id) {
        orderDAO.deleteById(id);
    }
}
