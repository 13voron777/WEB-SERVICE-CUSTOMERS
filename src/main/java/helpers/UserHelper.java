package helpers;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserHelper {
    private SessionFactory sessionFactory;

    public UserHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addUser(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<User> getAllUsers(){
        Session session = sessionFactory.openSession();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        List<User> users = session.createQuery(criteriaQuery).getResultList();
        return users;
    }

    public User checkUserPassword(String user, String password){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root)
                .where(cb.and(cb.equal(root.get("user_name"), user.toUpperCase()),
                        cb.equal(root.get("password"), password.toUpperCase())));
        Query query = session.createQuery(criteriaQuery);
        List<User> users = query.getResultList();
        session.close();
        return users.size() == 0 ? null : users.get(0);
    }
}
