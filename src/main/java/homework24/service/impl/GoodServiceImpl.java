package homework24.service.impl;

import homework24.dao.GoodDAO;
import homework24.model.Good;
import homework24.service.GoodService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
public class GoodServiceImpl implements GoodService {

    private GoodDAO goodDAO;

    public GoodServiceImpl(GoodDAO goodDAO) {
        this.goodDAO = goodDAO;
    }


    @Override
    public Good save(Good good) {
        goodDAO.save(good);

        return good;
    }

    @Override
    public Good getById(Long id) {
        return goodDAO.getById(id);
    }

    @Override
    public List<Good> getAll() {
        return goodDAO.getAll();
    }

    @Override
    public Good update(Long id, Good good) {
        good.setId(id);

        goodDAO.save(good);

        return good;
    }

    @Override
    public void deleteById(Long id) {
        goodDAO.deleteById(id);
    }
}
