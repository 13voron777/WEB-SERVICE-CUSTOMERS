package helpers;

import entity.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRoleHelper {
    private SessionFactory sessionFactory;

    public UserRoleHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addUserRole(UserRole userRole){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(userRole);
        session.getTransaction().commit();
        session.close();
    }

    public UserRole getUserRole(String userRole){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserRole> criteriaQuery = cb.createQuery(UserRole.class);
        Root<UserRole> root = criteriaQuery.from(UserRole.class);

        criteriaQuery.select(root)
                .where(cb.equal(root.get("role_name"), userRole));
        Query query = session.createQuery(criteriaQuery);

        List<UserRole> userRoles = query.getResultList();
        session.close();
        return userRoles.size() == 0 ? null : userRoles.get(0);
    }
}
