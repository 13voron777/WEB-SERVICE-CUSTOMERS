package helpers;

import entity.Service;
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
        session.close();
        return users;
    }

    public User getUserById(int id){
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public void updateUser(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUserById(int idUser) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, idUser);
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    public User checkUserPassword(String user, String password){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root)
                .where(cb.and(cb.equal(root.get("user_name"), user),
                        cb.equal(root.get("password"), password)));

        Query query = session.createQuery(criteriaQuery);
        List<User> users = query.getResultList();
        session.close();
        return users.size() == 0 ? null : users.get(0);
    }

    public void subscribeService(int idUser, int idService){
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, idUser);
        Service service = session.get(Service.class, idService);
        user.getServices().add(service);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public void unsubscribeService(int idUser, int idService){
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, idUser);
        Service service = session.get(Service.class, idService);
        user.getServices().remove(service);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
