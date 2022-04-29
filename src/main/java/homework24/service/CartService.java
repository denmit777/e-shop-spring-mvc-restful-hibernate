package homework24.service;

import homework24.model.Cart;

import java.math.BigDecimal;

public interface CartService {

    String print(Cart cart);

    BigDecimal getTotalPrice(Cart cart);
}
