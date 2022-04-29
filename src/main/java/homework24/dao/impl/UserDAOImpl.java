package homework24.dao.impl;

import homework24.dao.UserDAO;
import homework24.model.User;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final String QUERY_SELECT_FROM_USER = "from User";
    private static final String QUERY_SELECT_FROM_USER_BY_LOGIN = "from User u where u.login = :login";

    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        getCurrentSession().save(user);
    }

    @Override
    public User getById(Long id) {
        final User user = getCurrentSession().get(User.class, id);

        return user;
    }

    @Override
    public User getByLogin(String login) {
        final User user = (User) getCurrentSession().createQuery(QUERY_SELECT_FROM_USER_BY_LOGIN)
                .setParameter("login", login).uniqueResult();

        return user;
    }

    @Override
    public List<User> getAll() {
        final List<User> users = getCurrentSession().createQuery(QUERY_SELECT_FROM_USER).list();

        return users;
    }

    @Override
    public void deleteById(Long id) {
        final User user = getById(id);

        getCurrentSession().delete(user);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
