package homework24.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Good> goods;

    public Cart() {
        this.goods = new ArrayList();

    }

    public List<Good> getGoods() {
        return goods;
    }

    public void addGood(Good good) {
        goods.add(good);
    }

    public void deleteGoods() {
        goods.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "goods=" + goods +
                '}';
    }
}

